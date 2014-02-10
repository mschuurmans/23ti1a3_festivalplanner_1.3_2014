package nl.avans.festivalplanner.model;

import java.util.List;

public class FestivalHandler 
{
	private static FestivalHandler _instance = null;
	
	public static FestivalHandler Instance()
	{
		if(_instance == null)
			_instance = new FestivalHandler();
		
		return _instance;
	}
	
	private Festival _festival;
	
	public FestivalHandler()
	{
		_festival = new Festival();
	}
	
	/**
	 * saves the festival object to a file,
	 * the file path is defined with the settings panel
	 * @return boolean success
	 */
	public boolean saveToFile()
	{
		return true;
	}
	
	/**
	 * reads the festival from a file,
	 * the path is defined with the settings panel.
	 * @return
	 */
	public boolean readFromFile()
	{
		return true;
	}
	
	public void setArtists(List<Artist> artists)
	{
		this._festival.setArtists(artists);
	}
	
	public List<Artist> getArtists()
	{
		return this._festival.getArtists();
	}
}
