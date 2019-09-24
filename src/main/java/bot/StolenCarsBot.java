package bot;

import bot.commands.HelpCommand;
import bot.commands.StartCommand;
import bot.commands.StopCommand;
import com.vdurmont.emoji.EmojiParser;
import entity.Car;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import persistence.Factory;
import services.Emoji;

import java.sql.SQLException;
import java.util.List;

import static services.BotConfig.BOT_TOKEN;

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
            StringBuilder answerBuilder = new StringBuilder();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());

            List<Car> cars = null;
            try {
                cars = Factory.getInstance().getCarDAO().getCarByVehicleNumber(message.getText());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (!cars.isEmpty()) {
                if (cars.size() > 1)
                    answerBuilder.append("Знайдено ").append(cars.size())
                            .append(" ТЗ із номером ").append(message.getText()).append("\n\n");
                for (Car car : cars) {
                    answerBuilder.append(Emoji.replaceCarTypeWithEmoji(car.getBRAND())).append("\n");
                    answerBuilder.append(EmojiParser.parseToUnicode(":hash:")).append(" ");
                    answerBuilder.append(car.getVEHICLENUMBER()).append("\n");
                    answerBuilder.append(EmojiParser.parseToUnicode(":calendar:")).append(" Викрадено: ");
                    answerBuilder.append(car.getTHEFT_DATA()).append("\n\n");
                }
            } else
                answerBuilder.append("У базі викрадених не знайдено авто з номером ")
                        .append(message.getText());

            sendMessage.enableHtml(true);
            sendMessage.setText(answerBuilder.toString());

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
