package anpgtests;

import components.NumberPlateUtility;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import org.junit.Before;

@RunWith(SpringJUnit4ClassRunner.class)
@DisplayName("Testing numberplate generation")
@EnableRuleMigrationSupport
public class NumberPlateTest {


    private static NumberPlateUtility num;
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void init() {
        num = new NumberPlateUtility();
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
        File f = new File(String.valueOf(num.buildPlateImageAsFile(String.valueOf(folder.getRoot()))));
        assertTrue(f.exists());
    }

}
