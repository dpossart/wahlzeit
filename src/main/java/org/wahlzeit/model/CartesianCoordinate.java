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

public final class CartesianCoordinate extends AbstractCoordinate {

	private static final Logger log = Logger.getLogger(CartesianCoordinate.class.getName());
	private static HashMap<String, CartesianCoordinate> ccoordMap = new HashMap<String, CartesianCoordinate>();

	private final double x;
	private final double y;
	private final double z;

	/**
	 * @methodtype constructor
	 * @param x valid finite double value
	 * @param y valid finite double value
	 * @param z valid finite double value
	 * @return CartesianCoordinate
	 */
	private CartesianCoordinate(double x, double y, double z) {
		if (Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z)) {
			throw new IllegalArgumentException("NaN value occurred");
		}

		if (Double.isInfinite(x) || Double.isInfinite(y) || Double.isInfinite(z)) {
			throw new IllegalArgumentException("infinity value occurred");
		}

		this.x = x;
		this.y = y;
		this.z = z;
		assertClassInvariants();
	}

	public static CartesianCoordinate getCartesianCoordinate(double x, double y, double z) {
		String key = ""+ x + y + z;
		CartesianCoordinate res = ccoordMap.get(key);
		if (res == null) {
			synchronized (ccoordMap) {
				res = ccoordMap.get(key);
				if (res == null) {
					res = new CartesianCoordinate(x, y, z);
					ccoordMap.put(key, res);
				}
			}
		}
		return res;
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		assertClassInvariants();
		double quadSum = x * x + y * y + z * z;
		double radius = Math.sqrt(quadSum);
		double phi = Math.atan2(y, x);
		double theta = Math.acos(z / radius);
		assertClassInvariants();
		SphericCoordinate sc;
		try {
			sc = SphericCoordinate.getSphericCoordinate(phi, theta, radius);
		} catch (IllegalArgumentException e) {
			log.warning("spheric coordinate conversion failed: " + e.getMessage());
			return null;
		}
		return sc;
	}

	/**
	 * @methodtype get
	 */
	public double getX() {
		return x;
	}

	/**
	 * @methodtype get
	 */
	public double getY() {
		return y;
	}

	/**
	 * @methodtype get
	 */
	public double getZ() {
		return z;
	}

	@Override
	protected void assertClassInvariants() {
		assert !Double.isNaN(x);
		assert !Double.isNaN(y);
		assert !Double.isNaN(z);

		assert Double.isFinite(x);
		assert Double.isFinite(y);
		assert Double.isFinite(z);
	}

}
