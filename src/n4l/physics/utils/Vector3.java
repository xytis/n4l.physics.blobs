/**
 * 
 */
package n4l.physics.utils;


/**
 * @author xytis
 *
 */
public class Vector3 {
	private double x = 0;
	private double y = 0;
	private double z = 0;
	
	public Vector3() {
		
	}
	
	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public double getZ() {
		return this.z;
	}
	
	// Assign operations 
	public Vector3 copy() {
		return new Vector3(this.getX(), this.getY(), this.getZ());
	}
	
	// Math operations
	public Vector3 plus(Vector3 value) {
		return new Vector3(this.getX() + value.getX(), this.getY() + value.getY(), this.getZ() + value.getZ());
	}
	
	public Vector3 minus(Vector3 value) {
		return new Vector3(this.getX() - value.getX(), this.getY() - value.getY(), this.getZ() - value.getZ());
	}
	
	public Vector3 times(double value) {
		return new Vector3(this.getX() * value, this.getY() * value, this.getZ() * value);
	}
	
	public double times(Vector3 value) {
		return this.getX()*value.getX() + this.getY()*value.getY() + this.getZ()*value.getZ();
	}
	
	public Vector3 cross(Vector3 value) {
		return new Vector3(
				this.getY()*value.getZ() - this.getZ()*value.getY(),
				this.getZ()*value.getX() - this.getX()*value.getZ(),
				this.getX()*value.getY() - this.getY()*value.getX());
	}
	
	public Vector3 opposite() {
		return new Vector3( -this.getX(), -this.getY(), -this.getZ());
	}
	
	/**
     * Returns a length of this vector
     * 
     * @return double length.
     */
	public double length() {
		return Math.sqrt(this.getX()*this.getX() + this.getY()*this.getY());
	}
	
	/**
     * Returns a new normalized vector
     * 
     * @return Normalized vector of same direction.
     */
	public Vector3 normalize() {
		return this.copy().times(1.0/this.length());
	}
	
	/**
	 * If treated as a point, returns the distance
	 * @param to
	 * @return distance
	 */
	public double distance(Vector3 to) {
		//(x,y) to (a,b) --> ((a-x),(b-y)).length() 
		return to.minus(this).length();
	}
}
