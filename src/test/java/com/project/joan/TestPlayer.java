package com.project.joan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Junit5 test for Player class")
class TestPlayer {

	private Player p1;

	@BeforeEach
	public void initialise() {
		p1 = new Player(1);
	}

	@Test
	void testStartPosition() {
		assertTrue(p1.startPosition());
		p1.goAhead();
		assertFalse(p1.startPosition());
	}

	@Test
	void testCanShoot() {
		assertTrue(p1.canShoot());
		p1.shoot();
		assertFalse(p1.canShoot());
	}

	@Test
	void testGetOrientation() {
		assertEquals(p1.getOrientation(), "E");
		p1.rotate("A");
		assertEquals(p1.getOrientation(), "N");
	}

	@Test
	void testGetXPosition() {
		assertEquals(p1.getX_position(), 0);
		p1.goAhead();
		assertNotEquals(p1.getX_position(), 0);
		assertEquals(p1.getX_position(), 1);
	}

	@Test
	void testGetYPosition() {
		assertEquals(p1.getY_position(), 0);
		p1.goAhead();
		p1.rotate(Player.COUNTERCLOCKWISE);
		p1.goAhead();
		assertNotEquals(p1.getY_position(), 0);
		assertEquals(p1.getY_position(), 1);
	}
}
