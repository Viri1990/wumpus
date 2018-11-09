package com.project.joan;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner l_sc = new Scanner(System.in);
		System.out.println("WELCOME TO HUNT THE WUMPUS!!");
		System.out.println("Tell me the dimensions of your square board (min. 4 max. 10): ");
		int l_choice = l_sc.nextInt();
		Board b1 = new Board(l_choice);
		b1.drawBoard();

	}

}
