package com.project.joan;

import java.util.Random;

public class Board {

	private static int g_boardDimensions;
	private static int g_numberCaves;
	private static int[][] g_squareBoard;

	private static final int VOID = 0;
	private static final int WUMPUS = 2;
	private static final int STENCH = 3;
	private static final int CAVE = 4;
	private static final int WIND = 5;
	private static final int GOLD = 6;
	private static final int STENCHWIND = 8;
	private static final int INITIALPOS = 9;

	public Board(int p_dim, int p_numCaves) {// Let's initialise the board, parameter is the size of the square
		g_boardDimensions = p_dim;
		g_numberCaves = p_numCaves;
		g_squareBoard = new int[g_boardDimensions][g_boardDimensions];
		g_squareBoard[0][0] = INITIALPOS;
	}

	public void drawBoard() {
		System.out.println("VOID= " + VOID + "\nWUMPUS=" + WUMPUS + "\nSTENCH=" + STENCH + "\nCAVE=" + CAVE + "\nWIND= "
				+ WIND + "\nGOLD= " + GOLD + "\nINITIALPOS= " + INITIALPOS);
		System.out.println("BOARD: \n\n");
		for (int y = g_boardDimensions - 1; y >= 0; y--) {
			for (int x = 0; x <= g_boardDimensions - 1; x++) {
				System.out.print(" " + g_squareBoard[x][y]);
			}
			System.out.println("");
		}
	}

	public int whoIs(int p_x, int p_y) {
		try {
			return g_squareBoard[p_x][p_y];
		} catch (ArrayIndexOutOfBoundsException excepcion) {
			System.out.println("You are out of the board!!");
			return -1;
		}
	}

	public void initialisePieces() {
		initWumpus();
		initCaves();
		initGold();
	}

	private void initWumpus() {
		int[] l_validCoords = validCoord();
		Draw(WUMPUS, l_validCoords[0], l_validCoords[1]);
		initAdj(STENCH, l_validCoords[0], l_validCoords[1]);
	}

	private void initGold() {
		int[] l_validCoords = validCoord();
		Draw(GOLD, l_validCoords[0], l_validCoords[1]);
	}

	private void initCaves() {
		for (int i = 0; i <= g_numberCaves - 1; i++) {
			int[] l_validCoords = validCoord();
			Draw(CAVE, l_validCoords[0], l_validCoords[1]);
			initAdj(WIND, l_validCoords[0], l_validCoords[1]);
		}
	}

	private void initAdj(int p_item, int p_x, int p_y) {
		if (whoIs(p_x + 1, p_y) == VOID) {
			Draw(p_item, p_x + 1, p_y);
		} else if ((whoIs(p_x + 1, p_y) == WIND) || (whoIs(p_x + 1, p_y) == STENCH)) {
			Draw(STENCHWIND, p_x - 1, p_y);
		}
		if (whoIs(p_x - 1, p_y) == VOID) {
			Draw(p_item, p_x - 1, p_y);
		} else if ((whoIs(p_x - 1, p_y) == WIND) || (whoIs(p_x + 1, p_y) == STENCH)) {
			Draw(STENCHWIND, p_x - 1, p_y);
		}
		if (whoIs(p_x, p_y + 1) == VOID) {
			Draw(p_item, p_x, p_y + 1);
		} else if ((whoIs(p_x, p_y + 1) == WIND) || (whoIs(p_x + 1, p_y) == STENCH)) {
			Draw(STENCHWIND, p_x - 1, p_y);
		}
		if (whoIs(p_x, p_y - 1) == VOID) {
			Draw(p_item, p_x, p_y - 1);
		} else if ((whoIs(p_x, p_y - 1) == WIND) || (whoIs(p_x + 1, p_y) == STENCH)) {
			Draw(STENCHWIND, p_x - 1, p_y);
		}
	}

	private void Draw(int p_item, int p_x, int p_y) {
		switch (p_item) {
		case WUMPUS:
			g_squareBoard[p_x][p_y] = WUMPUS;
			break;
		case CAVE:
			g_squareBoard[p_x][p_y] = CAVE;
			break;
		case GOLD:
			g_squareBoard[p_x][p_y] = GOLD;
			break;
		case STENCH:
			g_squareBoard[p_x][p_y] = STENCH;
			break;
		case WIND:
			g_squareBoard[p_x][p_y] = WIND;
			break;

		}
	}

	private int[] validCoord() {
		int[] l_randomCoord;
		do {
			l_randomCoord = RandomNumbers();
		} while ((!(whoIs(l_randomCoord[0], l_randomCoord[1]) == VOID))
				|| (l_randomCoord[0] == 0 && l_randomCoord[1] == 1)
				|| (l_randomCoord[1] == 1 && l_randomCoord[0] == 0));
		return l_randomCoord;
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
