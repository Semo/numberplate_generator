import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

public class Main {


    public static void main(String[] args) throws Exception {

        NumberPlate np = new NumberPlate();
        np.buildPlateImage();
    }
}
