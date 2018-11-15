/**
 * 
 */
package com.project.joan;

/**
 * @author jdonetrodriguez
 *
 */
public enum Item {

	VOID(0), WUMPUS(2), STENCH(3), CAVE(4), WIND(5), GOLD(6), STENCHWIND(8), INITIALPOS(9);

	private int code;

	/**
	 * 
	 * @param p_code
	 */
	private Item(int p_code) {
		this.code = p_code;
	}

	/**
	 * 
	 * @return
	 */
	public int getCode() {
		return code;
	}
}
