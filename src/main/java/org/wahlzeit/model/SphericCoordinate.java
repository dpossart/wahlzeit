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

public class SphericCoordinate implements Coordinate {

	private double phi;
	private double theta;
	private double radius;

	public SphericCoordinate(double phi, double theta, double radius) {

		if ((Double.isNaN(phi)) || (Double.isNaN(theta)) || (Double.isNaN(radius)) || (Double.isInfinite(phi))
				|| (Double.isInfinite(theta)) || (Double.isInfinite(radius))) {
			throw new IllegalArgumentException("NaN or infinity");
		}

		if ((radius < 0) || (phi < 0) || (phi > 360) || (theta < 0) || (theta > 360)) {
			throw new IllegalArgumentException("only positives allowed, theta & phi 0-360");
		}

		this.phi = phi;
		this.theta = theta;
		this.radius = radius;
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		double x = radius * Math.cos(phi) * Math.sin(theta);
		double y = radius * Math.sin(phi) * Math.sin(theta);
		double z = radius * Math.cos(theta);
		return new CartesianCoordinate(x, y, z);
	}

	@Override
	public double getCartesianDistance(Coordinate coord) {
		return asCartesianCoordinate().getCartesianDistance(coord);
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		return this;
	}

	@Override
	public double getCentralAngle(Coordinate coord) {
		SphericCoordinate coord2 = coord.asSphericCoordinate();

		double deltaPhi = Math.abs(coord2.phi - phi);
		double sinTheta = Math.sin(theta) * Math.sin(coord2.theta);
		double cosTheta = Math.cos(theta) * Math.cos(coord2.theta);

		return Math.acos(sinTheta + cosTheta * Math.cos(deltaPhi));
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
		if (!(obj instanceof SphericCoordinate)) {
			return false;
		}

		SphericCoordinate coord2 = (SphericCoordinate) obj;
		boolean isEqualPhi = Double.compare(this.phi, coord2.phi) == 0;
		boolean isEqualTheta = Double.compare(this.theta, coord2.theta) == 0;
		boolean isEqualRadius = Double.compare(this.radius, coord2.radius) == 0;

		return isEqualPhi && isEqualTheta && isEqualRadius;
	}

	@Override
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + (int) phi;
		hash = 31 * hash + (int) theta;
		hash = 31 * hash + (int) radius;
		return hash;
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

}
