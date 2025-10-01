public class DatabaseConnection {
    private final Logger logger = Logger.getInstance();

    public void connect() {
        logger.logInfo("Connecting to database...");
    }

    public void disconnect() {
        logger.logInfo("Disconnecting from database...");
    }
}
