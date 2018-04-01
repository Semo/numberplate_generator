import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String randomElementOfList(ArrayList<String> arrayList) {
        ArrayList<String> givenList = arrayList;
        Random rand = new Random();
        return givenList.get(rand.nextInt(arrayList.size()));
    }

    public static String randomString() {
        final int N = alphabet.length();

        Random r = new Random();

        int length = r.nextInt((2 - 1) + 1) + 1;
        char[] charArray = new char[length];
        for (int i = 0; i < length; i++) {
            charArray[i] = alphabet.charAt(r.nextInt(N));
        }
        return new String(charArray);
    }

    public static void main(String[] args) throws Exception {
        Splitter sp = new Splitter();
        ArrayList<String> al = (ArrayList<String>) sp.getDistrictList();

        String randomDistrict = randomElementOfList(al);
        String randomString = randomString();
        int randomNum = 0;
        if (randomString.length() > 1) {
            randomNum = ThreadLocalRandom.current().nextInt(1, 99 + 1);
            randomString.concat(" ");
        } else {
            randomNum = ThreadLocalRandom.current().nextInt(1, 999 + 1);
        }
        final BufferedImage image = ImageIO.read(new File("/home/semo/springwebapps/anpg/src/numberplate1.png"));

        Graphics g = image.getGraphics();
        Font font = Font.createFont(Font.TRUETYPE_FONT,
                new FileInputStream("/usr/share/fonts/EuroPlate/EuroPlate.ttf"));
        font = font.deriveFont(105f);
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString(randomDistrict + "  " + randomString + " " + randomNum, 60, 90);
        g.dispose();

        ImageIO.write(image, "png", new File("test.png"));
        }
}

