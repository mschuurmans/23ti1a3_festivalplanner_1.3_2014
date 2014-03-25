/**
 * 
 */
package nl.avans.festivalplanner.utils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import nl.avans.festivalplanner.model.simulator.Element;
import nl.avans.festivalplanner.model.simulator.Signpost;

/**
 * RouteManger (Singleton)
 * 
 * @author Jordy Sipkema
 * @version 2.0
 */
public class RouteManager implements Serializable
{

	private static RouteManager _instance = null;

	private HashSet<Signpost> _signposts = new HashSet<>();

	/**
	 * 
	 */
	private RouteManager()
	{
		// TODO Auto-generated constructor stub
	}

	public static RouteManager instance()
	{
		if (_instance == null)
		{
			_instance = new RouteManager();
		}
		return _instance;
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
		if (destination.equals(location)) // Als dit waar is, zijn we klaar.
			return location;

		Signpost sp_loc = this.getPointingSignpost(location);
		Signpost sp_goal = this.getPointingSignpost(destination);

		// Controle: Kan ik vanaf dit punt wel naar het doel toe?
		if (this.isSignpostPointingTo(sp_loc, destination))
		{
			// STAP 1: Ben ik al bij een signpost?
			if (location instanceof Signpost)
			{
				// STAP 1 == JA: ga verder >>

				// STAP 2: Is de signpost die naar het doel wijst, gelijk aan de
				// signpost die naar mijn location wijst?
				if (sp_loc.equals(sp_goal))
				{
					// STAP 2 == JA: Dan gaan we rechtstreeks naar het doel!
					return destination;
				}
				else
				{
					// STAP 2 == NEE: Dan gaan we één Signpost dichter naar het
					// doel.
					return this.getCloserSignPost(sp_loc, destination);
				}

			}
			else
			{
				// STAP 1 == NEE: Ga hier eerst naar toe.
				return sp_loc;
			}
		}

		// Fallback
		return this.getPointingSignpost(destination);
	}

	/**
	 * Gets the specified signpost from the list.
	 * 
	 * @param signpost
	 *            The signpost to search for
	 * @return The signpost searched for OR null if no signpost is found
	 * @author Jordy Sipkema
	 */
	public Signpost getSignpost(Signpost signpost)
	{
		if (_signposts.contains(signpost))
		{
			for (Signpost sp : _signposts)
			{
				if (sp.equals(signpost))
				{
					return sp;
				}
			}
		}
		return null;
	}

	/**
	 * Gets all signposts registered in the RouteManager
	 * 
	 * @return All signposts
	 * @author Jordy Sipkema
	 */
	public Set<Signpost> getAllSignposts()
	{
		return _signposts;
	}

	/**
	 * Registers a signpost to the routemanager
	 * 
	 * @param signpost
	 *            The signpost to add
	 * @author Jordy Sipkema
	 */
	public void addSignpost(Signpost signpost)
	{
		_signposts.add(signpost);

	}

	/**
	 * Removes a signpost from the routemanager
	 * 
	 * WARNING: This method also removes all references to this signpost from
	 * ALL other signposts that are registered.
	 * 
	 * @param signpost
	 *            The signpost to remove
	 * @author Jordy Sipkema
	 */
	public void removeSignpost(Signpost signpost)
	{
		_signposts.remove(signpost);
		for (Signpost sp : this.getAllSignposts()){
			sp.removePointer(signpost);
		}
	}

	/**
	 * Adds a pointer to a signpost
	 * 
	 * @param signpost
	 *            The signpost to add the pointer to
	 * @param pointTo
	 *            The pointer to be added
	 * @return Boolean indicating succes.
	 * @author Jordy Sipkema
	 */
	public boolean addPointer(Signpost signpost, Element pointTo)
	{
		Signpost sp = this.getSignpost(signpost);
		if (sp != null)
		{
			sp.addPointer(pointTo);
			return true;
		}
		return false;
	}

	/**
	 * Removes a pointer from a singpost
	 * 
	 * @param signpost
	 *            The signpost to remove the pointer from.
	 * @param pointTo
	 *            The pointer to be removed
	 * @return Boolean indicating succes.
	 * @author Jordy Sipkema
	 */
	public boolean removePointer(Signpost signpost, Element pointTo)
	{
		Signpost sp = this.getSignpost(signpost);
		if (sp != null)
		{
			sp.removePointer(pointTo);
			return true;
		}
		return false;
	}

	/**
	 * Gets the signpost that is pointing to the given element
	 * 
	 * @param element
	 *            The element searching a pointer to
	 * @return The signpost that is pointing to this element OR if the element
	 *         is a signpost, the element (as a signpost)
	 * @author Jordy Sipkema
	 */
	public Signpost getPointingSignpost(Element element)
	{
		if (element instanceof Signpost)
			return (Signpost) element;

		for (Signpost sp : _signposts)
		{
			if (sp.containsPointerTo(element))
			{
				return sp;
			}
		}
		return null;
	}

	private boolean isSignpostPointingTo(Signpost signpost, Element goal)
	{
		if (signpost.containsPointerTo(goal))
		{
			return true;
		}
		for (Signpost sp : signpost.getReferencedSignposts())
		{
			if (isSignpostPointingTo(sp, goal))
			{
				return true;
			}
		}
		return false;
	}

	private Signpost getCloserSignPost(Signpost signpost, Element goal)
	{
		if (signpost.containsPointerTo(goal))
		{
			return signpost;
		}

		for (Signpost sp : signpost.getReferencedSignposts())
		{
			if (this.isSignpostPointingTo(sp, goal))
			{
				return sp;
			}
		}

		// Fallback:
		return this.getPointingSignpost(goal);
	}

}
