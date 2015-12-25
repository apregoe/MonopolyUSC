package MonopolyClient;

import MonopolyLibrary.MonopolyLibrary;
import MonopolyUI.CustomButton;
import MonopolyUI.CustomPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This Panel will pop up when the user register or logs in.
 * Here he/she will be able to choose if he/she wants to join or host a game
 */
public class HostOrJoinPanel extends CustomPanel{

    private JLabel questionLabel;
    private CustomButton hostButton, joinButton;
    private ActionListener hostButtonAction, joinButtonAction;
    private Image inImage;

    /**
     * This class receives two listenes, one for joinButton and the other for hostButton.
     * when the user presses the joinButton or hostButton, this Panel will be swapped to the
     * JoinPanel or HostPanel.
     * */
    public HostOrJoinPanel(ActionListener hostButtonAction, ActionListener joinButtonAction, Image inImage){
        super(inImage);
        this.hostButtonAction = hostButtonAction;
        this.joinButtonAction = joinButtonAction;
        this.inImage = inImage;
        initializeComponents();
        setupGUI();
        setupEvents();
    }

    private void initializeComponents() {
        questionLabel = new JLabel("Do you wanna host or join a game?");

        Image releasedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath+"/buttons/buttons.jpg");
        Image pressedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath+"/buttons/buttons_flipped.jpg");
        hostButton = new CustomButton("HOST", releasedImg, pressedImg, 20, Color.WHITE);
        joinButton = new CustomButton("JOIN", releasedImg, pressedImg, 20, Color.WHITE);
    }

    private void setupGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.insets = new Insets(0,0,0,15);
        this.add(hostButton, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.insets = new Insets(0,15,0,0);
        this.add(joinButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,0,60,0);
        gbc.gridwidth = 2;
        this.add(questionLabel, gbc);

        //Getting game font
        Font font = MonopolyLibrary.getGameFont(Font.PLAIN, 40);

        //changing font to components
        questionLabel.setFont(font);
        questionLabel.setForeground(Color.WHITE);
        font = MonopolyLibrary.getGameFont(Font.PLAIN, 20);
        hostButton.setFont(font);
        joinButton.setFont(font);
    }

    private void setupEvents() {
        hostButton.addActionListener(hostButtonAction);
        joinButton.addActionListener(joinButtonAction);
    }
}


















