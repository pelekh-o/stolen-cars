package bot.commands;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.ICommandRegistry;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HelpCommand extends BotCommand {

    private final ICommandRegistry commandRegistry;

    public HelpCommand(ICommandRegistry commandRegistry) {
        super("help", "Отримати інформацію");
        this.commandRegistry = commandRegistry;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        StringBuilder messageBuilder = new StringBuilder("Бот шукає інформацію у базі викрадених транспортних засобів за держномером.");
        messageBuilder.append("\nПросто введи номер (наприклад, вс0000на) та надішли. ");
        messageBuilder.append("Я повідомлю тебе, чи зареєстровано ТЗ у списку викрадених");
        messageBuilder.append("\n\nСписок команд:\n");

        for (IBotCommand botCommand : commandRegistry.getRegisteredCommands()) {
            messageBuilder.append("/").append(botCommand.getCommandIdentifier()).append(" - ");
            messageBuilder.append(botCommand.getDescription()).append("\n");
            System.out.println(botCommand.toString());
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableHtml(true);
        sendMessage.setChatId(chat.getId());
        sendMessage.setText(messageBuilder.toString());

        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
