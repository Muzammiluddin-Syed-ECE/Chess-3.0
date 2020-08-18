package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartingButtons extends JButton {
	JPanel panel;
	boolean random;
	ImageIcon icon;
	public Rectangle theBounds;
	public StartingButtons (boolean random){
		//super(icon);
		this.setMargin(new Insets(0,0,0,0));
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		this.setBorder(null);
		this.setContentAreaFilled(false);
		this.random = random;
		if (random == true) {
			this.icon = new ImageIcon(new ImageIcon("./res/Random button.png").getImage().getScaledInstance(300,132,Image.SCALE_FAST));
			this.setBounds(50, 225, 300, 132);
			this.theBounds = new Rectangle(50, 225, 300, 132);
		} else {
			this.icon = new ImageIcon(new ImageIcon("./res/Start Button.png").getImage().getScaledInstance(300,132,Image.SCALE_FAST));
			this.setBounds(50, 357, 300, 132);
			this.theBounds = new Rectangle(50, 357, 300, 132);
		}
		this.setIcon(icon);
	}	
	//@Override
	//public void paintComponent(Graphics g) {
	//	this.icon.paintIcon(c, g, x, y);
	//}
}
