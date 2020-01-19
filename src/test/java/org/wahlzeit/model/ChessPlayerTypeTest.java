package org.wahlzeit.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class ChessPlayerTypeTest {

	String validName = "Wassyl Ivanchuk";
	String invalidName = "0303";
	String invalidName1 = "";
	String invalidName2 = null;
	
	@Test
	public void testInit() {

		ChessPlayerType cp = new ChessPlayerType(validName);
		ChessPlayerType cp1 = null;
		ChessPlayerType cp2 = null;
		ChessPlayerType cp3 = null;
		boolean cp1failed = false;
		boolean cp2failed = false;
		boolean cp3failed = false;

		try {
			cp1 = new ChessPlayerType(invalidName);
		} catch (IllegalArgumentException e) {
			cp1failed = true;
		}

		try {
			cp2 = new ChessPlayerType(invalidName1);
		} catch (IllegalArgumentException e) {
			cp2failed = true;
		}

		try {
			cp3 = new ChessPlayerType(invalidName2);
		} catch (IllegalArgumentException e) {
			cp3failed = true;
		}

		assertEquals(validName, cp.getPlayerName());
		assertTrue(cp1failed);
		assertTrue(cp2failed);
		assertTrue(cp3failed);
	}
	
	@Test
	public void testCreateInstance() {
		ChessPlayerType cpt = new ChessPlayerType(validName);
		boolean created = cpt.createInstance() instanceof ChessPlayer ;
		assertTrue(created);
	}

	@Test
	public void testEquals() {
		ChessPlayerType cpt = new ChessPlayerType(validName);
		ChessPlayerType cp1 = new ChessPlayerType(validName);
		ChessPlayerType cp2 = new ChessPlayerType(validName+"test");
		assertTrue(cpt.equals(cp1));
		assertFalse(cpt.equals(cp2));
	}

	@Test
	public void testHashCode() {
		ChessPlayerType cpt = new ChessPlayerType(validName);
		ChessPlayerType cp1 = new ChessPlayerType(validName);
		ChessPlayerType cp2 = new ChessPlayerType(validName+"test");
		assertEquals(cpt.hashCode(),cp1.hashCode());
		assertNotEquals(cpt.hashCode(), cp2.hashCode());
	}
}
