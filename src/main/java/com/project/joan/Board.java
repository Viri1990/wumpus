package com.project.joan;

public class Board {

	private static int g_boardDimensions;
	private static int[][] g_squareBoard;
//	private Player g_p1;
	private static boolean flagGoAhead;
	
	private static final int VOID = 0;
	private static final int WUMPUS = 2;
	private static final int STENCH = 3;
	private static final int CAVE = 4;
	private static final int WIND = 5;
	private static final int GOLD = 6;
	private static final int STENCHWIND = 8;


	
	public Board(int p_dim) {//Let's initialise the board, parameter is the size of the square
		g_boardDimensions = p_dim;
		g_squareBoard = new int[g_boardDimensions][g_boardDimensions];
//		initialisePieces();
//		setPieces(0, 0, Player.getIdentifier());//Set the player in the initial position
	}
	
	public void drawBoard() {
		System.out.println("BOARD: \n\n");
		for (int i=0; i<= g_boardDimensions-1; i++) {
			for (int b=0; b<= g_boardDimensions-1; b++) {
				System.out.print(" "+g_squareBoard[i][b]);			
			}
			System.out.println("");
		}
	}
	
	public static void refresh() {
		System.out.println("Refresh Method!");
		
	}
	
	
	private void initialisePieces() {
	}
	
	public static boolean possibleGoAhead(int p_x, int p_y, String p_orientation) {
		System.out.println("Method possibleGoAhead");
		if (p_orientation.equalsIgnoreCase(Player.NORTH)&& p_y == g_boardDimensions-1) {
			return false;
		}
		else if (p_orientation.equalsIgnoreCase(Player.SOUTH)&& p_y == 0) {
			return false;
		}
		else if (p_orientation.equalsIgnoreCase(Player.EAST)&& p_x == g_boardDimensions-1) {
			return false;
		}
		else if (p_orientation.equalsIgnoreCase(Player.WEST)&& p_x == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
}
