package nl.avans.festivalplanner.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nl.avans.festivalplanner.model.simulator.Element;

/**
 * RouteManager (Singleton)
 * 
 * @author Jordy Sipkema
 */
public class RouteManager implements Serializable
{
	private static final long serialVersionUID = -2185251932877684767L;

	private static RouteManager _instance = null;
	private HashMap<Element, List<Element>> _nodeList = new HashMap<>();

	private RouteManager()
	{
	}

	/**
	 * Gives the next node someone must walk to in order to reach the final
	 * destination
	 * 
	 * @param destination
	 *            the final destination
	 * @param location
	 *            the current location
	 * @return The next node (this could be the final destination)
	 * @author Jordy Sipkema
	 */
	public Element getNextDestination(Element destination, Element location)
	{
		//Als de huidige locatie gelijk is aan het doel, dan zijn we er al.
		if (destination.equals(location)){
			return destination;
		}
		
		//Bestaat het element wil in de lijst?
		if (_nodeList.containsKey(destination)){
			Element nextstep = destination; 
			
			while(_nodeList.containsKey(nextstep)){
				List<Element> result = _nodeList.get(nextstep);
				if (result.contains(location)){
					return nextstep;
				}
				
				for (Element e : result){
					if (!e.equals(destination)){
						return this.getNextDestination(e, location);
					}
				}
			}
			
		}
		
		//Mocht er iets fout gaan, speel dan maar vals en loop rechtstreeks naar het doel ;)
		return destination;
	}

	/**
	 * Adds a node to the RouteManager-list. WARNING: If that destination is
	 * already in the list, it's through will be added (multiple points!).
	 * dest. <--+add+--> thro. 
	 * @param destination
	 *            the final destination
	 * @param through
	 *            the point someone must pass in order to get to the
	 *            destination.
	 * @author Jordy Sipkema
	 */
	public void addNode(Element destination, Element through)
	{
		List<Element> list = null;
		list = _nodeList.containsKey(destination) ? _nodeList.get(destination)
				: new ArrayList<Element>(); // Als de key bestaat, haal huidige
											// lijst op; Anders: maak een nieuwe
		
		/*
		 * UITLEG BOVENSTAANDE CODE
		 * ------------------------
		 * De code die hierboven staat, is gelijk aan deze code:
		 * 
		 * if (_nodeList.containsKey(destination)){
		 * 		list = _nodeList.get(destination);
		 * }else{
		 * 		list = new ArrayList<Element>();
		 * }
		 */
		
		
		list.add(through);
		_nodeList.put(destination, list);

		list = null; // Resetten voor volgende stap:

		list = _nodeList.containsKey(through) ? _nodeList.get(through)
				: new ArrayList<Element>(); // Als de key bestaat, haal huidige
											// lijst op; Anders: maak een nieuwe
		list.add(destination);
		_nodeList.put(through, list);
	}

	/**
	 * Removes a node from the RouteManager-list Example: if that destination
	 * doesn't exist anymore.
	 * Dest <--/remove/-- ???  
	 * @param destination
	 *            The final-node
	 * @author Jordy Sipkema
	 */
	public void removeNode(Element destination)
	{
		_nodeList.remove(destination);
		
		Iterator<Entry<Element, List<Element>>> it = _nodeList.entrySet().iterator();
		while (it.hasNext()){
			List<Element> list = it.next().getValue();
			list.remove(destination);
			
			//Als de list leeg is, dan gooien we de Entry gelijk weg. (Hij is useless..)
			if(list.size() < 1){
				it.remove();
			}
		}
	}
	
	/**
	 * Removes a path between destination and through
	 * Dest. <--/remove/--> Thro. 
	 * @param destination Point A
	 * @param through Point B
	 */
	public void removePath(Element destination, Element through){
		if (_nodeList.containsKey(through)){			
			List<Element> a = _nodeList.get(through);
			a.remove(destination);
			if (a.size() < 1){
				_nodeList.remove(through);
			}
		}
		if (_nodeList.containsKey(destination)){
			List<Element> b = _nodeList.get(destination);
			b.remove(through);
			if (b.size() < 1){
				_nodeList.remove(destination);
			}
		}
	}

	/**
	 * Returns a RouteManager Singleton instance
	 * 
	 * @return RouteManager instance
	 * @author Jordy Sipkema
	 */
	public static RouteManager instance()
	{
		if (_instance == null)
		{
			_instance = new RouteManager();
		}
		return _instance;
	}
}
