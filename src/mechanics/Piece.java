package mechanics;

import java.awt.Color;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public abstract class Piece {
	public int value;
	boolean unmoved = true;
	Tile location;
	boolean onBoard;
	Player player;
	public String name;
	boolean useEvolved = false;
	Tile originalLocation;
	ImageIcon piece;
	Piece evolved;
	public Piece(int value, Tile location, boolean onBoard, Player player) {
		
		this.value = value;
		this.location = location;
		this.originalLocation = location;
		this.onBoard = onBoard;
		this.player = player;
	}
	public void moveTo(Tile newLocation) {
		if (this instanceof King ) {	
			boolean move = true;
			if (newLocation != location) {
				if ((Math.abs(newLocation.get_x() - location.get_x()) == 2) && newLocation.get_y() == location.get_y() &&this.unmoved && ((newLocation.get_x() == 6 && Tile.getTile(7, location.get_y()).pieceOnTile instanceof Rook && Tile.getTile(7, location.get_y()).pieceOnTile.unmoved)) || ((newLocation.get_x() == 2 && Tile.getTile(0, location.get_y()).pieceOnTile instanceof Rook && Tile.getTile(0, location.get_y()).pieceOnTile.unmoved))){
					if (newLocation.occupied == true && newLocation.pieceOnTile.player == this.player) {
						move = false;
					}
					int x;
					int incrementx = (newLocation.get_x() - location.get_x())/Math.abs(newLocation.get_x() - location.get_x());
					for(int i = 1; i < Math.abs(newLocation.get_x()-location.get_x()); i++ ){
						x = incrementx * i;
						if (Tile.getTile(location.get_x()+x, location.get_y()).occupied) {
						
							move = false;
						}
					}
				}
			}if(move && this.unmoved && this.location != newLocation){
				if (newLocation.get_x() == 6) {
					Tile.history.add( new Moves(Boards.turn,location,newLocation, Tile.getTile(7, location.get_y()),location, Boards.player1.myPieces, Boards.player2.myPieces));
					this.forceTo(newLocation);
					Tile.getTile(7, location.get_y()).pieceOnTile.forceTo(Tile.getTile(5, location.get_y()));
					Tile.history.get(Boards.turn).inWriting();
					this.unmoved = false;
					Boards.turn++;
				}
				if (newLocation.get_x() == 2 ) {
					Tile.history.add( new Moves(Boards.turn,location,newLocation, Tile.getTile(0, location.get_y()),Tile.getTile(3, location.get_y()), Boards.player1.myPieces, Boards.player2.myPieces));
					this.forceTo(newLocation);
					Tile.getTile(0, location.get_y()).pieceOnTile.forceTo(Tile.getTile(3, location.get_y()));
					Tile.history.get(Boards.turn).inWriting();
					this.unmoved = false;
					Boards.turn++;
				}
				
			}
		}
		if (this.validMove(newLocation)) {
			
			if (newLocation.occupied) {
				newLocation.pieceOnTile.capture();
				newLocation.removePiece(); 
			}
			
			
			this.unmoved = false;
			
			newLocation.setPiece(this);
			location.removePiece();
			location = newLocation;
			
		}
	}
	
	public void forceTo(Tile newLocation) {
		location.removePiece();
		this.onBoard = true;
		newLocation.setPiece(this);
		location = newLocation;
		
	}
	public abstract boolean validMove (Tile newLocation);
	
	public abstract boolean validMove (Tile oldLocation, Tile newLocation, boolean b, Piece somePiece, boolean ignorePlayer);
	
	public abstract boolean willThisBlockTheWay(Piece defender, Tile block, Tile oldLocation, Tile newLocation);
	
	public static boolean checkMove (Piece somePiece, Tile olds, Tile news) {
		if (somePiece.validMove(olds,news,false, somePiece, false)){
			return true;
		} else {
			return false;
		}
	}
	public void capture () {
		onBoard = false;
		location.removePiece();
		this.player.opponent.capturedPiece[this.player.opponent.numberOfCaptures] = this;
		this.player.opponent.numberOfCaptures++;
	}
	public void highlightMoves() {
		int count = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (this.validMove(Tile.boardArray[i][j])) {
					Tile.boardArray[i][j].highlight();
					Tile.allHighlightedMoves[count] = Tile.boardArray[i][j];
					count++;
				}
			}
		}
	}
	public static boolean anyChecks(Player player, boolean checkForCheckMate) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 2; j++) {
				if (checkForCheckMate) {
					for (int k = 0; k < 8; k++) {
						for (int l = 0; l < 8; l++) {
							if(player.myPieces[i][j].validMove(Tile.getTile(k, l)) == true) {
								return false;
							}
						}
					}
					
				} else {
					if(player.opponent.myPieces[i][j].validMove(player.opponent.myPieces[i][j].location, player.kingLocation, false, player.kingLocation.pieceOnTile, false)){
						player.check.setText("CHECK");
						player.check.activate();
						player.opponent.freedomFighter = player.opponent.myPieces[i][j];
						player.assassin = player.opponent.myPieces[i][j];
						return true;
					} 
				}
			}
		}
		if (checkForCheckMate == false){
			player.check.deactivate();
			return false;
		} else {
			return true;
		}
		
	}
	public boolean isThisBlockingTheKing (Player opponent, Tile kingLocation, Tile newLocation) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 2; j++) {
				if (opponent.myPieces[i][j].validMove(opponent.myPieces[i][j].location, this.location, false, this, false) && opponent.myPieces[i][j].validMove(opponent.myPieces[i][j].location, kingLocation, false, this, false) == false) {
					if(opponent.myPieces[i][j].validMove(opponent.myPieces[i][j].location, kingLocation, true, this, false)){
						if (opponent.myPieces[i][j].location == newLocation && this.validMove(this.location, newLocation, false, this, false)){
							return false;
						}
						if (this.willThisBlockTheWay(this, newLocation, opponent.myPieces[i][j].location, this.location)){
							return false;
						}
						return true;
					} 
				} 
			}
		}
		return false;
	}
}