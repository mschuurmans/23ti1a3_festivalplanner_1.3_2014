package nl.avans.festivalplanner.view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import nl.avans.festivalplanner.view.panels.ArtistPanel;

public class ApplicationView extends JFrame
{
	
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 600;
	private Panel activePanel;
	private GUIHelper guiHelper = new GUIHelper();
	
	public static void main(String[] args) throws IOException
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
	
	public ApplicationView() throws IOException {
	    setTitle("Festival Planner TI1.3 A3");
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setPreferredSize(new Dimension(WIDTH, HEIGHT));
	    getContentPane().add(guiHelper.getMenuBar(), BorderLayout.PAGE_START);
	    getContentPane().add(guiHelper.getTabBar(), BorderLayout.CENTER);
	    

	    //getContentPane().add(new ArtistPanel(), BorderLayout.CENTER);

	//    getContentPane().add(new StagePanel(), BorderLayout.CENTER);

	    
	    //getContentPane().add(guiHelper.getStatusBar(), BorderLayout.PAGE_START);
	    pack();
	    setLocationRelativeTo(null);
	    setVisible(true);
	}
	
	public void showGui(JPanel p) {
	    getContentPane().add(p, BorderLayout.CENTER);
	    pack();
	    setVisible(true);
	}
}
