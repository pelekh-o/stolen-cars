import parser.Parser;

public class InitParser {
    public static void main(String[] args) {
        Parser parser = new Parser();
        try {
            if (parser.isUpdateNeeded()) {
                parser.retrieveUpdates();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
