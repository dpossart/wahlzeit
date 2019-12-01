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

public interface Coordinate {

	/**
	 * converts the current coordinate to a cartesian coordinate
	 * 
	 * @methodtype conversion
	 * @return CartesianCoordinate
	 */
	public CartesianCoordinate asCartesianCoordinate();

	/**
	 * calculates the cartesian distance to another coordinate
	 * 
	 * @methodtype get
	 * @param coord valid interpretation of a coordinate
	 * @return the cartesian distance
	 */
	public double getCartesianDistance(Coordinate coord);

	/**
	 * converts the current coordinate to a spheric coordinate
	 * 
	 * @methodtype conversion
	 * @return SphericCoordinate
	 */
	public SphericCoordinate asSphericCoordinate();

	/**
	 * calculates the central angle to another coordinate
	 * 
	 * @methodtype get
	 * @param coord valid interpretation of a coordinate
	 * @return the central angle
	 */
	public double getCentralAngle(Coordinate coord);

	/**
	 * compares the current coordinate with another one and returns if the two
	 * coordinates are the same or semantic equal
	 * 
	 * @methodtype comparsion
	 * @param coord valid interpretation of a coordinate
	 * @return the comparsion result
	 */
	public boolean isEqual(Coordinate coord);

}