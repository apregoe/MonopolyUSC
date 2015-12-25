package MonopolyServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.io.PrintWriter;
	import java.net.ServerSocket;
	import java.net.Socket;
import java.util.Vector;
import MonopolyGame.SinglePlayer;

public class MonopolyServer extends Thread {

	private ServerSocket ss;
	private Vector<ClientThread> threads;
	private int players;
	private boolean allConnected;
	int newCon;
	private Vector<SinglePlayer> playerObjectsConnected;

	public MonopolyServer(int port, int players){
		allConnected = false;
		this.players = players;
		try {
			ss = new ServerSocket(port);
			threads = new Vector<ClientThread>();
			int i = 0;
			while(i < players){
				Socket s = ss.accept();
				System.out.println("Connected: " + s.getInetAddress());
				ClientThread ct = new ClientThread(s, this, i);
				threads.add(ct);
				ct.start();
				i++;
			}
			allConnected = true;
			System.out.println("All players connected!");

		} catch(IOException e){
			System.out.println("ioe from GameServer: " + e.getMessage());
		}

		playerObjectsConnected = new Vector<SinglePlayer>();
	}

	public void run(){
		while(true){
			try {
				synchronized(this){
					this.wait();
				}
				Socket s = ss.accept();
				System.out.println("Connected: " + s.getInetAddress());
				ClientThread ct = new ClientThread(s, this, newCon);
				threads.add(ct);
				ct.start();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void newConneciton(int i){
		newCon = i;
		System.out.println("One of the players disconnected. Wating on new player...");
		this.notify();
	}

	public boolean checkAllConnected(){
		return allConnected;
	}

	public int getNumberOfPlayers(){
		return players;
	}

	public void sendMessageToAllClients(String message, ClientThread sendingThread){
		System.out.println(message + " ... Message sent to all by server");
		for(ClientThread ct : threads){
			if(sendingThread != ct){
				ct.sendMessage(message);
			}
		}
	}

	public void addPlayerObject( SinglePlayer p ) {
		playerObjectsConnected.add( p );
	}

	public Vector<SinglePlayer> getPlayerObjectsConnected() {
		return playerObjectsConnected;
	}
}