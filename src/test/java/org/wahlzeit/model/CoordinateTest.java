package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CoordinateTest {

	private final double validX = 1000.0;
	private final double validY = 125.0;
	private final double validZ = 100.0;
	private final double validPhi = 0.12435499;
	private final double validTheta = 1.4718922;
	private final double validR = 1012.731455;

	@Test
	public void testCartesianInitialization() {
		CartesianCoordinate cc = null;
		boolean hasFailed = false;
		
		try {
			cc = new CartesianCoordinate(validX, validY, validZ);
		} catch (Exception e) {
			hasFailed = true;
		}
		assertNotNull(cc);
		assertFalse(hasFailed);
	}
	
	@Test
	public void testSphericInitialization() {
		SphericCoordinate sc = null;
		boolean hasFailed = false;
		
		try {
			sc = new SphericCoordinate(validPhi, validTheta, validR);
		} catch (Exception e) {
			hasFailed = true;
		}
		
		assertNotNull(sc);
		assertFalse(hasFailed);
	}

	@Test
	public void testAsSphericCoordinate() {
		CartesianCoordinate cc = new CartesianCoordinate(validX, validY, validZ);
		SphericCoordinate sc = cc.asSphericCoordinate();
		
		double delta = SphericCoordinate.MAXDELTA;
		assertNotNull(sc);
		assertEquals(validR, sc.getRadius(), delta);
		assertEquals(validPhi, sc.getPhi(), delta);
		assertEquals(validTheta, sc.getTheta(), delta);
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


	@Test
	public void testGetCartesianDistance() {
		CartesianCoordinate cc = new CartesianCoordinate(validX, validY, validZ);
		CartesianCoordinate cc2 = new CartesianCoordinate(validX + 100, validY, validZ);
		
		double delta = CartesianCoordinate.MAXDELTA;
		double expected = 100;
		double actual = cc.getCartesianDistance(cc2);
		boolean equals = Math.abs(expected-actual)<delta;
		
		assertTrue(equals);
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
		CartesianCoordinate cc = new CartesianCoordinate(validX, validY, validZ);
		CartesianCoordinate cc2 = new CartesianCoordinate(validX, validY, validZ);
		CartesianCoordinate cc3 = new CartesianCoordinate(validX + 10.0, validY, validZ);
		SphericCoordinate sc = new SphericCoordinate(validPhi, validTheta, validR);
		SphericCoordinate sc2 = new SphericCoordinate(validPhi, validTheta, validR);
		SphericCoordinate sc3 = new SphericCoordinate(validPhi + 1, validTheta, validR);
		
		boolean isEqual = cc.isEqual(cc2);
		boolean isEqual2 = cc.isEqual(cc3);
		boolean isEqual3 = sc.isEqual(sc2);
		boolean isEqual4 = sc.isEqual(sc3);
		boolean isEqual5 = sc.isEqual(cc);
	
		assertTrue(isEqual);
		assertFalse(isEqual2);
		assertTrue(isEqual3);
		assertFalse(isEqual4);
		assertTrue(isEqual5);
	}
	
	
}
