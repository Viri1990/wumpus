package com.project.joan;

public class ItemUtils {
	/**
	 * This method inverts the item logic from Item -> Code to Code -> Item
	 * 
	 * @param p_code
	 * @return
	 */
	public static Item getItemFromCode(int p_code) {
		for (Item i : Item.values()) {
			if (i.getCode() == p_code) {
				return i;
			}
		}
		return null;
	}

}
