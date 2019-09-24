package services;

public class BotConfig {
    public static final String BOT_TOKEN = System.getenv("STOLENCARS_CHATBOT");
    public static final String BOT_NAME = "StolenCarsUAbot";

    public static final String DB_PASSWORD = System.getenv("DBPASS_STOLENCARS");
    public static final String DB_USER = System.getenv("DBUSER_STOLENCARS");
}
