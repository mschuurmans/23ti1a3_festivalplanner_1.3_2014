package nl.avans.festivalplanner.view;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import nl.avans.festivalplanner.view.panels.ArtistPanel;
import nl.avans.festivalplanner.view.panels.StagePanel;

public class ApplicationView extends JFrame
{
	
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 600;
	private Panel activePanel;
	private GUIHelper guiHelper = new GUIHelper();
	
	public ApplicationView() {
	    setTitle("Festival Planner TI1.3 A3");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setPreferredSize(new Dimension(WIDTH, HEIGHT));
	    getContentPane().add(guiHelper.getMenuBar(), BorderLayout.PAGE_START);
	    getContentPane().add(guiHelper.getTabBar(), BorderLayout.CENTER);
	    
//<<<<<<< HEAD
	  //  getContentPane().add(new ArtistPanel(), BorderLayout.CENTER);
///=======
	    getContentPane().add(new StagePanel(), BorderLayout.CENTER);
////>>>>>>> 1137f5f7a5f657accd72e0b04856b220a7c91b5f
	    
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
