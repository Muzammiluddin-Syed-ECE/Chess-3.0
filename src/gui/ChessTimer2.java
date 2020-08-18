package gui;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mechanics.Boards;
import mechanics.Player;

public class ChessTimer2 extends JPanel{
	boolean started = false;
	double tempTime;
	Player player = Boards.player1;
	JLabel timeleft;
	public static Timer chesstimer = new Timer();
	static double _seconds;
	static boolean pause = true;
	TimerTask chessTask= new TimerTask(){
		@Override
		public void run() {
			if (ChessTimer2._seconds == -1.0) {
				ChessTimer2.chesstimer.cancel();
			} else if (!(ChessTimer2._seconds > 0.0)) {
				JOptionPane.showMessageDialog(null, "You ran out of time");
				JOptionPane.showMessageDialog(null, "Sorry " + Boards.ppl2 + " you lost.");
				ChessTimer2.chesstimer.cancel();
				//InitializeGui.dispose();
			} else {
				if (ChessTimer2.pause == false) {
					ChessTimer2._seconds = ChessTimer2._seconds - 0.1;
					timeleft.setText(String.valueOf((int)(ChessTimer2._seconds - ChessTimer2._seconds%60)/60)+" mins " + String.valueOf(ChessTimer2._seconds%60).substring(0,2) + " seconds");
				}
			}
		}
	};
	
	
	public ChessTimer2( int _seconds) {
		this._seconds = _seconds;
		this.timeleft = new JLabel(String.valueOf(this._seconds));
		this.timeleft.setBackground(Color.LIGHT_GRAY);
		this.add(timeleft);
		this.startTime();
		this.tempTime = _seconds;
	}
	public void startTime () {
		this.chesstimer.schedule(this.chessTask, 0,100);
	}
	public void pause () {
		this.tempTime = this._seconds;
		this.pause= true;
	}
	public void unpause() {
		this._seconds = this.tempTime;
		this.pause= false;
	}
	
	
}