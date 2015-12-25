package MonopolyClient;

import MonopolyLibrary.MonopolyLibrary;

import javax.swing.*;
import java.awt.*;

/**
 * This class will be the main frame of the game,
 * just like SorryClientWindow from sorry.
 */
public class MonClientWindow extends JFrame {

    {
        setTitle("Monopoly");
        setSize(700,750);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(MonopolyLibrary.imagePath + "cursor/twoFingerCursor2.png");
        Cursor c = toolkit.createCustomCursor(image.getScaledInstance(40,48,Image.SCALE_SMOOTH) , new Point(0,
                0), "img");
        this.setCursor(c);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Double width = screenSize.getWidth()*0.75;
        Double height = screenSize.getHeight()*0.93;
        setMinimumSize(new Dimension(width.intValue(),height.intValue()));
        setMaximumSize(new Dimension(width.intValue(),height.intValue()));
        add(new ClientPanel(this));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args){
        //Create a new MonClientWindow
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MonClientWindow();
            }
        });
    }
}
