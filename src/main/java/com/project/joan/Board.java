package com.project.joan;

import java.util.Random;

public class Board {

	private int g_boardDimensions;
	private static int g_numberCaves;
	private static int[][] g_squareBoard;

	/**
	 * Initialise the board with the necessary parameters
	 * 
	 * @param p_dim
	 * @param p_numCaves
	 */
	public Board(int p_dim, int p_numCaves) {
		g_boardDimensions = p_dim;
		g_numberCaves = p_numCaves;
		g_squareBoard = new int[g_boardDimensions][g_boardDimensions];
		g_squareBoard[0][0] = Item.INITIALPOS.getCode();
	}

	/**
	 * Here we gonna call the corresponding methods to inicialise the items
	 * 
	 **/
	public void initialisePieces() {
		initWumpus();
		initCaves();
		initGold();
	}

	/**
	 * This methods will call to validCoord() to get valid positions inside the
	 * board where set the items
	 */
	private void initWumpus() {
		int[] l_validCoords = validCoord();
		Draw(Item.WUMPUS, l_validCoords[0], l_validCoords[1]);
		initAdj(Item.STENCH, l_validCoords[0], l_validCoords[1]);
	}

	private void initGold() {
		int[] l_validCoords = validCoord();
		Draw(Item.GOLD, l_validCoords[0], l_validCoords[1]);
	}

	private void initCaves() {
		for (int i = 0; i <= g_numberCaves - 1; i++) {
			int[] l_validCoords = validCoord();
			Draw(Item.CAVE, l_validCoords[0], l_validCoords[1]);
			initAdj(Item.WIND, l_validCoords[0], l_validCoords[1]);
		}
	}

	/**
	 * Wumpus and Caves need adjacent items, this method is responsible to set it //
	 * where corresponds
	 *
	 * @param p_item
	 * @param p_x
	 * @param p_y
	 */
	private void initAdj(Item p_item, int p_x, int p_y) {
		if (whoIs(p_x + 1, p_y) == Item.VOID) {
			Draw(p_item, p_x + 1, p_y);
		} else if ((isThere(Item.STENCH, p_x + 1, p_y))) {
			Draw(Item.STENCHWIND, p_x + 1, p_y);
		}
		if (whoIs(p_x - 1, p_y) == Item.VOID) {
			Draw(p_item, p_x - 1, p_y);
		} else if ((isThere(Item.STENCH, p_x - 1, p_y))) {
			Draw(Item.STENCHWIND, p_x - 1, p_y);
		}
		if (whoIs(p_x, p_y + 1) == Item.VOID) {
			Draw(p_item, p_x, p_y + 1);
		} else if ((isThere(Item.STENCH, p_x, p_y + 1))) {
			Draw(Item.STENCHWIND, p_x, p_y + 1);
		}
		if (whoIs(p_x, p_y - 1) == Item.VOID) {
			Draw(p_item, p_x, p_y - 1);
		} else if ((isThere(Item.STENCH, p_x, p_y - 1))) {
			Draw(Item.STENCHWIND, p_x, p_y - 1);
		}
	}

	/**
	 * Method to draw the corresponding item in base of the coordinates received
	 * 
	 * @param p_item
	 * @param p_x
	 * @param p_y
	 */
	private void Draw(Item p_item, int p_x, int p_y) {
		switch (p_item) {
		case WUMPUS:
			g_squareBoard[p_x][p_y] = Item.WUMPUS.getCode();
			break;
		case CAVE:
			g_squareBoard[p_x][p_y] = Item.CAVE.getCode();
			break;
		case GOLD:
			g_squareBoard[p_x][p_y] = Item.GOLD.getCode();
			break;
		case STENCH:
			g_squareBoard[p_x][p_y] = Item.STENCH.getCode();
			break;
		case WIND:
			g_squareBoard[p_x][p_y] = Item.WIND.getCode();
			break;
		case STENCHWIND:
			g_squareBoard[p_x][p_y] = Item.STENCHWIND.getCode();
			break;
		}
	}

	/**
	 * Method in charge to draw the board (Necessary for the development)
	 * 
	 */
	public void drawBoard() {
		System.out.println("VOID= " + Item.VOID + "\nWUMPUS=" + Item.WUMPUS + "\nSTENCH=" + Item.STENCH + "\nCAVE="
				+ Item.CAVE + "\nWIND= " + Item.WIND + "\nGOLD= " + Item.GOLD + "\nINITIALPOS= " + Item.INITIALPOS);
		System.out.println("BOARD: \n\n");
		for (int y = g_boardDimensions - 1; y >= 0; y--) {
			for (int x = 0; x <= g_boardDimensions - 1; x++) {
				System.out.print(" " + g_squareBoard[x][y]);
			}
			System.out.println("");
		}
	}

	/**
	 * Return the item present on the position received
	 * 
	 * @param p_x
	 * @param p_y
	 * @return
	 */
	public Item whoIs(int p_x, int p_y) {
		try {
			return ItemUtils.getItemFromCode(g_squareBoard[p_x][p_y]);
		} catch (ArrayIndexOutOfBoundsException excepcion) {
			return null;
		}
	}

	/**
	 * Will return true if the item is present in that position
	 * 
	 * @param p_item
	 * @param p_x
	 * @param p_y
	 * @return
	 */
	public boolean isThere(Item p_item, int p_x, int p_y) {
		switch (p_item) {
		case WUMPUS:
			if (whoIs(p_x, p_y) == Item.WUMPUS) {
				return true;
			}
			break;
		case CAVE:
			if (whoIs(p_x, p_y) == Item.CAVE) {
				return true;
			}
		case STENCH:
			if (whoIs(p_x, p_y) == Item.STENCH) {
				return true;
			}
			break;
		case WIND:
			if (whoIs(p_x, p_y) == Item.WIND) {
				return true;
			}
		case STENCHWIND:
			if (whoIs(p_x, p_y) == Item.STENCHWIND) {
				return true;
			}
		case INITIALPOS:
			if (whoIs(p_x, p_y) == Item.INITIALPOS) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * Delete the wumpus from the game
	 * 
	 * @param p_x
	 * @param p_y
	 */
	public void wumpusDead(int p_x, int p_y) {
		g_squareBoard[p_x][p_y] = Item.VOID.getCode();
		removeStench(p_x, p_y);
	}

	/**
	 * After killing the Wumpus we need to remove their Stench from the adjacent
	 * positions
	 * 
	 * @param p_x
	 * @param p_y
	 */
	public void removeStench(int p_x, int p_y) {
		if (isThere(Item.STENCH, p_x + 1, p_y)) {
			g_squareBoard[p_x + 1][p_y] = Item.VOID.getCode();
		} else if (isThere(Item.STENCHWIND, p_x + 1, p_y)) {
			g_squareBoard[p_x + 1][p_y] = Item.WIND.getCode();
		}
		if (isThere(Item.STENCH, p_x - 1, p_y)) {
			g_squareBoard[p_x - 1][p_y] = Item.VOID.getCode();
		} else if (isThere(Item.STENCHWIND, p_x - 1, p_y)) {
			g_squareBoard[p_x - 1][p_y] = Item.WIND.getCode();
		}
		if (isThere(Item.STENCH, p_x, p_y + 1)) {
			g_squareBoard[p_x][p_y + 1] = Item.VOID.getCode();
		} else if (isThere(Item.STENCHWIND, p_x, p_y + 1)) {
			g_squareBoard[p_x][p_y + 1] = Item.WIND.getCode();
		}
		if (isThere(Item.STENCH, p_x, p_y - 1)) {
			g_squareBoard[p_x][p_y - 1] = Item.VOID.getCode();
		} else if (isThere(Item.STENCHWIND, p_x, p_y - 1)) {
			g_squareBoard[p_x][p_y - 1] = Item.WIND.getCode();
		}
	}

	/**
	 * Create random coordenates and check if are valid
	 * 
	 * @return
	 */
	private int[] validCoord() {
		int[] l_randomCoord;
		int x;
		int y;
		do {
			l_randomCoord = RandomNumbers();
			x = l_randomCoord[0];
			y = l_randomCoord[1];
		} while ((!(whoIs(x, y) == Item.VOID))// Check if the position created is void
				|| (x == 0 && y == 1)//
				|| (x == 1 && y == 0));
		return l_randomCoord;
	}

	/**
	 * 
	 * @return
	 */
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

	/**
	 * Method responsible to check if we are trying to go out from the Board bounds
	 * 
	 * @param p_x
	 * @param p_y
	 * @param p_orientation
	 * @return
	 */
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

	/**
	 * 
	 * @return
	 */
	public int getG_boardDimensions() {
		return g_boardDimensions;
	}

}
