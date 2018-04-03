import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


/**
 *
 */
public class NumberPlate {

    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final String[] districts = {"0", "1", "AA", "ABG", "ABI", "AC", "AE", "AH",
            "AIC", "AK", "ALF", "ALZ", "AM", "ANA", "ANG", "ANK", "AÖ", "AP", "APD",
            "ARN", "ART", "AS", "ASL", "ASZ", "AT", "AU", "AUR", "AW", "AZ", "AZE",
            "B", "BAD", "BAR", "BB", "BBG", "BBL", "BC", "BCH", "BD", "BE", "BED",
            "BER", "BF", "BGD", "BGL", "BI", "BID", "BIN", "BIR", "BIT", "BIW", "BKS",
            "BL", "BLB", "BLK", "BM", "BN", "BNA", "BO", "BÖ", "BOH", "BOR", "BOT",
            "BP", "BRA", "BRB", "BRG", "BRK", "BRL", "BRV", "BS", "BSK", "BTF", "BÜD",
            "BÜR", "BÜS", "BÜZ", "BW", "BWL", "BYL", "BZ", "C", "CA", "CAS", "CB",
            "CE", "CHA", "CLP", "CLZ", "CO", "COC", "COE", "CR", "CUX", "CW", "D",
            "DA", "DAH", "DAN", "DAU", "DBR", "DD", "DE", "DEG", "DEL", "DGF", "DH",
            "DI", "DIL", "DIN", "DIZ", "DKB", "DL", "DLG", "DM", "DN", "DO", "DON",
            "DU", "DUD", "DÜW", "DW", "DZ", "E", "EA", "EB", "EBE", "EBN", "ECK", "ED",
            "EE", "EF", "EG", "EH", "EI", "EIC", "EIL", "EIN", "EIS", "EL", "EM",
            "EMD", "EMS", "EN", "ER", "ERB", "ERH", "ERK", "ERZ", "ES", "ESW", "EU",
            "EW", "F", "FB", "FD", "FDB", "FDS", "FEU", "FF", "FFB", "FG", "FI", "FKB",
            "FL", "FLÖ", "FN", "FO", "FOR", "FRG", "FRI", "FRW", "FS", "FT", "FTL",
            "FÜS", "FW", "FZ", "G", "GA", "GAN", "GAP", "GC", "GD", "GDB", "GE", "GEL",
            "GER", "GF", "GG", "GHA", "GHC", "GI", "GK", "GL", "GLA", "GM", "GMN",
            "GN", "GNT", "GOA", "GOH", "GP", "GR", "GRA", "GRH", "GRI", "GRM", "GRZ",
            "GS", "GT", "GTH", "GÜ", "GUB", "GUN", "GV", "GVM", "GW", "GZ", "HA",
            "HAB", "HAL", "HAM", "HAS", "HBN", "HBS", "HC", "HDH", "HDL", "HE", "HEB",
            "HEF", "HEI", "HEL", "HER", "HET", "HF", "HG", "HGN", "HGW", "HH", "HHM",
            "HI", "HIG", "HIP", "HK", "HL", "HM", "HMÜ", "HOG", "HOH", "HOL", "HOM",
            "HOR", "HÖS", "HOT", "HP", "HR", "HRO", "HS", "HSK", "HST", "HV", "HVL",
            "HWI", "HX", "HY", "HZ", "IGB", "IK", "IL", "ILL", "IN", "IZ", "J", "JE",
            "JL", "JÜL", "K", "KB", "KC", "KE", "KEH", "KEL", "KF", "KG", "KH", "KI",
            "KIB", "KK", "KLE", "KLZ", "KM", "KN", "KO", "KÖN", "KÖT", "KÖZ", "KR",
            "KRU", "KS", "KT", "KU", "KÜN", "KUS", "KW", "KY", "KYF", "LAN", "LAU",
            "LB", "LBS", "LBZ", "LC", "LD", "LDK", "LDS", "LEO", "LER", "LEV", "LG",
            "LH", "LI", "LIB", "LIF", "LIP", "LL", "LM", "LN", "LÖ", "LÖB", "LOS",
            "LP", "LR", "LRO", "LSA", "LSN", "LSZ", "LU", "LÜN", "LUP", "LWL", "MA",
            "MAB", "MAK", "MAL", "MB", "MC", "MD", "ME", "MED", "MEG", "MEI", "MEK",
            "MER", "MET", "MG", "MGH", "MGN", "MH", "MHL", "MI", "MIL", "MK", "MKK",
            "ML", "MM", "MN", "MO", "MOD", "MOL", "MON", "MOS", "MQ", "MR", "MS",
            "MSE", "MSH", "MSP", "MST", "MTK", "MTL", "MÜ", "MÜR", "MVL", "MW",
            "MY", "MYK", "MZG", "NAI", "NAU", "NB", "ND", "NDH", "NE", "NEA", "NEB",
            "NEN", "NES", "NEW", "NF", "NH", "NI", "NK", "NL", "NM", "NMB", "NMS",
            "NÖ", "NOH", "NOL", "NOM", "NOR", "NP", "NR", "NRW", "NT", "NU", "NVP",
            "NW", "NWM", "NY", "NZ", "OA", "OAL", "OB", "OBB", "OBG", "OC", "OCH",
            "OD", "OE", "OF", "OG", "OH", "OHA", "ÖHR", "OHV", "OHZ", "OK", "OP",
            "OPR", "OSL", "OVI", "OVL", "OZ", "P", "PAF", "PAN", "PB", "PCH", "PE",
            "PI", "PIR", "PL", "PLÖ", "PM", "PN", "PR", "PRÜ", "PW", "PZ", "QFT",
            "QLB", "RA", "RC", "RD", "RDG", "RE", "REG", "REI", "RG", "RH", "RI",
            "RID", "RIE", "RL", "RM", "RN", "ROF", "ROK", "ROS", "ROT", "ROW",
            "RP", "RPL", "RS", "RSL", "RT", "RU", "RÜD", "RÜG", "RV", "RW", "RZ",
            "S", "SAB", "SAD", "SAL", "SAW", "SB", "SBG", "SBK", "SC", "SCZ",
            "SDH", "SDL", "SDT", "SE", "SEB", "SEE", "SEF", "SEL", "SFB", "SFT",
            "SG", "SGH", "SH", "SHA", "SHG", "SHK", "SHL", "SI", "SIG", "SIM",
            "SK", "SL", "SLE", "SLF", "SLK", "SLN", "SLS", "SLÜ", "SLZ", "SM",
            "SMÜ", "SN", "SO", "SOB", "SOG", "SOK", "SÖM", "SON", "SP", "SPB",
            "SPN", "SRB", "SRO", "ST", "STA", "STB", "STD", "STE", "STL", "SU",
            "SUL", "SÜW", "SWA", "SZ", "SZB", "TBB", "TDO", "TE", "TET", "TF",
            "TG", "THL", "THW", "TIR", "TO", "TÖL", "TP", "TR", "TS", "TÜ", "TUT",
            "UE", "UEM", "UFF", "UH", "UM", "UN", "USI", "V", "VAI", "VB", "VEC",
            "VER", "VG", "VIE", "VIT", "VK", "VOH", "VR", "VS", "W", "WA", "WAF",
            "WAK", "WAN", "WAT", "WB", "WBS", "WDA", "WE", "WEL", "WEN", "WES",
            "WF", "WHV", "WI", "WIL", "WIS", "WIT", "WIZ", "WK", "WL", "WLG",
            "WM", "WMS", "WN", "WND", "WO", "WOB", "WOH", "WOS", "WR", "WRN",
            "WS", "WSF", "WST", "WSW", "WT", "WTM", "WUG", "WÜM", "WUN", "WUR",
            "WW", "WZ", "WZL", "X", "Y", "Z", "ZE", "ZEL", "ZI", "ZIG", "ZP",
            "ZR", "ZZ"};


    /**
     * @param stringArray
     * @return
     */
    public String randomElementOfList(String[] stringArray) {
        Random rand = new Random();
        return stringArray[rand.nextInt(stringArray.length)];
    }

    /**
     * @return
     */
    public String randomString() {
        final int N = alphabet.length();

        Random r = new Random();

        int length = r.nextInt((2 - 1) + 1) + 1;
        char[] charArray = new char[length];
        for (int i = 0; i < length; i++) {
            charArray[i] = alphabet.charAt(r.nextInt(N));
        }
        return new String(charArray);
    }

    /**
     * @return
     */
    public String buildPlateString() {
        String randomDistrict = randomElementOfList(districts);
        String randomString = randomString();
        int randomNum = 0;
        if (randomString.length() > 1) {
            randomNum = ThreadLocalRandom.current().nextInt(1, 99 + 1);
            randomString.concat(" ");
        } else {
            randomNum = ThreadLocalRandom.current().nextInt(1, 999 + 1);
        }

        return randomDistrict + "  " + randomString + " " + randomNum;
    }

    /**
     * @throws IOException
     * @throws FontFormatException
     */
    public String buildPlateImage(String targetDir) throws IOException, FontFormatException {
        ClassLoader classLoader = getClass().getClassLoader();
        final BufferedImage image = ImageIO.read(classLoader.getResource("numberplate1.png"));

        String plateString = buildPlateString();
        String trimmedFileName = targetDir + "/" + plateString.replaceAll("\\s+","") + ".png";

        Graphics g = image.getGraphics();
        Font font = Font.createFont(Font.TRUETYPE_FONT,
                new FileInputStream("/usr/share/fonts/EuroPlate/EuroPlate.ttf"));
        font = font.deriveFont(105f);
        g.setColor(Color.BLACK);
        g.setFont(font);

        g.drawString(plateString, 60, 90);
        g.dispose();

        ImageIO.write(image, "png", new File(trimmedFileName));

        return trimmedFileName;
    }
}
