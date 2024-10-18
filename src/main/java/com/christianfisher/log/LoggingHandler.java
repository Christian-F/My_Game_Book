package com.christianfisher.log;

import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

public class LoggingHandler extends StreamHandler {

    @Override
    public void publish(LogRecord record) {
        // Add my logic here
        super.publish((record));
    }

    @Override
    public void flush() {
        super.flush();
    }

    @Override
    public void close() throws SecurityException {
        super.close();
    }


}
