package nl.avans.festivalplanner.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Festival implements Serializable
{
	private static final long serialVersionUID = -284695987818801744L;

	private String _name;
	private GregorianCalendar _date;
	private int _tickets;
	private List<Artist> _artists = new ArrayList<Artist>();
	private ArrayList<Stage> _stages = new ArrayList<Stage>();
	private Schedule _schedule = new Schedule();
	
	public void setSchedule(Schedule value)
	{
		this._schedule = value;
	}
	
	public Schedule getSchedule()
	{
		return this._schedule;
	}
	
	public void setArtists(List<Artist> value)
	{
		this._artists = value;
	}
	
	public List<Artist> getArtists()
	{
		return this._artists;
	}
	
	public void setStages(ArrayList<Stage> value)
	{
		this._stages = value;
	}
	
	public ArrayList<Stage> getStages()
	{
		return this._stages;
	}
	
	public void setTicket(int value)
	{
		this._tickets = value;
	}
	
	public int getTickets()
	{
		return this._tickets;
	}
	
	public void setName(String value)
	{
		_name = value;
	}
	
	public String getName()
	{
		return this._name;
	}
	
	public void setDate(GregorianCalendar value)
	{
		this._date = value;
	}
	
	public GregorianCalendar getDate()
	{
		return this._date;
	}
	
	
}
