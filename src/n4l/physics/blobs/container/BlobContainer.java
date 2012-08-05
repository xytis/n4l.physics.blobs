/**
 * 
 */
package n4l.physics.blobs.container;

import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author xytis
 *
 */
public class BlobContainer {
	
	private LinkedList<Blob> m_blobList;
	
	public boolean checkCollision(Blob item) {
		//Set up little scenes, basically sort the items
		return false;
	}
	
	public void update() {
		Collections.sort(m_blobList, new BlobComparator());
		
		ListIterator<Blob> it = m_blobList.listIterator();
		   
	    while(it.hasNext())
	    {
	    	Blob item = it.next();
	    	item.update();
	    }
	}
	
	public void render() {
		ListIterator<Blob> it = m_blobList.listIterator();
		   
	    while(it.hasNext())
	    {
	    	it.next().render();
	    }
	}
	
}
