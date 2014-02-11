package nl.avans.festivalplanner.model;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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

	public ArrayList<Stage> getStagesTest() 
	{
		ArrayList<Stage> stageList = new ArrayList<Stage>();

		stageList.add(new Stage("test1", 100, new Dimension(10, 20),
				new Dimension(10, 20)));
		stageList.add(new Stage("test2", 100, new Dimension(10, 20),
				new Dimension(10, 20)));
		stageList.add(new Stage("test3", 100, new Dimension(10, 20),
				new Dimension(10, 20)));
		stageList.add(new Stage("test4", 100, new Dimension(10, 20),
				new Dimension(10, 20)));
		stageList.add(new Stage("test5", 100, new Dimension(10, 20),
				new Dimension(10, 20)));
		stageList.add(new Stage("test6", 100, new Dimension(10, 20),
				new Dimension(10, 20)));
		// stageList.add(new Stage("test7", 100,null,null));
		// stageList.add(new Stage("test8", 100,null,null));
		// stageList.add(new Stage("test9", 100,null,null));
		// stageList.add(new Stage("test10", 100,null,null));
		// stageList.add(new Stage("test11", 100,null,null));
		// stageList.add(new Stage("test12", 100,null,null));

		return stageList;
	}

	public void setArtists(List<Artist> artists) 
	{
		this._festival.setArtists(artists);
		System.out.println("File saved: " + this.saveToFile());
	}

	public List<Artist> getArtists()
	{
		return this._festival.getArtists();
	}
}
