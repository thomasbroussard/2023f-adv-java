package fr.epita.tests;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingTest {

    private static final Logger LOGGER = LogManager.getLogger(LoggingTest.class);


    public static void main(String[] args) {
        LOGGER.debug("test message");
    }

}
