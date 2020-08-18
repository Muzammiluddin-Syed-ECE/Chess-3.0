package mechanics;

import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import gui.InitializeGui;

public class Tile extends JButton {
	public static Tile pressOne;
	public static Tile pressTwo;
	public static boolean pressedOnce;
	public static boolean noPresses = true;
	public static Tile[][] boardArray = new Tile[9][9];
	public static ArrayList<Moves> history = new ArrayList<Moves>();
	public static Tile[] allHighlightedMoves = new Tile[28];
	public static boolean random = true;
	int _x;
	int _y;
	public Color tileColour;
	boolean occupied;
	public Piece pieceOnTile;
	private boolean firstPress;
	public ImageIcon background;
	public Tile (String string) {
		super(string);
	}
	public Tile(int _x, int _y, Player one, Player two) {
		
		this._x = _x;
		this._y = _y;
		if (_x%2==_y%2) {
			this.tileColour = new Color(226, 196, 163);
		} else {
			this.tileColour = new Color(109, 65, 18);
		}
		this.setBackground(tileColour);
		if (random == false) {
			if(_y == 6) {
				setPiece(new Pawn(1, this, true, Boards.player1));
			}
			else if(_y == 7) {
				if(_x == 0 || _x == 7) {
					setPiece(new Rook(5, this, true, Boards.player1));
				}if(_x == 1 || _x == 6){
					setPiece(new Knight(3, this, true, Boards.player1));
				}if(_x == 2 || _x == 5){
					setPiece(new Bishop(3, this, true, Boards.player1));
				}if(_x == 3) {
					setPiece(new Queen(9, this, true, Boards.player1));
				}if(_x == 4) {
					setPiece(new King(40, this, true, Boards.player1));
					Boards.player1.kingLocation = this;
					Boards.player2.opponent.kingLocation = this;
				}
			} else if(_y == 1) {
				setPiece(new Pawn(1, this, true, Boards.player2));
			} else if(_y == 0) {
				if(_x == 0 || _x == 7) {
					setPiece(new Rook(5, this, true, Boards.player2));
				}if(_x == 1 || _x == 6){
					setPiece(new Knight(3, this, true, Boards.player2));
				}if(_x == 2 || _x == 5){
					setPiece(new Bishop(3, this, true, Boards.player2));
				}if(_x == 3) {
					setPiece(new Queen(9, this, true, Boards.player2));
				}if(_x == 4) { 
					setPiece(new King(40, this, true, Boards.player2));
					Boards.player2.kingLocation = this;
					Boards.player1.opponent.kingLocation = this;
				}
			} else {
				this.setIcon(new ImageIcon(new BufferedImage(65, 65, BufferedImage.TYPE_INT_ARGB)));
			}
		} else {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 2; j++) {
					if (_y > 5) {
						if (_y == 7 && _x == 4 )  {
							setPiece(new King(40, this, true, Boards.player1));
							Boards.player1.kingLocation = this;
							Boards.player2.opponent.kingLocation = this;
						} else if (Player.placementConditions1[_x][7-_y] instanceof Pawn) {
							setPiece(new Pawn(1, this, true, Boards.player1));
						} else if (Player.placementConditions1[_x][7-_y] instanceof Knight) {
							setPiece(new Knight(3, this, true, Boards.player1));
						} else if (Player.placementConditions1[_x][7-_y] instanceof Bishop) {
							setPiece(new Bishop(3, this, true, Boards.player1));
						} else if (Player.placementConditions1[_x][7-_y] instanceof Rook) {
							setPiece(new Rook(5, this, true, Boards.player1));
						} else if (Player.placementConditions1[_x][7-_y] instanceof Queen) {
							setPiece(new Queen(9, this, true, Boards.player1));
							}
					} else if (_y < 2) {
						if (_y == 0 && _x == 4 )  {
							setPiece(new King(40, this, true, Boards.player2));
							Boards.player2.kingLocation = this;
							Boards.player1.opponent.kingLocation = this;
						} else if (Player.placementConditions2[_x][_y] instanceof Pawn) {
							setPiece(new Pawn(1, this, true, Boards.player2));
						} else if (Player.placementConditions2[_x][_y] instanceof Knight) {
							setPiece(new Knight(3, this, true, Boards.player2));
						} else if (Player.placementConditions2[_x][_y] instanceof Bishop) {
							setPiece(new Bishop(3, this, true, Boards.player2));
						} else if (Player.placementConditions2[_x][_y] instanceof Rook) {
							setPiece(new Rook(5, this, true, Boards.player2));
						} else if (Player.placementConditions2[_x][_y] instanceof Queen) {
							setPiece(new Queen(9, this, true, Boards.player2));
						}
						
					} else {
						this.setIcon(new ImageIcon(new BufferedImage(65, 65, BufferedImage.TYPE_INT_ARGB)));
					}
				}
			}
		}
		boardArray[_x][_y] = this;
		
	}
	
	public void setPiece (Piece somePiece) {
		pieceOnTile = somePiece;
		occupied = true;
		this.setIcon(new ImageIcon (somePiece.piece.getImage().getScaledInstance(71,71,Image.SCALE_SMOOTH)));
		if (somePiece instanceof King) {
			somePiece.player.kingLocation = this;
		} 
		
		if (somePiece instanceof Pawn){
			Pawn temp = (Pawn) somePiece;
			if (somePiece.player.pawnsformation == this.get_y()&& somePiece.useEvolved == false&& somePiece.evolved == null){
				UserOutputs idk = new UserOutputs(new PawnsformationOutput (this, temp),"");
				idk.display();
				somePiece.useEvolved = true;
				this.history.get(Boards.turn-1).evolved = somePiece.useEvolved;
			}
			if (somePiece.unmoved) {
				somePiece.unmoved = false;
			}
		}
		this.setBorder(null);
		this.setBorderPainted(false);
		this.setOpaque(true);
	}
	public Piece getPiece () {
		return pieceOnTile;
		
	}
	public int get_x () {
		return this._x;
	}
	public int get_y () {
		return this._y;
	}
	public void removePiece() {
		
		occupied = false;
		this.pieceOnTile = null;
		this.setIcon(new ImageIcon(new BufferedImage(71, 71, BufferedImage.TYPE_INT_ARGB)));
		this.setText("");
		this.setBorder(null);
		this.setBorderPainted(false);
		this.setOpaque(true);
	}
	public void thisPressedOnce () {
		if (this.occupied) {
			this.firstPress = true;
			this.pressedOnce = true;
		}
	}
	public boolean getPressed() {
		return this.pressedOnce;
	}
	public boolean press(int turn) {
		boolean moved = false;
	
		if (Tile.noPresses && this.occupied) {
			if (this.pieceOnTile.player.number == turn%2) {
				Tile.pressOne = this;
				this.thisPressedOnce();
				this.pieceOnTile.highlightMoves();
				Tile.noPresses = false;
			}
		} else {
			if (Tile.noPresses == false) {
				int count = 0;
				while (Tile.allHighlightedMoves[count] != null) {
					Tile.allHighlightedMoves[count].removeHighlight();
					count++;
				}
				Tile.pressTwo = this;
				if (Tile.pressTwo == null) {
					System.out.println("FUUUUUUUCK");
				}
				if (Tile.pressOne.pieceOnTile.validMove(Tile.pressTwo)){
					moved = true;
					history.add( new Moves(turn,Tile.pressOne,this, Boards.player1.myPieces, Boards.player2.myPieces, Boards.player1.kingLocation.pieceOnTile.unmoved, Boards.player2.kingLocation.pieceOnTile.unmoved, false));
					history.get(turn).inWriting();
				}
				Tile.pressOne.pieceOnTile.moveTo(this);//***********************************
				if (moved) {
					if (Boards.turn>0 || Boards.turn == 0) {
					
						if (Piece.anyChecks(Boards.player1, false)) {
						
							if (Piece.anyChecks(Boards.player1, true)) {
							
								Boards.player1.checkMate = true;
								JOptionPane.showMessageDialog(null, "White king is in checkmate");
								JOptionPane.showMessageDialog(null, "Sorry " + Boards.ppl1 + " you lost.");
								InitializeGui.dispose(false);
							}
						}
						if (Piece.anyChecks(Boards.player2, false)) {
							
							if (Piece.anyChecks(Boards.player2, true)) {
							
								Boards.player2.checkMate = true;
								JOptionPane.showMessageDialog(null, "Black king is in checkmate");
								JOptionPane.showMessageDialog(null, "Sorry " + Boards.ppl2 + " you lost.");
								InitializeGui.dispose(false);
							}
						}
					}
				}
				
			}
			Tile.noPresses = true;
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				boardArray[i][j].firstPress = false;
			}
		}
		return moved;
		
	}
	public void undo(int turn){
		
		Boards.player1.myPieces = history.get(turn-1).p1Pieces;
		Boards.player2.myPieces = history.get(turn-1).p2Pieces;
		Tile oldLocation = history.get(turn-1).oldLocation;
		Tile newLocation = history.get(turn-1).newLocation;
		Piece movingPiece = history.get(turn-1).movingPiece;
		if (history.get(turn-1).rook != null) {
			Tile rookoldLocation = history.get(turn-1).rookoldLocation;
			Tile rooknewLocation = history.get(turn-1).rooknewLocation;
			Piece rook = history.get(turn-1).rook;
			rook.forceTo(rookoldLocation);
			rooknewLocation.removePiece();
			
		} 
		movingPiece.forceTo(oldLocation);
		movingPiece.useEvolved = history.get(turn-1).evolved;
		newLocation.removePiece();
		if (history.get(turn-1).capturedPiece != null) {
			Piece capturedPiece = history.get(turn-1).capturedPiece;
			capturedPiece.forceTo(newLocation);
			capturedPiece.player.opponent.capturedPiece[capturedPiece.player.opponent.numberOfCaptures - 1] = null;
			capturedPiece.player.opponent.numberOfCaptures--;
		}
		Boards.player1.kingLocation.pieceOnTile.unmoved = history.get(turn-1).p1KingUnmoved;
		Boards.player2.kingLocation.pieceOnTile.unmoved = history.get(turn-1).p2KingUnmoved;
		history.remove(turn-1);
		if (Piece.anyChecks(Boards.player1, false)) {
			if (Piece.anyChecks(Boards.player1, true)) {
				Boards.player1.checkMate = true;
			}
		}
		if (Piece.anyChecks(Boards.player2, false)) {
			if (Piece.anyChecks(Boards.player2, true)) {
				Boards.player2.checkMate = true;
			}
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.getTile(i, j).firstPress = false;
			}
		}
		
	}
	
	public static Tile getTile(int i, int j) {
		return boardArray[i][j];
	}
	public String inWriting() {
		return "x: " + this._x + " y: " + this._y;
	}
	public void highlight () {
		this.setBorderPainted(true);
		if (this.occupied == false) {
			this.setIcon(new ImageIcon(new BufferedImage(41, 41, BufferedImage.TYPE_INT_ARGB)));
		} else {
			this.setIcon(new ImageIcon (this.pieceOnTile.piece.getImage().getScaledInstance(41,41,Image.SCALE_SMOOTH)));
		}
		
		if (this.occupied){
			this.setBorder(new LineBorder(Color.RED, 15));
		} else {
			this.setBorder(new LineBorder(Color.GREEN, 15));
		}
		this.setOpaque(true);
	}
	public void removeHighlight() {
		if (this.occupied == false) {
			this.setIcon(new ImageIcon(new BufferedImage(71, 71, BufferedImage.TYPE_INT_ARGB)));
		} else {
			this.setIcon(this.pieceOnTile.piece);
		}
		this.setBorder(null);
		this.setBorderPainted(false);
		this.setOpaque(true);
		
	}
	
}