package MonopolyClient;

import MonopolyGame.GameManager;
import MonopolyGame.SinglePlayer;
import MonopolyLibrary.MonopolyLibrary;
import MonopolyServer.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * This class is just like ClientPanel from sorry
 * it will manage all the other Panels as a cardlayout
 */
public class ClientPanel extends JPanel{
    private HostOrJoinPanel hostOrJoinPanel;
    private GamePanel gamePanel;
    private SelectTokenPanel selectTokenPanel;
    private PlayerSelectPanel playerSelectPanel;
    private Client player;
    private MonopolyServer server;

    //background Images
    private Image backgroundImage;
    private GameManager gameManager;

    private MonClientWindow monopolyClientWindow;
    private LogInPanel logInPanel;
    private HostPanel hostPanel;
    private JoinPanel joinPanel;
    private DatabaseConnector databaseConnector;
    private SinglePlayer clientPlayer;

    public ClientPanel(MonClientWindow monopolyClientWindow) {
        this.monopolyClientWindow = monopolyClientWindow;
        databaseConnector = new DatabaseConnector();

        //setting background images for UI

        backgroundImage = MonopolyLibrary.getImage(MonopolyLibrary.imagePath+"panels/tommy_trojan_background.jpg");
        

        //Instantiating LogInPanel, since it's the first panel
        logInPanel = new LogInPanel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(databaseConnector.authenticateUser(logInPanel.getUsername(), logInPanel.getPassword())) {
                    swapTo(hostOrJoinPanel);
                }
            }
        }, backgroundImage, true, databaseConnector);

        setLayout(new BorderLayout());
        add(logInPanel);
        try {
            refreshComponents();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void refreshComponents() throws IOException {
        gameManager = new GameManager();
        backgroundImage = MonopolyLibrary.getImage(MonopolyLibrary.imagePath+"panels/USC_01.png");
        hostOrJoinPanel = new HostOrJoinPanel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapTo(hostPanel);
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapTo(joinPanel);
            }
        }, backgroundImage);

        joinPanel = new JoinPanel(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingWorker<Void, Void> playerStarter = new SwingWorker<Void, Void>(){
                    @Override
                    protected Void doInBackground() throws Exception {
                        player = new Client(joinPanel.getIp(), joinPanel.getPort(), logInPanel.getUsername());
                        player.setClientPanel(joinPanel.getClientPanel());
                        selectTokenPanel.setClient(player);
                        player.start();
                        return null;
                    }
                };
                playerStarter.execute();
                swapTo(selectTokenPanel);
            }
        }, backgroundImage, this);

        hostPanel = new HostPanel(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                swapTo(playerSelectPanel);
            }
        }, backgroundImage);

        selectTokenPanel = new SelectTokenPanel(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                clientPlayer = new SinglePlayer( selectTokenPanel.getPawn(), player.getName(), 1500 );
                player.setGameManager(gameManager);
                player.setPlayer(clientPlayer);
                gamePanel.showPlayerInfo();
                player.startUpGM();
                gamePanel.startInGo();
                swapTo(gamePanel);
            }
        }, backgroundImage);

        playerSelectPanel = new PlayerSelectPanel(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int portNumber = hostPanel.getPort();
                //start server
                SwingWorker<Void, Void> serverStarter = new SwingWorker<Void, Void>(){
                    @Override
                    protected Void doInBackground() throws Exception {
                        server = new MonopolyServer(portNumber, playerSelectPanel.getNumberOfPlayers());
                        server.start();
                        return null;
                    }
                };
                serverStarter.execute();

                //start client
                SwingWorker<Void, Void> hostPlayerStarter = new SwingWorker<Void, Void>(){
                    @Override
                    protected Void doInBackground() throws Exception {
                        player = new Client("localhost", portNumber, logInPanel.getUsername());
                        player.setClientPanel(playerSelectPanel.getClientPanel());
                        selectTokenPanel.setClient(player);
                        clientPlayer = new SinglePlayer( selectTokenPanel.getPawn(), player.getName(), 1500 );
                        player.setGameManager(gameManager);
                        player.setPlayer(clientPlayer);
                        player.start();
                        return null;
                    }
                };
                hostPlayerStarter.execute();
                swapTo(selectTokenPanel);
            }
        }, backgroundImage, this);


        backgroundImage = MonopolyLibrary.getImage(MonopolyLibrary.imagePath+"panels/tommy_trojan_background.jpg");
        gamePanel = new GamePanel(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        }, gameManager, backgroundImage);
    }

    private void swapTo(JPanel panel){
        ClientPanel.this.removeAll();
        ClientPanel.this.add(panel);
        ClientPanel.this.revalidate();
    }
}