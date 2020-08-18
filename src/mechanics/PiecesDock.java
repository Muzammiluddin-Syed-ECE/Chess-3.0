package mechanics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PiecesDock extends JPanel{
	int player;
	Piece[] pieces = new Piece[16];
	JPanel[] dock = new JPanel[16];
	JLabel[] dockImages = new JLabel[16];
	int numberOfPieces = 0;
	Color background;
	//static boolean update = true;
	public PiecesDock (int player) {
		this.player = player;
		this.background = (Color.WHITE);
		if (player == 1) {
			this.background = (Color.black);
		}
		this.setBackground(background);
		for (int i = 0; i < 16; i++) {
			this.dock[i] = new JPanel();
			this.dockImages[i] = new JLabel(new ImageIcon(new BufferedImage(31, 31, BufferedImage.TYPE_INT_ARGB)));
			this.dock[i].add(this.dockImages[i]);
			this.dock[i].setPreferredSize(new Dimension(32,32));
			this.add(dock[i]);
		}
		
	}
	public void addPiece(Piece somePiece) {
		this.pieces[numberOfPieces] = somePiece;
		this.dockImages[numberOfPieces].setIcon(new ImageIcon (this.pieces[numberOfPieces].piece.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH)));
		//this.dock[numberOfPieces].add(this.dockImages[numberOfPieces]);
		numberOfPieces++;
	}
	public void removePiece() {
			numberOfPieces--;
			this.pieces[numberOfPieces] = null;
			this.dockImages[numberOfPieces].setIcon(new ImageIcon(new BufferedImage(31, 31, BufferedImage.TYPE_INT_ARGB)));
			//this.dock[numberOfPieces] = null;
			//this.dock[numberOfPieces] = new JPanel();
			//this.dock[numberOfPieces].setPreferredSize(new Dimension(32,32));
			//this.dockImages[numberOfPieces] = new JLabel (new ImageIcon(new BufferedImage(31, 31, BufferedImage.TYPE_INT_ARGB)));
		
	}
	
	
}