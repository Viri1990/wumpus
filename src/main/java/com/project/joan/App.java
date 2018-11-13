package com.project.joan;

import java.util.Scanner;

public class App {
	//All actions player carry out
	private final String EXIT = "Q";
	private final String GOAHEAD = "W";
	private final String COUNTERCLOCKWISE = "A";
	private final String CLOCKWISE = "D";
	private final String SHOOT = "E";

	//Items present on the game
	private final int VOID = 0;
	private final int WUMPUS = 2;
	private final int STENCH = 3;
	private final int CAVE = 4;
	private final int WIND = 5;
	private final int GOLD = 6;
	private final int STENCHWIND = 8;
	private final int INITIALPOS = 9;

	//Global variables we will interact on
	private Scanner g_sc;
	private boolean G_ALIVE;
	private boolean G_GOLD;
	private boolean G_VICTORY;
	private Board g_board;
	private Player g_player;

	public App() {
		g_sc = new Scanner(System.in);
	}
	
	//Entry point of the app
	public static void main(String[] args) {
		App l_app = new App();
		l_app.start();
	}	
	
	//Method start beggins with the flow of the app
	public void start() {
		System.out.println("WELCOME TO HUNT THE WUMPUS!!\n");

		int l_choice;
		do {
			System.out.println("What you want to do?\n 1-Play game \n 2-Read the instructions \n 3-Exit");
			l_choice = g_sc.nextInt();
			menu(l_choice);
		} while (l_choice != 3);
	}
	
	//In this menu we will choose between the three different options
	private void menu(int p_choice) {
		switch (p_choice) {
		case 1:
			play();
			break;
		case 2:
			showInstructions();
			break;
		case 3:
			System.exit(0);
		}
	}
	
	//Method play is in charge to initialise the items and read the inputs from the user to call the method checkOptChoosed
	private void play() {
		prepareGame();
		System.out.println("Let's start!!\n");
		String l_entry = "";
		while (!l_entry.equalsIgnoreCase(EXIT) && G_ALIVE && !G_VICTORY) {
			position();
			System.out.println("\nPress a key: ");
			l_entry = g_sc.next().toUpperCase();
			checkOptChoosed(l_entry);
		}
	}
	
	//Initialise the variables that will determine the result of the game and the actors involved
	private void prepareGame() {
		G_ALIVE = true;
		G_GOLD = false;
		G_VICTORY = false;
		createBoard();
		createPlayer();
	}
	
	//Here we will choose the dimensions of the board, besides the number of caves
	//Here we also call the methods of the Board class responsible to initialise the static items (Caves,Wumpus,adjacents)
	//There are some checks to assure data introduced accomplish the specifications
	private void createBoard() {
		int l_dimBoard;
		int l_numCaves;
		do {
			System.out.println("Tell me the dimensions of your square board (min. 6 max. 12): ");
			while (!g_sc.hasNextInt()) {
				System.out.println("Please insert a valid number!");
				System.out.println("Tell me the dimensions of your square board (min. 6 max. 12 ): ");
				g_sc.next();
			}
			l_dimBoard = g_sc.nextInt();
		} while (l_dimBoard < 6 || l_dimBoard > 12);
		do {
			System.out.println("Tell me the number of caves (min. 4 max. 10): ");
			while (!g_sc.hasNextInt()) {
				System.out.println("Please insert a valid number!");
				System.out.println("Tell me the dimensions of your square board (min. 4 max. 10): ");
				g_sc.next();
			}
			l_numCaves = g_sc.nextInt();
		} while (l_numCaves < 4 || l_numCaves > 10);

		g_board = new Board(l_dimBoard, l_numCaves);
		g_board.drawBoard();
		g_board.initialisePieces();
		g_board.drawBoard();

	}
	
	//This method is responsable to create the player that we will interact with
	//We will choose the arrows that player will dispose and check the values is inside the bounds. 
	private void createPlayer() {
		int l_numArrows;
		do {
			System.out.println("Tell me the arrows you want to dispose (max.10): ");
			while (!g_sc.hasNextInt()) {
				System.out.println("Please insert a valid number!");
				System.out.println("Tell me the arrows you want to dispose: ");
				g_sc.next();
			}
			l_numArrows = g_sc.nextInt();
		} while (l_numArrows > 0 && l_numArrows > 10);
		g_player = new Player(l_numArrows);
	}

	private void showInstructions() {
		System.out.println("\n\nInstructions: \n Q-Exit \nW-Go Ahead \nA-Turn left \nD-Turn right \nE-Shoot\n\n");
	}

	private void position() {
		System.out.println("\nMy coordinates are: ");
		System.out.println("x= " + g_player.getX_position());
		System.out.println("y= " + g_player.getY_position());
		System.out.println("Orientation= " + g_player.getOrientation());
	}

	//Here we will check if the player is able to do the action request
	//If so, the flow will be redirected to the corresponding method
	private void checkOptChoosed(String p_entry) {
		switch (p_entry) {
		case EXIT:
			System.out.println("EXIT");
			break;
		case GOAHEAD:
			System.out.println("GOAHEAD");
			checkMove();
			break;
		case COUNTERCLOCKWISE:
			System.out.println("COUNTER CLOCKWISE");
			g_player.rotate(COUNTERCLOCKWISE);
			break;
		case CLOCKWISE:
			System.out.println("CLOCKWISE");
			g_player.rotate(CLOCKWISE);
			break;
		case SHOOT:
			if (g_player.canShoot()) {
				g_player.shoot();
				checkKill();
			}
			break;
		default:
			System.out.println("NON VALID CHOICE!");
			break;
		}
	}

	//Here we will check if the arrow fired got the Wumpus;
	//In base of the orientation of the player, we will go over the positions it has in front
	//If so, we will delete the Wumpus from the board
	private void checkKill() {
		switch (g_player.getOrientation()) {
		case Player.NORTH:
			for (int y = g_player.getY_position(); y <= g_board.getG_boardDimensions(); y++) {
				if (g_board.isThere(WUMPUS, g_player.getX_position(), y)) {
					g_board.wumpusDead(g_player.getX_position(), y);
					System.out.println("YOU KILLED THE WUMPUSSS!!");
					break;
				}
			}
			break;
		case Player.SOUTH:
			for (int y = g_player.getY_position(); y >= 0; y--) {
				if (g_board.isThere(WUMPUS, g_player.getX_position(), y)) {
					g_board.wumpusDead(g_player.getX_position(), y);
					System.out.println("YOU KILLED THE WUMPUSSS!!");
					break;
				}
			}
			break;
		case Player.EAST:
			for (int x = g_player.getX_position(); x <= g_board.getG_boardDimensions(); x++) {
				if (g_board.isThere(WUMPUS, x, g_player.getY_position())) {
					g_board.wumpusDead(x, g_player.getY_position());
					System.out.println("YOU KILLED THE WUMPUSSS!!");
					break;
				}
			}
			break;
		case Player.WEST:
			for (int x = g_player.getX_position(); x >= 0; x--) {
				if (g_board.isThere(WUMPUS, x, g_player.getY_position())) {
					g_board.wumpusDead(x, g_player.getY_position());
					System.out.println("YOU KILLED THE WUMPUSSS!!");
					break;
				}
			}
			break;
		}

	}

	//To decide if player can move, we will check, first of all, if he is inside the bounds of the board
	//If so, we will advance the position of the player by method goAhead() and check if in the same positions there is any item (refresh method)
	private void checkMove() {
		if (!g_board.possibleGoAhead(g_player.getX_position(), g_player.getY_position(), g_player.getOrientation())) {
			System.out.println("YOU GET THE WALL!");
			return;
		}
		g_player.goAhead();
		refresh();
	}

	//Here we will consult what's there in the position of the player and will advice of it
	//Besides of set values on the global variables responsibles to check the result of the game
	private void refresh() {
		switch (g_board.whoIs(g_player.getX_position(), g_player.getY_position())) {
		case WUMPUS:
			System.out.println("WUMPUS CATCHED YOU!!");
			G_ALIVE = false;
			break;
		case CAVE:
			System.out.println("YOU FELL IN THE CAVE!!");
			G_ALIVE = false;
			break;
		case GOLD:
			System.out.println("YOU GET THE GOLD!!");
			G_GOLD = true;
			break;
		case STENCH:
			System.out.println("SMELLS TO WUMPUS!!");
			break;
		case WIND:
			System.out.println("HARD WIND!!");
			break;
		case STENCHWIND:
			System.out.println("HARD WIND AND WUMPUS SMELL!!");
			break;
		case INITIALPOS:
			if (G_GOLD) {
				System.out.println("YOU WON!!");
				G_VICTORY = true;
			}
			break;
		}

	}

}
