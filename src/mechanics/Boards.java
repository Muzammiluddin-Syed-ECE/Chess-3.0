package mechanics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import gui.Check;
import gui.ChessTimer1;
import gui.ChessTimer2;
import gui.InitializeGui;
import gui.StartingButtons;


public class Boards extends JPanel {
	
	private Tile[][] board = new Tile[8][8];
	public static Player player1 = new Player(0);
	public static Player player2 = new Player(1);
	public static boolean undoed = false;
	private buttonListener[][] count = new buttonListener[8][8];
	public static int turn;
	public static boolean continueGame = true;
	public static int playerChoice;
	boolean whiteBoard = true;
	JPanel one, two, three, four, five, six, seven, eight; 
	JPanel all = new JPanel();
	public static String ppl1 = "Default Player 1 Name";
	public static String ppl2 = "Default Player 2 Name";
	public static JLabel playersChoicePopUp = new JLabel(new ImageIcon(new ImageIcon("./res/Start Background.png").getImage().getScaledInstance(800, 500, Image.SCALE_FAST)));
	public static JPanel timerChoicePopUp = new JPanel();
	public static boolean random = false;
	public static boolean repeat = true;
	public static StartingButtons playerChoice1 = new StartingButtons(false);
	public static StartingButtons playerChoice2 = new StartingButtons(true);
	
	public Boards(int turn) {
		System.out.println("Game is loading...");
		//Player & turn declarations
		Boards.player1.setOpponent(player2); 
		Boards.player2.setOpponent(player1);
		Boards.turn = turn;
		
		playerChoice1.addActionListener(new player1ChoiceListener(playerChoice1));
	    playerChoice2.addActionListener(new player2ChoiceListener(playerChoice2));
	    JPanel playerChoicesub = new JPanel();
	    playerChoicesub.setLayout(new BoxLayout(playerChoicesub, BoxLayout.Y_AXIS));
	    playerChoicesub.add(playerChoice1);
		playerChoicesub.add(playerChoice2);
		playerChoicesub.setOpaque(false);
	    BorderLayout playerLayout = new BorderLayout();
	    playersChoicePopUp.setLayout(playerLayout);
		playersChoicePopUp.add(playerChoicesub, BorderLayout.PAGE_END);
		playersChoicePopUp.setVisible(true);
		JOptionPane.showMessageDialog(null,	playersChoicePopUp, "Game Mode", JOptionPane.PLAIN_MESSAGE);
		    
		for (int i = 0; i < 8; i++) {
        	for (int j = 0; j < 8; j++) {
        		Tile atile = new Tile(i,j,Boards.player1,Boards.player2);
        		if (j < 2) {
        			Boards.player2.myPieces[i][j] = atile.pieceOnTile;
        		}
        		if (j > 5) {
        			Boards.player1.myPieces[i][7-j] = atile.pieceOnTile;
        		}
        		atile.setMargin(new Insets(0,0,0,0));
        		atile.setBorderPainted(false);
        		count[i][j] = new buttonListener(atile,this);
        		atile.addActionListener(count[i][j]);
				board[i][j] = atile;
        	}
        }
			
		
		
		//Create Content Panel, set layout
		
		one = new JPanel(new GridBagLayout());
		two = new JPanel(new GridBagLayout());
		three = new JPanel(new GridBagLayout());
		four = new JPanel(new GridBagLayout());
		five = new JPanel(new GridBagLayout());
		six = new JPanel(new GridBagLayout());
		seven = new JPanel(new GridBagLayout());
		eight = new JPanel(new GridBagLayout());
		
		
		all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
		
		//Add the components to the Content Pane
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (j == 0)
					one.add(board[i][j]);
				if (j == 1) 
					two.add(board[i][j]);
				if (j == 2)
					three.add(board[i][j]);
				if (j == 3) 
					four.add(board[i][j]);
				if (j == 4)
					five.add(board[i][j]);
				if (j == 5) 
					six.add(board[i][j]);
				if (j == 6)
					seven.add(board[i][j]);
				if (j == 7) 
					eight.add(board[i][j]);
			}
		}
		
	   
	  
	    JButton timerChoice1 = new JButton("5 Mins.");
		JButton timerChoice2 = new JButton("15 Mins.");
		JButton timerChoice3 = new JButton("25 Mins.");
		timerChoice1.addActionListener(new timerChoices1());
		timerChoice2.addActionListener(new timerChoices2());
		timerChoice3.addActionListener(new timerChoices3());
		timerChoicePopUp.setSize(100,200);
		timerChoicePopUp.setLayout( new FlowLayout());
		timerChoicePopUp.add(timerChoice1);
		timerChoicePopUp.add(timerChoice2);
		timerChoicePopUp.add(timerChoice3);
	    timerChoicePopUp.setVisible(true);
	    while (repeat) {
	    	JOptionPane.showMessageDialog(null, timerChoicePopUp, "Time selection", JOptionPane.PLAIN_MESSAGE);
	    	if (Player.duration != 0) {
	    		repeat = false;
	    	}
	    }
	    repeat = true;
		//Initialize components
			//Buttons
		
		
		while (repeat) {
			ppl1 = JOptionPane.showInputDialog(null,"What is your name, Player 1");
			ppl2 = JOptionPane.showInputDialog(null,"What is your name, Player 2");
			if (ppl1 != null && ppl2 != null) {
				repeat = false;
			}
		}
		
		String pl1 = ppl1;
		String pl2 = ppl2;
		
	    if (pl1.indexOf(" ") != -1) {
	    	pl1 = pl1.substring(0, pl1.indexOf(" "));
	    }
	    if (pl2.indexOf(" ") != -1) {
	    	pl2 = pl2.substring(0, pl2.indexOf(" "));
	    }
	    if (pl1.length() > 10) {
	    	pl1 = pl1.substring(0, 10);
	    }
	    if (pl2.length() > 10) {
	    	pl2 = pl2.substring(0, 10);
	    }
	    //Adding all the components to all
	    all.add(new JLabel(pl1));
		all.add(player2.check);
		all.add(player1.capturedPieces);
		all.add(one);
		all.add(two);
		all.add(three);
		all.add(four);
		all.add(five);
		all.add(six);
		all.add(seven);
		all.add(eight);
		all.add(player2.capturedPieces);
		
		all.add(player1.check);
		all.add(new JLabel(pl2));
		all.setOpaque(false);
		//Add panels to the panel that will become the frame
		
		//Set this windows attributes
		this.add(all);
		this.setOpaque(false);
		Player.player1Timer = new ChessTimer1(Player.duration);
		Player.player2Timer = new ChessTimer2(Player.duration);
		System.out.println("Game has loaded.");
	}
	public void flip (){
		//this.remove(all);
		
		JPanel all1;  
		this.all = new JPanel();
		all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
		if (this.whiteBoard == true) {
			whiteBoard = false;
			all.add(player1.capturedPieces);
			all.add(eight);
			all.add(seven);
			all.add(six);
			all.add(five);
			all.add(four);
			all.add(three);	
			all.add(two);	
			all.add(one);
			all.add(player2.capturedPieces);
		} else {
			whiteBoard = true;
			all.add(player1.capturedPieces);
			all.add(one);
			all.add(two);
			all.add(three);
			all.add(four);
			all.add(five);
			all.add(six);
			all.add(seven);
			all.add(eight);
		}
		//this.add(all);
	}
	class buttonListener implements ActionListener{
		//So the problem with my turn system is that if i make a tile increment its turn everytime its pressed
		// then all the other tiles will still be a turn behind
		//so i made a static array of buttons so that i could 
		//increase all of their turns when even a single one of them is pressed
		//public buttonListener[][] counts = new buttonListener[8][8];
		Tile ab;
		Boards one;
		public buttonListener(Tile a, Boards one) {
			ab = a;
			this.one = one;
		}
		public void actionPerformed(ActionEvent arg0) {
			if (ab.press(turn)){// I CHANGED PRESS FROM RETURN VOID TO RETURN BOOLEAN
				if(Tile.history.get(turn).capturedPiece != null) {
					if (Tile.history.get(Boards.turn).capturedPiece.player.number == 0) {
						player1.capturedPieces.addPiece(Tile.history.get(turn).capturedPiece);
					} else if (Tile.history.get(turn).capturedPiece.player.number == 1) {
						player2.capturedPieces.addPiece(Tile.history.get(turn).capturedPiece);
					}
				}
				turn++;
				if (turn%2 == Boards.player1.number) {
					
					Player.player1Timer.unpause();
					Player.player2Timer.pause();
				} else {
					Player.player2Timer.unpause();
					Player.player1Timer.pause();
				}
				InitializeGui.chess.player1.whosOn.refresh(turn);
				Boards.player1.scorekeeper.update();
				Boards.player2.scorekeeper.update();
				//one.flip();
				
			}
			
		}
		
	}
	public class undoListener implements ActionListener{
		public undoListener[][] undi = new undoListener[8][8];
		Tile ab;
		Boards one;
		public undoListener(Tile a, Boards one) {
			ab = a;
			this.one = one;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			
			Player.player1Timer.pause();
			Player.player2Timer.pause();
			if (turn>0) {
				System.out.println("undo: "+turn);
				if(Tile.history.get(turn-1).capturedPiece != null) {
					if (Tile.history.get(turn-1).capturedPiece.player.number == 0) {
						player1.capturedPieces.removePiece();
					} else if (Tile.history.get(turn-1).capturedPiece.player.number == 1) {
						player2.capturedPieces.removePiece();;
					}
				}
				ab.undo(turn);
				turn--;
				if (turn%2 == Boards.player1.number) {
					
					Player.player1Timer.unpause();
					Player.player2Timer.pause();
				} else {
					Player.player2Timer.unpause();
					Player.player1Timer.pause();
				}
				InitializeGui.chess.player1.whosOn.refresh(turn);
				Boards.player1.scorekeeper.update();
				Boards.player2.scorekeeper.update();
			}
		}
	}
	public class player1ChoiceListener implements ActionListener{
		StartingButtons _button;
		Icon buttonIcon;
		ImageIcon picked = new ImageIcon(new ImageIcon("./res/Picked.png").getImage().getScaledInstance(300,168,Image.SCALE_FAST));
		public player1ChoiceListener (StartingButtons _button) {
			this._button = _button;
			this.buttonIcon = this._button.getIcon();
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			Boards.playerChoice2.setVisible(false);
			//this._button.setIcon(new ImageIcon(new ImageIcon("./res/Picked.png").getImage().getScaledInstance(200,112,Image.SCALE_FAST)));
			//this._button.setBounds(50, 357, 200, 112 );
			Boards.playerChoice = 0;
			Tile.random = false;
			//Boards.playersChoicePopUp.setBackground(Color.LIGHT_GRAY);

			//this._button.setIcon(buttonIcon);
		}
		
	}
	public class player2ChoiceListener implements ActionListener{
		StartingButtons _button;
		Icon buttonIcon;
		int counting = 0;
		public player2ChoiceListener (StartingButtons _button) {
			this._button = _button;
			this.buttonIcon = this._button.getIcon();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			this.counting++;
			Boards.playerChoice1.setVisible(false);
			//this._button.setIcon(new ImageIcon(new ImageIcon("./res/Picked.png").getImage().getScaledInstance(200,112,Image.SCALE_FAST)));
			//this._button.setBounds(50, 225, 200, 112 );
			Boards.playerChoice = 0;
			
			//Boards.playersChoicePopUp.setBackground(Color.BLACK);
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 2; j++) {
					Player.placementConditions1[i][j] = this.random(this.counting, new Pawn(0,null,false,null ), new Knight(0,null,false,null ), new Bishop(0,null,false,null ), new Rook(0,null,false,null ), new Queen(0,null,false,null ));
					Player.placementConditions2[i][j] = this.random(this.counting, new Pawn(0,null,false,null ), new Knight(0,null,false,null ), new Bishop(0,null,false,null ), new Rook(0,null,false,null ), new Queen(0,null,false,null ));
				}
			}
			Tile.random = true;
			System.out.println("Randomness Level: " + this.counting);
			//this._button.setIcon(buttonIcon);
		}
		public Piece random (int counting, Piece one, Piece two, Piece three, Piece four, Piece five) {
			double randompicker = Math.random()*42;
			if (counting == 1) {
				if (0 < randompicker && randompicker <=21)
					return one;
				else if (21 < randompicker && randompicker <=28)
					return two;
				else if (28 < randompicker && randompicker <=35)
					return three;
				else if (35 < randompicker && randompicker <=39.5)
					return four;
				else 
					return five;
			} else if (counting == 2) {
				if (0 < randompicker && randompicker <=14.7)
					return one;
				else if (14.7 < randompicker && randompicker <=22.47)
					return two;
				else if (22.47 < randompicker && randompicker <=30.24)
					return three;
				else if (30.24 < randompicker && randompicker <=36.54)
					return four;
				else 
					return five;
			} else if (counting == 3) {
				if (0 < randompicker && randompicker <=11.55)
					return one;
				else if (11.55 < randompicker && randompicker <=19.635)
					return two;
				else if (19.635 < randompicker && randompicker <=27.72)
					return three;
				else if (27.72 < randompicker && randompicker <=35.02)
					return four;
				else 
					return five;
			} else {
				if (0 < randompicker && randompicker <=8.4)
					return one;
				else if (8.4 < randompicker && randompicker <=16.8)
					return two;
				else if (16.8 < randompicker && randompicker <=25.2)
					return three;
				else if (25.2 < randompicker && randompicker <=33.6)
					return four;
				else 
					return five;
			}
		}
		
	}
	public class timerChoices1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Player.duration = 300;
			
		}
		
	}
	public class timerChoices2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Player.duration = 900;
			
		}
		
	}
	public class timerChoices3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Player.duration = 1500;
			
		}
		
	}
	
}