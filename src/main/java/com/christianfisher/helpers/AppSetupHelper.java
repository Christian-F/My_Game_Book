package com.christianfisher.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * This class will perform the initial, behind-the-scenes, setup for the app including:
 *  - Establish and populate the properties file(s)
 *  - Create necessary file structure
 *      - TODO: Future state, this will be configurable through the GUI
 *  - Create database file(s)
 */

public class AppSetupHelper {
    Logger logger = Logger.getLogger(AppSetupHelper.class.getName());
    final String CLASSNAME = AppSetupHelper.class.getName();

    final String PROPERTIES_FILE_NAME = "app.properties";
    final String DB_PATH_PROP_NAME = "databasePath";
    final String LOGGING_PATH_NAME = "Logs";
    final String APP_DIR_NAME = "MyGameBook";
    final String LOG_FILENAME = "MyGameBook.log";
    final String DATA_DIR_NAME = "data";
    final String SETTING_DIR_NAME = "Settings";
    String userDocumentsPath;
    String loggingFile;

    public AppSetupHelper() {
        userDocumentsPath = getUserDocumentsDir();
        createAppDirectory();
        createPropertiesFile();
        createLoggingFile();
    }

    public String getLoggingFile() {
        return this.loggingFile;
    }

    private void createPropertiesFile() {
        Properties properties = new Properties();
        properties.setProperty("databasePath", buildDataPath());
        properties.setProperty("logPath", createLoggingFile());

        File directory = new File(Paths.get(userDocumentsPath, APP_DIR_NAME, SETTING_DIR_NAME).toString());
        if (!directory.exists()) {
            directory.mkdirs(); // create directories if they do not exist
        }

        File propsFile = new File(Paths.get(userDocumentsPath, APP_DIR_NAME, SETTING_DIR_NAME, PROPERTIES_FILE_NAME).toFile().getAbsolutePath());

        try {
            if (propsFile.createNewFile()) {
                System.out.println("Create props file.");
            } else {
                System.out.println("Failed to create props file");
            }
        } catch (IOException e){
            System.out.println("Could not create props file:");
            e.printStackTrace();
        }

        try (FileOutputStream fos = new FileOutputStream(propsFile)) {
            properties.store(fos, "Built properties file.");
        } catch (Exception e) {
            System.out.println("Failed to write properties to properties file.");
        }
    }

    private String createLoggingFile() {
        String logFileName;
        buildLogPath();
        File directory = new File(Paths.get(userDocumentsPath, APP_DIR_NAME, LOGGING_PATH_NAME).toString());
        if (!directory.exists()) {
            directory.mkdirs(); // create directories if they do not exist
        }

        try {
            File logFile = new File(Paths.get(String.valueOf(directory), LOG_FILENAME).toString());
            if (!logFile.exists()) {
                logFile.createNewFile();
                this.loggingFile = logFile.getAbsolutePath();
            }
            logFileName = logFile.getAbsolutePath();
            logger.info("Successfully Created logFile");
        } catch (IOException e){
            System.out.println("Could not create Log File:");
            e.printStackTrace();
            return "";
        }
        return logFileName;
    }

    private String getUserDocumentsDir() {
        String path = "";
        try {
            path = Paths.get(System.getProperty("user.home"), "Documents").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return path;
    }

    private void createAppDirectory() {
        File appDir = new File(Paths.get(userDocumentsPath, APP_DIR_NAME).toString());
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
    }

    private String buildDataPath() {
        return "";
    }

    private void buildLogPath() {
        File directory = new File(Paths.get(userDocumentsPath, APP_DIR_NAME, LOGGING_PATH_NAME).toString());
        if (!directory.exists()) {
            directory.mkdirs(); // create directories if they do not exist
        }
    }

    public static void main(String[] args) {
        AppSetupHelper test = new AppSetupHelper();
    }

}
