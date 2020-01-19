package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ChessPlayerTest {

	ChessPlayer cp;
	ChessPlayer cp1;
	ChessPlayer cp2;
	
	@Before
	public void setUp() {
		String validName = "Wassyl Ivanchuk";
		ChessPlayerType cpt = new ChessPlayerType(validName);
		ChessPlayerType cpt1 = new ChessPlayerType(validName);
		ChessPlayerType cpt2 = new ChessPlayerType(validName+"test");
		cp = new ChessPlayer(cpt);
		cp1 = new ChessPlayer(cpt1);
		cp2 = new ChessPlayer(cpt2);
	}
	
	@Test
	public void testGetId() {
		assertNotEquals(cp.getId(), cp1.getId());
	}
	
	@Test
	public void testEquals() {
		assertTrue(cp.equals(cp1));
		assertFalse(cp.equals(cp2));
	}

	@Test
	public void testHashCode() {
		assertNotEquals(cp.hashCode(),cp1.hashCode());
	}
}
