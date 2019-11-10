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

public class ChessPlayerPhotoTest {

	@Test
	public void testChessPlayerPhotoInit1() {
		String playerName = "Wassyl Ivanchuk";
		String place = "Gibraltar";
		int year = 2000;
		
		ChessPlayerPhoto cp = new ChessPlayerPhoto(playerName, place, year);
		
		assertEquals(playerName, cp.getPlayerName());
		assertEquals(place, cp.getPlace());
		assertEquals(year, cp.getYear());
		assertNotEquals("Bla", cp.getPlayerName());
		assertNotEquals("Bla Bla", cp.getPlace());
		assertNotEquals(111, cp.getYear());
	}
	
	@Test
	public void testChessPlayerPhotoInit2() {
		String playerName = "Wassyl Ivanchuk";
		String place = "Gibraltar";
		int year = 2000;
		PhotoId id = new PhotoId(123);
		
		ChessPlayerPhoto cp = new ChessPlayerPhoto(id, playerName, place, year);
		
		assertEquals(playerName, cp.getPlayerName());
		assertEquals(place, cp.getPlace());
		assertEquals(year, cp.getYear());
		assertEquals(id, cp.getId());
		assertNotEquals("Bla", cp.getPlayerName());
		assertNotEquals("Bla Bla", cp.getPlace());
		assertNotEquals(111, cp.getYear());
		assertNotEquals(new PhotoId(120931), cp.getId());
	}
}
