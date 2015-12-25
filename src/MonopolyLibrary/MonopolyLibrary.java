package MonopolyLibrary;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * static methods that are useful to get predifined images and font(s)
 */
public class MonopolyLibrary {

    public static String imagePath = "src/images/";
    public static String fontPath = "src/font/High School USA Serif.ttf";
    public static Color goldColor = new Color(255, 215, 0);
    public static Color cardinalColor = new Color(189,32,49);
    public static Font getGameFont(int style, int size){
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,  new FileInputStream(MonopolyLibrary.fontPath));
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException in MonopolyLibrary.getGameFont()");
            e.printStackTrace();
        } catch (FontFormatException e) {
            System.out.println("FontFormatException in MonopolyLibrary.getGameFont()");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException in MonopolyLibrary.getGameFont()");
            e.printStackTrace();
        }
        if(font == null){
            throw new NullPointerException("NullPointerException in MonopolyLibrary.getGameFont()");
        }
        return font.deriveFont(style, size);
    }

    public static Image getImage(String dirString) throws NullPointerException{
        Image img = null;
        try{
            img = ImageIO.read(new File(dirString));
        } catch (IOException e) {
            System.out.println("IOException in MonopolyLibrary.getImage()");
            e.printStackTrace();
        }
        if(img == null){
            throw new NullPointerException("NullPointerException");
        }
        return img;
    }
}
