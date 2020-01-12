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
	private final double validPhi = 0.12;
	private final double validTheta = 1.47;
	private final double validR = 1012.73;
	private final double con = 0.000001;

	@Test
	public void testCartesianInitialization() {
		CartesianCoordinate cc = null;
		boolean hasFailed = false;
		
		try {
			cc = CartesianCoordinate.getCartesianCoordinate(validX, validY, validZ);
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
			sc = SphericCoordinate.getSphericCoordinate(validPhi, validTheta, validR);
		} catch (Exception e) {
			hasFailed = true;
		}
		
		assertNotNull(sc);
		assertFalse(hasFailed);
	}

	@Test
	public void testAsSphericCoordinate() {
		CartesianCoordinate cc = CartesianCoordinate.getCartesianCoordinate(validX, validY, validZ);
		SphericCoordinate sc = cc.asSphericCoordinate();
		
		double delta = SphericCoordinate.MAXDELTA;
		assertNotNull(sc);
		assertEquals(validR, sc.getRadius(), delta);
		assertEquals(validPhi, sc.getPhi(), delta);
		assertEquals(validTheta, sc.getTheta(), delta);
	}
	
	@Test
	public void testAsCartesianCoordinate() {
		SphericCoordinate sc = SphericCoordinate.getSphericCoordinate(validPhi, validTheta, validR);;
		
		CartesianCoordinate cc = sc.asCartesianCoordinate();
		
		double delta = 1;
		assertNotNull(cc);
		assertEquals(1000.0,cc.getX(),delta);
		assertEquals(120.0,cc.getY(),delta);
		assertEquals(102.0,cc.getZ(),delta);
	}


	@Test
	public void testGetCartesianDistance() {
		CartesianCoordinate cc = CartesianCoordinate.getCartesianCoordinate(validX, validY, validZ);
		CartesianCoordinate cc2 = CartesianCoordinate.getCartesianCoordinate(validX +100, validY, validZ);
		
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
		CartesianCoordinate cc = CartesianCoordinate.getCartesianCoordinate(validX, validY, validZ);;
		CartesianCoordinate cc2 =CartesianCoordinate.getCartesianCoordinate(validX, validY, validZ);
		CartesianCoordinate cc3 = CartesianCoordinate.getCartesianCoordinate(validX+100, validY, validZ);
		SphericCoordinate sc = SphericCoordinate.getSphericCoordinate(validPhi, validTheta, validR);;
		SphericCoordinate sc2 = SphericCoordinate.getSphericCoordinate(validPhi, validTheta, validR);
		SphericCoordinate sc3 = SphericCoordinate.getSphericCoordinate(validPhi+1, validTheta, validR);
		
		boolean isEqual = cc.isEqual(cc2);
		boolean isEqual2 = cc.isEqual(cc3);
		boolean isEqual3 = sc.isEqual(sc2);
		boolean isEqual4 = sc.isEqual(sc3);
		
		assertTrue(isEqual);
		assertFalse(isEqual2);
		assertTrue(isEqual3);
		assertFalse(isEqual4);
	}
	
	@Test
	public void testCartesianSharing() {
		CartesianCoordinate cc = CartesianCoordinate.getCartesianCoordinate(validX, validY, validZ);
		CartesianCoordinate cc2 = CartesianCoordinate.getCartesianCoordinate(validX+con, validY+con, validZ+con);
		CartesianCoordinate cc3 = CartesianCoordinate.getCartesianCoordinate(1,1,1);
		assertTrue(cc==cc2);
		assertFalse(cc==cc3);
	}
	
	@Test
	public void testSphericSharing() {
		SphericCoordinate sc = SphericCoordinate.getSphericCoordinate(validPhi, validTheta, validR);
		SphericCoordinate sc2 = SphericCoordinate.getSphericCoordinate(validPhi+con, validTheta+con, validR+con);
		SphericCoordinate sc3 = SphericCoordinate.getSphericCoordinate(10,10,10);
		assertTrue(sc==sc2);
		assertFalse(sc==sc3);
	}
	
	@Test
	public void testCartesianImmutability() {
		CartesianCoordinate cc = CartesianCoordinate.getCartesianCoordinate(validX, validY, validZ);
		CartesianCoordinate cc2 = CartesianCoordinate.getCartesianCoordinate(1, 1, 1);
		double x = cc.getX();
		double y = cc.getY();
		double z = cc.getZ();
		cc.getCartesianDistance(cc2);
		cc.getCentralAngle(cc2);
		cc.isEqual(cc2);
		cc.asSphericCoordinate().asCartesianCoordinate();
		assertTrue(x == cc.getX());
		assertTrue(y == cc.getY());
		assertTrue(z == cc.getZ());
	}
	
	@Test
	public void testSphericImmutability() {
		SphericCoordinate sc = SphericCoordinate.getSphericCoordinate(validPhi, validTheta, validR);
		SphericCoordinate sc2 = SphericCoordinate.getSphericCoordinate(10, 10, 10);
		double p = sc.getPhi();
		double t = sc.getTheta();
		double r = sc.getRadius();
		sc.getCartesianDistance(sc2);
		sc.getCentralAngle(sc2);
		sc.isEqual(sc2);
		sc.asCartesianCoordinate().asSphericCoordinate();
		assertTrue(p == sc.getPhi());
		assertTrue(t == sc.getTheta());
		assertTrue(r == sc.getRadius());
	}
	
	
	
	
}
