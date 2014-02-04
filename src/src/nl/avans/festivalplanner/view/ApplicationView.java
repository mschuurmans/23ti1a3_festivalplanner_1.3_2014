package nl.avans.festivalplanner.view;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import nl.avans.festivalplanner.view.panels.ArtistPanel;

public class ApplicationView extends JFrame
{
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	private Panel activePanel;
	private GUIHelper guiHelper = new GUIHelper();
	
	public ApplicationView() {
	    setTitle("Festival Planner TI1.3 A3");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setPreferredSize(new Dimension(WIDTH, HEIGHT));
	    getContentPane().add(guiHelper.getTabBar(), BorderLayout.PAGE_START);
	    getContentPane().add(guiHelper.getMenuBar(), BorderLayout.PAGE_START);
	    
	    getContentPane().add(new ArtistPanel(), BorderLayout.CENTER);
	    
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
