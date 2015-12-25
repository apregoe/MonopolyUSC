package MonopolyClient;

import MonopolyLibrary.MonopolyLibrary;
import MonopolyUI.CustomButton;
import MonopolyUI.CustomPanel;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * If the player decides to host a game, this pane will appear
 */
public class HostPanel extends CustomPanel{
    private JLabel portLabel;
    private JTextArea portTArea;
    private CustomButton selectPawnButton;
    private ActionListener selectPawnListener;
    private Image inImage;
    public HostPanel(ActionListener selectPawnListener, Image inImage) {
        super(inImage);
        this.inImage = inImage;
        this.selectPawnListener = selectPawnListener;

        initializeComponents();
        setupGUI();
        addEvents();
    }

    public int getPort(){
        return Integer.parseInt(portTArea.getText());
    }

    private void initializeComponents() {
        portLabel = new JLabel("PORT:   ");
        portTArea = new JTextArea(){
            int limit = 10;
            {
                this.setText("6789");
            }
            @Override
            protected Document createDefaultModel() {
                return new LimitDocument();
            }
            class LimitDocument extends PlainDocument {

                @Override
                public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
                    if (str == null) return;

                    if ((getLength() + str.length()) <= limit) {
                        super.insertString(offset, str, attr);
                    }
                }

            }
        };
        Image releasedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath+"/buttons/buttons.jpg");
        Image pressedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath+"/buttons/buttons_flipped.jpg");
        selectPawnButton = new CustomButton("Token Selection!", releasedImg, pressedImg, 25, Color.WHITE);
    }

    private void setupGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,150,0);
        gbc.anchor = GridBagConstraints.LINE_END;
        add(portLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,150,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        add(portTArea, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0,0,0,0);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(selectPawnButton, gbc);

        //Getting game font from library
        Font font = MonopolyLibrary.getGameFont(Font.PLAIN, 35);

        //editing components to custom UI
        portLabel.setFont(font);
        portLabel.setForeground(Color.WHITE);
        portTArea.setBackground(MonopolyLibrary.goldColor);
        portTArea.setForeground(MonopolyLibrary.cardinalColor);
        portTArea.setFont(font);
        selectPawnButton.setFont(font);

        //editing dimensions of textarea
        FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(font);
        int height = fm.getHeight();
        portTArea.setPreferredSize(new Dimension(90, height + 2));

    }

    private void addEvents() {
        selectPawnButton.addActionListener(selectPawnListener);
    }
}
