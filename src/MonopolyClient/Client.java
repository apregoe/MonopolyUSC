package MonopolyClient;
import MonopolyGame.GameHelpers;
import MonopolyGame.GameManager;
import MonopolyGame.GamePawn;
import MonopolyGame.SinglePlayer;
import MonopolyUI.CustomButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Client extends Thread {

	private BufferedReader br;
	private PrintWriter pw;
	private JTextPane receivedMessagesTextPane;
	DefaultStyledDocument doc;
	private CustomButton[] pawnButtons;
	private String[] pawnNames;
	private int confirmedPlayers;
	private String selectedPawn;

	private int numberOfPlayers;
	private boolean serverUp;
	private ClientPanel clientPanel;
	private String username;
	private SinglePlayer player;
	private GameManager gameManager;
	private Vector<SinglePlayer> players;
	//////////maybe owned properties storage

	public Client(String hostname, int port, String username) {
		try {
			Socket s = new Socket(hostname, port);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream());
			confirmedPlayers = 0;
			this.start();
		} catch(IOException ioe){
			System.out.println("ioe from Client: " + ioe.getMessage());
		}
		serverUp = true;
		this.username = username;
		players = new Vector<SinglePlayer>();
	}

	public void run(){
		try {
			while(true){
				////////////////maybe change to objectStream and check where it is an instance of a String
				String message = br.readLine();
				if(message == null){
					//clientPanel.startOver();
					/////////////////////////this is if the hosts disconnects
					break;
				}

				//number of players message
				else if(message.startsWith("#")){
					String numberMessage = message.substring(1);
					numberOfPlayers = Integer.parseInt(numberMessage);
				}
				//button change message
				else if(message.startsWith("S")){
					String buttonMessage = message.substring(1);
					changeButtons(buttonMessage);
				}

				//setup gamemanager
				else if(message.startsWith("~")){
					//parse string
					String pawnID = message.substring(1, 2);
					System.out.println(pawnID);
					String playerName = message.substring(2);
					System.out.println(playerName);
					GamePawn gamePawn2 = new GamePawn(10, 10, GameHelpers.getTokenImage(0), GameHelpers.getTokenName(0));
					SinglePlayer player2 = new SinglePlayer(gamePawn2, playerName, 1500);
					if(players.size() > 1){
						players.remove(1);
						players.add(player2);
					}

				}
				//other player move message
				else if(message.startsWith("@")){
					
				}

				/*
				//player disconnected
				else if(message.startsWith("X")){
					//Receiving player number and state
					String state = message.substring(3);
					String colorNum = message.substring(2, 3);
					String playerNum = message.substring(1, 2);
					
					int stateNum = Integer.parseInt(state);
					int playerSelectionNum = Integer.parseInt(colorNum);
					int disconnectedPlayer = Integer.parseInt(playerNum);

					if(stateNum == 0){
						pawnButtons[playerSelectionNum].setEnabled(true);
						sendMessage("!" + disconnectedPlayer);
					}
					else{
						String disMessage = "\n" + userNames[disconnectedPlayer] + " has left the game.";
						try {
							//send message
						} catch (BadLocationException e) {
							e.printStackTrace();
						}
					}
				}
				*/
				////////////////////////////kind of messages ready!
			}
		} catch(IOException ioe){
			System.out.println("ioe from Client run: " + ioe.getMessage());
		}
	}

	public int getNumberOfPlayers(){
		return numberOfPlayers;
	}

	private synchronized void changeButtons(String selection){
		//System.out.println(selection);

		//first selection
		if(selection.length() < 2){
			int index = Integer.parseInt(selection);
			//for(int i = 0; i < pawnButtons.length; i++){
			//if( !(pawnButtons[i].getName().equals(selectedPawn))){
			//	if(pawnButtons[i].getName().equals(selection)){
			pawnButtons[index].setEnabled(false);
			pawnButtons[index].setPressed();
			//	}
			//}
			return;
		}
		//first, second and third selection
		char newSelection = selection.charAt(0);
		char oldSelection = selection.charAt(1);
		int newSelectionNum = Character.getNumericValue(newSelection);
		int oldSelectionNum = Character.getNumericValue(oldSelection);
		System.out.println(newSelectionNum + "new selection");
		System.out.println(oldSelectionNum + "old selection");

		//	for(int i = 0; i < pawnButtons.length; i++){
		//		if( !(pawnButtons[i].getName().equals(selectedPawn))){
		//			if(pawnButtons[i].getName().equals(newSelection)){
		pawnButtons[newSelectionNum].setEnabled(false);
		pawnButtons[newSelectionNum].setPressed();
		//}
		//else if(pawnButtons[i].getName().equals(oldSelection)){
		pawnButtons[oldSelectionNum].setEnabled(true);
		pawnButtons[oldSelectionNum].setUnpressed();
		//}
		//}
		//}
	}


	public void setReceivedMessages(JTextPane receivedMessages){
		receivedMessagesTextPane = receivedMessages;
		doc = (DefaultStyledDocument) receivedMessagesTextPane.getDocument();
	}





	public void setPawnButtons(CustomButton[] pawnButtons, String[] names){
		this.pawnButtons = pawnButtons;
		pawnNames = names;
	}

	public void sendMessage(String message){
		pw.println(message);
		pw.flush();
	}

	public void setPawnSelection(String pawn){
		selectedPawn = pawn;
	}

	public void setClientPanel(ClientPanel client){
		clientPanel = client;
	}


	public void setState(int i) {
		sendMessage("$"+i);
	}


	public void setPawnSelectionNumber(int buttonSelection) {
		sendMessage("&"+buttonSelection);

	}

	public void setPlayer(SinglePlayer player) {
		this.player = player;
		players.add(this.player);
	}

	public void setGameManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	public void startUpGM(){
		gameManager.setup( numberOfPlayers, players );
	}
}