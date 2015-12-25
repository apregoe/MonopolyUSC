package MonopolyClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import MonopolyLibrary.MonopolyLibrary;
import MonopolyUI.ClearPanel;
import MonopolyUI.CustomButton;
import MonopolyUI.CustomPanel;
import MonopolyUI.CustomRadioButton;

public class PlayerSelectPanel extends CustomPanel {
	private static final long serialVersionUID = -4510696620583889943L;
	private final ClientPanel clientPanel;

	private int selection = -1;
	private final int numOptions = 3;
	private CustomRadioButton[] optionButtons;
	private ButtonGroup buttonGroup;
	private JLabel selectPlayerLabel;
	private ActionListener gameListener;
	private ClearPanel buttonPanel;

	private JButton confirmButton;

	private final String selectNumPlayerString = "Select the number of players";

	private static final Insets spacing = new Insets(60,80,60,80);

	public int getNumberOfPlayers() {
		return selection;
	}

	public PlayerSelectPanel(ActionListener gameListener, Image inImage, ClientPanel clientPanel){
		super(inImage,true);
		this.gameListener = gameListener;
		this.clientPanel = clientPanel;
		initializeComponents();
		setupGUI();
		setupEvents();
	}

	private void initializeComponents() {
		confirmButton = new CustomButton(
				"Confirm",
				MonopolyLibrary.getImage(MonopolyLibrary.imagePath + "buttons/buttons.jpg"),
				MonopolyLibrary.getImage(MonopolyLibrary.imagePath + "buttons/buttons_flipped.jpg"),
				40, Color.WHITE
		);
		selectPlayerLabel = new JLabel(selectNumPlayerString);

		buttonGroup = new ButtonGroup();
		optionButtons = new CustomRadioButton[numOptions];

		//creating radioButtons
		optionButtons[0] = new CustomRadioButton(
				MonopolyLibrary.getImage("src/images/jerseys/jersey2.png"),
				MonopolyLibrary.getImage("src/images/jerseys/jersey2_bw.png")
		);
		optionButtons[0].setPreferredSize(new Dimension(180,180));

		optionButtons[1] = new CustomRadioButton(
				MonopolyLibrary.getImage("src/images/jerseys/jersey3.png"),
				MonopolyLibrary.getImage("src/images/jerseys/jersey3_bw.png")
		);
		optionButtons[1].setPreferredSize(new Dimension(180,180));

		optionButtons[2] = new CustomRadioButton(
				MonopolyLibrary.getImage("src/images/jerseys/jersey4.png"),
				MonopolyLibrary.getImage("src/images/jerseys/jersey4_bw.png")
		);
		optionButtons[2].setPreferredSize(new Dimension(180,180));
	}

	private void setupGUI() {
		ClearPanel bottomPanel = new ClearPanel();
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.setBorder(new EmptyBorder(spacing));
		bottomPanel.add(Box.createGlue());
		bottomPanel.add(Box.createGlue());
		bottomPanel.add(confirmButton);

		ClearPanel topPanel = new ClearPanel();
		topPanel.setBorder(new EmptyBorder(spacing));
		topPanel.add(selectPlayerLabel);

		ClearPanel centerPanel = new ClearPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

		buttonPanel = new ClearPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));


		centerPanel.add(buttonPanel);

		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add(topPanel);
		add(centerPanel);
		add(bottomPanel);


		confirmButton.setFont(MonopolyLibrary.getGameFont(Font.BOLD, 22));

		selectPlayerLabel.setFont(MonopolyLibrary.getGameFont(Font.PLAIN, 35));
		selectPlayerLabel.setForeground(Color.WHITE);
	}

	private void setupEvents() {

		confirmButton.addActionListener(this.gameListener);

		ClearPanel numPanel = new ClearPanel();
		for(int i = 0; i < numOptions; ++i) {
			final int buttonSelection = i+2;
//optionButtons[i].setPreferredSize(new Dimension(180, 180));

			optionButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					selection = buttonSelection;
					confirmButton.setEnabled(true);
				}
			});
			buttonGroup.add(optionButtons[i]);
			numPanel.add(optionButtons[i]);
//JLabel numLabel = new JLabel(""+buttonSelection);
//numLabel.setBackground(new Color(0,0,0,0));
//numLabel.setOpaque(true);
//numLabel.setFont(Library.getFont("fonts/kenvector_future_thin.ttf", Font.PLAIN, 28));
//numPanel.add(numLabel);

			buttonPanel.add(numPanel);
		}
	}

	public ClientPanel getClientPanel() {
		return clientPanel;
	}
}