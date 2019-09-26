import bot.StolenCarsBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import static services.BotConfig.BOT_NAME;

public class InitBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new StolenCarsBot(BOT_NAME));
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
