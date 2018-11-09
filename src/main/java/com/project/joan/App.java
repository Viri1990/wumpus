package com.project.joan;

import java.util.Scanner;

public class App {
	private static final String EXIT = "Q";
	private static final String GOAHEAD = "W";
	private static final String TURNLEFT = "A";
	private static final String TURNRIGHT = "D";
	private static final String SHOOT = "E";
	Scanner g_sc = new Scanner(System.in);
	Board g_board; 
	Player g_player;
	
	public static void main(String[] args) {
		App l_app = new App();
		l_app.createBoard();
		l_app.createPlayer();
		l_app.play();
	}
	
	public void createBoard() {
		System.out.println("WELCOME TO HUNT THE WUMPUS!!");
		System.out.println("Tell me the dimensions of your square board (min. 4 max. 10): ");
		int l_choice = g_sc.nextInt();
		g_board = new Board(l_choice);
		g_board.drawBoard();
	}
	
	public void createPlayer() {
		System.out.println("Tell me the arrows you want to dispose: ");
		int l_choice = g_sc.nextInt();
		g_player = new Player(l_choice);
	}
	
	public void play() {
		System.out.println("Instructions: \n Q-Exit \nW-Go Ahead \nA-Turn left \nD-Turn right \nE-Shoot");
		System.out.println("Let's start!!");
		String result = null;
		String l_entry = "";
		while (!l_entry.equalsIgnoreCase(EXIT)) {
			System.out.println("Press a key: ");
			l_entry = g_sc.nextLine().toUpperCase();
			switch (l_entry) {
			case EXIT: l_entry = EXIT; 
			result = EXIT;
			break;
			case GOAHEAD: g_player.goAhead(); 
			result = GOAHEAD;
			break;
			case TURNLEFT: g_player.rotate(TURNLEFT); 
			result = TURNLEFT;
			break;
			case TURNRIGHT: g_player.rotate(TURNLEFT); 
			result = TURNRIGHT;
			break;
			case SHOOT: g_player.shoot(); 
			result = SHOOT;
			break;
			default: result = "NON VALID CHOICE!"; break;
			}
			System.out.println("Your choice is: "+result);
		}

		
	}

}
