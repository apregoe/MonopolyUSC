package MonopolyUI;

import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class CustomButton extends JButton{
	private static final long serialVersionUID = 7074393176L;

	private Image toDraw;
	protected final Image released;
	protected final Image pressed;
	protected String name;
	private final int mFontSize;
	protected Color fontColor = null;


	public CustomButton(String name, Image released, Image pressed, int inFontSize) {
		super(name);
		this.name = name;
		toDraw = this.released = released;
		this.pressed = pressed;
		mFontSize = inFontSize;
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				toDraw = pressed;
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				toDraw = released;
			}
		});
		
		//setOpaque(true);
		setContentAreaFilled(false);
		//setBackground(new Color(0,0,0,0));
	}

	//2nd constructor to specify Button font color, pretty useful!
	public CustomButton(String name, Image released, Image pressed, int inFontSize, Color fontColor){
		super(name);
		this.name = name;
		this.fontColor = fontColor;
		toDraw = this.released = released;
		this.pressed = pressed;
		mFontSize = inFontSize;

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				toDraw = pressed;
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				toDraw = released;
			}
		});

		//setOpaque(true);
		setContentAreaFilled(false);
		//setBackground(new Color(0,0,0,0));
	}

	public CustomButton(Image released, Image pressed){
		super("");
		this.name = "";
		toDraw = released;
		this.released = released;
		this.pressed = pressed;
		mFontSize = 0;
	}

	@Override
	protected void paintComponent(Graphics g) {
		//super.paintComponent(g);
		if(fontColor != null) {
			g.setColor(this.fontColor);
		}
		g.drawImage(toDraw, 0, 0, getWidth(), getHeight(), null);
		FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(this.getFont());
		int height = fm.getHeight();
		g.drawString(name, 20, height);
		g.setFont(this.getFont());
	}
	
	@Override
	protected void paintBorder(Graphics g) {}
	
	public String getName(){
		return this.name;
	}

	public void setPressed(){
		toDraw = pressed;
	}

	public void setUnpressed(){
		toDraw = released;
	}
}