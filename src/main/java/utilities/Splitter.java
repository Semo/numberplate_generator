/*
 * Copyright (c) 2018.  semo
 */

package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Splitter {

    private List<String> readTxtIntoStringList(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner input = new Scanner(file);
        List<String> list = new ArrayList<>();

        while (input.hasNextLine()) {
            list.add(input.nextLine());
        }
        return list;
    }


    private ArrayList<String> splitter(List<String> textlist, String pattern) {
        ArrayList<String> results = new ArrayList<>();

        Pattern p = Pattern.compile(pattern);

        for (String s : textlist) {
            Matcher matcher = p.matcher(s);
            while (matcher.find()) {
                String token = matcher.group(1); //group 0 is always the entire match
                if (token.length() < 4) {
                    results.add(token);
                }
            }
        }
        return results;
    }

    public List<String> getDistrictList() {
        List<String> results = new ArrayList<>();

        String pattern = "'''(.*?)'''$";
        try {
            results = splitter(readTxtIntoStringList("/home/semo/springwebapps/anpg/src/quell.txt"), pattern);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return results;
    }


}
