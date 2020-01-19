/*
 * Copyright (c) 2019 by Dennis Possart
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;

public class ChessPlayerPhotoTest {

	ChessPlayerManager man;

	@Before
	public void setUp() {
		man = ChessPlayerManager.getInstance();
	}

	@Test
	public void testChessPlayerPhotoInit1() {
		String playerName = "Wassyl Ivanchuk";
		ChessPlayer c = man.createChessPlayer(playerName);
		ChessPlayerPhoto cp = new ChessPlayerPhoto(c);

		assertEquals(playerName, cp.getChessPlayer().getChessPlayerType().getPlayerName());
	}

	@Test
	public void testChessPlayerPhotoInit2() {
		String playerName = "Wassyl Ivanchuk";
		PhotoId id = new PhotoId(123);
		ChessPlayer c = man.createChessPlayer(playerName);
		ChessPlayerPhoto cp = new ChessPlayerPhoto(id, c);

		assertEquals(playerName, cp.getChessPlayer().getChessPlayerType().getPlayerName());
		assertEquals(id, cp.getId());
		assertNotEquals("Bla", cp.getChessPlayer().getChessPlayerType().getPlayerName());
		assertNotEquals(new PhotoId(120931), cp.getId());
	}
}
