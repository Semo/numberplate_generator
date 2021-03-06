/*
 * Copyright (c) 2018.  semo
 */

package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    private static Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        log.trace("Entering application.");
        SpringApplication.run(Main.class, args);

    }
}
