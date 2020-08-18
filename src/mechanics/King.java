package mechanics;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class King extends Piece {
	
	boolean unmoved = true;
	public King(int value, Tile location, boolean onBoard, Player player) {
		super(value, location, onBoard, player);
		this.piece = new ImageIcon("./res/King1.png");
		if (this.player.number == 0) {
			this.name = "White King";
		} else {
			this.piece = new ImageIcon("./res/King.png");
			this.name = "Black King";
		}
		this.piece = new ImageIcon (this.piece.getImage().getScaledInstance(71,71,Image.SCALE_SMOOTH));	
	}

	@Override
	public boolean validMove(Tile newLocation) {
		if (newLocation == this.location) {
			return false;
		}
		if (this.player.inCheck == false) {
			if (newLocation != location) {
				if (newLocation.occupied == true && newLocation.pieceOnTile.player == this.player) {
					return false;
				} if (King.anythingBlockingTheWay(this.location, newLocation, false, newLocation)){
					return false;
				} else {
					return true;
				}
			} else {
				
				return false;
			}
		}  else {
			if (newLocation == location) {
				return false;
			}
			if (this.validMove(this.location, newLocation, false, newLocation.pieceOnTile, false)&& this.player.assassin.validMove(this.player.assassin.location,newLocation, true, this,false) == false) {
				return true;
			} else {
				return false;
			}
		}
	}
	public boolean validMove(Tile oldLocation, Tile newLocation, boolean hypothetical, Piece blocker, boolean ignorePlayer) {
		
		if (newLocation != oldLocation) {
			if (newLocation.occupied == true && newLocation.pieceOnTile.player == this.player) {
				return false;
			}
			if (Math.abs(newLocation.get_x() - oldLocation.get_x()) < 2 && Math.abs(newLocation.get_y() - oldLocation.get_y())< 2) {
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 2; j++) {
						if(player.opponent.myPieces[i][j].validMove(player.opponent.myPieces[i][j].location, newLocation, false, player.kingLocation.pieceOnTile, true)){
							return false;
						} 
						
					}
				}
				return true;
			} else {
				return false;
			}
		
		} else {
			return false;
		}
	}
	public static boolean anythingBlockingTheWay(Tile oldLocation, Tile newLocation, boolean hypothetical, Tile blocker) {
		if (Math.abs(newLocation.get_x() - oldLocation.get_x()) < 2 && Math.abs(newLocation.get_y() - oldLocation.get_y())< 2) {
			if (Math.abs(newLocation.get_x() - oldLocation.get_x()) < 2 && Math.abs(newLocation.get_y() - oldLocation.get_y())< 2) {
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 2; j++) {
						if(oldLocation.pieceOnTile.player.opponent.myPieces[i][j].validMove(oldLocation.pieceOnTile.player.opponent.myPieces[i][j].location, newLocation, false, oldLocation.pieceOnTile, true)){
							return true;
						} 
					}
				}
			
				return false;
			}
		}
		return true;
	}
	@Override
	public boolean willThisBlockTheWay(Piece defender, Tile block, Tile oldLocation, Tile newLocation) {
		if (defender.validMove(defender.location,oldLocation, false, oldLocation.pieceOnTile, false)) {
			return true;
		}
	
		if (defender.validMove(defender.location,block, false, block.pieceOnTile, false)) {
			if (block != newLocation) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	

}