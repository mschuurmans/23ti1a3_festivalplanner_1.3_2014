package nl.avans.festivalplanner.model.simulator;

import java.util.*;

/**
 * Entity used to save intersection data
 * @Author Michiel Schuurmans
 */
public class Intersection
{
	private HashMap<Element, Element> _items;

	public Intersection()
	{
		this._items = new HashMap<Element, Element>();
	}

	public void addOption(Element goal, Element moveTo)
	{
		this._items.put(goal, moveTo);
	}

	public Element nextMove(Element goal)
	{
		return this._items.get(goal);	
	}
}
