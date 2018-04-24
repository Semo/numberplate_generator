package anpgtests;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

//import org.junit.Before;
import org.junit.Rule;
//import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import components.NumberPlate;

@RunWith(SpringJUnit4ClassRunner.class)
@DisplayName("Testing numberplate generation")
@EnableRuleMigrationSupport
public class NumberPlateTest {


    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private static NumberPlate num;

    @BeforeAll
    public static void init() {
        num = new NumberPlate();
    }

    @DisplayName("Should return a random String element of a given Array.")
    @Test
    public void shouldGetRandomElementOfList() {
        String[] districts = {"0", "1", "AA", "ABG", "ABI", "AC", "AE", "AH",
                "AIC", "AK", "ALF", "ALZ", "AM", "ANA", "ANG", "ANK", "AÖ", "AP", "APD"};
        assertNotEquals(num.randomElementOfList(districts), num.randomElementOfList(districts));

    }

    @DisplayName("Should return a random String from an internal String.")
    @Test
    public void shouldReturnRandomString() {
        assertNotEquals(num.randomString(), num.randomString());
    }

    @DisplayName("Should build a random number and char String.")
    @Test
    public void shouldReturnAPlateString() {
        assertNotEquals(num.buildPlateString(), num.buildPlateString());
    }

    @DisplayName("Should generate a new numberplate image file")
    @Test
    public void shouldBuildPlateImage() {
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