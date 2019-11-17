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

public class CartesianCoordinate implements Coordinate {

	private double x;
	private double y;
	private double z;

	public CartesianCoordinate(double x, double y, double z) {
		if ((Double.isNaN(x)) || (Double.isNaN(y)) || (Double.isNaN(z)) || (Double.isInfinite(x))
				|| (Double.isInfinite(y)) || (Double.isInfinite(x))) {
			throw new IllegalArgumentException("NaN or Infinity");
		}

		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	@Override
	public double getCartesianDistance(Coordinate coord) {
		CartesianCoordinate coord2 = coord.asCartesianCoordinate();

		double difX = coord2.x - x;
		double difY = coord2.y - y;
		double difZ = coord2.z - z;
		
		double distX = difX*difX;
		double distY = difY*difY;
		double distZ = difZ*difZ;

		return Math.sqrt(distX + distY + distZ);
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		double quadSum = x*x + y*y+ z*z;
		double radius = Math.sqrt(quadSum);
		double phi = Math.atan2(y, x);
		double theta = Math.acos(z / radius);

		return new SphericCoordinate(phi, theta, radius);
	}

	@Override
	public double getCentralAngle(Coordinate coord) {
		return asSphericCoordinate().getCentralAngle(coord);
	}

	@Override
	public boolean isEqual(Coordinate coord) {
		return this.equals(coord);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CartesianCoordinate)) {
			return false;
		}

		CartesianCoordinate coord2 = (CartesianCoordinate) obj;
		boolean isEqualX = Double.compare(this.x, coord2.x) == 0;
		boolean isEqualY = Double.compare(this.y, coord2.y) == 0;
		boolean isEqualZ = Double.compare(this.z, coord2.z) == 0;

		return isEqualX && isEqualY && isEqualZ;
	}

	@Override
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + (int) x;
		hash = 31 * hash + (int) y;
		hash = 31 * hash + (int) z;
		return hash;
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

}
