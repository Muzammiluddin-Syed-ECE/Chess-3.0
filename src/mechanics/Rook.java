package mechanics;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Rook extends Piece {
	
	boolean unmoved = true;
	
	public Rook(int value, Tile location, boolean onBoard, Player player) {
		// TODO Auto-generated constructor stub
		super(value, location, onBoard, player);
		if (value != 0) {
			this.piece = new ImageIcon("./res/Rook1.png");
			this.name = "White Rook";
			if (player.number == 1) {
				this.name = "Black Rook";
				this.piece = new ImageIcon("./res/9820.png");this.piece = new ImageIcon (this.piece.getImage().getScaledInstance(71,71,Image.SCALE_SMOOTH));
			
			}
			this.piece = new ImageIcon (this.piece.getImage().getScaledInstance(71,71,Image.SCALE_SMOOTH));	
		}
		this.unmoved = true;
		//this.piece = new ImageIcon(getClass().getResource("/Chess 0.2/src/pieces/pieceIcons/Rook_Figure_(Marble).png"));
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
			if (newLocation != location) {
				if (newLocation.occupied == true && newLocation.pieceOnTile.player == this.player) {
					return false;
				}if (anythingBlockingTheWay(this.location,newLocation, false, this)) {
					return false;
				} else {
					return true;
				}
			/*if (newLocation.get_x() == location.get_x()) { 
				int y;
				int incrementy = (newLocation.get_y() - location.get_y())/Math.abs(newLocation.get_y() - location.get_y());
				for(int i = 1; i < Math.abs(newLocation.get_y()-location.get_y()); i++ ){
					y = incrementy * i;
					if (Tile.getTile(location.get_x(), location.get_y()+y).occupied) {
						return false;
					}
				}
				return true;
			} else if (newLocation.get_y() == location.get_y()) {
				
				int x;
				int incrementx = (newLocation.get_x() - location.get_x())/Math.abs(newLocation.get_x() - location.get_x());
				for(int i = 1; i < Math.abs(newLocation.get_x()-location.get_x()); i++ ){
					x = incrementx * i;
					if (Tile.getTile(location.get_x()+x, location.get_y()).occupied) {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}*/
			} else {
				//System.out.println("another name for the rook");
				return false;
			}
		}  else {
			//System.out.println(this.assassin.name);
			if(this.player.assassin.willThisBlockTheWay(this,newLocation, this.player.assassin.location, this.player.kingLocation)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static boolean anythingBlockingTheWay(Tile oldLocation, Tile newLocation, boolean hypothetical, Piece blocker) {
		if (newLocation.get_x() == oldLocation.get_x()) { 
			int y;
			int incrementy = (newLocation.get_y() - oldLocation.get_y())/Math.abs(newLocation.get_y() - oldLocation.get_y());
			for(int i = 1; i < Math.abs(newLocation.get_y()-oldLocation.get_y()); i++ ){
				y = incrementy * i;
				if (hypothetical) {
					if (Tile.getTile(oldLocation.get_x(), oldLocation.get_y()+y).occupied && Tile.getTile(oldLocation.get_x(), oldLocation.get_y()+y) != blocker.location) {
						return true;
					}
				} else {
					if (Tile.getTile(oldLocation.get_x(), oldLocation.get_y()+y).occupied) {
						return true;
					}
				}
			}
			return false;
		} else if (newLocation.get_y() == oldLocation.get_y()) {
			
			int x;
			int incrementx = (newLocation.get_x() - oldLocation.get_x())/Math.abs(newLocation.get_x() - oldLocation.get_x());
			for(int i = 1; i < Math.abs(newLocation.get_x()- oldLocation.get_x()); i++ ){
				x = incrementx * i;
				if (hypothetical) {
					if (Tile.getTile(oldLocation.get_x()+x, oldLocation.get_y()).occupied && Tile.getTile(oldLocation.get_x()+x, oldLocation.get_y()) != blocker.location) {
						return true;
					}
				} else {
					if (Tile.getTile(oldLocation.get_x()+x, oldLocation.get_y()).occupied) {
						return true;
					}
				}
			}
			return false;
			
		}
		return true;
	}
	public boolean validMove(Tile oldLocation, Tile newLocation, boolean hypothetical, Piece blocker, boolean ignorePlayer) {
		//System.out.println("another (*_*) for the rook");
		if (this.onBoard == false){
			return false;
		}
		if (newLocation != oldLocation) {
			if (newLocation.occupied == true && newLocation.pieceOnTile.player == this.player && ignorePlayer == false) {
				return false;
			}if (anythingBlockingTheWay(oldLocation,newLocation, hypothetical, blocker)) {
				return false;
			} else {
				return true;
			}
		} else {
			//System.out.println("another name for the rook");
			return false;
		}
	}

	@Override
	public boolean willThisBlockTheWay(Piece defender,Tile block, Tile oldLocation, Tile newLocation) {
		if (block == oldLocation && defender.validMove(defender.location,block, false, oldLocation.pieceOnTile, false)) {
			return true;
		}
		if (defender.validMove(defender.location,block , false, block.pieceOnTile, false)) {
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
			
			} else {
				return false;
			}
		} else {
			return false;
		}
			
	}
	
}