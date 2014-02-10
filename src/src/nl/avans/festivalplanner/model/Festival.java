package nl.avans.festivalplanner.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import nl.avans.festivalplanner.view.ApplicationView;

public class Festival implements Serializable
{
	private static final long serialVersionUID = -284695987818801744L;

	private String _name;
	private GregorianCalendar _date;
	private int _tickets;
	private ArrayList<Artist> _artists;
	private ArrayList<Stage> _stageList;
	private Schedule _schedule;
	
	public void setSchedule(Schedule value)
	{
		this._schedule = value;
	}
	
	public Schedule getSchedule()
	{
		return this._schedule;
	}
	
	public void setArtists(ArrayList<Artist> value)
	{
		this._artists = value;
	}
	
	public ArrayList<Artist> getArtists()
	{
		return this._artists;
	}
	
	public void setStages(ArrayList<Stage> value)
	{
		this._stageList = value;
	}
	
	public ArrayList<Stage> getStages()
	{
		return this._stageList;
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
