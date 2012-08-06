/**
 * 
 */
package n4l.physics.blobs.container;

import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author xytis
 *
 */
public class BlobContainer {
	
	private LinkedList<Blob> m_blobList = new LinkedList<Blob>();
	
	public void addItem(Blob blob) {
		m_blobList.add(blob);
	}
	
	public void addList(LinkedList<Blob> blobList) {
		m_blobList.addAll(blobList);
	}
	
	public void checkCollision(Blob item) {
		ListIterator<Blob> it = m_blobList.listIterator();
		   
	    while(it.hasNext())
	    {
	    	Blob temp = it.next();
	    	if (item != temp) {
	    		if (item.close(temp)) {
	    			item.collide(temp);
	    		}
	    	}
	    }
	}
	
	public void update(double dt) {
		//Collections.sort(m_blobList, new BlobComparator());
		
		ListIterator<Blob> it = m_blobList.listIterator();
		   
	    while(it.hasNext())
	    {
	    	Blob item = it.next();
	    	checkCollision(item);
	    	item.update(dt);
	    }
	    
	    it = m_blobList.listIterator();
		   
	    while(it.hasNext())
	    {
	    	it.next().flush();
	    }
	}
	
	public void render(Graphics2D g) {
		ListIterator<Blob> it = m_blobList.listIterator();
		   
	    while(it.hasNext())
	    {
	    	it.next().render(g);
	    }
	}
	
}
