package MonopolyUI;

import java.awt.Color;
/*
 * This code was taken from
 */
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JRadioButton;

public class CustomRadioButton extends JRadioButton{

	private static final long serialVersionUID = -2379744418193606172L;
	private Image mImage;
	private final Image mSelected;
	private final Image mNotSelected;

	public CustomRadioButton(Image inSelected, Image inNotSelected) {
		mSelected = inSelected;
		mImage = mNotSelected = inNotSelected;
		setOpaque(false);
		//setContentAreaFilled(false);
		//setBorderPainted(false);
		//setForeground(Color.WHITE);
		//setBackground(Color.WHITE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		if(isSelected()) mImage = mSelected;
		else mImage = mNotSelected;
		g.drawImage(mImage, 0, 0, getWidth(), getHeight(), null);
	}
}
