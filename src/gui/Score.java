package gui;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import mechanics.Player;

public class Score extends JLabel{
	int total;
	Player player;
	public Score (Player player) {
		this.player = player;
		this.setText("Score: 0");
		this.setBackground(Color.LIGHT_GRAY);
		this.setOpaque(true);
	}
	public void updateTotal() {
		int count = 0;
		this.total = 0;
		while (this.player.capturedPiece[count]!= null) {
			this.total += this.player.capturedPiece[count].value;
			count++;
		}

		this.setBackground(Color.LIGHT_GRAY);
	}
	public void update() {
		this.updateTotal();
		this.setText("Score: "+String.valueOf(this.total));
		this.setBackground(Color.LIGHT_GRAY);
	}
	
}
