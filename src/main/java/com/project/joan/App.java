package com.project.joan;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
	private static final String EXIT = "Q";
	private static final String GOAHEAD = "W";
	private static final String COUNTERCLOCKWISE = "A";
	private static final String CLOCKWISE = "D";
	private static final String SHOOT = "E";

	private static final int VOID = 0;
	private static final int WUMPUS = 2;
	private static final int STENCH = 3;
	private static final int CAVE = 4;
	private static final int WIND = 5;
	private static final int GOLD = 6;
	private static final int STENCHWIND = 8;
	private static final int INITIALPOS = 9;
	
	public static final String NORTH = "N";
	public static final String SOUTH = "S";
	public static final String EAST = "E";
	public static final String WEST = "W";

	private Scanner g_sc = new Scanner(System.in);
	private boolean G_ALIVE;
	private boolean G_GOLD;
	private boolean G_VICTORY;
	Board g_board;
	Player g_player;

	public static void main(String[] args) {
		App l_app = new App();
		l_app.start();
	}

	private void createBoard() {
		int l_dimBoard;
		int l_numCaves;
		do {
			System.out.println("Tell me the dimensions of your square board (min. 4 max. 10): ");
			while (!g_sc.hasNextInt()) {
				System.out.println("Please insert a valid number!");
				System.out.println("Tell me the dimensions of your square board (min. 4 max. 10): ");
				g_sc.next();
			}
			l_dimBoard = g_sc.nextInt();
		} while (l_dimBoard < 4 || l_dimBoard > 10);
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

	public void start() {
		System.out.println("WELCOME TO HUNT THE WUMPUS!!\n");

		int l_choice;
		do {
			System.out.println("What you want to do?\n 1-Play game \n 2-Read the instructions \n 3-Exit");
			l_choice = g_sc.nextInt();
			menu(l_choice);
		} while (l_choice != 3);
	}

	public void menu(int p_choice) {
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

	public void showInstructions() {
		System.out.println("\n\nInstructions: \n Q-Exit \nW-Go Ahead \nA-Turn left \nD-Turn right \nE-Shoot\n\n");
	}

	private void prepareGame() {
		G_ALIVE = true;
		G_GOLD = false;
		G_VICTORY = false;
		createBoard();
		createPlayer();
	}

	private void play() {
		prepareGame();
		System.out.println("Let's start!!");
		String l_entry = "";
		while (!l_entry.equalsIgnoreCase(EXIT) && G_ALIVE && !G_VICTORY) {
			position();
			System.out.println("\nPress a key: ");
			l_entry = g_sc.next().toUpperCase();
			String l_optChoosed = checkOptChoosed(l_entry);
		}
	}

	private void position() {
		System.out.println("My coordinates are: ");
		System.out.println("x= " + g_player.getX_position());
		System.out.println("y= " + g_player.getY_position());
		System.out.println("Orientation= " + g_player.getOrientation());
	}

	private String checkOptChoosed(String p_entry) {
		String l_result = null;
		switch (p_entry) {
		case EXIT:
			p_entry = EXIT;
			l_result = "EXIT";
			break;
		case GOAHEAD:
			checkMove();
			l_result = "GOAHEAD";
			break;
		case COUNTERCLOCKWISE:
			g_player.rotate(COUNTERCLOCKWISE);
			l_result = "COUNTER CLOCKWISE";
			break;
		case CLOCKWISE:
			g_player.rotate(CLOCKWISE);
			l_result = "CLOCKWISE";
			break;
		case SHOOT:
			g_player.shoot();
			checkKill();
			l_result = "SHOOT";
			break;
		default:
			l_result = "NON VALID CHOICE!";
			break;
		}
		return l_result;
	}
	
	private void checkKill() {
			switch (g_player.getOrientation()) {
			case NORTH:
				
				break;
			case SOUTH:
				
				break;
			case EAST:
				
				break;
			case WEST:
				
				break;
			}

	}

	private void checkMove() {
		if (!g_board.possibleGoAhead(g_player.getX_position(), g_player.getY_position(), g_player.getOrientation())) {
			System.out.println("YOU GET THE WALL!");
			return;
		}
		g_player.goAhead();
		refresh();
	}

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
