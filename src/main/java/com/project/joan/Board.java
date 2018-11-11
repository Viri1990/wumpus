package com.project.joan;

import java.util.Random;

public class Board {

	private static int g_boardDimensions;
	private static int g_numberCaves;
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

	public Board() {

	}

	public Board(int p_dim, int p_numCaves) {// Let's initialise the board, parameter is the size of the square
		g_boardDimensions = p_dim;
		g_numberCaves = p_numCaves;
		g_squareBoard = new int[g_boardDimensions][g_boardDimensions];
	}

	public void drawBoard() {
		System.out.println("BOARD: \n\n");
		for (int y = g_boardDimensions - 1; y >= 0; y--) {
			for (int x = 0; x <= g_boardDimensions - 1; x++) {
				System.out.print(" " + g_squareBoard[x][y]);
			}
			System.out.println("");
		}
	}

	public int whoIs(int p_x, int p_y) {
		return g_squareBoard[p_x][p_y];
	}

	public void initialisePieces() {
		initWumpus();
		initCaves();
		initGold();
	}

	private void initWumpus() {
		int[] l_validCoords = isValidCoord();
		wumpusDraw(l_validCoords[0], l_validCoords[1]);
	}
	
	private void initGold() {
		int[] l_validCoords = isValidCoord();
		goldDraw(l_validCoords[0], l_validCoords[1]);
	}

	private int[] isValidCoord() {
		int[] l_randomCoord;
		do {
			l_randomCoord = RandomNumbers();
		} while (!(whoIs(l_randomCoord[0], l_randomCoord[1]) == VOID));
		return l_randomCoord;
	}

	private void wumpusDraw(int p_x, int p_y) {
		g_squareBoard[p_x][p_y] = WUMPUS;
	}
	
	private void goldDraw(int p_x, int p_y) {
		g_squareBoard[p_x][p_y] = GOLD;
	}

	private void initCaves() {
		for (int i = 0; i <= g_numberCaves; i++) {
			int[] l_validCoords = isValidCoord();
			caveDraw(l_validCoords[0], l_validCoords[1]);
		}
	}

	private void caveDraw(int p_x, int p_y) {
		g_squareBoard[p_x][p_y] = CAVE;
	}

	private int[] RandomNumbers() {
		Random l_random = new Random();
		int l_x;
		int l_y;
		do {
			l_x = l_random.nextInt(g_boardDimensions);
			l_y = l_random.nextInt(g_boardDimensions);
		} while ((l_x == l_y) && (l_x == 0));
		return new int[] { l_x, l_y };
	}

	public boolean possibleGoAhead(int p_x, int p_y, String p_orientation) {
		if (p_orientation.equalsIgnoreCase(Player.NORTH) && p_y == g_boardDimensions - 1) {
			return false;
		} else if (p_orientation.equalsIgnoreCase(Player.SOUTH) && p_y == 0) {
			return false;
		} else if (p_orientation.equalsIgnoreCase(Player.EAST) && p_x == g_boardDimensions - 1) {
			return false;
		} else if (p_orientation.equalsIgnoreCase(Player.WEST) && p_x == 0) {
			return false;
		} else {
			return true;
		}
	}

}
