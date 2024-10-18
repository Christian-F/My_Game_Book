package com.christianfisher.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.Logger;
import java.nio.file.Paths;

/*
    Currently in progress. I don't like the way this helper class turned out, so some of the startup portions are moving
    to their own class - AppSetupHelper
 */

public class FileSystemHelper {
    Logger logger = Logger.getLogger(FileSystemHelper.class.getName());
    final String CLASSNAME = FileSystemHelper.class.getName();
    final String PROPERTIES_FILE_NAME = "src/app.properties";
    final String DB_PATH_PROP_NAME = "databasePath";
    final String LOGGING_PATH_NAME = "logPath";
    final String APP_DIR_NAME = "MyGameBook";
    final String LOG_FILENAME = "MyGameBook.log";

    String dbPath;
    String logPath;

    public FileSystemHelper() {

    }

    public String getLogPath(){
        return this.logPath;
    }

    public String getdbPath(){
        return this.dbPath;
    }

    public void startUp() {
        // Check if we've already sorted out the properties file
        if(verifyProperties()) {
            logger.info("Properties file verified.");
            findLoggingFile();
        } else {
            System.out.println("Failed set establish system properties.");
        }
    }

    private boolean verifyProperties() {
        boolean result = false;
        Properties properties = new Properties();
        // Read in the props file
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE_NAME)){
            properties.load(fis);
        } catch ( Exception e ){
            e.printStackTrace();
            return false;
        }

        try (FileOutputStream fos = new FileOutputStream(PROPERTIES_FILE_NAME)) {
            if (properties.getProperty(DB_PATH_PROP_NAME).toString().length() < 1) {
                String path = createDBPath();
                properties.setProperty(DB_PATH_PROP_NAME, path);
                this.dbPath = path;
                logger.info("Created db path property to " + dbPath);
            } else {
                this.dbPath = properties.getProperty(DB_PATH_PROP_NAME);
                logger.info("Found existing db path property at " + dbPath);
            }
            if (properties.getProperty(LOGGING_PATH_NAME).toString().length() < 1) {
                String path = createLoggingPath();
                properties.setProperty(LOGGING_PATH_NAME, path);
                this.logPath = path;
                logger.info("Created logging path prop to " + logPath);
            } else {
                this.logPath = properties.getProperty(LOGGING_PATH_NAME);
                logger.info("Found existing logging path prop at " + logPath);
            }

            properties.store(fos, "App created properties. DO NOT EDIT");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private String createDBPath(){
        String path = "";
        String userHomeDir = System.getProperty("user.home");
        String userDocumentsDir = Paths.get(userHomeDir, "Documents").toString();
        path = Paths.get(userDocumentsDir, APP_DIR_NAME, "Data").toString();
        File dbDir = new File(path);
        dbDir.mkdir();
        if (dbDir.mkdir()){
            System.out.println("Successfully created data directory");
        } else {
            System.out.println("Could not create data directory");
        }

        return path;
    }

    private String createLoggingPath(){
        String path = "";
        String userHomeDir = System.getProperty("user.home");
        String userDocumentsDir = Paths.get(userHomeDir, "Documents").toString();
        path = Paths.get(userDocumentsDir, APP_DIR_NAME, "Logs").toString();

        File loggingDir = new File(path);
        if (loggingDir.mkdir()){
            System.out.println("Successfully created logging directory");
        } else {
            System.out.println("Could not create logging directory");
        }

        return path;
    }

    private boolean findLoggingFile() {
        // TODO: check if log file exists, make it if not
        String logFilePath = Paths.get(getLogPath() ,LOG_FILENAME).toString();
        System.out.println(logFilePath);
        File file = new File(logFilePath);
        try {
            if (!file.exists()){
                file.createNewFile();
            } else {

            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }

    private boolean findDatabase() {
        // TODO: check if database exists, make it if not
        return false;
    }

    private void createLoggingFile() {

    }

}
