/*
 * Copyright (c) 2018.  semo
 */

package main;

import components.NumberPlateUtility;
import org.springframework.http.HttpStatus;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import services.RestfulClient;

import java.awt.*;
import java.io.IOException;

@ShellComponent
public class NumberplateClientCommands {

    @ShellMethod("Saying hi to a given person's name.")
    public String sayHi(
            @ShellOption(help = "needs a name") String text
    ) {
        return "Hi, " + text;
    }

    @ShellMethod("Add up to sum.")
    public int sum(
            @ShellOption int a,
            @ShellOption int b) {
        return a + b;
    }

    @ShellMethod("Generates a numberplate at the given location.")
    public String simple(
            @ShellOption(help = "path to image") String path
    ) throws IOException, FontFormatException {
        NumberPlateUtility np = new NumberPlateUtility();
        np.buildPlateImageAsFile(path);
        return "Done.";
    }

    @ShellMethod("Sends one simple POST request.")
    public String one() {
        RestfulClient rc = new RestfulClient();
        NumberPlateUtility np = new NumberPlateUtility();
        HttpStatus response = rc.postNumberPlate(np.completeImage());
        if (response == HttpStatus.OK) {
            return "Request sent successfully.";
        }
        return String.format("Request failed: %s", response.getReasonPhrase());
    }

}
