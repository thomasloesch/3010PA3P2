// FILE: Connect4Driver.java
// NAME: Thomas Loesch
// DATE: 04/02/15

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class Connect4Driver {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Connect Four");
		Connect4Board board = new Connect4Board();
		ArrayList<String> choices = new ArrayList<String>();
		String playerColor = null;
		
		choices.add("Red");
		choices.add("Green");
		choices.add("Blue");
		choices.add("Yellow");
		choices.add("Orange");
		choices.add("Pink");
		
		while (playerColor == null){
			playerColor = (String)JOptionPane.showInputDialog(null, "Please select a color for player 1:", "Select Color", JOptionPane.QUESTION_MESSAGE, null, choices.toArray(), choices.get(0)); 
			if (playerColor == null) JOptionPane.showMessageDialog(null, "Please select a color");
		}
		board.setPlayerOneColor(resolveColor(playerColor));
		
		choices.remove(playerColor);
		
		playerColor = null;
		while (playerColor == null){
			playerColor = (String)JOptionPane.showInputDialog(null, "Please select a color for player 2:", "Select Color", JOptionPane.QUESTION_MESSAGE, null, choices.toArray(), choices.get(0)); 
			if (playerColor == null) JOptionPane.showMessageDialog(null, "Please select a color");
		}
		board.setPlayerTwoColor(resolveColor(playerColor));
		
		frame.setContentPane( board );
		frame.setSize(800,600);
		frame.setLocation(100,100);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private static Color resolveColor(String color) {
		Color retval;
	
		if	   (color.equals("Red")) 	retval = Color.RED;
		else if(color.equals("Green")) 	retval = Color.GREEN;
		else if(color.equals("Blue")) 	retval = Color.BLUE;
		else if(color.equals("Yellow")) retval = Color.YELLOW;
		else if(color.equals("Orange")) retval = Color.ORANGE;
		else 							retval = Color.PINK;
		
		return retval;
	}
}

