package nl.avans.festivalplanner.view;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
<<<<<<< HEAD

import nl.avans.festivalplanner.view.panels.ArtistPanel;
import nl.avans.festivalplanner.view.panels.StagePanel;
=======
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
>>>>>>> 8e8967d633eadf1870c35fab105757768d06920e

public class ApplicationView extends JFrame
{
	
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 600;
	private Panel activePanel;
	private GUIHelper guiHelper = new GUIHelper();
	
	public static void main(String[] args)
	{
		new ApplicationView();
		
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (Exception e) {
	       // handle exception
	    }
	}
	
	public ApplicationView() {
	    setTitle("Festival Planner TI1.3 A3");
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setPreferredSize(new Dimension(WIDTH, HEIGHT));
	    getContentPane().add(guiHelper.getMenuBar(), BorderLayout.PAGE_START);
	    getContentPane().add(guiHelper.getTabBar(), BorderLayout.CENTER);
	    
<<<<<<< HEAD
//<<<<<<< HEAD
	  //  getContentPane().add(new ArtistPanel(), BorderLayout.CENTER);
///=======
	    getContentPane().add(new StagePanel(), BorderLayout.CENTER);
////>>>>>>> 1137f5f7a5f657accd72e0b04856b220a7c91b5f
=======

	    //getContentPane().add(new ArtistPanel(), BorderLayout.CENTER);

	   // getContentPane().add(new StagePanel(), BorderLayout.CENTER);

>>>>>>> 8e8967d633eadf1870c35fab105757768d06920e
	    
	    //getContentPane().add(guiHelper.getStatusBar(), BorderLayout.PAGE_START);
	    pack();
	    setVisible(true);
	}
	
	public void showGui(JPanel p) {
	    getContentPane().add(p, BorderLayout.CENTER);
	    pack();
	    setVisible(true);
	}
}
