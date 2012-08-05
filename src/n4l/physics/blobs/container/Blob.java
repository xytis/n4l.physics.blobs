/**
 * 
 */
package n4l.physics.blobs.container;

import java.awt.geom.Point2D;

/**
 * @author xytis
 *
 */
public class Blob {
	private BlobContainer 	m_blobContainer = null;
	private Point2D.Double 	m_center = new Point2D.Double();
	private Point2D.Double 	m_velocity = new Point2D.Double();
	private Double			m_radius = new Double(1);
	
	public Blob(BlobContainer blobContainer, Point2D.Double center) {
		m_blobContainer = blobContainer;
		m_center.setLocation(center);
	}
	
	public Point2D.Double getCenter() {
		return m_center;
	}
	
	public Double getRadius() {
		return m_radius;
	}
	
	public void reflect(Point2D.Double point) {
		
	}
	
	public void update() {
		m_center.setLocation(m_center.getX() + m_velocity.getX(), m_center.getY() + m_velocity.getY());
	}
	
	public void render() {
		
	}
}
