package mechanics;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Knight extends Piece {

	public Knight(int value, Tile location, boolean onBoard, Player player) {
		super(value,location,onBoard, player);
		if (value != 0) {
			this.piece = new ImageIcon("./res/Knight1.png");
			this.name = "White Knight";
			if (player.number == 1) {
				this.name = "Black Knight";
				this.piece = new ImageIcon("./res/Knight.png");
				
			}
			this.piece = new ImageIcon (this.piece.getImage().getScaledInstance(71,71,Image.SCALE_SMOOTH));	
		}	
	}

	@Override
	public boolean validMove(Tile newLocation) {
		if (this.onBoard == false){
			return false;
		}
		if (this.player.inCheck == false) {
			if (this.isThisBlockingTheKing(this.player.opponent, this.player.kingLocation, newLocation)) {
				return false;
			}
			if (newLocation != location) {
				if (newLocation.occupied == true && newLocation.pieceOnTile.player == this.player) {
					return false;
				} 
				if (Knight.anythingBlockingTheWay(this.location, newLocation, false, newLocation)){
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}
		}  else {
			if (this.isThisBlockingTheKing(this.player.opponent, this.player.kingLocation, newLocation)) {
				return false;
			}
			if(this.player.assassin.willThisBlockTheWay(this,newLocation, this.player.assassin.location, this.player.kingLocation)) {
				return true;
			} else {
				return false;
			}
		}
		
	}
	public boolean validMove(Tile oldLocation, Tile newLocation, boolean hypothetical, Piece blocker, boolean ignorePlayer) {
		if (this.onBoard == false){
			return false;
		}
		if (newLocation != oldLocation) {
			if (newLocation.occupied == true && newLocation.pieceOnTile.player == this.player && ignorePlayer == false) {
				return false;
			}
			if (Math.abs(newLocation.get_x()-oldLocation.get_x()) == 2 && Math.abs(newLocation.get_y()-oldLocation.get_y()) == 1/*newLocation.get_x() == location.get_x() + 2 || newLocation.get_x()==location.getX()-2 && newLocation.get_y() == location.get_y() + 1 || newLocation.get_y()==location.get_y()-1*/){
				return true;
			} else if (Math.abs(newLocation.get_x()-oldLocation.get_x()) == 1 && Math.abs(newLocation.get_y()-oldLocation.get_y()) == 2/*newLocation.get_y() == location.get_y() + 2 || newLocation.get_y()==location.get_y()-2 && newLocation.get_x() == location.get_x() + 1 || newLocation.get_x()==location.get_x()-1*/){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}
	public static boolean anythingBlockingTheWay(Tile oldLocation, Tile newLocation, boolean hypothetical, Tile blocker) {
		if (Math.abs(newLocation.get_x()-oldLocation.get_x()) == 2 && Math.abs(newLocation.get_y()-oldLocation.get_y()) == 1/*newLocation.get_x() == location.get_x() + 2 || newLocation.get_x()==location.getX()-2 && newLocation.get_y() == location.get_y() + 1 || newLocation.get_y()==location.get_y()-1*/){
			return false;
		} else if (Math.abs(newLocation.get_x()-oldLocation.get_x()) == 1 && Math.abs(newLocation.get_y()-oldLocation.get_y()) == 2/*newLocation.get_y() == location.get_y() + 2 || newLocation.get_y()==location.get_y()-2 && newLocation.get_x() == location.get_x() + 1 || newLocation.get_x()==location.get_x()-1*/){
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
		if (defender.validMove(defender.location, block, false, block.pieceOnTile, false)) {
			if (block == oldLocation && block == this.location) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	

}