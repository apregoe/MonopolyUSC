package MonopolyGame;

import MonopolyClient.GamePanel;
 import MonopolyGame.Constants;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Vector;

public class GameManager {

    // Game components
    private GameBoard gameBoard;
    private StudentChestDeck scDeck;
    private ChanceDeck cDeck;

    // Game control for dice roll
    private Die die1;
    private Die die2;
    private int diceValue;

    // Player information
    private Vector<SinglePlayer> livePlayers;
    private int numCurrentPlayers;
    private int mainPlayer = 0;
    public SinglePlayer getMainPlayer() { return livePlayers.elementAt(mainPlayer); }
    public SinglePlayer getOtherPlayer() { return livePlayers.elementAt(1); }
    private SinglePlayer currentPlayer;

    // do we need a Card instance?

    private boolean gameOver = false;
    public boolean isGameOver() {return gameOver; }

    private String winnerName = null;
    public String getWinner() { return winnerName; }

    private Tile currentTile;

    private GamePanel gamePanel;

    // load in all the game components
    {
        gameBoard = new GameBoard( new File( Constants.GAME_FILE_PATH ) );
        scDeck = new StudentChestDeck( new File( Constants.STUDENT_CHEST_FILE_PATH));
        cDeck = new ChanceDeck( new File( Constants.CHANCE_FILE_PATH ) );
        die1 = new Die();
        die2 = new Die();
    }

    public void setup( int numPlayers, Vector<SinglePlayer> playersFromFrontEnd ) {
        numCurrentPlayers = numPlayers;
        /*
        for ( int i = 0; i < numCurrentPlayers; i++ ) {
            livePlayers[i] = playersFromFrontEnd.elementAt( i );
        }
        */
        livePlayers = playersFromFrontEnd;
        currentPlayer = playersFromFrontEnd.elementAt( 0 );

        //for ( int i = 0; i < numCurrentPlayers; i++ ) {
        //    livePlayers.elementAt(i).setCoords(10, 10);
        //}

    }

    public Tile getTile( int x, int y ) {
        return gameBoard.getTile( x, y );
    }

    // host is position 0
    // other is position 1

    public void rollDice() {
        diceValue = die1.rollDie() + die2.rollDie();
        System.out.println("Dice roll: " + diceValue);
        JOptionPane.showMessageDialog( null, diceValue, "Dice Roll", JOptionPane.PLAIN_MESSAGE );


    //    if(currentPlayer.takeTurnFromJail()){
    //        diceValue = 0;
    //        endTurn();
    //    }else{)
            Tile t = move(gameBoard.getTile(currentPlayer.getX(), currentPlayer.getY()),
                    currentPlayer.getPawn(), diceValue);
            tileCheck(t);
            endTurn();
    //    }
    }

    private void endTurn() {
        mainPlayer = (++mainPlayer % numCurrentPlayers);
    }

    public boolean tileClicked( Tile tile, SinglePlayer playerClick ) {
        return true;
    }

    private Tile move( Tile start, GamePawn g, int numSpaces ) {

        for ( int i = 0; i < numSpaces; i++ ) {
            start = start.getNext();
        }
        g.setCoords( start.getX(), start.getY() );

        return start;
    }

    private void tileCheck( Tile currentTile ) {
        MonopolyObject mo = currentTile.getMonopolyObject();

        if ( mo instanceof Property ) {
            System.out.println( "We've got a Property" );
            Property p = (Property)mo;
            if ( !p.isOwned() ) {
                System.out.println( "You can buy this property." );
                int purchaseOption = JOptionPane.showConfirmDialog( null,
                            "Would you like to purchase this property?",
                            "Monopoly",
                            JOptionPane.YES_NO_OPTION );
                if ( purchaseOption == JOptionPane.YES_OPTION ) {
                    System.out.println( "Yes option" );
                    currentPlayer.purchase( p );
                } else {
                    System.out.println( "No option - keep playing the game" );
                }
            } else {
                SinglePlayer owner = p.getOwner();
                int value = p.getBasicRent();
                if ( currentPlayer.getAccountAmount() - value < 0 ) {
                    deactivatePlayer(currentPlayer);
                } else {
                    currentPlayer.deductFromAccount( value );
                    owner.addToAccount( value );
                }
            }
        } else if ( mo instanceof Control ) {
            System.out.println( "We've got a Control" );
            // Display card
            // Execute command
        } else if ( mo instanceof Road ) {
            System.out.println( "We've got a Road" );
            Road r = (Road)mo;
            if ( !r.isOwned() ) {
                System.out.println( "You can buy this Road" );
                int purchaseOption = JOptionPane.showConfirmDialog( null,
                        "Would you like to purchase this property?",
                        "Monopoly",
                        JOptionPane.YES_NO_OPTION );
                if ( purchaseOption == JOptionPane.YES_OPTION ) {
                    System.out.println( "Yes option" );
                    currentPlayer.purchase( r );
                } else {
                    System.out.println( "No option - keep playing the game" );
                }
            } else {
                SinglePlayer owner = r.getOwner();
                int value = r.getBasicRent();
                if ( currentPlayer.getAccountAmount() - value < 0 ) {
                    deactivatePlayer(currentPlayer);
                } else {
                    currentPlayer.deductFromAccount( value );
                    owner.addToAccount( value );
                }
            }
        } else if ( mo instanceof StudentChest ) {
            System.out.println( "We've got a StudentChest" );
            // Display card
            // Execute Command
        } else if ( mo instanceof Chance ) {
            System.out.println( "We've got a Chance" );
            // Display card
            // Execute command
        } else if (mo instanceof Jail) {
            return;
        }else{
            System.out.println( "Unrecognized component" );
            // Display error!
        }

    }



    private void setBoard() {

    }

    public void deactivatePlayer(SinglePlayer p){
        numCurrentPlayers--;
        p.releaseProperties();

        JOptionPane.showMessageDialog(null,
                "You lost! Have a great day!",
                "Thanks for Playing USC Monopoly", JOptionPane.ERROR_MESSAGE);

    }

    public void setGamePanel( GamePanel inGamePanel ) {
        gamePanel = inGamePanel;
    }

}