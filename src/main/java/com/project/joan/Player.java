package com.project.joan;

public class Player {

	private static final String NORTH = "N";
	private static final String SOUTH = "S";
	private static final String EAST = "E";
	private static final String WEST = "W";

	private int arrows;
	private String direction;
	private int[] location;
	boolean gold;
	
	public Player(int p_arr) {
		arrows = p_arr;
		direction = EAST;
	}
	
	public void goAhead() {
		System.out.println("Method goAhead");
	}
	
	public void rotate(String p_dir) {
		System.out.println("Method rotate");
	}
	public void shoot() {
		System.out.println("Method shoot");
	}
	
}
