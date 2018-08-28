/*
 * Copyright (c) 2018.  semo
 */

package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * Utility class which reads any Property file.
 */
public class ReadConfigs {

    private static Logger log = LogManager.getLogger(ReadConfigs.class);


    public ReadConfigs() {}

    /**
     * Returns map of properties from a *.properties file
     *
     * @param pathToProperties
     * @return map of properties
     * @throws IOException
     */
    public Map<String, String> getPropertiesAsMap(String pathToProperties) {
        Map<String, String> map = new HashMap<>();

        try {
            Properties props = new Properties();
            File file = new File(pathToProperties);
            FileInputStream inputStream = new FileInputStream(file);

            props.load(inputStream);

            Enumeration enumKeys = props.keys();
            while (enumKeys.hasMoreElements()) {
                String key = (String) enumKeys.nextElement();
                map.put(key, props.getProperty(key));
            }
            return map;

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return map;
    }
}
