/**
 * 
 */
package nl.avans.festivalplanner.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * The Act-class is used to store the information about one act on a given start
 * and end time. This act should contain a Stage and a Artist.
 * 
 * @author Jordy Sipkema & Rudy Tjin-Kon-Koen
 * @version 11/02/2014
 */
public class Act {
	public Act(String _name, Stage _stage, Artist _artist,
			GregorianCalendar _startTime, GregorianCalendar _endTime) {
		super();
		this._name = _name;
		this._stage = _stage;
		this._artist = _artist;
		this._startTime = _startTime;
		this._endTime = _endTime;
	}

	public Act() {

	}

	private String _name = "";
	private Stage _stage = null;
	private Artist _artist = null;
	private GregorianCalendar _startTime = null;
	private GregorianCalendar _endTime = null;

	/**
	 * @return the name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this._name = name;
	}

	/**
	 * @return the stage
	 */
	public Stage getStage() {
		return _stage;
	}

	/**
	 * @param stage
	 *            the stage to set
	 */
	public void setStage(Stage stage) {
		this._stage = stage;
	}

	/**
	 * @return the artist
	 */
	public Artist getArtist() {
		return _artist;
	}

	/**
	 * @param artist
	 *            the artist to set
	 */
	public void setArtist(Artist artist) {
		this._artist = artist;
	}

	/**
	 * @return the startTime
	 */
	public GregorianCalendar getStartTime() {
		return _startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(GregorianCalendar startTime) {
		this._startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public GregorianCalendar getEndTime() {
		return _endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(GregorianCalendar endTime) {
		this._endTime = endTime;
	}

	public String toString() {
		String result = "";
		result = " Act naam: " + _name + " Stage naam: " + _stage.getName()
				+ " Artiest naam: " + _artist.getName() + " Start tijd: "
				+ _startTime.get(Calendar.HOUR_OF_DAY) + ":"
				+ _startTime.get(Calendar.MINUTE) + " Eind tijd: "
				+ _endTime.get(Calendar.HOUR_OF_DAY) + ":"
				+ _endTime.get(Calendar.MINUTE);
		return result;
	}

}
