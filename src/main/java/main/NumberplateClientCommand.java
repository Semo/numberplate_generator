/*
 * Copyright (c) 2018.  semo
 */

package main;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class NumberplateClientCommand {

    @ShellMethod("Saying hi to a given person's name.")
    public String sayHi(
            @ShellOption String text
    ) {
        return "Hi, " + text;
    }

    @ShellMethod("Add up to sum.")
    public int sum (
            @ShellOption int a,
            @ShellOption int b)
    {
        return a+b;
    }



}
