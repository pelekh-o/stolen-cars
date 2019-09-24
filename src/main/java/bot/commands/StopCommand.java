package bot.commands;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import persistence.Factory;

public class StopCommand extends BotCommand {
    public StopCommand() {
        super("stop", "Зупинити спілкування із ботом");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        entity.User dbUser = Factory.getInstance().getUserDAO().getUserByTelegramId(user.getId());
        if (dbUser != null) {
            dbUser.setIsRemembered((byte) 0);
            Factory.getInstance().getUserDAO().updateUser(dbUser);

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chat.getId());
            sendMessage.setText("Щасти, " + dbUser.getName() + ". \nНадіюся, зустрінемося знову!");

            try {
                absSender.execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
