package nl.avans.festivalplanner.model;

import java.awt.Dimension;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import nl.avans.festivalplanner.model.simulator.Element;
import nl.avans.festivalplanner.model.simulator.Intersection;
import nl.avans.festivalplanner.model.simulator.People;
import nl.avans.festivalplanner.model.simulator.Entrance;
import nl.avans.festivalplanner.utils.Enums.Text;
import nl.avans.festivalplanner.utils.Enums.States;
import nl.avans.festivalplanner.model.simulator.Vector;
import nl.avans.festivalplanner.utils.Utils;
import java.util.*;
import nl.avans.festivalplanner.model.simulator.*;

public class FestivalHandler implements Serializable 
{
	private static FestivalHandler _instance = null;
	private boolean debug = true;
	private List<Element> _facilities = new ArrayList<Element>();
	private List<Element> _stands = new ArrayList<Element>();
	private List<Element> _remaining = new ArrayList<Element>();
	private Map<Element, Intersection> _intersections = new HashMap<Element, Intersection>();
	private SimulatorControl _controls = new SimulatorControl();
	public static FestivalHandler Instance() 
	{
		if (_instance == null)
			_instance = new FestivalHandler();

		return _instance;
	}

	private Festival _festival;	
	private Stage _testStage1; //TESTING! TODO REMOVE
	private Stage _testStage2; //TESTING! TODO REMOVE

	private List _elementsOnTerrain = new ArrayList<Element>();
	private Map<Element, Intersection> _intersectionOnTerrain = new HashMap<>();

	// begin of simulator controls
	
	public void setIntersection(Element element, Intersection intersec)
	{
		_intersections.put(element, intersec);
	}
	public Intersection getIntersection(Element element)
	{
		return _intersections.get(element);
	}
	
	public Element getNextElement(Element current, Element goal)
	{
		return _intersections.get(current).nextMove(goal);
	}

	public SimulatorControl getControls()
	{
		return _controls;
	}	
	/**
	 *@Author Kasper Balink
	 */
	private boolean peopleOnField()
	{
		for(Element p : FestivalHandler.Instance().getElementsOnTerrain())
		{
			if(p instanceof People)
				return true;
		}
		return false;
	}
	
	public boolean requirementsOK(){
		return (entrancePlaced() && signpostPlaced());
	}
	
	/**
 	 *@Author Michiel Schuurmans
	 */
	public boolean entrancePlaced()
	{
		for(Element e : getElementsOnTerrain())
		{
			if(e instanceof Entrance)
				return true;
		}
		return false;
	}
	
	/**
	 * @author Jordy Sipkema
	 */
	public boolean signpostPlaced(){
		for(Element e : getElementsOnTerrain()){
			if (e instanceof Signpost){
				return true;
			}
		}
		return false;
	}

	/**
	 *@Author Michiel Schuurmans
	 */
	public void updateSimulator()
	{	
		if(requirementsOK())
		{
			if(!peopleOnField())
			{
				int value = 10;
				for(int i=0; i<value; i++)
				{ 
					int startX = 0;
					int startY = 0;
					for(Element e : getElementsOnTerrain())
					{
						if(e instanceof Entrance)
						{
							startX =(int) e.getRandomPosition().getX();
							startY =(int) e.getRandomPosition().getY();
						}
					}
					int speed = (int)(Math.random()*6+3);
					float direction = 0;
					People visitor = new People(new Vector(startX,startY), speed, direction);
					FestivalHandler.Instance().addElementToTerrain(visitor);
				}
			}

			if(this._controls.getState() == States.Running)
			{
				for(Element e : getElementsOnTerrain())
				{
					e.update();
				}

				_controls.update();
			}
		}
	}
	// end of simulator controls

	private FestivalHandler() 
	{
		_festival = new Festival();
	}
	
	public Intersection getIntersectionOptions(Element element)
	{
		return _intersectionOnTerrain.get(element);
	}
	
	public void setIntersectionOptions(Element element, Intersection intersection)
	{
		_intersectionOnTerrain.put(element, intersection);
	}
	
	public List<Element> getFacilities()
	{
		return _facilities;
	}
	
	public void addFacilities(Element e)
	{
		_facilities.add(e);
	}
	
	public List<Element> getStands()
	{
		return _stands;
	}
	
	public void addStands(Element e)
	{
		_stands.add(e);
	}
	
	public List<Element> getRemaining(){
		return _remaining;
	}
	
	public void addRemaining(Element e){
		_remaining.add(e);
	}
	/**
	 * Returns a list of all the items on the simulator terrain
	 * @Author Michiel
	 */
	public List<Element> getElementsOnTerrain()
	{
		return this._elementsOnTerrain;
	}

	/**
	 * @Author Michiel
	 */
	public void addElementToTerrain(Element value)
	{
		this._elementsOnTerrain.add(value);
	}

	/**
	 * @Author Michiel
	 */
	public void removeElementFromTerrain(Element value)
	{
		this._elementsOnTerrain.remove(value);
	}

	/**
	 * saves the festival object to a file, the file path is defined with the
	 * settings panel
	 * 
	 * @return boolean success
	 */
	public boolean saveToFile()
	{
		ClassLoader classLoader = FestivalHandler.class.getClassLoader();
		File classpathRoot = new File(classLoader.getResource("").getPath());

		String path = classpathRoot.getPath();

		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("Data", "dat"));
		int returnVal = fc.showSaveDialog(new JPanel());

		if (returnVal == JFileChooser.APPROVE_OPTION) 
		{
			File file = fc.getSelectedFile();

			if (!file.getAbsolutePath().endsWith(".dat"))
				file = new File(file.getAbsolutePath()+ ".dat");

			try 
			{
				// File outputFile = new File(file.getAbsolutePath());
				if (file.exists()) {
					int dialogResult = JOptionPane.showConfirmDialog(null,Text.FileExistsWantToOverride.toString(),Text.Warning.toString(), 1);
					if (dialogResult == JOptionPane.YES_OPTION)
					{
						// doorgaan
					} 
					else
					{
						return false;
					}
				}

				Utils.writeObject(file, _instance);
				JOptionPane.showMessageDialog(null,Text.FileSavedSucces.toString(), Text.Info.toString(),1);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}

		} 
		else 
		{
			return false;
		}

		return true;
	}

	public void setFestival(Festival value)
	{
		this._festival = value;
	}
	public void setElementsOnTerrain(List<Element> value)
	{
		this._elementsOnTerrain = value;
	}
	/**
	 * reads the festival from a file, the path is defined with the settings
	 * panel.
	 * 
	 * @return
	 */
	public boolean readFromFile() 
	{

		JFileChooser fc = new JFileChooser(new File(FestivalHandler.class.getClassLoader().getResource("").getPath()).getPath());

		int returnVal = fc.showOpenDialog(new JPanel());

		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			File file = fc.getSelectedFile();

			try
			{
				FestivalHandler handler  = (FestivalHandler)Utils.readObject(file);
				_instance.setFestival(handler.getFestival());
				_instance.setElementsOnTerrain(handler.getElementsOnTerrain());
				//JOptionPane.showMessageDialog(null,Text.FileOpenedSucces.toString(), Text.Info.toString(),1);
			}
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null,Text.FileDoesNotExist.toString(), Text.Info.toString(), 1);
				System.out.println("Laden is mislukt want: " + e.getMessage());
				return false;
			}

			if(debug)
				System.out.println("File geladen: " + file.getAbsolutePath());

			return true;
		} 
		else
		{
			//JOptionPane.showMessageDialog(null,Text.FileDoesNotExist.toString(), Text.Info.toString(), 1);
			_festival = new Festival();

			if(debug)
				System.out.println("Open command geannuleerd.");

			return false;
		}
	}

	public ArrayList<Stage> getStages()
	{
		return _festival.getStages();
	}

	public void setStage(ArrayList<Stage> value)
	{
		_festival.setStages(value);
	}

	public void setActs(ArrayList<Act> value)
	{
		_festival.getSchedule().setActs(value);
	}

	/*
	 * debugging purposes
	 */
	public ArrayList<Stage> getStagesTest() 
	{
		ArrayList<Stage> stageList = new ArrayList<Stage>();


		stageList.add(new Stage("test1", 100, new Dimension(10, 20),
				new Dimension(10, 20), ""));
		//		
		//		_testStage1 = new Stage("test2", 100, new Dimension(10, 20), new Dimension(10, 20), "");
		//		
		//		stageList.add(_testStage1);
		//		
		//		_testStage2 = new Stage("test3", 100, new Dimension(10, 20), new Dimension(10, 20), "");
		//		
		//		stageList.add(_testStage2);
		//		
		//		stageList.add(new Stage("test4", 100, new Dimension(10, 20), new Dimension(10, 20), ""));
		//		stageList.add(new Stage("test5", 100, new Dimension(10, 20), new Dimension(10, 20), ""));
		//		stageList.add(new Stage("test6", 100, new Dimension(10, 20), new Dimension(10, 20), ""));
		//		stageList.add(new Stage("test7", 100, new Dimension(10, 20), new Dimension(10, 20), ""));
		//		stageList.add(new Stage("test8", 100, new Dimension(10, 20), new Dimension(10, 20), ""));
		//		stageList.add(new Stage("test9", 100, new Dimension(10, 20), new Dimension(10, 20), ""));
		//		stageList.add(new Stage("test10", 100, new Dimension(10, 20), new Dimension(10, 20), ""));
		//		stageList.add(new Stage("test11", 100, new Dimension(10, 20), new Dimension(10, 20), ""));
		//		stageList.add(new Stage("test12", 100, new Dimension(10, 20), new Dimension(10, 20), ""));

		return stageList;
	}

	public ArrayList<Act> getActsTest()
	{
		ArrayList<Act> actList = new ArrayList<Act>();

		// act1
		GregorianCalendar startTime = new GregorianCalendar();
		startTime.set(Calendar.HOUR_OF_DAY, 16);
		startTime.set(Calendar.MINUTE, 00);

		GregorianCalendar endTime = (GregorianCalendar) startTime.clone();
		endTime.add(Calendar.HOUR_OF_DAY, 4); //duration of the event

		Act act = new Act();
		act.setName("Dries Roelvink");
		act.setStartTime(startTime);
		act.setEndTime(endTime);
		act.setStage(_testStage1);

		actList.add(act);
		// endofact1

		// act2
		startTime = new GregorianCalendar();
		startTime.set(Calendar.HOUR_OF_DAY, 13);
		startTime.set(Calendar.MINUTE, 00);

		endTime = (GregorianCalendar) startTime.clone();
		endTime.add(Calendar.HOUR_OF_DAY, 1); //duration of the event
		endTime.add(Calendar.MINUTE, 45);

		act = new Act();
		act.setName("Annie en de Rekels");
		act.setStartTime(startTime);
		act.setEndTime(endTime);
		act.setStage(_testStage2);

		actList.add(act);
		// endofact2

		return actList;
	}

	public void addAct(Act act) 
	{
		_festival.getSchedule().addAct(act);
	}

	public ArrayList<Act> getActs()
	{
		return _festival.getSchedule().getActs();	
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
	public void remove(Act act)
	{
		ArrayList<Act> acts = this._festival.getSchedule().getActs();
		this._festival.getSchedule().getActs().remove((acts.indexOf(act)));
	}	

	public void setMap(String map)
	{
		this._festival.setMap(map);
	}

	public String getMap()
	{
		return this._festival.getMap();
	}

}
