import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport;
import org.junit.rules.TemporaryFolder;
import unused.ReadConfigs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Testing read configuration properties")
@EnableRuleMigrationSupport
public class ReadConfigsTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private ReadConfigs rc = new ReadConfigs();

    @DisplayName("Should load a property file and return a map")
    @Test
    void loadPropertyFileIntoMap() {

        Properties prop = new Properties();
        String propFileLocation = null;
        FileOutputStream output = null;

        try {
            propFileLocation = folder.getRoot() + "/config.properties";
            output = new FileOutputStream(propFileLocation);

            prop.setProperty("prop1", "val1");
            prop.setProperty("prop2", "val2");
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("prop2", "val2");
        expectedMap.put("prop1", "val1");

        Map<String, String> actualMap = rc.getPropertiesAsMap(propFileLocation);
        assertEquals(actualMap, expectedMap);
    }

}
