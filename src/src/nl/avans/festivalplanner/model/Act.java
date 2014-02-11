/**
 * 
 */
package nl.avans.festivalplanner.model;

import java.util.GregorianCalendar;

/**
 * @author Jordy Sipkema
 * 
 */
public class Act
{
	private String _name = "";
	private Stage _stage = null;
	private Artist _artist = null;
	private GregorianCalendar _startTime = null;
	private GregorianCalendar _endTime = null;

	/**
	 * @return the name
	 */
	public String getName()
	{
		return _name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		this._name = name;
	}

	/**
	 * @return the stage
	 */
	public Stage getStage()
	{
		return _stage;
	}

	/**
	 * @param stage
	 *            the stage to set
	 */
	public void setStage(Stage stage)
	{
		this._stage = stage;
	}

	/**
	 * @return the artist
	 */
	public Artist getArtist()
	{
		return _artist;
	}

	/**
	 * @param artist
	 *            the artist to set
	 */
	public void setArtist(Artist artist)
	{
		this._artist = artist;
	}

	/**
	 * @return the startTime
	 */
	public GregorianCalendar getStartTime()
	{
		return _startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(GregorianCalendar startTime)
	{
		this._startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public GregorianCalendar getEndTime()
	{
		return _endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(GregorianCalendar endTime)
	{
		this._endTime = endTime;
	}

}
