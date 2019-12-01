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

public abstract class AbstractCoordinate implements Coordinate {

	public static final double MAXDELTA = 0.001;

	@Override
	public double getCartesianDistance(Coordinate coord) {
		assertCoordinateIsNull(coord);
		assertClassInvariants();

		CartesianCoordinate cur = this.asCartesianCoordinate();
		CartesianCoordinate coord2 = coord.asCartesianCoordinate();

		double difX = coord2.getX() - cur.getX();
		double difY = coord2.getY() - cur.getY();
		double difZ = coord2.getZ() - cur.getZ();

		double distX = difX * difX;
		double distY = difY * difY;
		double distZ = difZ * difZ;

		double distance = Math.sqrt(distX + distY + distZ);

		assertClassInvariants();
		assert !Double.isNaN(distance);
		assert Double.isFinite(distance);
		assert distance >= 0.0;

		return distance;
	}

	@Override
	public double getCentralAngle(Coordinate coord) {
		assertCoordinateIsNull(coord);
		assertClassInvariants();

		SphericCoordinate cur = this.asSphericCoordinate();
		SphericCoordinate coord2 = coord.asSphericCoordinate();

		double deltaPhi = Math.abs(coord2.getPhi() - cur.getPhi());
		double sinTheta = Math.sin(cur.getTheta()) * Math.sin(coord2.getTheta());
		double cosTheta = Math.cos(cur.getTheta()) * Math.cos(coord2.getTheta());

		double centralAngle = Math.acos(sinTheta + cosTheta * Math.cos(deltaPhi));

		assertClassInvariants();
		assert !Double.isNaN(centralAngle);
		assert Double.isFinite(centralAngle);
		assert centralAngle >= 0.0;
		assert centralAngle <= 360.0;

		return centralAngle;
	}

	@Override
	public boolean isEqual(Coordinate coord) {
		return equals(coord);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		CartesianCoordinate cur = this.asCartesianCoordinate();
		Coordinate coord = (Coordinate) obj;
		CartesianCoordinate coord2 = coord.asCartesianCoordinate();
		boolean isEqualX = Math.abs(cur.getX() - coord2.getX()) < MAXDELTA;
		boolean isEqualY = Math.abs(cur.getY() - coord2.getY()) < MAXDELTA;
		boolean isEqualZ = Math.abs(cur.getZ() - coord2.getZ()) < MAXDELTA;

		return isEqualX && isEqualY && isEqualZ;
	}

	@Override
	public int hashCode() {
		CartesianCoordinate cur = this.asCartesianCoordinate();
		int hash = 17;
		hash = 31 * hash + (int) cur.getX();
		hash = 31 * hash + (int) cur.getY();
		hash = 31 * hash + (int) cur.getZ();
		return hash;
	}
	
	protected void assertCoordinateIsNull(Coordinate coord) {
		if(coord == null) {
			throw new IllegalArgumentException("coordinate is null");
		}
	}

	abstract protected void assertClassInvariants();
}
