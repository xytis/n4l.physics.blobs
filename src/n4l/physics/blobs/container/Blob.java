/**
 * 
 */
package n4l.physics.blobs.container;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.ListIterator;

import n4l.physics.utils.Vector3;


/**
 * @author xytis
 *
 */
public class Blob {
	//private BlobContainer 	m_blobContainer = null;
	private Vector3 		m_center = new Vector3();
	private Vector3 		m_velocity = new Vector3();
	private Vector3			m_acceleration = new Vector3();
	
	private Vector3 		m_next_center = new Vector3();
	private Vector3 		m_next_velocity = new Vector3();
	private Vector3			m_next_acceleration = new Vector3();
	
	private Double			m_radius = new Double(10);
	private Double			m_mass = new Double(1);
	
	private LinkedList<Link> m_links = new LinkedList<Link>();
	
	public Blob(Vector3 center, Vector3 velocity) {
		m_center = center.copy();
		m_velocity = velocity.copy();
	}
	
	public Blob(Vector3 center) {
		m_center = center.copy();
	}
	
	public Vector3 getCenter() {
		return m_center;
	}
	
	public Double getRadius() {
		return m_radius;
	}
	
	public Double getMass() {
		return m_mass;
	}
	
	public Vector3 getVelocity() {
		return m_velocity;
	}
	
	public void setVelocity(Vector3 velocity) {
		m_next_velocity = velocity;
	}
	
	public void linkTo(Blob blob) {
		Link link = new Link(this, blob);
		this.addLink(link);
		blob.addLink(link);
	}
	
	public void addLink(Link link) {
		m_links.add(link);
	}
	
	/**
	 * Returns true if the Blobs are in their radius reach.
	 * @param to
	 * @return
	 */
	public boolean close(Blob to) {
		return this.getCenter().distance(to.getCenter()) <= (this.getRadius() + to.getRadius());
	}
	
	/**
	 * Forces to collide with a Blob
	 * 
	 * @param a Blob
	 */
	public void collide(Blob with) {
		//Fuck thinking.
		//http://wp.freya.no/3d-math-and-physics/simple-sphere-sphere-collision-detection-and-collision-response/
		
		//Find a basis vector to serve as a x axis:
		Vector3 x = this.getCenter().minus(with.getCenter());
		x = x.normalize();
		
		Vector3 v1 = this.getVelocity();
		double x1 = x.times(v1);
		
		Vector3 v1x = x.times(x1);
		Vector3 v1y = v1.minus(v1x);
		
		double m1 = this.getMass();
		
		//Reverse the x axis
		x = x.times(-1);
		
		Vector3 v2 = with.getVelocity();
		double x2 = x.times(v2);
		
		Vector3 v2x = x.times(x2);
		Vector3 v2y = v1.minus(v2x);
		
		double m2 = with.getMass();
		
		//Setter
		this.setVelocity(v1x.times((m1-m2)/(m1+m2)).plus(v2x.times((2*m2)/(m1+m2))).plus(v1y));
		with.setVelocity(v1x.times((2*m2)/(m1+m2)).plus(v2x.times((m1-m2)/(m1+m2))).plus(v2y));
		
	}
	
	public void reflect(Vector3 normale) {
		//v' = v - 2 (v*n)*n
		m_next_velocity = m_velocity.minus(normale.times(m_velocity.times(normale)).times(2));
	}
	
	public void update(double dt) {
		ListIterator<Link> it = m_links.listIterator();
		
		m_next_acceleration = new Vector3(0,0,0);
	    while(it.hasNext())
	    {
	    	Link link = it.next();
	    	m_next_acceleration = m_next_acceleration.plus(link.force(this));
	    }
	    
	    m_next_velocity = m_acceleration.times(this.getMass()*dt);//m_velocity.plus(m_acceleration.times(this.getMass()*dt));
	    
	    m_next_center = m_center.plus(m_velocity.times(dt));
	}
	
	public void flush() {
		m_center = m_next_center.copy();
		m_velocity = m_next_velocity.copy();
		m_acceleration = m_next_acceleration.copy();
	}
	
	public void render(Graphics2D g) {
		g.drawOval(
				(int) (this.getCenter().getX() - this.getRadius()/2),
				(int) (this.getCenter().getY() - this.getRadius()/2),
				this.getRadius().intValue(),
				this.getRadius().intValue());
		
		//Force lines:
		g.setColor(new Color(128,40,128));
		g.drawLine(
				(int) (this.getCenter().getX()),
				(int) (this.getCenter().getY()),
				(int) (this.getCenter().getX() + m_acceleration.getX()),
				(int) (this.getCenter().getY() + m_acceleration.getY()));
		g.setColor(Color.white);
		
		ListIterator<Link> it = m_links.listIterator();
		   
	    while(it.hasNext())
	    {
	    	it.next().render(g);
	    }
	}
}
