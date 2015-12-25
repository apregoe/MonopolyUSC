package MonopolyServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread{

	private Socket s;
	private BufferedReader br;
	private PrintWriter pw;
	private MonopolyServer gs;
	private int clientNumber;
	private int clientState;
	private int clientPawn;

	//remember this thread is only responsible for one connection, but the server is responsible for all the connections
	public ClientThread(Socket s, MonopolyServer gs, int Number){
		this.s = s;
		this.gs = gs;
		clientNumber = Number;
		String number = "#" + gs.getNumberOfPlayers();
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream());
			sendMessage(number);
		} catch(IOException ioe){
			System.out.println("ioe from ClientThread: " + ioe.getMessage());
		}
	}

	public void run() {
		try {
			while(true){
				String message = br.readLine();
				if(message == null){
					gs.sendMessageToAllClients("X"+ clientNumber + clientPawn + clientState, this);
					break;
				}
				/*
				//new connection message
				else if(message.startsWith("!")){
					String newCon = message.substring(1);
					int newPosition = Integer.parseInt(newCon);
					gs.newConneciton(newPosition);
					continue;
				}
				//current state message
				else if(message.startsWith("$")){
					String state = message.substring(1);
					clientState = Integer.parseInt(state);
					continue;
				}
				*/
				//pawn selection message
				else if(message.startsWith("&")){
					String state = message.substring(1);
					clientPawn = Integer.parseInt(state);
					continue;
				}
				/*
				//confirm notification
				else if(message.startsWith("~")){
					gs.sendMessageToAllClients(message, null);
				}
				*/
				else{
					gs.sendMessageToAllClients(message, this);
				}
			}
		} catch(IOException ioe){
			System.out.println("ioe in ClientThread run: " + ioe.getMessage());
		}
	}

	public void sendMessage(String message) {
		if(pw != null){
			pw.println(message);
			pw.flush();
		}

	}
}
