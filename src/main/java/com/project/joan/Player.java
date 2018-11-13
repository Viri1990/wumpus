package com.project.joan;

public class Player {

	//All possibles orientations of the player
	public final String GOAHEAD = "W";
	public static final String COUNTERCLOCKWISE = "A";
	public static final String CLOCKWISE = "D";
	public final String SHOOT = "E";

	//Identifier of the player on the board
	private final int IDENTIFIER = 1;
	
	//All possibles orientations of the player
	public static final String NORTH = "N";
	public static final String SOUTH = "S";
	public static final String EAST = "E";
	public static final String WEST = "W";

	//Characteristics of the player
	private int x_position;
	private int y_position;
	private int arrows;
	private String orientation;
	boolean gold;


	//Player will be initialised on the initial position, orientation EAST and with the numb of arrows chose
	public Player(int p_arrows) {
		x_position = 0;
		y_position = 0;
		orientation = EAST;
		this.arrows = p_arrows;
	}

	//Method responsible to advance the player in base of the orientation it has
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

	//Rotate the player in base of the direction and the orientation it has
	public void rotate(String p_dir) {
		if (p_dir.equalsIgnoreCase(COUNTERCLOCKWISE)) {
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

	//Check if it still having the possibility to shoot
	public boolean canShoot() {
		if(arrows>0) {
			return true;
		}
		System.out.println("You already used all the arrows!");	
		return false;
	}
	
	public void shoot() {
		this.arrows = this.arrows-1;
		System.out.println("SHOOT!");
	}
	
	//Check if the player is in the initial position
	public boolean startPosition() {
		if(getX_position()==0 && getY_position()==0) {
			return true;
		}
		return false;
	}

	public int getIdentifier() {
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
