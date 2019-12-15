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

import java.util.HashMap;
import java.util.logging.Logger;

public final class SphericCoordinate extends AbstractCoordinate {

	private static final Logger log = Logger.getLogger(SphericCoordinate.class.getName());

	private static HashMap<String, SphericCoordinate> scoordMap = new HashMap<String, SphericCoordinate>();

	private final double phi;
	private final double theta;
	private final double radius;

	/**
	 * @methodtype constructor
	 * @param phi    valid finite double value within the range of 0.0 and 360.0
	 * @param theta  valid finite double value within the range of 0.0 and 360.0
	 * @param radius valid positive finite double value
	 * @return SphericCoordinate
	 */
	private SphericCoordinate(double phi, double theta, double radius) {
		if (Double.isNaN(radius) || Double.isNaN(phi) || Double.isNaN(theta)) {
			throw new IllegalArgumentException("NaN value occurred");
		}

		if (Double.isInfinite(radius) || Double.isInfinite(phi) || Double.isInfinite(theta)) {
			throw new IllegalArgumentException("infinity values occurred");
		}

		if (radius < 0.0 || phi < 0.0 || theta < 0.0) {
			throw new IllegalArgumentException("negatives are not allowed");
		}

		if (phi > 360.0 || theta > 360.0) {
			throw new IllegalArgumentException("phi or theta can't be bigger than 360.0");
		}

		this.phi = phi;
		this.theta = theta;
		this.radius = radius;
		assertClassInvariants();
	}

	public static SphericCoordinate getSphericCoordinate(double phi, double theta, double radius) {
		String key = "" + phi + theta + radius;
		SphericCoordinate res = scoordMap.get(key);
		if (res == null) {
			synchronized (scoordMap) {
				res = scoordMap.get(key);
				if (res == null) {
					res = new SphericCoordinate(phi, theta, radius);
					scoordMap.put(key, res);
				}
			}
		}
		return res;
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		assertClassInvariants();
		double x = radius * Math.cos(phi) * Math.sin(theta);
		double y = radius * Math.sin(phi) * Math.sin(theta);
		double z = radius * Math.cos(theta);
		assertClassInvariants();
		CartesianCoordinate cc;
		try {
			cc = CartesianCoordinate.getCartesianCoordinate(x, y, z);
		} catch (IllegalArgumentException e) {
			log.warning("cartesian coordinate conversion failed: " + e.getMessage());
			return null;
		}
		return cc;
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		return this;
	}

	/**
	 * @methodtype get
	 */
	public double getPhi() {
		return phi;
	}

	/**
	 * @methodtype get
	 */
	public double getTheta() {
		return theta;
	}

	/**
	 * @methodtype get
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	protected void assertClassInvariants() {
		assert !Double.isNaN(radius);
		assert !Double.isNaN(phi);
		assert !Double.isNaN(theta);

		assert Double.isFinite(radius);
		assert Double.isFinite(phi);
		assert Double.isFinite(theta);

		assert radius >= 0.0;
		assert phi >= 0.0;
		assert theta >= 0.0;

		assert phi <= 360.0;
		assert theta <= 360.0;
	}

}
