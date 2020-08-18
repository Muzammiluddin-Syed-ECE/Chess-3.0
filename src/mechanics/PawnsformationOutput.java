package mechanics;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PawnsformationOutput extends JPanel{
	JButton knight, bishop, rook, queen;
	Piece pawnsformation;
	public PawnsformationOutput(Tile tile, Piece somePiece) {
		JButton knight, bishop, rook, queen;
		knight = new JButton("Knight");
		knight.addActionListener(new buttonListener(knight, tile, somePiece, this));
		bishop = new JButton("Bishop");
		bishop.addActionListener(new buttonListener(bishop, tile, somePiece, this));
		rook = new JButton("Rook");
		rook.addActionListener(new buttonListener(rook, tile, somePiece, this));
		queen = new JButton("Queen");
		queen.addActionListener(new buttonListener(queen, tile, somePiece, this));
		this.setBackground(Color.pink);
		this.add(knight);
			
		this.add(bishop);
		this.add(rook);
		this.add(queen);
		this.setVisible(true);
		this.setOpaque(true);
	}
	public class buttonListener implements ActionListener{
		JButton button;
		Tile tile;
		Piece somePiece;
		PawnsformationOutput thisone;
		public buttonListener (JButton button, Tile _tile, Piece somePiece, PawnsformationOutput thisone) {
			this.button = button;
			this.tile = _tile;
			this.somePiece = somePiece;
			this.thisone = thisone;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Piece temp = this.tile.pieceOnTile;
			if(this.button.getText().equals("Knight")) {
				new Knight(3, this.tile, true, somePiece.player).forceTo(this.tile);
				this.thisone.pawnsformation = new Knight(3, this.tile, true, somePiece.player);	
			} else if(this.button.getText().equals("Bishop")) {
				new Bishop(3, this.tile, true, somePiece.player).forceTo(this.tile);
				this.thisone.pawnsformation = new Bishop(3, this.tile, true, somePiece.player);
			} else if(this.button.getText().equals("Rook")) {
				new Rook(5, this.tile, true, somePiece.player).forceTo(this.tile);
				this.thisone.pawnsformation = new Rook(5, this.tile, true, somePiece.player);
			} else if(this.button.getText().equals("Queen")) {
				new Queen(9, this.tile, true, somePiece.player).forceTo(this.tile);
				this.thisone.pawnsformation = new Queen(9, this.tile, true, somePiece.player);
			}
			this.button.setBackground(Color.RED);
			if (somePiece.player.number == 0){			
				somePiece.player.myPieces[somePiece.originalLocation.get_x()][7-somePiece.originalLocation.get_y()] = this.tile.pieceOnTile;
			}if (somePiece.player.number == 1){
				somePiece.player.myPieces[somePiece.originalLocation.get_x()][somePiece.originalLocation.get_y()] = this.tile.pieceOnTile;
			}
		}
	}
}