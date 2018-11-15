package com.project.joan;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBoard {

	Board b1;
	Board b2;
	Player p1;

	@BeforeEach
	void initialise() {
		b1 = new Board(6, 5);
		b1.initialisePieces();
		p1 = new Player(1);
	}

	@Test
	void testWhoIs() {
		assertEquals(b1.whoIs(0, 0), Item.INITIALPOS);
	}

	@Test
	void testIsThere() {
		assertTrue(b1.isThere(Item.INITIALPOS, 0, 0));
	}

	@Test
	void testPossibleGoAhead() {
		assertTrue(b1.possibleGoAhead(p1.getX_position(), p1.getY_position(), p1.getOrientation()));
		p1.goAhead();
		assertTrue(b1.possibleGoAhead(p1.getX_position(), p1.getY_position(), p1.getOrientation()));
		p1.goAhead();
		assertTrue(b1.possibleGoAhead(p1.getX_position(), p1.getY_position(), p1.getOrientation()));
		p1.goAhead();
		assertTrue(b1.possibleGoAhead(p1.getX_position(), p1.getY_position(), p1.getOrientation()));
		p1.goAhead();
		assertTrue(b1.possibleGoAhead(p1.getX_position(), p1.getY_position(), p1.getOrientation()));
		p1.goAhead();
		assertFalse(b1.possibleGoAhead(p1.getX_position(), p1.getY_position(), p1.getOrientation()));

	}

	@Test
	void testGetG_boardDimensions() {
		assertEquals(b1.getG_boardDimensions(), 6);
		b2 = new Board(8, 6);
		assertEquals(b2.getG_boardDimensions(), 8);
	}

}
