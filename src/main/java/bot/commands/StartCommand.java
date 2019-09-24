package bot.commands;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import persistence.Factory;

import java.sql.SQLException;
import java.util.Date;

public class StartCommand extends BotCommand {

    public StartCommand() {
        super("start", "Почати спілкування із ботом");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        StringBuilder messageText = new StringBuilder();
        String name = user.getFirstName();
        if (user.getLastName() != null)
            name += " " + user.getLastName();

        entity.User dbUser = null;

        dbUser = Factory.getInstance().getUserDAO().getUserByTelegramId(user.getId());
        if (dbUser != null) {
            messageText.append("Привіт, ").append(name).append(".\n");
            messageText.append("Здається, ми вже знайомі.");
            if (dbUser.getIsRemembered() == 0) {
                dbUser.setIsRemembered((byte) 1);
                Factory.getInstance().getUserDAO().updateUser(dbUser);
            }
        } else {
            Factory.getInstance().getUserDAO().addUser(
                    new entity.User(user.getId(), name, user.getUserName(), (byte) 1, new Date(), chat.getId()));
            messageText.append("Привіт, ").append(name).append("\n");
            messageText.append("Цей бот допоможе тобі перевірити наявність авто у базі даних викрадених.");
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat.getId());
        sendMessage.setText(messageText.toString());

        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
