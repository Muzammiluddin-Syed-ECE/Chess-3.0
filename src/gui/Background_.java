package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Background_ extends JPanel{
	public Background_ () {
		super();
	}
	@Override
	public void paintComponent(Graphics g) {
		new ImageIcon(new ImageIcon("./res/wood _texture3154.jpg").getImage().getScaledInstance(1400, 800, Image.SCALE_FAST)).paintIcon(this, g, 0, 0);;
	}
	
}
