// FILE: Connect4Board.java
// NAME: Thomas Loesch
// DATE: 04/02/2015

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class Connect4Board extends JPanel {
	private Connect4Game game;
	private Canvas canvas;
	private uiHandler uih;
	private JTextField turnText;
	private JLabel turnLabel;
	private int h;
	private int w;
	private int sqw;
	private int sqh;
	private Color player1Color;
	private Color player2Color;
	
	private final int COLS;
	private final int ROWS;
	
	public Connect4Board() {
		game = new Connect4Game();
		ROWS = 6;
		COLS = 8;
		
		createComponents();
	}
	
	private  class Canvas extends JPanel implements MouseListener {
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			setBackground(Color.WHITE);
			
			h = getHeight();
			w = getWidth();
			
			sqh = h / ROWS;
			sqw = w / COLS;	
			
			for (int i=0; i < COLS; i++) {
				for (int j = ROWS - 1; j >= 0; j--) {
					if ( (i+j)%2 == 0)  g.setColor(Color.WHITE);
					else g.setColor(Color.BLACK);
					g.fillRect(i*sqw, j*sqh, sqw, sqh);

					if(game.getValueInLoc(i, ROWS - j - 1) == Connect4Game.CHECKER_PLAYER_1) {
						g.setColor(player1Color);
						g.fillOval(i * sqw, j * sqh, sqw, sqh);
					}
					else if(game.getValueInLoc(i, ROWS - j - 1) == Connect4Game.CHECKER_PLAYER_2) {
						g.setColor(player2Color);
						g.fillOval(i * sqw, j * sqh, sqw, sqh);
					}
				}
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			int col = e.getX() / sqw;
			if(game.getGameState() == Connect4Game.PLAYER_1_TURN ||
			   game.getGameState() == Connect4Game.PLAYER_2_TURN) {
				try {
					game.makeMove(col);
				} 
				catch (ArrayIndexOutOfBoundsException x) {
					JOptionPane.showMessageDialog(null, "That column is full");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "You cannot make a move right now");
				if(game.getGameState() == Connect4Game.PLAYER_1_WIN ||
				   game.getGameState() == Connect4Game.PLAYER_2_WIN)	
				JOptionPane.showMessageDialog(null, "Please start a new game to continue");
			}

			turnText.setText(Integer.toString(game.getTurn()));
			
			if(game.getGameState() == Connect4Game.PLAYER_1_TURN)
				turnLabel.setText("Player 1's turn");
			else if(game.getGameState() == Connect4Game.PLAYER_2_TURN)
				turnLabel.setText("Player 2's turn");
			else if(game.getGameState() == Connect4Game.PLAYER_1_WIN)
				turnLabel.setText("The winner is Player 1!");
			else if(game.getGameState() == Connect4Game.PLAYER_2_WIN)
				turnLabel.setText("The winner is Player 2!");
			else if(game.getGameState() == Connect4Game.TIE)
				turnLabel.setText("Tie!");
			else
				turnLabel.setText("ERROR");
			
			paintComponent(getGraphics());
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}	
		
	}
	
	private  class uiHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			game.reset();
			turnText.setText("1");
			turnLabel.setText("Player 1's turn");
			
			canvas.paintComponent(getGraphics());
		}
		
	}
	
	private void createComponents() { 

		this.setLayout( new BorderLayout());
		
		JPanel uip = new JPanel();
		uip.setLayout( new FlowLayout());

		JLabel label = new JLabel("Turn");
		turnLabel = new JLabel("Player 1's turn");
		turnText = new JTextField(3);
		turnText.setText("1");
		JButton b4 = new JButton("New Game");
		uip.add(b4);
		uip.add(label);
		uip.add(turnText);
		uip.add(turnLabel);

		uih = new uiHandler();
		b4.addActionListener(uih);
		canvas = new Canvas();
		canvas.setBackground(new Color(210, 180, 140));

		canvas.addMouseListener(canvas);

		this.add(canvas, BorderLayout.CENTER);
		this.add(uip, BorderLayout.SOUTH);
	}
	
	public void setPlayerOneColor(Color _color) { player1Color = _color; }
	public void setPlayerTwoColor(Color _color) { player2Color = _color; }

}
