package MonopolyClient;

import MonopolyGame.GamePawn;
import MonopolyLibrary.MonopolyLibrary;
import MonopolyUI.CustomButton;
import MonopolyUI.CustomPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Select Pawn panel
 */
public class SelectTokenPanel extends CustomPanel{
    private JLabel selectPawnLabel;
    private String[] pawnNames = {"blimp", "football", "helmet", "segway", "sword", "torch", "traveller", "trojan"};
    private CustomButton[] pawnButtons;
    private CustomButton confirmButton;
    private ActionListener gameBoardListener;
    private Client player;
    private int selection;
    private Vector<Image> images;
    private GamePawn gamePawn;

    public SelectTokenPanel(ActionListener gameBoardListener,Image inImage) {
        super(inImage);
        this.gameBoardListener = gameBoardListener;

        images = new Vector<Image>();

        initializeComponents();
        setupGUI();
        setupEvents();
    }

    private void initializeComponents() {

        selectPawnLabel = new JLabel("Select your trojan token");
        pawnButtons = new CustomButton[8];

        //creating Images
        Image pressedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath + "pawns/blimp.png");
        Image releasedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath+"pawns/blimp_bw.png");
        pawnButtons[0] = new CustomButton(releasedImg, pressedImg);
        pawnButtons[0].setPreferredSize(new Dimension(200, 150));
        images.add( pressedImg );

        pressedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath + "pawns/football.png");
        releasedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath+"pawns/football_bw.png");
        pawnButtons[1] = new CustomButton(releasedImg, pressedImg);
        pawnButtons[1].setPreferredSize(new Dimension(150, 150));
        images.add( pressedImg );
        pressedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath + "pawns/helmet.gif");
        releasedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath+"pawns/helmet_bw.gif");
        pawnButtons[2] = new CustomButton(releasedImg, pressedImg);
        pawnButtons[2].setPreferredSize(new Dimension(150, 150));
        images.add( pressedImg );
        pressedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath + "pawns/segway.png");
        releasedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath+"pawns/segway_bw.png");
        pawnButtons[3] = new CustomButton(releasedImg, pressedImg);
        pawnButtons[3].setPreferredSize(new Dimension(150, 150));
        images.add( pressedImg );
        pressedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath + "pawns/sword.gif");
        releasedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath+"pawns/sword_bw.gif");
        pawnButtons[4] = new CustomButton(releasedImg, pressedImg);
        pawnButtons[4].setPreferredSize(new Dimension(200, 150));
        images.add( pressedImg );
        pressedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath + "pawns/torch.gif");
        releasedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath+"pawns/torch_bw.gif");
        pawnButtons[5] = new CustomButton(releasedImg, pressedImg);
        pawnButtons[5].setPreferredSize(new Dimension(150, 150));
        images.add( pressedImg );
        pressedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath + "pawns/traveller.gif");
        releasedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath+"pawns/traveller_bw.gif");
        pawnButtons[6] = new CustomButton(releasedImg, pressedImg);
        pawnButtons[6].setPreferredSize(new Dimension(150, 150));
        images.add( pressedImg );
        pressedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath + "pawns/trojan.gif");
        releasedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath+"pawns/trojan_bw.gif");
        pawnButtons[7] = new CustomButton(releasedImg, pressedImg);
        pawnButtons[7].setPreferredSize(new Dimension(150, 200));
        images.add( pressedImg );
        //confirm Button
        releasedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath + "buttons/buttons.jpg");
        pressedImg = MonopolyLibrary.getImage(MonopolyLibrary.imagePath + "buttons/buttons_flipped.jpg");
        confirmButton = new CustomButton("Confirm", releasedImg, pressedImg, 30, Color.WHITE);
        selection = -1;
        }

    private void setupGUI() {
        //setting layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0,0,100,0);
        add(selectPawnLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0,0,0,10);
        add(pawnButtons[0], gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0,0,0,10);
        add(pawnButtons[1], gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0,0,0,10);
        add(pawnButtons[2], gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0,0,0,10);
        add(pawnButtons[3], gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0,0,80,10);
        add(pawnButtons[4], gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0,0,80,10);
        add(pawnButtons[5], gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0,0,80,10);
        add(pawnButtons[6], gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0,0,80,10);
        add(pawnButtons[7], gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        //gbc.anchor = GridBagConstraints.CENTER;
        add(confirmButton, gbc);
        //changing the size of every image

        Font font = MonopolyLibrary.getGameFont(Font.PLAIN, 40);

        confirmButton.setFont(font);
        selectPawnLabel.setFont(font);

        selectPawnLabel.setForeground(Color.WHITE);

        }

    private void setupEvents() {
        confirmButton.addActionListener(gameBoardListener);


        for(int i = 0; i < pawnButtons.length; i++){
            final int index = i;
            pawnButtons[i].addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {

                    int oldSelection = -1;
                    //first time selection
                    if(selection == -1){
                        selection = index;
                        String name = pawnNames[index];
                        System.out.println(name + " for slection == null");
                        player.setPawnSelection(name);
                        player.sendMessage("S" + index);
                        player.setPawnSelectionNumber(index);
                        pawnButtons[index].setPressed();
                        pawnButtons[index].setEnabled(false);
                        confirmButton.setEnabled(true);
                        gamePawn = new GamePawn(10, 10, images.elementAt(selection), name);
                        player.sendMessage("~"+gamePawn.getValue()+player.getName());

                        }

                    //second, third and so on selection
                    else{
                        oldSelection = selection;
                        selection = index;
                        player.setPawnSelection(pawnNames[index]);
                        player.setPawnSelectionNumber(index);
                        player.sendMessage("S" + selection + oldSelection);
                        gamePawn = new GamePawn(10, 10, images.elementAt(selection), pawnNames[selection]);
                        //player.updatePawnSelection(gamePawn);
                        pawnButtons[oldSelection].setEnabled(true);
                        pawnButtons[oldSelection].setUnpressed();
                        pawnButtons[selection].setEnabled(false);
                        pawnButtons[selection].setPressed();
                        player.sendMessage("~"+gamePawn.getValue()+player.getName());
                        }
                    }

                });
            }
        }

    public void setClient(Client client) {
        this.player = client;
        player.setPawnButtons(pawnButtons, pawnNames);
    }

    public GamePawn getPawn() {
        return gamePawn;
    }
}
