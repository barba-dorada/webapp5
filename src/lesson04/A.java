package lesson04;


import ru.javawebinar.webapp.model.SectionType;

import java.io.IOException;
import java.util.Date;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by vad on 11.01.2015.
 */
public class A {

    public static void main(String[] args) {
/*        try {
            LogManager.getLogManager().readConfiguration(
                    A.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }*/

        long currentDateTime = System.currentTimeMillis();
        System.out.println(currentDateTime);

        Date currentDate = new Date(1420986601000l);

        //printing value of Date
        System.out.println("current Date: " + currentDate);

        Logger logger = Logger.getLogger(A.class.getName());
        logger.info("info1");
        logger.fine("fine1");

        for (SectionType sectionType : SectionType.values()) {
            System.out.println(sectionType.getTitle());

        }


    }
}
