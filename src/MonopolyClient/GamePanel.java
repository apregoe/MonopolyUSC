package MonopolyClient;

import MonopolyGame.*;
import MonopolyLibrary.MonopolyLibrary;
import MonopolyUI.CustomButton;
import MonopolyUI.CustomPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.*;

/*
 * GamePanel
 * Panel to hold the graphical game
 * */
public class GamePanel extends JPanel {
    private static final long serialVersionUID = 1593344194657112518L;

    //A grid to hold all the tiles in the game
    private final static int boardSize = 11;
    private final TilePanel[][] tileGrid;

    //The game manager that runs the actual logic
    private final GameManager gameManager;

    //The way to exit the game menu
    private final ActionListener mQuitAction;

    private CustomButton diceButton;
    private PlayerInfoFrame playerInfoFrame;

    public GamePanel(ActionListener inQuitAction, GameManager gameManager, Image backgroundImage) throws IOException {//, GameManager inGameManager){
        mQuitAction = inQuitAction;
        this.gameManager = gameManager;

        //Create the GUI to be a grid for all the tiles
        setLayout(new GridLayout(11, 11));
        tileGrid = new TilePanel[11][11];

        for(int y = 0; y < boardSize; ++y){
            for (int x = 0; x < boardSize; ++x){
                //if its the diceButton
                if((x == 5) && (y == 5)){
                    diceButton = new CustomButton(ImageIO.read(new File(MonopolyLibrary.imagePath + "specialLocations/dice.jpeg")),
                            ImageIO.read(new File(MonopolyLibrary.imagePath + "specialLocations/dice.jpeg")));
                    diceButton.addActionListener( new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gameManager.rollDice();
                        }
                    });
                    add(diceButton);
                }else{
                    tileGrid[x][y] = new TilePanel(gameManager.getTile(x,y));
                    add(tileGrid[x][y]);
                }
            }
        }


        diceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameManager.rollDice();
            }
        });

        //This is used to make sure the GameManager can redraw the GUI
        gameManager.setGamePanel(this);
//redraw();
    }

    public void showPlayerInfo() {
        //Create a new MonClientWindow
        playerInfoFrame = new PlayerInfoFrame(GameHelpers.getRoadImage(0)
                        , new SinglePlayer(
                            new GamePawn(0,0, GameHelpers.getTokenImage(0)
                                , GameHelpers.getTokenName(0)), "test", 1500));
    }

    public void startInGo() {
        CustomPanel pawnOne = new CustomPanel( gameManager.getMainPlayer().getPawn().getImage() );
        CustomPanel pawnTwo = new CustomPanel( gameManager.getOtherPlayer().getPawn().getImage() );
        tileGrid[10][10].add(pawnOne);
        tileGrid[10][10].add(pawnTwo);
    }

    /*
public void redraw() {
for(TilePanel row[] : tileGrid) {
for(TilePanel tp : row) {
tp.update();
}
}
revalidate();
repaint();
}
*/
    //Each tile is a square in the grid, it can be null to hold a blank square
    class TilePanel extends JPanel {
        private static final long serialVersionUID = -9071191204545371340L;

        private Tile mTile;
        private Stack<Component> components;
        private boolean mDrawBack;

        private CustomPanel pawnOne = new CustomPanel(null);
        private CustomPanel pawnTwo = new CustomPanel(null);
        private boolean pawnDisplayed = false;
        private BufferedImage background;

        TilePanel(Tile tile)  {
            this.mTile = tile;
            //Used to keep track what component should be displayed
            components = new Stack<Component>();

            //If we are a meaningful tile in the game
            if( mTile != null) {
                mDrawBack = true;
                if((mTile.getX() == 10) && (mTile.getY() == 6)){
                    System.out.println("mTile name: " + mTile.getName());
                }
                if(mTile.getName().equals(GameHelpers.getControlName(0))){//Go
                    setImage(GameHelpers.getControlImages(0));
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(0))){//doheny library
                    setImage(GameHelpers.getPropertyImage(0));

                }else if(mTile.getName().equals("Student Chest")){//chest in south TODO add in GameHelper an instance of chest name
                    setImage(GameHelpers.getStudentChestImage());
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(1))){//leavy library
                    setImage(GameHelpers.getPropertyImage(1));
                }else if(mTile.getName().equals(GameHelpers.getControlName(1))){//TuitionDue
                    setImage(GameHelpers.getControlImages(1));
                }else if(mTile.getName().equals(GameHelpers.getRoadName(0))) {//exposition
                    setImage(GameHelpers.getRoadImage(0));
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(2))) {//Webb Tower
                    setImage(GameHelpers.getPropertyImage(2));
                }else if(mTile.getName().equals("Chance")) {//Chance in south TODO add in GameHelpers an instance of Chance name
                    setImage(GameHelpers.getChanceImage());
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(3))) {//Cardinals Garden
                    setImage(GameHelpers.getPropertyImage(3));
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(4))) {//Troy east
                    setImage(GameHelpers.getPropertyImage(4));
                }else if(mTile.getName().equals(GameHelpers.getControlName(2))) {//SJACS / just visiting
                    setImage(GameHelpers.getControlImages(2));
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(5))) {//Cromwell Field
                    setImage(GameHelpers.getPropertyImage(5));
                }else if(mTile.getName().equals(GameHelpers.getControlName(3))) {//Campus crusier
                    setImage(GameHelpers.getControlImages(3));
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(6))) {//IM Field
                    setImage(GameHelpers.getPropertyImage(6));
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(7))) {//Lyon Center
                    setImage(GameHelpers.getPropertyImage(7));
                }else if(mTile.getName().equals(GameHelpers.getRoadName(1))) {//Vermont st
                    setImage(GameHelpers.getRoadImage(1));
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(8))) {//Tommy's Place
                    setImage(GameHelpers.getPropertyImage(8));
                }else if(mTile.getName().equals("Student Chest")) {// Chest in left TODO add in GameHelper an instance of chest name
                    setImage(GameHelpers.getStudentChestImage());
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(9))) {//Ground 0
                    setImage(GameHelpers.getPropertyImage(9));
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(10))) {//Bovard Administration
                    setImage(GameHelpers.getPropertyImage(10));
                }else if(mTile.getName().equals(GameHelpers.getControlName(4))) {//Free parking
                    setImage(GameHelpers.getControlImages(4));
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(11))) {//EVK
                    setImage(GameHelpers.getPropertyImage(11));
                }else if(mTile.getName().equals("Chance")) {//Chance in north TODO add in GameHelpers an instance of Chance name
                    setImage(GameHelpers.getChanceImage());
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(12))) {//Cafe 84
                    setImage(GameHelpers.getPropertyImage(12));
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(13))) {//Parkside
                    setImage(GameHelpers.getPropertyImage(13));
                }else if(mTile.getName().equals(GameHelpers.getRoadName(2))) {//Jeff Blv
                    setImage(GameHelpers.getRoadImage(2));
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(14))) {//EEB
                    setImage(GameHelpers.getPropertyImage(14));
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(15))) {//SAL
                    setImage(GameHelpers.getPropertyImage(15));
                }else if(mTile.getName().equals(GameHelpers.getControlName(5))) {//USC Bookstore
                    setImage(GameHelpers.getControlImages(5));
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(16))) {//RTH
                    setImage(GameHelpers.getPropertyImage(16));
                }else if(mTile.getName().equals(GameHelpers.getControlName(6))) {//Go to SJACS
                    setImage(GameHelpers.getControlImages(6));
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(17))) {//Alumni house
                    setImage(GameHelpers.getPropertyImage(17));
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(18))) {//Fisher House
                    setImage(GameHelpers.getPropertyImage(18));
                }else if(mTile.getName().equals("Student Chest")) {//Chest in right TODO add in GameHelpers an instance of Student Chest name
                    setImage(GameHelpers.getStudentChestImage());
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(19))) {//Trophy Room
                    setImage(GameHelpers.getPropertyImage(19));
                }else if(mTile.getName().equals(GameHelpers.getRoadName(3))) {//Figueroa St
                    setImage(GameHelpers.getRoadImage(3));
                }else if(mTile.getName().equals("Chance")) {//Chance in right TODO add in GameHelpers an instance of Chance name
                    setImage(GameHelpers.getChanceImage());
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(20))) {//USC Village
                    setImage(GameHelpers.getPropertyImage(20));
                }else if(mTile.getName().equals(GameHelpers.getControlName(7))) {//Mandatory fee
                    setImage(GameHelpers.getControlImages(7));
                }else if(mTile.getName().equals(GameHelpers.getPropertyName(21))) {//Memorial Coliseum
                    setImage(GameHelpers.getPropertyImage(21));
                }
                //TODO The tiles missing are the two buttons in the middle, the student chest and chance
            }
        }

        @Override
        public Component add(Component c){
            super.add(c);

            return c;
        }

        //Update the tile based on its properties
        protected void update() {

        }

        @Override
        protected void paintComponent(Graphics g){
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
        public void setImage(Image inImage) {
            background = (BufferedImage)inImage;
        }

    }

    @Override
    protected void paintComponent(Graphics g){
        BufferedImage ttbackground = null;
        try {
            ttbackground = ImageIO.read(new File("src/images/panels/tommy_trojan_background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(ttbackground, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    class PlayerInfoFrame extends JFrame{
        public PlayerInfoFrame(Image inImage, SinglePlayer player) {
            setMinimumSize(new Dimension(200,100));
            setMaximumSize(new Dimension(200,100));
            this.setTitle("My info table");
            add(new PlayerInfoPanel(inImage, player));

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image image = toolkit.getImage(MonopolyLibrary.imagePath + "cursor/twoFingerCursor2.png");
            Cursor c = toolkit.createCustomCursor(image.getScaledInstance(20,25,Image.SCALE_SMOOTH) , new Point(0,
                    0), "img");
            this.setCursor(c);
        }

        class PlayerInfoPanel extends CustomPanel{
            SinglePlayer player;
            ImageIcon tokenImage;
            JLabel moneyLabel;
            int moneyAmount;
            public PlayerInfoPanel(Image inImage, SinglePlayer player) {
                super(inImage);

                this.player = player;
                setPreferredSize(new Dimension(200, 100));

                intializeComponents();
                createGUI();
                setupEvents();
            }
            private void intializeComponents() {
                tokenImage = new ImageIcon(player.getPawn().getImage());
                moneyAmount = player.getMoney();
                moneyLabel = new JLabel();
                moneyLabel.setText("$" + Integer.toString(moneyAmount));
            }

            private void createGUI() {
                setLayout(new GridBagLayout());

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                this.add(new JLabel(tokenImage), gbc);

                gbc.gridx = 1;
                gbc.gridy = 0;
                this.add(moneyLabel, gbc);
            }

            private void setupEvents() {

            }
        }
    }
}











