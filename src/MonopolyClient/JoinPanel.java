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
 * If the player decides to join a game, this pane will appear
 */
public class JoinPanel extends CustomPanel{

    private CustomButton selectPawnButton;
    private JLabel portLabel, ipLabel;
    private JTextArea ipTArea, portTArea;
    private ActionListener selectPawnListener;
    private Image inImage;
    private ClientPanel clientPanel;

    public JoinPanel(ActionListener selectPawnListener, Image inImage, ClientPanel clientPanel){
        super(inImage);
        clientPanel = clientPanel;
        this.inImage = inImage;
        this.clientPanel = clientPanel;
        this.selectPawnListener = selectPawnListener;
        initializeComponents();
        setupGUI();
        setupEvents();
    }

    public int getPort(){
        return Integer.parseInt(portTArea.getText());
    }

    public String getIp(){
        return ipTArea.getText();
    }

    public ClientPanel getClientPanel(){
    	return clientPanel;
    }
    
    private void initializeComponents() {
        portLabel = new JLabel("PORT: ");
        portTArea = new JTextArea(){
            private int limit = 10;
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
        ipLabel = new JLabel("IP:  ");
        ipTArea = new JTextArea(){
            private int limit = 10;
            @Override
            protected Document createDefaultModel() {
                return new LimitDocument();
            }
            {
                this.setText("localhost");
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
        selectPawnButton = new CustomButton("Token Selection!", releasedImg, pressedImg, 20, Color.WHITE);
    }

    private void setupGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,10,0);
        gbc.anchor = GridBagConstraints.LINE_END;
        this.add(portLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,10,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        this.add(portTArea, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0,0,100,0);
        gbc.anchor = GridBagConstraints.LINE_END;
        this.add(ipLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,0,100,0);
        this.add(ipTArea, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        this.add(selectPawnButton, gbc);

        //getting Game Font
        Font font = MonopolyLibrary.getGameFont(Font.PLAIN, 40);

        //setting font and colors to components
        portLabel.setFont(font);
        portLabel.setForeground(Color.WHITE);
        ipLabel.setFont(font);
        ipLabel.setForeground(Color.WHITE);
        portTArea.setBackground(MonopolyLibrary.goldColor);
        portTArea.setFont(font);
        portTArea.setForeground(Color.WHITE);
        ipTArea.setBackground(MonopolyLibrary.cardinalColor);
        ipTArea.setFont(font);
        ipTArea.setForeground(Color.WHITE);
        selectPawnButton.setFont(font);

        //editing text area so they have a fixed size
        FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(font);
        int height = fm.getHeight();
        portTArea.setPreferredSize(new Dimension(100, height + 2));
        ipTArea.setPreferredSize(new Dimension(250, height + 2));
    }

    private void setupEvents() {
        selectPawnButton.addActionListener(selectPawnListener);
    }
}