package com.christianfisher.log;

import com.christianfisher.helpers.AppSetupHelper;
import com.christianfisher.helpers.FileSystemHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.*;

public class LoggingManager { ;
    static Logger logger = Logger.getLogger(LoggingManager.class.getName());
    FileSystemHelper fileSystemHelper = new FileSystemHelper();
    String logPath;

    public LoggingManager() {
        AppSetupHelper appHelper = new AppSetupHelper();
        logPath = appHelper.getLoggingFile();
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/gameBookLogging.properties"));
        } catch (SecurityException | IOException e1){
            e1.printStackTrace();
        }
        logger.setLevel(Level.FINE);
        logger.addHandler(new ConsoleHandler());
        // My handler
        logger.addHandler(new LoggingHandler());

        try {
            //FilerHandler file name with max size and number of log files limit
            Handler fileHandler = new FileHandler(logPath, 2000, 5);
            fileHandler.setFormatter(new LoggingFormatter());
            //Custom Filter
            fileHandler.setFilter(new LoggingFilter());
            logger.addHandler(fileHandler);

            for(int i=0; i < 1000; i ++){
                //logging messages
                logger.log(Level.INFO, "Msg"+i);
            }
            logger.log(Level.CONFIG, "Config data");
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }
}
