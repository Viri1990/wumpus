package com.project.joan;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class App {
	private static final String EXIT = "Q";
	private static final String GOAHEAD = "W";
	private static final String TURNLEFT = "A";
	private static final String TURNRIGHT = "D";
	private static final String SHOOT = "E";
	
	private static final int VOID = 0;
	private static final int WUMPUS = 2;
	private static final int STENCH = 3;
	private static final int CAVE = 4;
	private static final int WIND = 5;
	private static final int GOLD = 6;
	private static final int STENCHWIND = 8;
	
	private Scanner g_sc = new Scanner(System.in);
	private boolean G_ALIVE;
	private boolean G_GOLD;
	Board g_board;
	Player g_player;

	public static void main(String[] args) {
		App l_app = new App();
		l_app.menu();
	}

	public void createBoard() {
		System.out.println("Tell me the dimensions of your square board (min. 4 max. 10): ");
		int l_choiceDimensions = g_sc.nextInt();
		System.out.println("Tell me the number of caves (min. 4 max. 10): ");
		int l_choiceCavesNum = g_sc.nextInt();
		g_board = new Board(l_choiceDimensions, l_choiceCavesNum);
		g_board.drawBoard();
		g_board.initialisePieces();
		g_board.drawBoard();
	}

	public void createPlayer() {
		System.out.println("Tell me the arrows you want to dispose: ");
		int l_choice = g_sc.nextInt();
		g_player = new Player(l_choice);
	}

	public void menu() {
		System.out.println("WELCOME TO HUNT THE WUMPUS!!\n");
		int l_choice = 0;
		while (l_choice != 3) {
			System.out.println("What you want to do?\n 1-Play game \n 2-Read the instructions \n 3-Exit");
			l_choice = g_sc.nextInt();
			switch (l_choice) {
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
	}

	public void showInstructions() {
		System.out.println("\n\nInstructions: \n Q-Exit \nW-Go Ahead \nA-Turn left \nD-Turn right \nE-Shoot\n\n");
	}

	public void play() {
		G_ALIVE = true;
		G_GOLD = false;
		createBoard();
		createPlayer();
		System.out.println("Let's start!!");
		String result = null;
		String l_entry = "";
		while (!l_entry.equalsIgnoreCase(EXIT) && G_ALIVE) {
			System.out.println("My coordinates are: ");
			System.out.println("x= " + g_player.getX_position());
			System.out.println("y= " + g_player.getY_position());
			System.out.println("Orientation= " + g_player.getOrientation());
			System.out.println("\nPress a key: ");
			l_entry = g_sc.next().toUpperCase();
			switch (l_entry) {
			case EXIT:
				l_entry = EXIT;
				result = EXIT;
				break;
			case GOAHEAD:
				checkMove();
				result = GOAHEAD;
				break;
			case TURNLEFT:
				g_player.rotate(TURNLEFT);
				result = TURNLEFT;
				break;
			case TURNRIGHT:
				g_player.rotate(TURNRIGHT);
				result = TURNRIGHT;
				break;
			case SHOOT:
				g_player.shoot();
				result = SHOOT;
				break;
			default:
				result = "NON VALID CHOICE!";
				break;
			}
			System.out.println("Your choice is: " + result);
			if(isVictory()) {
				System.out.println("YOU WON!!");
				return;
			}
		}

	}
	
	private boolean isVictory() {
		if(g_player.startPosition() && G_GOLD) {
			return true;
		}
		return false;
	}
	
	private void checkMove() {
		if (!g_board.possibleGoAhead(g_player.getX_position(), g_player.getY_position(),
				g_player.getOrientation())) {
			System.out.println("YOU GET THE WALL!");
			return;
		}
		g_player.goAhead();
		refresh();
	}
	
	private void refresh() {
		switch(g_board.whoIs(g_player.getX_position(), g_player.getY_position())) {
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
		}
		
	}

}
