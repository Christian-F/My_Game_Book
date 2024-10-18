package com.christianfisher;

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
        // spin up the fileSystemHelper
        FileSystemHelper fileSystemHelper = new FileSystemHelper();
        fileSystemHelper.startUp();
    }
}