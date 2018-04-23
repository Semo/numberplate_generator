/*
 * Copyright (c) 2018.  semo
 */

package main;import components.NumberPlate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class Main {

    private static Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

        log.trace("Entering application.");

        SpringApplication.run(Main.class, args);

//        NumberPlate np = new NumberPlate();
//        System.out.println(np.buildPlateImage("."));
    }
}
