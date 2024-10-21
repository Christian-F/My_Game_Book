package com.christianfisher;

import com.christianfisher.helpers.AppSetupHelper;
import com.christianfisher.helpers.FileSystemHelper;
import com.christianfisher.log.LoggingManager;

import java.util.logging.Logger;

public class Main {
    Logger logger = Logger.getLogger(Main.class.getName());

    public Main() {

    }

    public static void main(String[] args) {
        Main main = new Main();
        main.init();
    }

    public void init(){
        //TODO: - Fix logging system startup
        //The logging startup system needs work, will come back to it.
        //AppSetupHelper appSetupHelper = new AppSetupHelper();

        // Start up necessary services

    }
}