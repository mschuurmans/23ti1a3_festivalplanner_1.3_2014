/**
 * 
 */
package nl.avans.festivalplanner.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jordy Sipkema
 * @version 04-02-2014
 */
public class Schedule
{
	private List<Act> _acts = new ArrayList<Act>();
	
	public Schedule() {
		_acts.add(new Act());
	}
	
	public List getActs() {
		return _acts;
	}
}
