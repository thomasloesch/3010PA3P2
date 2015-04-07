// FILE: Connect4Game.java
// NAME: Thomas Loesch
// DATE: 04/02/15

import java.util.ArrayList;

public class Connect4Game {
	private int gameState;
	private int turn;
	private Column[] gameArray;
	
	public static final int CHECKER_PLAYER_1 = 1;
	public static final int CHECKER_PLAYER_2 = 2;
	
	public static final int ERROR = 0;
	public static final int PLAYER_1_TURN = 1;
	public static final int PLAYER_2_TURN = 2;
	public static final int PLAYER_1_WIN = 3;
	public static final int PLAYER_2_WIN = 4;
	public static final int TIE = 5;
	
	public Connect4Game() { 
		gameState = 1; 
		gameArray = new Column[8];
		turn = 1;
		for(int i = 0; i < gameArray.length; i++) gameArray[i] = new Column();
	}
	
	public boolean makeMove(int col) { 
		if(gameState != PLAYER_1_TURN && gameState != PLAYER_2_TURN) throw new IllegalStateException("You can't make a move right now");
		boolean retval = gameArray[col].add(gameState); 
		
		if(retval) {
			if(isWinner(col)) {
				if(gameState == PLAYER_1_TURN)		gameState = PLAYER_1_WIN;
				else								gameState = PLAYER_2_WIN;
			}
			else if(getAllPossibleMoves().isEmpty())gameState = TIE;
			else {
				if(gameState == PLAYER_1_TURN)		gameState = PLAYER_2_TURN;
				else								gameState = PLAYER_1_TURN;
				turn++;
			}
		}
		
		return retval;
	}

	public ArrayList<Integer> getAllPossibleMoves() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		for(int i = 0; i < gameArray.length; i++) {
			if(gameArray[i].getNext() != -1)
				temp.add(i);	
		}
		
		return temp;
	}
	
	public int getGameState() 					{ return gameState; }
	public int getTurn()						{ return turn; }
	public void reset() 						{ for(int i = 0; i < gameArray.length; i++) gameArray[i].reset(); gameState = 1; turn = 1;}
	public int getValueInLoc(int col, int row) 	{ return gameArray[col].getLoc(row); }
	
	private boolean isWinner(int col) {
		int[] directions = new int[7];
		int currentPos = getValueInLoc(col, gameArray[col].getCurr());
		
		try { directions[0] = getValueInLoc(col + 1	, gameArray[col].getCurr() + 1); } 	catch(ArrayIndexOutOfBoundsException e) { directions[0] = 0; }
		try { directions[1] = getValueInLoc(col + 1	, gameArray[col].getCurr()); }		catch(ArrayIndexOutOfBoundsException e) { directions[1] = 0; }
		try { directions[2] = getValueInLoc(col + 1	, gameArray[col].getCurr() - 1); }	catch(ArrayIndexOutOfBoundsException e) { directions[2] = 0; }
		try { directions[3] = getValueInLoc(col		, gameArray[col].getCurr() - 1); }	catch(ArrayIndexOutOfBoundsException e) { directions[3] = 0; }
		try { directions[4] = getValueInLoc(col - 1	, gameArray[col].getCurr() - 1); }	catch(ArrayIndexOutOfBoundsException e) { directions[4] = 0; }
		try { directions[5] = getValueInLoc(col - 1	, gameArray[col].getCurr()); }		catch(ArrayIndexOutOfBoundsException e) { directions[5] = 0; }
		try { directions[6] = getValueInLoc(col - 1	, gameArray[col].getCurr() + 1); }	catch(ArrayIndexOutOfBoundsException e) { directions[6] = 0; }
		
		if(directions[0] == currentPos) {
			if(directions[4] == currentPos) {
				try { if(getValueInLoc(col + 2 , gameArray[col].getCurr() + 2) == currentPos) return true; }
				catch(ArrayIndexOutOfBoundsException e) {}
				try { if(getValueInLoc(col - 2 , gameArray[col].getCurr() - 2) == currentPos) return true; }
				catch(ArrayIndexOutOfBoundsException e) {}
			}
			else {
				try { 
					if(getValueInLoc(col + 3 , gameArray[col].getCurr() + 3) == currentPos &&
					   getValueInLoc(col + 2 , gameArray[col].getCurr() + 2) == currentPos) return true;
				}
				catch(ArrayIndexOutOfBoundsException e) {}
			}
		}
		if(directions[1] == currentPos) {
			if(directions[5] == currentPos) {
				try { if(getValueInLoc(col + 2 , gameArray[col].getCurr()) == currentPos) return true; }
				catch(ArrayIndexOutOfBoundsException e) {}
				try { if(getValueInLoc(col - 2 , gameArray[col].getCurr()) == currentPos) return true; }
				catch(ArrayIndexOutOfBoundsException e) {}
			}
			else {
				try { 
					if(getValueInLoc(col + 3 , gameArray[col].getCurr()) == currentPos &&
					   getValueInLoc(col + 2 , gameArray[col].getCurr()) == currentPos) return true;
				}
				catch(ArrayIndexOutOfBoundsException e) {}
			}
		}
		if(directions[2] == currentPos) {
			if(directions[6] == currentPos) {
				try { if(getValueInLoc(col + 2 , gameArray[col].getCurr() - 2) == currentPos) return true; }
				catch(ArrayIndexOutOfBoundsException e) {}
				try { if(getValueInLoc(col - 2 , gameArray[col].getCurr() + 2) == currentPos) return true; }
				catch(ArrayIndexOutOfBoundsException e) {}
			}
			else {
				try { 
					if(getValueInLoc(col + 3 , gameArray[col].getCurr() - 3) == currentPos &&
					   getValueInLoc(col + 2 , gameArray[col].getCurr() - 2) == currentPos) return true;
				}
				catch(ArrayIndexOutOfBoundsException e) {}
			}
		}
		if(directions[3] == currentPos) {
			try { 
				if(getValueInLoc(col , gameArray[col].getCurr() - 3) == currentPos &&
				   getValueInLoc(col , gameArray[col].getCurr() - 2) == currentPos) return true;
			}
			catch(ArrayIndexOutOfBoundsException e) {}
		}
		if(directions[4] == currentPos) {
			try { 
				if(getValueInLoc(col - 3 , gameArray[col].getCurr() - 3) == currentPos &&
				   getValueInLoc(col - 2 , gameArray[col].getCurr() - 2) == currentPos) return true;
			}
			catch(ArrayIndexOutOfBoundsException e) {}
		}
		if(directions[5] == currentPos) {
			try { 
				if(getValueInLoc(col - 3 , gameArray[col].getCurr()) == currentPos &&
				   getValueInLoc(col - 2 , gameArray[col].getCurr()) == currentPos) return true;
			}
			catch(ArrayIndexOutOfBoundsException e) {}
		}
		if(directions[6] == currentPos) {
			try { 
				if(getValueInLoc(col - 3 , gameArray[col].getCurr() + 3) == currentPos &&
				   getValueInLoc(col - 2 , gameArray[col].getCurr() + 2) == currentPos) return true;
			}
			catch(ArrayIndexOutOfBoundsException e) {}
		}
		
		return false;
	}
	
	private class Column {
		private int 	top;
		private int[] 	contents;
		
		public Column() { contents = new int[6]; top = 0; }
		
		public boolean add(int x) { 
			if(isFull() == true) return false;
			contents[top++] = x; 
			return true;
		}
		
		public int getNext() 		{ if(top == 6) return -1; return top; }
		public int getCurr()		{ return top - 1; }
		public int getLoc(int x) 	{ return contents[x]; }
		public void reset() 		{ for(int i = 0; i < contents.length; i++) contents[i] = 0; top = 0; }
		public boolean isFull() 	{ if(top == 7) return true; else return false; }
	}
}
