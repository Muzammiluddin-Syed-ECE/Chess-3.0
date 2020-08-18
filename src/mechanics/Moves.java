package mechanics;

import gui.Check;
import gui.InitializeGui;

public class Moves {
	Piece movingPiece;
	Piece capturedPiece;
	Tile oldLocation;
	Tile newLocation;
	Piece rook;
	Tile rookoldLocation;
	Tile rooknewLocation;
	boolean player1inCheck; 
	boolean player2inCheck;
	boolean p1KingUnmoved;
	boolean p2KingUnmoved;
	Piece[][] p1Pieces = new Piece[8][2];
	Piece[][] p2Pieces = new Piece[8][2];
	boolean evolved;
	
	int turn = 0;
	public Moves (int turn, Tile oldLocation, Tile newLocation, Piece[][] _p1Pieces, Piece[][] _p2Pieces, boolean _p1KingUnmoved, boolean _p2KingUnmoved, boolean evolved) {
		this.movingPiece = oldLocation.pieceOnTile;
		this.capturedPiece = newLocation.pieceOnTile;
		this.oldLocation = oldLocation;
		this.newLocation = newLocation;
		this.turn = turn;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 2; j++) {
				this.p1Pieces[i][j] = _p1Pieces[i][j];
				this.p2Pieces[i][j] = _p2Pieces[i][j];
			}
		}
		this.p1KingUnmoved = _p1KingUnmoved;
		this.p2KingUnmoved = _p2KingUnmoved;
		this.evolved = evolved;
	}
	public Moves (int turn, Tile oldLocation, Tile newLocation,Tile rookoldLocation, Tile rooknewLocation, Piece[][] _p1Pieces,	Piece[][] _p2Pieces) {
		this.movingPiece = oldLocation.pieceOnTile;
		this.oldLocation = oldLocation;
		this.newLocation = newLocation;
		this.rook = rookoldLocation.pieceOnTile;
		this.rooknewLocation = rooknewLocation;
		this.rookoldLocation = rookoldLocation;
		this.turn = turn;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 2; j++) {
				this.p1Pieces[i][j] = _p1Pieces[i][j];
				this.p2Pieces[i][j] = _p2Pieces[i][j];
			}
		}
	}
	public void inWriting () {
		String nameOfTheCaptured;
		if (this.rook != null) {
			if (this.newLocation.get_x() == 7) {
				System.out.println(this.movingPiece.name + " castled kingside");
			} else {
				System.out.println(this.movingPiece.name + " castled queenside");
			}
		} else {
			if (capturedPiece == null) {
				nameOfTheCaptured = "";
				System.out.println(this.movingPiece.name+" ("+this.oldLocation.get_x()+":"+this.oldLocation.get_y()+") moved to " + this.newLocation.get_x()+":"+this.newLocation.get_y());
			} else {
				nameOfTheCaptured = this.capturedPiece.name;
				System.out.println(this.movingPiece.name+" ("+this.oldLocation.get_x()+":"+this.oldLocation.get_y()+") moved to " + this.newLocation.get_x()+":"+this.newLocation.get_y()+" and captured "+ nameOfTheCaptured);
			}
		}
		
	}
	public static boolean check (Piece agressor,Tile oldLocation, Tile kingLocation) {
		if (agressor.validMove(oldLocation,kingLocation, false, oldLocation.pieceOnTile, false)) {
			return true;
		} else {
			return false;
		}
	}
}