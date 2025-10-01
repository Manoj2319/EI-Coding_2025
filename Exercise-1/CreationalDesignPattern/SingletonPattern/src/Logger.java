public class Logger {
    private static volatile Logger instance; 
    private Logger() {
        // private constructor
    }

    public static Logger getInstance() {
        if (instance == null) {  
            synchronized (Logger.class) {  
                if (instance == null) {  
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void logInfo(String message) {
        System.out.println("[INFO] " + message);
    }

    public void logError(String message) {
        System.out.println("[ERROR] " + message);
    }
}
