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
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;




public class ChessPlayerPhotoFactoryTest {

	ChessPlayerPhotoFactory f = null;
	ChessPlayerPhoto p1 = null;
	ChessPlayerPhoto p2 = null;
	String name = "Bobby";
	String place = "New York";
	int year = 1965;
	PhotoId id = new PhotoId(123);
	
	@Before
	public void setup() throws Exception {
		f = ChessPlayerPhotoFactory.getInstance();
		p1 = new ChessPlayerPhoto(name, place, year);
		p2  = new ChessPlayerPhoto(id, name, place, year);
	}
	
	@Test
	public void testCreatePhoto() {
		ChessPlayerPhoto cpp = f.createPhoto(name, place, year);
		assertNotNull(cpp);
		assertEquals(p1.getPlayerName(), cpp.getPlayerName());
		assertEquals(p1.getPlace(), cpp.getPlace());
		assertEquals(p1.getYear(), cpp.getYear());
	}
	
	public void testCreatePhoto2() {
		ChessPlayerPhoto cpp = f.createPhoto(name, place, year);
		assertNotNull(cpp);
		assertEquals(p2.getId(), cpp.getId());
		assertEquals(p2.getPlayerName(), cpp.getPlayerName());
		assertEquals(p2.getPlace(), cpp.getPlace());
		assertEquals(p2.getYear(), cpp.getYear());
	}
}
