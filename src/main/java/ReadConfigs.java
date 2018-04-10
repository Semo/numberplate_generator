import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * Utility class which reads any Property file.
 */
public class ReadConfigs {

    public ReadConfigs() {
    }

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

            if (inputStream != null) {
                props.load(inputStream);

                Enumeration enumKeys = props.keys();
                while (enumKeys.hasMoreElements()) {
                    String key = (String) enumKeys.nextElement();
                    map.put(key, props.getProperty(key));
                }
                return map;
            } else {
                throw new FileNotFoundException("property file '" + pathToProperties + "' not found in the classpath");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
