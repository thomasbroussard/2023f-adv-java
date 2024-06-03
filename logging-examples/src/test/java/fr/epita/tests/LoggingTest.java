package fr.epita.tests;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

public class LoggingTest {

    private static final Logger LOGGER = LogManager.getLogger(LoggingTest.class);


    public static void main(String[] args) {
        LOGGER.debug("test message");
        LOGGER.debug("test message for : {}, at {}", "this is a parameter message or an eval", new Date());
        LOGGER.debug(()-> new Date());

        try{
            Integer.parseInt("0.0");
        }catch (Exception e){
            LOGGER.error("this is a provoked error", e);
            //avoid this:
//            e.printStackTrace();
//            System.out.println(e);
        }


    }

}
