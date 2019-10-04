package bot;

import bot.commands.HelpCommand;
import bot.commands.StartCommand;
import bot.commands.StopCommand;
import com.vdurmont.emoji.EmojiParser;
import entity.Car;
import entity.CarInfo;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import persistence.Factory;
import services.BotConfig;
import services.Emoji;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class StolenCarsBot extends TelegramLongPollingCommandBot {

    public StolenCarsBot(String botUsername) {
        super(botUsername);
        register(new StartCommand());
        register(new StopCommand());

        HelpCommand helpCommand = new HelpCommand(this);
        register(helpCommand);

        registerDefaultAction(((absSender, message) -> {
            SendMessage unknownCommand = new SendMessage();
            unknownCommand.setChatId(message.getChatId());
            unknownCommand.setText("Невідома команда. Глянь у /help");
            try {
                absSender.execute(unknownCommand);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            helpCommand.execute(absSender, message.getFrom(), message.getChat(), new String[] {});
        }));
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        if (!update.hasMessage()) {
            throw new IllegalStateException("Update doesn't have a body.");
        }

        entity.User dbUser = Factory.getInstance().getUserDAO().getUserByTelegramId(update.getMessage().getFrom().getId());
        if (dbUser.getIsRemembered() == (byte) 0) {
            dbUser.setIsRemembered((byte) 1);
            Factory.getInstance().getUserDAO().updateUser(dbUser);
        }

        Message message = update.getMessage();
        if (message.hasText()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.enableHtml(true);
            String answerText = setAnswerText(message);
            sendMessage.setText(answerText);
            System.out.println(answerText);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private String setAnswerText(Message message) {
        String messageText = message.getText().toUpperCase();
        StringBuilder answerBuilder = new StringBuilder();
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");

        Car car = null;
        CarInfo carInfo = null;
        Date theftDate = null;
        String brand = null;
        boolean isWanted = true;
        boolean isFullInfo = true;

        answerBuilder.append("Інформація про транспортний засіб із номером <b>")
                .append(messageText).append("</b>:\n\n");

        try {
            car = (Car) Factory.getInstance().getCarDAO().getCarByVehicleNumber(messageText).get(0);
            brand = Emoji.replaceCarTypeWithEmoji(car.getBRAND());
            theftDate = car.getTHEFT_DATA();
        } catch (Exception e) {
            isWanted = false;
        }

        try {
            carInfo = (CarInfo) Factory.getInstance().getCarInfoDAO().getCarByVehicleNumber(messageText).get(0);
            if (brand == null) brand = carInfo.getBrand();
        } catch (Exception e) {
            isFullInfo = false;
        }

        if (!isFullInfo && !isWanted)
            answerBuilder.append("НЕ ЗНАЙДЕНО");
        else {
            answerBuilder.append("<b>").append(brand).append("</b>\n");

            if (isWanted) {
                answerBuilder.append(EmojiParser.parseToUnicode(":calendar:"))
                        .append(" <b>Викрадено: ")
                        .append(dateFormat.format(theftDate))
                        .append("</b>\n");
                String vin = car.getBODYNUMBER();
                if (!vin.equals(""))
                    answerBuilder.append("\nVIN: ").append(vin);
            }
            else {
                answerBuilder.append("ТЗ немає у базі викрадених\n\n");
            }

            if (isFullInfo) {
                answerBuilder.append("\nКузов: ").append(carInfo.getKind().toLowerCase())
                        .append(" ").append(carInfo.getBody().toLowerCase());
                answerBuilder.append("\nРік виготовлення: ").append(carInfo.getMakeYear());
                answerBuilder.append("\nКолір: ").append(carInfo.getColor().toLowerCase());
                answerBuilder.append("\nПаливо: ").append(carInfo.getFuel().toLowerCase());
                answerBuilder.append("\nОб\'єм двигуна: ").append(carInfo.getCapacity());
                answerBuilder.append("\nВага: ").append(carInfo.getTotalWeight());
                answerBuilder.append("\nРеєстрація: ").append(dateFormat.format(carInfo.getRegdate()));
                answerBuilder.append("\nТип реєстрації: ").append(carInfo.getOperationName().toLowerCase());
            }
        }

        return answerBuilder.toString();
    }

    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }
}
