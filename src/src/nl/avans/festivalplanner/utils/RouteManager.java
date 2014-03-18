package nl.avans.festivalplanner.utils;

import java.io.Serializable;
import java.util.HashMap;

import nl.avans.festivalplanner.model.simulator.Element;

public class RouteManager implements Serializable
{
	private static final long serialVersionUID = -2185251932877684767L;

	private static RouteManager _instance = null;
	private HashMap<Element, Element> _nodeList = new HashMap<>();

	private RouteManager()
	{

	}

	public Element getNextDestination(Element destination, Element location)
	{
		Element nextstep = destination; 
		while(location != destination){
			Element result = _nodeList.get(nextstep);
			if (location == result){
				return nextstep;
			}
			nextstep = result;
		}
		return destination;
	}

	public void addNode(Element destination, Element through)
	{
		_nodeList.put(destination, through);
	}

	public void removeNode(Element destination)
	{
		_nodeList.remove(destination);
	}

	public static RouteManager instance()
	{
		if (_instance == null)
		{
			_instance = new RouteManager();
		}
		return _instance;
	}
}
