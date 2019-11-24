package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

	public static final double MAXDELTA = 0.001;
	
	@Override
	public double getCartesianDistance(Coordinate coord) {
		CartesianCoordinate cur = this.asCartesianCoordinate();
		CartesianCoordinate coord2 = coord.asCartesianCoordinate();

		double difX = coord2.getX() - cur.getX();
		double difY = coord2.getY() - cur.getY();
		double difZ = coord2.getZ() - cur.getZ();
		
		double distX = difX*difX;
		double distY = difY*difY;
		double distZ = difZ*difZ;

		return Math.sqrt(distX + distY + distZ);
	}
	
	@Override
	public double getCentralAngle(Coordinate coord) {
		SphericCoordinate cur = this.asSphericCoordinate();		
		SphericCoordinate coord2 = coord.asSphericCoordinate();

		double deltaPhi = Math.abs(coord2.getPhi() - cur.getPhi());
		double sinTheta = Math.sin(cur.getTheta()) * Math.sin(coord2.getTheta());
		double cosTheta = Math.cos(cur.getTheta()) * Math.cos(coord2.getTheta());

		return Math.acos(sinTheta + cosTheta * Math.cos(deltaPhi));
	}
	
	@Override
	public boolean isEqual(Coordinate coord) {
		return equals(coord);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if (this == obj) {
			return true;
		}
		
		CartesianCoordinate cur = this.asCartesianCoordinate();
		Coordinate coord = (Coordinate) obj;
		CartesianCoordinate coord2 = coord.asCartesianCoordinate();
		boolean isEqualX = Math.abs(cur.getX()-coord2.getX()) < MAXDELTA;
		boolean isEqualY = Math.abs(cur.getY()-coord2.getY()) < MAXDELTA;
		boolean isEqualZ = Math.abs(cur.getZ()-coord2.getZ()) < MAXDELTA;

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
}
