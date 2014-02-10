package nl.avans.festivalplanner.model;

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
}
