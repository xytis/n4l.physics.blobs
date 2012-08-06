/**
 * 
 */
package n4l.physics.blobs.container;

import java.awt.Graphics2D;

import n4l.physics.utils.Vector3;

/**
 * @author xytis
 *
 */
public class Link {
	private Blob lside;
	private Blob rside;
	
	private double length = 10;
	private double strength = 10;
	
	public Link(Blob l, Blob r) {
		lside = l;
		rside = r;
	}
	
	public Link(Blob l, Blob r, double length, double strength) {
		lside = l;
		rside = r;
		this.length = length;
		this.strength = strength;
	}
	
	public Vector3 force(Blob from) {
		Vector3 force;
		if (from == lside) {
			//Make a vector
			force = rside.getCenter().minus(lside.getCenter());
		} else {
			force = lside.getCenter().minus(rside.getCenter());
		}
		double distance = force.length();
		double power = (distance-length)*(distance-length)*strength;
		force = force.normalize().times(power); //May actually point to other direction.
		return force;
	}
	
	public void render(Graphics2D g) {
		g.drawLine((int)lside.getCenter().getX(), (int)lside.getCenter().getY(), (int)rside.getCenter().getX(), (int)rside.getCenter().getY());
	}
}
