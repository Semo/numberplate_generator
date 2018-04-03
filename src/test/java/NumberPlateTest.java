import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport;
import org.junit.rules.TemporaryFolder;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testing numberplate generation")
@EnableRuleMigrationSupport
class NumberPlateTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private NumberPlate num;

    @BeforeEach
    void init() {
        num = new NumberPlate();
    }

    @DisplayName("Should return a random String element of a given Array.")
    @Test
    void shouldGetRandomElementOfList() {
        String[] districts = {"0", "1", "AA", "ABG", "ABI", "AC", "AE", "AH",
                "AIC", "AK", "ALF", "ALZ", "AM", "ANA", "ANG", "ANK", "AÖ", "AP", "APD"};
        assertNotEquals(num.randomElementOfList(districts), num.randomElementOfList(districts));

    }

    @DisplayName("Should return a random String from an internal String.")
    @Test
    void shouldReturnRandomString() {
        assertNotEquals(num.randomString(), num.randomString());
    }

    @DisplayName("Should build a random number and char String.")
    @Test
    void shouldReturnAPlateString() {
        assertNotEquals(num.buildPlateString(), num.buildPlateString());
    }

    @DisplayName("Should generate a new numberplate image file")
    @Test
    void shouldBuildPlateImage() {
        try {
            File f = new File(num.buildPlateImage(String.valueOf(folder.getRoot())));
            assertTrue(f.exists());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }

    }

}