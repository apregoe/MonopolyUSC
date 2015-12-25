package MonopolyClient;
import MonopolyLibrary.MonopolyLibrary;
import MonopolyUI.CustomButton;
import MonopolyUI.CustomPanel;
import MonopolyServer.DatabaseConnector;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * First panel that appears, where the player can log in, register, or enter as a guest
 */
public class LogInPanel extends CustomPanel{
    private JLabel welcomeLabel, usernameLabel,instructionLabel1, instructionLabel2, passwordLabel, spacemdfk;
    private CustomButton loginButton, registerButton, guestButton;
    private JTextArea usernameTArea;
    private JPasswordField passwordTArea;
    private JPanel usernamePanel, passwordPanel, buttonsPanel;
    private ActionListener nextPanelAction;
    private DatabaseConnector databaseConnector;
    private Image inImage;
    private Font font;

    public LogInPanel(ActionListener nextPanelAction, Image inImage, boolean drawInBack, DatabaseConnector databaseConnector){
        super(inImage, drawInBack);
        this.inImage = inImage;
        this.nextPanelAction = nextPanelAction;
        this.databaseConnector = databaseConnector;

        initializeComponents();
        createGUI();
        createEvents();
    }

    public String getUsername(){
        return usernameTArea.getText();
    }

    public String getPassword(){
        return passwordTArea.getText();
    }

    private void initializeComponents() {
        //initializing labels
        welcomeLabel = new JLabel("Welcome to Monopoly!");
        instructionLabel1 = new JLabel("Please log in or continue as a guest");
        instructionLabel2 = new JLabel("if you would like to watch a game in progress!");
        usernameLabel = new JLabel("Username:                                            ");
        passwordLabel = new JLabel("Password:");
        spacemdfk = new JLabel("                                             ");

        //initializing text areas
        //The following two classes set up max characters for each text field
        usernameTArea = new JTextArea(){
            private int limit = 10;
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
        passwordTArea = new JPasswordField() {
            private int limit = 15;

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

        //initializing buttons
        Image releasedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath+"/buttons/buttons.jpg");
        Image pressedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath+"/buttons/buttons_flipped.jpg");
        loginButton = new CustomButton("Log in", releasedImg, pressedImg, 20, Color.WHITE);
        registerButton = new CustomButton("Register", releasedImg, pressedImg, 20, Color.WHITE);
        guestButton = new CustomButton("Guest", releasedImg, pressedImg, 20, Color.WHITE);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pwd = String.valueOf(passwordTArea.getPassword());
                if (databaseConnector.authenticateUser(usernameTArea.getText(), pwd)) {
                    JOptionPane.showMessageDialog(LogInPanel.this,
                            "Success! You typed the right password.");
                } else {
                    JOptionPane.showMessageDialog(LogInPanel.this,
                            "Invalid username or password. Try again.",
                            "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pwd = String.valueOf(passwordTArea.getPassword());
                if (databaseConnector.addUser(usernameTArea.getText(), pwd)) {
                    JOptionPane.showMessageDialog(LogInPanel.this,
                            "Success! You registered a new account.");
                } else {
                    JOptionPane.showMessageDialog(LogInPanel.this,
                            "Username is taken. Please try a different username.",
                            "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //initializing panels
        usernamePanel = new JPanel(new FlowLayout());
        passwordPanel = new JPanel(new FlowLayout());
        buttonsPanel = new JPanel(new FlowLayout());
    }

    private void createGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //overall layout for this panel
        gbc.gridx = gbc.gridy = 0;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 200, 0);
        add(welcomeLabel, gbc);

        gbc.fill = 0;
        ++gbc.gridy;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(usernamePanel, gbc);

        ++gbc.gridy;
        gbc.insets = new Insets(0, 0, 150, 0);
        add(passwordPanel,gbc);

        ++gbc.gridy;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(buttonsPanel, gbc);

        //seting up subpanels (usernamePanel, passwordPanel, buttonsPanel), they are all flowlayout
        //usernamePanel, that is its label and text area
        usernamePanel.add(usernameLabel);
        usernamePanel.add(passwordLabel);

        //passwordPanel, that is its label and text area
        passwordPanel.add(usernameTArea);
        passwordPanel.add(spacemdfk);
        passwordPanel.add(passwordTArea);

        //buttonsPanel, that is all the buttons in this JPanel
        buttonsPanel.add(loginButton);
        buttonsPanel.add(registerButton);
        buttonsPanel.add(guestButton);

        //modifying components so they look good

        //setting UI Font
        font = MonopolyLibrary.getGameFont(Font.PLAIN, 50);
        welcomeLabel.setFont(font);
        font = MonopolyLibrary.getGameFont(Font.PLAIN, 30);
        usernameLabel.setFont(font);
        usernameTArea.setFont(font);
        passwordLabel.setFont(font);
        spacemdfk.setFont(font);
        loginButton.setFont(font);
        registerButton.setFont(font);
        guestButton.setFont(font);
        spacemdfk.setFont(font);

        //Changing components color
        welcomeLabel.setForeground(Color.WHITE);
        usernameLabel.setForeground(Color.WHITE);
        usernameTArea.setBackground(MonopolyLibrary.cardinalColor);
        usernameTArea.setForeground(MonopolyLibrary.goldColor);
        passwordLabel.setForeground(Color.WHITE);
        passwordTArea.setBackground(MonopolyLibrary.goldColor);
        passwordTArea.setForeground(MonopolyLibrary.cardinalColor);

        //setting subpanels background to transparent
        usernamePanel.setOpaque(false);
        passwordPanel.setOpaque(false);
        buttonsPanel.setOpaque(false);

        //changing input area dimensions
        FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(font);
        int height = fm.getHeight();
        System.out.println("font size = " + height);
        usernameTArea.setPreferredSize(new Dimension(150, height + 2));
        passwordTArea.setPreferredSize(new Dimension(150, height + 2));
    }

    private void createEvents() {
        loginButton.addActionListener(nextPanelAction);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}








