package nl.avans.festivalplanner.settings;

import java.io.File;
import java.io.Serializable;

import nl.avans.festivalplanner.utils.Enums.Languages;
import nl.avans.festivalplanner.utils.Utils;

public class SettingsController implements Serializable
{
	private static SettingsController _instance = null;
	
	public static SettingsController Instance()
	{
		if(_instance == null)
			_instance = new SettingsController();
		
		return _instance;
	}
	
	private Languages _language = Languages.english;
	private String _settingsFile = "bin/festivalPlannerSettings.dat";
	
	private SettingsController()
	{
		File file = new File(_settingsFile);
		
		System.out.println("SettingsController file: " + file.getAbsolutePath());
		
		if(file.exists()) 
		{// if exists load
			SettingsController cp = (SettingsController)Utils.readObject(file);
			this._language = cp.getLanguage();
		}
		else // if it doesnt exist write to file.
			Utils.writeObject(file, this);
		
		
	}
	
	public void save()
	{
		File file = new File(_settingsFile);
		Utils.writeObject(file, this);		
	}
	
	public void setLanguage(Languages value)
	{
		this._language = value;
	}
	
	public Languages getLanguage()
	{
		return this._language;
	}
}
