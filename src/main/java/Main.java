/*
 * Copyright (c) 2018.  semo
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    private static Logger log = LogManager.getLogger(Main.class);
    public static void main(String[] args) throws Exception {

        log.trace("Entering application.");

        NumberPlate np = new NumberPlate();
        System.out.println(np.buildPlateImage("."));

    }
}
