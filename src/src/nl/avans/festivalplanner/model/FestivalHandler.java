package nl.avans.festivalplanner.model;

import java.awt.Dimension;
import java.util.ArrayList;
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
	
	public ArrayList<Stage> getStages()
	{
		return _festival.getStages();
	}
	
	public void setStage(ArrayList<Stage> value)
	{
		_festival.setStages(value);
	}
	
	public ArrayList<Stage> getStagesTest()
	{
		ArrayList<Stage> stageList = new ArrayList<Stage>();
		
		stageList.add(new Stage("test1", 100,new Dimension(10, 20),new Dimension(10, 20)));
		stageList.add(new Stage("test2", 100,new Dimension(10, 20),new Dimension(10, 20)));
		stageList.add(new Stage("test3", 100,new Dimension(10, 20),new Dimension(10, 20)));
		stageList.add(new Stage("test4", 100,new Dimension(10, 20),new Dimension(10, 20)));	
		stageList.add(new Stage("test5", 100,new Dimension(10, 20),new Dimension(10, 20)));
		stageList.add(new Stage("test6", 100,new Dimension(10, 20),new Dimension(10, 20)));
//		stageList.add(new Stage("test7", 100,null,null));
//		stageList.add(new Stage("test8", 100,null,null));		
//		stageList.add(new Stage("test9", 100,null,null));
//		stageList.add(new Stage("test10", 100,null,null));
//		stageList.add(new Stage("test11", 100,null,null));
//		stageList.add(new Stage("test12", 100,null,null));
		
		return stageList;
	}
	
	public void setArtists(List<Artist> artists)
	{
		this._festival.setArtists(artists);
	}
	
	public List<Artist> getArtists()
	{
		return this._festival.getArtists();
	}
	
	public Festival getFestival(){
		return this._festival;
	}
}
