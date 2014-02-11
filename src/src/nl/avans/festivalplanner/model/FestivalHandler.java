package nl.avans.festivalplanner.model;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import nl.avans.festivalplanner.utils.Enums.Text;

public class FestivalHandler 
{
	private static FestivalHandler _instance = null;
	private boolean debug = true;
	public static FestivalHandler Instance() 
	{
		if (_instance == null)
			_instance = new FestivalHandler();

		return _instance;
	}

	private Festival _festival;
	private Stage _testStage1; //TESTING! TODO REMOVE
	private Stage _testStage2; //TESTING! TODO REMOVE

	public FestivalHandler() 
	{
		_festival = new Festival();
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
					int dialogResult = JOptionPane.showConfirmDialog(null,
							Text.FileExistsWantToOverride.toString(),
							Text.Warning.toString(), 1);
					if (dialogResult == JOptionPane.YES_OPTION)
					{
						// doorgaan
					} 
					else
					{
						return false;
					}
				}

				ObjectOutputStream out = new ObjectOutputStream(
						new FileOutputStream(file));
				out.writeObject(_festival);
				out.flush();
				out.close();
				JOptionPane.showMessageDialog(null,
						Text.FileSavedSucces.toString(), Text.Info.toString(),
						1);
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
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
				Object obj = in.readObject();

				_festival = (Festival) obj;
				JOptionPane.showMessageDialog(null,Text.FileOpenedSucces.toString(), Text.Info.toString(),1);
				in.close();
			}
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null,Text.FileDoesNotExist.toString(), Text.Info.toString(), 1);
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

	/*
	 * debugging purposes
	 */
	public ArrayList<Stage> getStagesTest() 
	{
		ArrayList<Stage> stageList = new ArrayList<Stage>();


		stageList.add(new Stage("test1", 100, new Dimension(10, 20),
				new Dimension(10, 20)));
		
		_testStage1 = new Stage("test2", 100, new Dimension(10, 20), new Dimension(10, 20));
		
		stageList.add(_testStage1);
		
		_testStage2 = new Stage("test3", 100, new Dimension(10, 20), new Dimension(10, 20));
		
		stageList.add(_testStage2);
		
		stageList.add(new Stage("test4", 100, new Dimension(10, 20), new Dimension(10, 20)));
		stageList.add(new Stage("test5", 100, new Dimension(10, 20), new Dimension(10, 20)));
		stageList.add(new Stage("test6", 100, new Dimension(10, 20), new Dimension(10, 20)));

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
