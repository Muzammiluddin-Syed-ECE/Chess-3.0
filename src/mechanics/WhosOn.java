package mechanics;

import java.awt.Color;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import mechanics.Boards.buttonListener;

public class WhosOn extends JPanel {
	
	int player;
	Color background1 = Color.WHITE;
	Color background2 = Color.BLACK;
	JButton temp = new JButton();
	public WhosOn(){
		temp.setIcon(new ImageIcon(new BufferedImage(65, 65, BufferedImage.TYPE_INT_ARGB)));
		temp.setMargin(new Insets(0,0,0,0));
		temp.setBorderPainted(false);
		this.setBorder(new LineBorder(new Color(226, 196, 163), 10));
		temp.addActionListener(null);
		temp.setOpaque(true);
		temp.setBackground(background1);
		this.add(temp);
		this.setVisible(true);
		this.setBackground(background1);
	}
	public void refresh (int turn) {
		if (turn %2 == 0) {
			this.setBackground(background1);
			this.temp.setBackground(background1);
		} else {
			this.setBackground(background2);
			this.temp.setBackground(background2);
		}
	}
	
}