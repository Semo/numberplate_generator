import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class MyCanvas extends JComponent{

    public void paintComponent(Graphics g){
        BufferedImage bufferedImage = new BufferedImage(300, 200, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.PLAIN, 96);
        g2.setFont(font);

        g2.drawString("B SN 9015", 40, 120);


        RenderedImage rendImage = bufferedImage;

        File file = new File("newimage.png");
        try {
            ImageIO.write(rendImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        file = new File("newimage.jpg");
        try {
            ImageIO.write(rendImage, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
