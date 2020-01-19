package org.wahlzeit.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ChessPlayerManagerTest {
	
	@Test
	public void testCreateChessPlayer() {
		String validName = "Wassyl Ivanchuk";
		ChessPlayerType cpt = new ChessPlayerType(validName);
		ChessPlayer cp = new ChessPlayer(cpt);
		ChessPlayer cp1 = ChessPlayerManager.getInstance().createChessPlayer(validName);
		assertTrue(cp.equals(cp1));
	}
}
