package com.christianfisher.helpers;

import java.io.File;
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
    final String LOGGING_PATH_NAME = "logPath";
    final String APP_DIR_NAME = "MyGameBook";
    final String LOG_FILENAME = "MyGameBook.log";
    final String DATA_DIR_NAME = "data";
    final String SETTING_DIR_NAME = "settings";
    String userDocumentsPath;


    AppSetupHelper() {
        userDocumentsPath = getUserDocumentsDir();
        createPropertyFile();
    }

    private void createPropertyFile() {
        Properties properties = new Properties();
        properties.setProperty("databasePath", buildDataPath());
        properties.setProperty("logPath", buildLogPath());

        File propsFile = new File(Paths.get(userDocumentsPath, APP_DIR_NAME, SETTING_DIR_NAME, PROPERTIES_FILE_NAME).toFile().getAbsolutePath());

        File directory = new File(Paths.get(userDocumentsPath, SETTING_DIR_NAME).toString());
        if (!directory.exists()) {
            directory.mkdirs(); // create directories if they do not exist
        }

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

        }

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

    private String buildDataPath() {
        return "";
    }

    private String buildLogPath() {
        return "";
    }


    public static void main(String[] args) {
        AppSetupHelper test = new AppSetupHelper();

    }

}
