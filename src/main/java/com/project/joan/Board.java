package com.project.joan;

import java.util.Random;

public class Board {

	private int g_boardDimensions;
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

	// Initialise the board with the necessary parameters
	public Board(int p_dim, int p_numCaves) {
		g_boardDimensions = p_dim;
		g_numberCaves = p_numCaves;
		g_squareBoard = new int[g_boardDimensions][g_boardDimensions];
		g_squareBoard[0][0] = INITIALPOS;
	}

	// Here we gonna call the corresponding methods to inicialise the items
	public void initialisePieces() {
		initWumpus();
		initCaves();
		initGold();
	}

	// This methods will call to validCoord() to get valid positions inside the
	// board where set the items
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

	// Wumpus and Caves need adjacent items, this method is responsible to set it
	// where corresponds
	private void initAdj(int p_item, int p_x, int p_y) {
		System.out.println("\n\n");
		drawBoard();
		if (whoIs(p_x + 1, p_y) == VOID) {
			Draw(p_item, p_x + 1, p_y);
		} else if ((isThere(STENCH, p_x + 1, p_y))) {
			Draw(STENCHWIND, p_x + 1, p_y);
		}
		if (whoIs(p_x - 1, p_y) == VOID) {
			Draw(p_item, p_x - 1, p_y);
		} else if ((isThere(STENCH, p_x - 1, p_y))) {
			Draw(STENCHWIND, p_x - 1, p_y);
		}
		if (whoIs(p_x, p_y + 1) == VOID) {
			Draw(p_item, p_x, p_y + 1);
		} else if ((isThere(STENCH, p_x, p_y + 1))) {
			Draw(STENCHWIND, p_x, p_y + 1);
		}
		if (whoIs(p_x, p_y - 1) == VOID) {
			Draw(p_item, p_x, p_y - 1);
		} else if ((isThere(STENCH, p_x, p_y - 1))) {
			Draw(STENCHWIND, p_x, p_y - 1);
		}
	}

	// Method to draw the corresponding item in base of the coordinates received
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
		case STENCHWIND:
			g_squareBoard[p_x][p_y] = STENCHWIND;
			break;
		}
	}

	// Method in charge to draw the board (Necessary for the development)
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

	// Return the item present on the position received
	public int whoIs(int p_x, int p_y) {
		try {
			return g_squareBoard[p_x][p_y];
		} catch (ArrayIndexOutOfBoundsException excepcion) {
			return -1;
		}
	}

	// Will return true if the item is present in that position
	public boolean isThere(int p_item, int p_x, int p_y) {
		switch (p_item) {
		case WUMPUS:
			if (whoIs(p_x, p_y) == WUMPUS) {
				return true;
			}
			break;
		case CAVE:
			if (whoIs(p_x, p_y) == CAVE) {
				return true;
			}
		case STENCH:
			if (whoIs(p_x, p_y) == STENCH) {
				return true;
			}
			break;
		case WIND:
			if (whoIs(p_x, p_y) == WIND) {
				return true;
			}
		case STENCHWIND:
			if (whoIs(p_x, p_y) == STENCHWIND) {
				return true;
			}
		case INITIALPOS:
			if (whoIs(p_x, p_y) == INITIALPOS) {
				return true;
			}
			break;
		}
		return false;
	}

	// Delete the wumpus from the game
	public void wumpusDead(int p_x, int p_y) {
		g_squareBoard[p_x][p_y] = VOID;
		removeStench(p_x, p_y);
	}

	//After killing the Wumpus we need to remove their Stench from the adjacent positions!!
	public void removeStench(int p_x, int p_y) {
		if (isThere(STENCH, p_x + 1, p_y)) {
			g_squareBoard[p_x + 1][p_y] = VOID;
		} else if (isThere(STENCHWIND, p_x + 1, p_y)) {
			g_squareBoard[p_x + 1][p_y] = WIND;
		}
		if (isThere(STENCH, p_x - 1, p_y)) {
			g_squareBoard[p_x - 1][p_y] = VOID;
		} else if (isThere(STENCHWIND, p_x - 1, p_y)) {
			g_squareBoard[p_x - 1][p_y] = WIND;
		}
		if (isThere(STENCH, p_x, p_y + 1)) {
			g_squareBoard[p_x][p_y + 1] = VOID;
		} else if (isThere(STENCHWIND, p_x, p_y + 1)) {
			g_squareBoard[p_x][p_y + 1] = WIND;
		}
		if (isThere(STENCH, p_x, p_y - 1)) {
			g_squareBoard[p_x][p_y - 1] = VOID;
		} else if (isThere(STENCHWIND, p_x, p_y - 1)) {
			g_squareBoard[p_x][p_y - 1] = WIND;
		}
		drawBoard();
	}

	// Create random coordenates and check if are valid
	private int[] validCoord() {
		int[] l_randomCoord;
		int x;
		int y;
		do {
			l_randomCoord = RandomNumbers();
			x = l_randomCoord[0];
			y = l_randomCoord[1];
		} while ((!(whoIs(x, y) == VOID))// Check if the position created is void
				|| (x == 0 && y == 1)//
				|| (x == 1 && y == 0));
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

	// Method responsible to check if we are trying to go out from the Board bounds
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

	public int getG_boardDimensions() {
		return g_boardDimensions;
	}

}
