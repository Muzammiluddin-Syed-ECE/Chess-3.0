package mechanics;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Queen extends Piece {

	public Queen(int value, Tile location, boolean onBoard, Player player) {
		super(value, location, onBoard, player);
		if (value != 0) {
			this.piece = new ImageIcon("./res/Queen1.png");
			this.name = "White Queen";
			if (player.number == 1) {
				this.name = "Black Queen";
				this.piece = new ImageIcon("./res/Queen.png");
			}	
			this.piece = new ImageIcon (this.piece.getImage().getScaledInstance(71,71,Image.SCALE_SMOOTH));	
		}
	}
	@Override
	public boolean validMove(Tile newLocation) {
		if (this.onBoard == false){
			return false;
		}
		if (this.isThisBlockingTheKing(this.player.opponent, this.player.kingLocation, newLocation)) {
			return false;
		}
		if (this.player.inCheck == false) {
			if (newLocation != location ) {
				if (newLocation.occupied == true && newLocation.pieceOnTile.player == this.player) {
					return false;
				}
				if (Queen.anythingBlockingTheWay(this.location, newLocation, false, newLocation.pieceOnTile)) {
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}
		}  else {
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
		if (newLocation != oldLocation ) {
			if (newLocation.occupied == true && newLocation.pieceOnTile.player == this.player && ignorePlayer == false) {
				return false;
			}
			if (Queen.anythingBlockingTheWay(oldLocation, newLocation, hypothetical, blocker)) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	public static boolean anythingBlockingTheWay(Tile oldLocation, Tile newLocation, boolean hypothetical, Piece blocker) {
		if (Rook.anythingBlockingTheWay(oldLocation, newLocation, hypothetical, blocker) && Bishop.anythingBlockingTheWay(oldLocation, newLocation, hypothetical, blocker)) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public boolean willThisBlockTheWay(Piece defender, Tile block, Tile oldLocation, Tile newLocation) {
		if (block == oldLocation && defender.validMove(defender.location,block, false, oldLocation.pieceOnTile, false)) {
			return true;
		}
		if (defender.validMove(defender.location,block, false, block.pieceOnTile, false)) {
			if (newLocation.get_x() == oldLocation.get_x()) { 
				int y;
				int incrementy = (newLocation.get_y() - oldLocation.get_y())/Math.abs(newLocation.get_y() - oldLocation.get_y());
				for(int i = 1; i < Math.abs(newLocation.get_y()-oldLocation.get_y()); i++ ){
					y = incrementy * i;
					if (Tile.getTile(oldLocation.get_x(), oldLocation.get_y()+y) == block) {
						return true;
					}
				}
				return false;
			} else if (newLocation.get_y() == oldLocation.get_y()) {
			
				int x;
				int incrementx = (newLocation.get_x() - oldLocation.get_x())/Math.abs(newLocation.get_x() - oldLocation.get_x());
				for(int i = 1; i < Math.abs(newLocation.get_x()- oldLocation.get_x()); i++ ){
					x = incrementx * i;
					if (Tile.getTile(oldLocation.get_x()+x, oldLocation.get_y())== block) {
						return true;
					}
				}
				return false;
			
			} else if (Math.abs(Math.abs(newLocation.get_y())- Math.abs(oldLocation.get_y())) == Math.abs(Math.abs(newLocation.get_x()) - Math.abs(oldLocation.get_x()))) {
				int differencey = newLocation.get_y() - oldLocation.get_y(); 
				int differencex = newLocation.get_x() - oldLocation.get_x();
				int x;
				int y;
				int incrementx = (newLocation.get_x() - oldLocation.get_x())/Math.abs(newLocation.get_x() - oldLocation.get_x());
				int incrementy = (newLocation.get_y() - oldLocation.get_y())/Math.abs(newLocation.get_y() - oldLocation.get_y());
				for(int i = 1; i < Math.abs(newLocation.get_x()-oldLocation.get_x()); i++ ){
					x = incrementx * i;
					y = incrementy * i;
					if (Tile.getTile(oldLocation.get_x()+x, oldLocation.get_y()+y) == block) {
						return true;
					}
				}
				return false;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}