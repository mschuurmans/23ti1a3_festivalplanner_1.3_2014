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
		super.setImage("bin/SIGNPOST.png");
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
	
	public Set<Element> getAllPointers(){
		return this.routes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((routes == null) ? 0 : routes.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Signpost))
			return false;
		Signpost other = (Signpost) obj;
		if (routes == null)
		{
			if (other.routes != null)
				return false;
		}
		else if (!routes.equals(other.routes))
			return false;
		return true;
	}


}
