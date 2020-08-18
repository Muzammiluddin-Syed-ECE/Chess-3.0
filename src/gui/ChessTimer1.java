package gui;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mechanics.Boards;
import mechanics.Player;

public class ChessTimer1 extends JPanel{
	double tempTime;
	JLabel timeleft;
	public static Timer chesstimer = new Timer();
	static double _seconds;
	static boolean pause = false;
	TimerTask chessTask= new TimerTask(){
		@Override
		public void run() {
			if (ChessTimer1._seconds == -1.0) {
				JOptionPane.showMessageDialog(null, "Game has ended");
				
				ChessTimer1.chesstimer.cancel();
				//InitializeGui.dispose();
			} else if (!(ChessTimer1._seconds > 0.0)) {
				JOptionPane.showMessageDialog(null, "You ran out of time");
				JOptionPane.showMessageDialog(null, "Sorry " + Boards.ppl1 + " you lost.");
				ChessTimer1.chesstimer.cancel();
				InitializeGui.dispose(true);
			} else {
				if (ChessTimer1.pause == false) {
					ChessTimer1._seconds = ChessTimer1._seconds - 0.1;
					timeleft.setText(String.valueOf((int)(ChessTimer1._seconds - ChessTimer1._seconds%60)/60)+" mins " + String.valueOf(ChessTimer1._seconds%60).substring(0,2) + " seconds");
					
				}
			}
		}
	};
	
	public ChessTimer1( int _seconds) {
		this._seconds = _seconds;
		this.timeleft = new JLabel(String.valueOf(this._seconds));
		this.timeleft.setBackground(Color.LIGHT_GRAY);
		this.add(timeleft);
		this.startTime();
		//this.setOpaque(false);
	}
	public void startTime () {
		this.chesstimer.schedule(this.chessTask, 0,100);
	}
	public void pause () {
		this.tempTime = this._seconds;
		this.pause = true;
	}
	public void unpause() {
		this._seconds = this.tempTime;
		this.pause = false;
	}
	
	
}