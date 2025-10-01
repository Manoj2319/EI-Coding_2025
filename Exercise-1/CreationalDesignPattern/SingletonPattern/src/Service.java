
public class Service {
    private final Logger logger = Logger.getInstance();

    public void processTask(String taskName) {
        logger.logInfo("Starting task: " + taskName);
        try {
            Thread.sleep(500);
            logger.logInfo("Finished task: " + taskName);
        } catch (InterruptedException e) {
            logger.logError("Task interrupted: " + taskName);
        }
    }
}
