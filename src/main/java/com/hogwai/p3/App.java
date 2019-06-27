package com.hogwai.p3;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class App 
{
    private static final Logger LOGGER = LogManager.getLogger(App.class.getName());

    public static void main( String[] args )
    {
        LOGGER.trace(System.getProperty("java.class.path"));
        LOGGER.trace("Entering application.");
        LOGGER.error("error Message Logged !!!");
        LOGGER.warn("warn Message Logged !!!");
        LOGGER.info("info Message Logged !!!");
    }
}
