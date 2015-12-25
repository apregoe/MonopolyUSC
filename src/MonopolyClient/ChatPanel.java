package MonopolyClient;

import MonopolyUI.CustomButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import MonopolyUI.CustomButton;
import MonopolyUI.CustomButton;

public class ChatPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private String name;
	private Client player;
	private JTextArea toSendTextArea;
	private JTextPane receivedTextPane;
	private JButton sendButton;
	private JScrollPane receivedScrollPane;
	
	public ChatPanel(String Name, Client client){
		this.name = Name;
		player = client;
		//////////maybe trojan background and white font??? 
		//setBackground(Color.BLACK);
		
		////////what font????!
		Font customFont = null;
		try {
			customFont = Font.createFont( Font.TRUETYPE_FONT, new File("fonts/kenvector_future.ttf")).deriveFont(Font.BOLD, 15);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DefaultStyledDocument doc = new DefaultStyledDocument();
		receivedTextPane = new JTextPane(doc);
		receivedTextPane.setFont(customFont);
		receivedTextPane.setBackground(Color.BLACK);
		FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(customFont);
		int height = fm.getHeight();
		receivedTextPane.setPreferredSize(new Dimension(640, height*5));
		receivedTextPane.setAutoscrolls(false);
		DefaultCaret dc = (DefaultCaret)receivedTextPane.getCaret();
		dc.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		receivedScrollPane = new JScrollPane(receivedTextPane);
		receivedScrollPane.setPreferredSize(new Dimension(640, height*5));
		toSendTextArea = new JTextArea(1, 20);
		toSendTextArea.setOpaque(true);
		toSendTextArea.setPreferredSize(new Dimension(520, height));
		toSendTextArea.setFont(customFont);
		
		try{
			sendButton = new CustomButton(
				"Send",
				ImageIO.read(new File("images/buttons/buttons.jpg")),
				ImageIO.read(new File("images/buttons/buttons_flipped.jpg")),
				22
			);
		} catch(IOException ioe){
			ioe.printStackTrace();
		}
		
		//what font?????
		//sendButton.setFont(customFont);
		//sendButton.setSize(23, 17);
		
		sendButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(toSendTextArea.getText() == null) return;
				String message = name + ":" + toSendTextArea.getText();
				String messageWName = toSendTextArea.getText();
				//change color to name and add it to the message to be put in the JTextPane

				SimpleAttributeSet attSet = new SimpleAttributeSet();
				/////get some trojan looking fonts
				//StyleConstants.setFontFamily(attSet, customFont.getFontName());
				try{
					doc.insertString(doc.getLength(), messageWName, attSet);
				} catch (BadLocationException ble) {
					ble.printStackTrace();
				}
				
				//receivedTextArea.append("\n" + message);
				String ChatMessage = "C" + message;
				player.sendMessage(ChatMessage);
				toSendTextArea.setText("");
			}
		});
		
		setLayout(new BorderLayout());
		add(receivedScrollPane, BorderLayout.CENTER);
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 4;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		southPanel.add(toSendTextArea, gbc);
		gbc.gridwidth = 1;
		gbc.gridx = 5;
		gbc.gridy = 0;
		gbc.ipadx = 20;
		gbc.fill = GridBagConstraints.BOTH;
		southPanel.add(sendButton, gbc);
		
		add(southPanel, BorderLayout.SOUTH);
		player.setReceivedMessages(receivedTextPane);
		
	}
}