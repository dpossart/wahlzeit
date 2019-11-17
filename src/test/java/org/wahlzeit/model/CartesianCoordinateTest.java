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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CartesianCoordinateTest {

	double validX = 1000.0;
	double validY = 125.0;
	double validZ = 100.0;

	@Test
	public void testInitialization() {
		CartesianCoordinate cc = null;
		boolean hasFailed = false;
		
		try {
			cc = new CartesianCoordinate(validX, validY, validZ);
		} catch (IllegalArgumentException e) {
			hasFailed = true;
		}
		assertNotNull(cc);
		assertFalse(hasFailed);
	}

	@Test
	public void testAsSphericCoordinate() {
		CartesianCoordinate cc = new CartesianCoordinate(validX, validY, validZ);
		
		SphericCoordinate sc = cc.asSphericCoordinate();
		
		double delta = 0.01;
		assertNotNull(sc);
		assertEquals(1012.73, sc.getRadius(), delta);
		assertEquals(0.12, sc.getPhi(), delta);
		assertEquals(1.47, sc.getTheta(), delta);
	}

	@Test
	public void testGetCartesianDistance() {
		CartesianCoordinate cc = new CartesianCoordinate(validX, validY, validZ);
		CartesianCoordinate cc2 = new CartesianCoordinate(validX + 100, validY, validZ);
		
		double distance = cc.getCartesianDistance(cc2);
		boolean equals = Double.compare(distance, 100) == 0;
		
		assertTrue(equals);
	}

	@Test
	public void testIsEqual() {
		CartesianCoordinate cc = new CartesianCoordinate(validX, validY, validZ);
		CartesianCoordinate cc2 = new CartesianCoordinate(validX, validY, validZ);
		CartesianCoordinate cc3 = new CartesianCoordinate(validX + 10.0, validY, validZ);

		boolean isEqual = cc.isEqual(cc2);
		boolean isEqual2 = cc.isEqual(cc3);

		assertTrue(isEqual);
		assertFalse(isEqual2);
	}

}
