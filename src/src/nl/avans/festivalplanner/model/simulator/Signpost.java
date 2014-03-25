/**
 * 
 */
package nl.avans.festivalplanner.model.simulator;

import java.awt.Dimension;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Jordy Sipkema
 *
 */
public class Signpost extends Building
{
	private static final long serialVersionUID = -2015151528368231571L;

	private HashSet<Element> routes = new HashSet<>();
	
	/**
	 * @param _size
	 * @param _position
	 */
	public Signpost(Dimension _size, Vector _position)
	{
		super(new Dimension(50,50), _position);
		super.setImage("");
	}

	/**
	 * Adds a pointer to this signpost.
	 * Note: this element should be directly accessible from this signpost!
	 * 
	 * @param element The element to be added to this signpost.
	 * @return Returns true if the element is added.
	 */
	public boolean addPointer(Element element){
		return routes.add(element);
	}
	
	/**
	 * Removes a pointer from this signpost.
	 * @param element The element to be removed.
	 */
	public void removePointer(Element element){
		routes.remove(element);
	}

	/**
	 * Checks if a pointer to this element is on this signpost.
	 * @param element The element to check 
	 * @return Returns true if this element is on this signpost.
	 */
	public boolean containsPointerTo(Element element){
		return routes.contains(element);
	}
	
	/**
	 * Gets all signposts this signpost references to.
	 * @return Set<Signpost> or a empty set is there are no signposts referenced.
	 */
	public Set<Signpost> getReferencedSignposts(){
		Set<Signpost> sp = new HashSet<>(); 
		Iterator<Element> it = routes.iterator();
		while(it.hasNext()){
			Element e = (Element) it.next();
			if (e instanceof Signpost){
				sp.add((Signpost)e);
			}
		}
		
		if(sp.isEmpty()){
			return Collections.emptySet();
		}
		return sp;
	}
	
}
