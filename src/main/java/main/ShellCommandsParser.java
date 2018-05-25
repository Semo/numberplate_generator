/*
 * Copyright (c) 2018.  semo
 */

package main;


import java.util.HashMap;
import java.util.Map;

public class ShellCommandsParser {

    private Map<String, String> arguments = new HashMap<>();

    public ShellCommandsParser(String[] args) {
        parse(args);
    }

    private void parse(String... args) {
        for (int i = 0; i < args.length; i++) {
            arguments.put(args[i], args[++i]);
        }
    }

    public int size() {
        return arguments.size();
    }

    public boolean hasOption(String option) {
        return arguments.containsKey(option);
//        return arguments.stream().anyMatch(argument -> arguments.contains(option));
    }

    public String valueOf(String option) {
        return arguments.get(option);
    }
}
