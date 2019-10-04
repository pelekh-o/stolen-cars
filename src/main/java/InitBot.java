import bot.StolenCarsBot;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import static services.BotConfig.BOT_NAME;

public class InitBot {
    private static Logger log = Logger.getLogger(InitBot.class);

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new StolenCarsBot(BOT_NAME));
            log.info("The bot is working now");
        } catch (TelegramApiRequestException e) {
            log.error(e.toString());
        }
    }
}
