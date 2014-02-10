package nl.avans.festivalplanner.settings;

import java.io.Serializable;

public class Settings implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5348570495376218029L;
	
	private static Settings _instance = null;
	
	public static Settings Instance()
	{
		if(_instance == null)
			_instance = new Settings();
		
		return _instance;
	}
	
	public Settings()
	{
		
	}
}
