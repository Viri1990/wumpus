package com.project.joan;

public class Player {

	private static final String GOAHEAD = "W";
	private static final String TURNLEFT = "A";
	private static final String TURNRIGHT = "D";
	private static final String SHOOT = "E";

	private static final int IDENTIFIER = 1;
	public static final String NORTH = "N";
	public static final String SOUTH = "S";
	public static final String EAST = "E";
	public static final String WEST = "W";

	private int x_position;
	private int y_position;
	private int arrows;
	private String orientation;
	boolean gold;

	public Player(int p_arrows) {
		x_position = 0;
		y_position = 0;
		orientation = EAST;
		this.arrows = p_arrows;
	}

	public void move() {
		
		goAhead();

	}

	public void goAhead() {
		switch (this.orientation) {
		case NORTH:
			setY_position(getY_position() + 1);
			break;
		case SOUTH:
			setY_position(getY_position() - 1);
			break;
		case EAST:
			setX_position(getX_position() + 1);
			break;
		case WEST:
			setX_position(getX_position() - 1);
			break;
		}
	}

	public void rotate(String p_dir) {
		if (p_dir.equalsIgnoreCase(TURNLEFT)) {
			switch (this.orientation) {
			case NORTH:
				this.orientation = WEST;
				break;
			case SOUTH:
				this.orientation = EAST;
				break;
			case EAST:
				this.orientation = NORTH;
				break;
			case WEST:
				this.orientation = SOUTH;
				break;
			}
		}
		else {
			switch (this.orientation) {
			case NORTH:
				this.orientation = EAST;
				break;
			case SOUTH:
				this.orientation = WEST;
				break;
			case EAST:
				this.orientation = SOUTH;
				break;
			case WEST:
				this.orientation = NORTH;
				break;
			}
		}
	}

	public void shoot() {
		System.out.println("Method shoot");
	}

	public static int getIdentifier() {
		return IDENTIFIER;
	}

	public int getX_position() {
		return x_position;
	}

	public void setX_position(int x_position) {
		this.x_position = x_position;
	}

	public int getY_position() {
		return y_position;
	}

	public void setY_position(int y_position) {
		this.y_position = y_position;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
}
