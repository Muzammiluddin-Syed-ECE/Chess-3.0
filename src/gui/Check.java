package gui;

import java.awt.TextField;

import javax.swing.JOptionPane;

import mechanics.Player;

public class Check extends TextField{
	String caption;
	Player player;
	public Check (Player player){
		super("",5);
		this.player = player;
		
	}
	public void setCaption (String string) {
		this.setText(string);
	}
	public void activate () {
		JOptionPane.showMessageDialog(null, this.player.kingLocation.pieceOnTile.name +" is in check");
		this.setCaption("CHECK");
		this.player.inCheck = true;
		
	}
	public void deactivate() {
		this.setCaption("");
		this.player.inCheck = false;
	}
}