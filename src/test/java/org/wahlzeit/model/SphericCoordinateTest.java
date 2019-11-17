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

public class SphericCoordinateTest {

	double validPhi = 0.12435499;
	double validTheta = 1.4718922;
	double validR = 1012.731455;

	@Test
	public void testInitialization() {
		SphericCoordinate sc = null;
		boolean hasFailed = false;
		
		try {
			sc = new SphericCoordinate(validPhi, validTheta, validR);
		} catch (IllegalArgumentException e) {
			hasFailed = true;
		}
		
		assertNotNull(sc);
		assertFalse(hasFailed);
	}

	@Test
	public void testAsCartesianCoordinate() {
		SphericCoordinate sc = new SphericCoordinate(validPhi, validTheta, validR);
		
		CartesianCoordinate cc = sc.asCartesianCoordinate();
		
		double delta = 0.01;
		assertNotNull(cc);
		assertEquals(1000.0,cc.getX(),delta);
		assertEquals(125.0,cc.getY(),delta);
		assertEquals(100.0,cc.getZ(),delta);
	}

	//TODO
	@Test
	public void testGetCentralAngle() {
		
		//SphericCoordinate sc = new SphericCoordinate(validPhi, validTheta, validR);
		//SphericCoordinate sc2 = new SphericCoordinate(validPhi+100, validTheta, validR);
		//double centralAngle = sc.getCentralAngle(sc2);
		
	}

	@Test
	public void testIsEqual() {
		SphericCoordinate sc = new SphericCoordinate(validPhi, validTheta, validR);
		SphericCoordinate sc2 = new SphericCoordinate(validPhi, validTheta, validR);
		SphericCoordinate sc3 = new SphericCoordinate(validPhi + 1, validTheta, validR);

		boolean isEqual = sc.isEqual(sc2);
		boolean isEqual2 = sc.isEqual(sc3);

		assertTrue(isEqual);
		assertFalse(isEqual2);

	}
}
