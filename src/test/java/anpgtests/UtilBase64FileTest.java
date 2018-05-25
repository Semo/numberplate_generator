package anpgtests;

import static org.junit.Assert.assertEquals;

import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import components.NumberPlateUtility;
import utilities.UtilBase64File;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableRuleMigrationSupport
public class UtilBase64FileTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	private static NumberPlateUtility num;

	@Before
	public void init() {
		num = new NumberPlateUtility();
	}

	@Test
	public void testEncodeFromFile() {
		try {
			File f = num.buildPlateImageAsFile(String.valueOf(folder.getRoot()));
			FileInputStream imageInFile = new FileInputStream(f);
			byte imageData[] = new byte[(int) f.length()];
			imageInFile.read(imageData);
			String encodedFilef = Base64.getEncoder().encodeToString(imageData);
			imageInFile.close();
			assertEquals(encodedFilef, UtilBase64File.encodeFromFile(f.getPath()));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
