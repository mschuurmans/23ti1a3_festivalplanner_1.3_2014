/**
 * 
 */
package nl.avans.festivalplanner.model;

import java.util.ArrayList;

/**
 * @author Jordy Sipkema & Rudy Tjin-Kon-Koen
 * @version 11-02-2014
 */
public class Schedule
{
	private ArrayList<Act> _acts = new ArrayList<Act>();

	public void addAct(Act act)
	{
		_acts.add(act);
	}
	
	public void removeAct(Act act)
	{
		_acts.remove(act);
	}
	
	public ArrayList<Act> getActs()
	{
		return _acts;
	}
}
