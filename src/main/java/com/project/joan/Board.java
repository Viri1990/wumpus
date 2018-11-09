package com.project.joan;

public class Board {

	private int boardDimensions;
	private int[][] squareBoard;
	private static final int PLAYER = 1;
	private static final int WUMPUS = 2;
	private static final int HEDOR = 3;
	private static final int CAVE = 4;
	private static final int WIND = 5;
	private static final int GOLD = 6;
	private static final int HEDORWIND = 8;
	
	public Board(int p_dim) {//Let's initialise the board, parameter is the size of the square
		boardDimensions = p_dim;
		squareBoard = new int[boardDimensions][boardDimensions];
		setPieces(0, 0, PLAYER);//Set the player in the initial position
	}
	
	public void drawBoard() {
		System.out.println("BOARD: \n\n");
		for (int i=0; i<= boardDimensions-1; i++) {
			for (int b=0; b<= boardDimensions-1; b++) {
				System.out.print(" "+squareBoard[i][b]);			
			}
			System.out.println("");
		}
	}
	
	private void setPieces(int x, int y, int p_items) {
		squareBoard[x][y] = p_items;
	}
	
}
