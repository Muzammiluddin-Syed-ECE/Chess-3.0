package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gui.InitializeGui.allplayersListener;
import mechanics.Boards.undoListener;
import mechanics.Boards;
import mechanics.Player;
import mechanics.Tile;

public class InitializeGui implements ActionListener{

	public static int turn = 0;
	public static Boards chess = new Boards(turn);
	public static JFrame frame = new JFrame();
	TextField p1;
	TextField p2;
	public static String pl1; 
	public static String pl2;
	public InitializeGui () {
		/*Basically the way my gui is layed out is:
		 * ________(Panel everything)_______________________________________
		 * |																|
		 * |	 __Boards(custom JPanel) chess__   							| 	
		 * |	|	       player2 name         | 							| 	
		 * |	|	  player2 checkIndicator    |  							| 
		 * |	|	________________________    |  	____(Panel extra)___	|
		 * |	|	|			            |   |  	|	JLabel ScoreP1  |	| 
		 * |	|	|			            |   |  	|	JPanel timerP1	|	|	
		 * |	|	|			            |   |  	|		P1 pieces   |	|	
		 * |	|	|		 (Game)         |   |	|		  Undo		|	|
		 * |	|   |                       |   | 	|		Forfeit		|	|	
		 * |	|	|						|	|	|   JPanel timerP2	|	|
		 * |	|	|						|	|	|	JLabel Score P2 |	|
		 * |	|	|						|	|	|					|	|
		 * |	|	|-----------------------	|	|-------------------	|
		 * |	|	player1 checkIndicator		|							|
		 * |	|		  Player1 Name			|							|
		 * |	|-------------------------------							|
		 * |																|
		 * |----------------------------------------------------------------|	
		 */
		
		//Extra buttons - Declarations
		JButton playerOne = new JButton("Player 1 pieces");
		JButton playerTwo  = new JButton("Player 2 pieces");
		Tile undo = new Tile("Undo");undo.setText("Undo");
		JButton forfeit = new JButton("Forfeit");
		
		//Extra buttons - addListeners
		playerOne.addActionListener(new allplayersListener(Boards.player1));
		playerTwo.addActionListener(new allplayersListener(Boards.player2));
		undo.addActionListener(chess.new undoListener(undo,chess));
		forfeit.addActionListener(this);
		
		//Create Extra - content pane
		JPanel extra = new JPanel();
				
		//Setting Extra layout
		extra.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
				
		//add Extra buttons to Extra
		extra.add(chess.player2.scorekeeper, gbc);
		extra.add(chess.player2.player2Timer, gbc);
		extra.add(playerOne, gbc);
		extra.add(playerTwo, gbc);
		extra.add(undo, gbc);
		extra.add(forfeit, gbc);
		extra.add(chess.player1.player1Timer, gbc);
		extra.add(chess.player1.scorekeeper, gbc);
		extra.setOpaque(false);
		
		//Create everything which is going to be the panel that contains everything
		Background_ everything = new Background_();
		
		//Add the things that needed to be added
		everything.add(chess);//The actual Chess game
		everything.add(chess.player2.whosOn); //Indicator of which player is n
		everything.add(extra); //Adding extra
		everything.setOpaque(false); // 
		
		//Seting the frames characteristics
		this.frame.setIconImage(new ImageIcon("./res/Knight.png").getImage());
		this.frame.setContentPane(everything);
		this.frame.pack();
		this.frame.setTitle("Chess Mania");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
		this.frame.setVisible(true);
	}

	public static void main(String[] args) {//Where everything starts
		// TODO Auto-generated method stub
		System.out.println("History: Piece *original location* (x,y) moved to *new location* (x,y) and captured Piece");
		final InitializeGui cheese = new InitializeGui();
	}
	public static void dispose(boolean timeOut) {
		InitializeGui.declareWinner(timeOut);
		InitializeGui.frame.dispose();
		Player.player1Timer._seconds = -1;
		Player.player2Timer._seconds = -1;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		InitializeGui.dispose(false);
	}
	public static void declareWinner(boolean timeOut) {
		String message = null;
		if (Boards.player1.checkMate != true && Boards.player2.checkMate != true) {
				message = Boards.ppl2 + " forfeited, " + Boards.ppl1 + " is the winner";
			if (Boards.turn %2 == 1) {
				message = Boards.ppl1 + " forfeited, " + Boards.ppl2 + " is the winner";
			}
			
		} if (timeOut) {
			if(Boards.player1.scorekeeper.total > Boards.player2.scorekeeper.total) {
				message = Boards.ppl2 + " has " +(Boards.player1.scorekeeper.total - Boards.player2.scorekeeper.total)+" more points than "+ Boards.ppl1 + ", "+Boards.ppl2+"is the winner";
			}else if ((Boards.player1.scorekeeper.total < Boards.player2.scorekeeper.total)) {
				message = Boards.ppl1 + " has " +(Boards.player2.scorekeeper.total - Boards.player1.scorekeeper.total)+" more points than "+ Boards.ppl2 + ", "+Boards.ppl1+"is the winner";
			} else {
				message = "You both are tied, therefore you both lose";
			}
		}
		JOptionPane.showMessageDialog(null,message,"Results",JOptionPane.PLAIN_MESSAGE);
	}
	public class allplayersListener implements ActionListener{
		public undoListener[][] undi = new undoListener[8][8];
		Player player;
		public allplayersListener(Player player) {
			this.player = player;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 2; j++) {
					System.out.println(this.player.myPieces[i][j].name);
					
				}
			
			}
		}
	}
	public class player1ChoiceListener implements ActionListener{
		boolean player = false;
		@Override
		public void actionPerformed(ActionEvent e) {
			this.player = true; 
			
		}
		
	}
	public class player2ChoiceListener implements ActionListener{
		boolean player = false;
		@Override
		public void actionPerformed(ActionEvent e) {
			this.player = true; 
			
		}
		
	}
	

}