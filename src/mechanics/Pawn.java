package mechanics;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Pawn extends Piece{
	boolean doubleJump;
	boolean unmoved;
	Piece evolved = null;
	boolean useEvolved = false;
	public Pawn(int value, Tile location, boolean onBoard, Player player) {
		super(value, location, onBoard, player);
		if (value != 0) {
			this.piece = new ImageIcon("./res/Pawn1.png");
			this.name = "White Pawn " + (this.location.get_x() +1);
			if (player.number == 1) {
				this.piece = new ImageIcon("./res/Pawn.png");
				this.name = "Black pawn " + (this.location.get_x() +1);
			}
			this.piece = new ImageIcon (this.piece.getImage().getScaledInstance(71,71,Image.SCALE_SMOOTH));	
		}
	}

	@Override
	public boolean validMove(Tile newLocation) {
		
		if (this.onBoard == false){
			return false;
		}
		boolean yValid;
		boolean xValid;
		int players = 1;
		if(this.player.number == 0) {
			players = -1;
		}
		if (this.isThisBlockingTheKing(this.player.opponent, this.player.kingLocation, newLocation)) {
			return false;
		}
		if (this.player.inCheck == false) {
			if (newLocation != location) {
				if (newLocation.occupied == true && newLocation.pieceOnTile.player == this.player) {
					return false;
				}
				if (useEvolved == false){
					if (Pawn.anythingBlockingTheWay(this.location, newLocation, false, newLocation.pieceOnTile)) {
						return false;
					} else {
						return true;
					}
				} else {
					if (evolved.validMove(newLocation)) {
						return true;
					} else {
						return false;
					}
				}
			} else { 
				return false;	
			}
		} else {
			if(this.player.assassin.willThisBlockTheWay(this,newLocation, this.player.assassin.location, this.player.kingLocation)) {
				return true;
			} else {
				return false;
			}
		}
	}
	public boolean validMove( Tile oldLocation,Tile newLocation, boolean hypothetical, Piece blocker, boolean ignorePlayer) {
		if (this.onBoard == false){
			return false;
		}
		boolean yValid;
		boolean xValid;
		int players = 1;
		if(this.player.number == 0) {
			players = -1;
		}
		if (newLocation.occupied == true && newLocation.pieceOnTile.player == this.player && ignorePlayer == false) {
			return false;
		}
		if (newLocation.get_y() == oldLocation.get_y() + players && (newLocation.get_x() == oldLocation.get_x() + 1 || newLocation.get_x() == oldLocation.get_x() - 1) && newLocation.occupied == true){  
			return true;
		} 
		if (newLocation.occupied){
			return false;
		} 
		if(this.player.opponent.inCheck && this.player.opponent.assassin == this && Boards.turn%2 != this.player.number) {
			return false;
		}
		if (this.useEvolved == false) {	
			if(newLocation.get_y() == oldLocation.get_y() + players && newLocation.get_x() == oldLocation.get_x()&& newLocation.occupied == false){
				return true;
			} else if(newLocation.get_y() == oldLocation.get_y() + players*2 && newLocation.get_x() == oldLocation.get_x() && (oldLocation.get_y() == 1 || oldLocation.get_y() == 6)&& newLocation.occupied == false) {
				int y;
				int incrementy = (newLocation.get_y() - oldLocation.get_y())/Math.abs(newLocation.get_y() - oldLocation.get_y());
				for(int i = 1; i < Math.abs(newLocation.get_y()-oldLocation.get_y()); i++ ){
					y = incrementy * i;
					if (Tile.getTile(oldLocation.get_x(), oldLocation.get_y()+y).occupied) {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}
		} else {
			int _y_;
			if (this.player.number == 0) {
				_y_ = this.originalLocation.get_y();
			} else {
				_y_ = 7-this.originalLocation.get_y();
			}
			
			Piece temp = this.player.myPieces[this.originalLocation.get_x()][_y_];
			if (temp.validMove(this.location, newLocation, hypothetical, blocker, ignorePlayer)) {
				return false;
			} else {
				return true;
			}
		}
	}
	public static boolean anythingBlockingTheWay(Tile oldLocation, Tile newLocation, boolean hypothetical, Piece blocker) {
		int players = 1;
		if(oldLocation.pieceOnTile.player.number == 0) {
			players = -1;
		}
		if(newLocation.get_y() == oldLocation.get_y() + players && newLocation.get_x() == oldLocation.get_x()&& newLocation.occupied == false){
			return false;
		} else if (newLocation.get_y() == oldLocation.get_y() + players && (newLocation.get_x() == oldLocation.get_x() + 1 || newLocation.get_x() == oldLocation.get_x() - 1) && newLocation.occupied == true){  
			return false;
		} else if(newLocation.get_y() == oldLocation.get_y() + players*2 && newLocation.get_x() == oldLocation.get_x() && (oldLocation.get_y() == 1 || oldLocation.get_y() == 6)&& newLocation.occupied == false) {
			int y;
			int incrementy = (newLocation.get_y() - oldLocation.get_y())/Math.abs(newLocation.get_y() - oldLocation.get_y());
			for(int i = 1; i < Math.abs(newLocation.get_y()-oldLocation.get_y()); i++ ){
				y = incrementy * i;
				if (Tile.getTile(oldLocation.get_x(), oldLocation.get_y()+y).occupied) {
					return true;
				}
			}
			return false;
		} else {
			return true;
		}
		
	}
	@Override
	public boolean willThisBlockTheWay(Piece defender, Tile block, Tile oldLocation, Tile newLocation) {
		if (block == oldLocation && defender.validMove(defender.location,block, false, oldLocation.pieceOnTile, false)) {
			return true;
		}
		if (newLocation != oldLocation) {
			if (defender.validMove(defender.location,block, false, block.pieceOnTile, false)) {
				if (block == oldLocation) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	public void evolve (Piece temp) {
		this.evolved = temp;
		this.piece = this.evolved.piece;
		this.useEvolved = true;
		if (this.player.number == 0) {
			this.player.myPieces[this.originalLocation.get_x()][7-this.originalLocation.get_y()] = this.evolved;
		}else { 
			this.player.myPieces[this.originalLocation.get_x()][this.originalLocation.get_y()] = this.evolved;
			this.player.myPieces[this.originalLocation.get_x()][7-this.originalLocation.get_y()].onBoard = true;
	
		}
		this.location.setPiece(this);
	}
	
	

}