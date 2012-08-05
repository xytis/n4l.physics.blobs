/**
 * 
 */
package n4l.physics.blobs.container;

import java.util.Comparator;

/**
 * @author xytis
 *
 */
public class BlobComparator implements Comparator<Blob>{
	@Override
	public int compare(Blob o1, Blob o2) {
		//X is first sorting order
		if (o1.getCenter().getX() > o2.getCenter().getX()) {
			return -1;
		}
		if (o1.getCenter().getX() < o2.getCenter().getX()) {
			return 1;
		}
		//if X is the same, rely on Y
		if (o1.getCenter().getY() > o2.getCenter().getY()) {
			return -1;
		}
		if (o1.getCenter().getY() < o2.getCenter().getY()) {
			return 1;
		}
		//Damn, these points have the same coords...
		return 0;
	}
}
