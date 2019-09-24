import bot.StolenCarsBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import parser.Parser;
import services.Emoji;

import static services.BotConfig.BOT_NAME;

public class Main {
    public static void main(String[] args) {
        /*Parser parser = new Parser();
        try {
            if (parser.isUpdateNeeded()) {
                parser.retrieveUpdates();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new StolenCarsBot(BOT_NAME));
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
