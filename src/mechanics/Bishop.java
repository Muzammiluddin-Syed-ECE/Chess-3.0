package mechanics;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Bishop extends Piece {
	
	public Bishop(int value, Tile location, boolean onBoard, Player player) {
		super(value, location, onBoard, player);
		if (value != 0) {
			this.name = "White Bishop";
			this.piece = new ImageIcon("./res/Bishop1.png");
			if (player.number == 1) {
				this.name = "Black Bishop";
				this.piece = new ImageIcon("./res/Bishop.png");
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
				} if (Bishop.anythingBlockingTheWay(this.location, newLocation, false,this)){
					return false;
				}
			else {
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
	public boolean willThisBlockTheWay(Piece defender, Tile block, Tile oldLocation, Tile newLocation) {
	
		if (block == oldLocation && defender.validMove(defender.location,block, false, defender, false)) {
			
			return true;
		}
		if (defender.validMove(defender.location,block,false,defender, false)) {
			if (block != defender.location) {
				if (Math.abs(Math.abs(newLocation.get_y())- Math.abs(oldLocation.get_y())) == Math.abs(Math.abs(newLocation.get_x()) - Math.abs(oldLocation.get_x()))) {
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
		} else {
			return false;
		}
	}
	public static boolean anythingBlockingTheWay(Tile oldLocation, Tile newLocation, boolean hypothetical, Piece blocker) {
		if (oldLocation != newLocation){
			if (Math.abs(Math.abs(newLocation.get_y())- Math.abs(oldLocation.get_y())) == Math.abs(Math.abs(newLocation.get_x()) - Math.abs(oldLocation.get_x()))) {
			
				int differencey = newLocation.get_y() - oldLocation.get_y(); 
				int differencex = newLocation.get_x() - oldLocation.get_x();
				int x;
				int y;
				int incrementx = (newLocation.get_x() - oldLocation.get_x())/Math.abs(newLocation.get_x() - oldLocation.get_x());
				int incrementy = (newLocation.get_y() - oldLocation.get_y())/Math.abs(newLocation.get_y() - oldLocation.get_y());
				for(int i = 1; i < Math.abs(newLocation.get_x()-oldLocation.get_x()); i++ ){
					x = incrementx * i;
					y = incrementy * i;
					if (hypothetical) {
						if (Tile.getTile(oldLocation.get_x()+x, oldLocation.get_y()+y).occupied && Tile.getTile(oldLocation.get_x()+x, oldLocation.get_y()+y) != blocker.location) {
							return true;
						}
					} else {
						
						if (Tile.getTile(oldLocation.get_x()+x, oldLocation.get_y()+y).occupied) {
				
								return true;
						}
					}
				}
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	public boolean validMove(Tile oldLocation, Tile newLocation, boolean hypothetical, Piece blocker, boolean ignorePlayer) {
		
		if (this.onBoard == false){
			return false;
		}
		if (newLocation.occupied == true && newLocation.pieceOnTile.player == this.player && ignorePlayer == false) {
			return false;
		} 
		if (anythingBlockingTheWay(oldLocation, newLocation, hypothetical, blocker)){
			return false;
		} else {
			return true;
		}
		
	}
	
	

}