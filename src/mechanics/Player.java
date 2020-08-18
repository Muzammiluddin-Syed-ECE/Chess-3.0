package mechanics;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gui.Check;
import gui.ChessTimer1;
import gui.ChessTimer2;
import gui.Score;

public class Player {
	public Piece[][] myPieces = new Piece[8][2];
	int number;
	PiecesDock capturedPieces = new PiecesDock(number);
	public int numberOfCaptures = 0;
	public Score scorekeeper = new Score(this);
	public Piece[] capturedPiece = new Piece[16];
	public static Piece[] pieces1 = new Piece[16];
	public static Piece[] pieces2 = new Piece[16];
	public static Piece[] pawnsformations = new Piece[8];
	public static WhosOn whosOn = new WhosOn();
	public static Piece[][] placementConditions1 = new Piece[8][2];
	public static Piece[][] placementConditions2 = new Piece[8][2];
	public Check check = new Check(this);
	public Player opponent;
	public boolean inCheck;
	public boolean checkMate;
	public JOptionPane checkAlert;
	public Piece assassin;
	public Piece freedomFighter;
	public Tile kingLocation;
	int pawnsformation;
	static int duration = 0;
	public static ChessTimer1 player1Timer;
	public static ChessTimer2 player2Timer;
	Player(int number) {
		this.number = number % 2;
		if (number == 0) {
			this.pawnsformation = 0; 
			System.out.println("Player 1 is loaded");
		} else {
			this.pawnsformation = 7;
			System.out.println("Player 2 is loaded");
		}
		
	}
	
	public void setOpponent (Player _opponent) {
		this.opponent = _opponent;
	}
}