package com.smarthome.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

public class LoggerConfig {

    private static final Logger LOGGER = Logger.getLogger("com.smarthome");
    private static boolean initialized = false;

    public static synchronized Logger getLogger() {
        if (!initialized) {
            try {
                
                LOGGER.setUseParentHandlers(false);

                
                ConsoleHandler consoleHandler = new ConsoleHandler();
                consoleHandler.setLevel(Level.INFO);
                LOGGER.addHandler(consoleHandler);

                
                String userHome = System.getProperty("user.home"); 
                String logDirPath = userHome + File.separator + "Documents" + File.separator + "SmartHomeLogs";
                File logDir = new File(logDirPath);
                if (!logDir.exists()) {
                    logDir.mkdirs(); 
                }

                String logFilePath = logDirPath + File.separator + "smarthome-app.log";

                FileHandler fileHandler = new FileHandler(logFilePath, true);
                fileHandler.setFormatter(new SimpleFormatter());
                fileHandler.setLevel(Level.FINE);
                LOGGER.addHandler(fileHandler);

                
                LOGGER.setLevel(Level.FINE);

                initialized = true;

            } catch (IOException e) {
                System.err.println("Failed to initialize logger: " + e.getMessage());
            }
        }
        return LOGGER;
    }
}
