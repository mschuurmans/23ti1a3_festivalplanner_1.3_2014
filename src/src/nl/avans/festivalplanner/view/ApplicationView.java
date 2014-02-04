package nl.avans.festivalplanner.view;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ApplicationView extends JFrame{
	private Panel activePanel;
	private GUIHelper guiHelper = new GUIHelper();
	
	public ApplicationView() {
	    setTitle("Festival Planner TI1.3 A3");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setPreferredSize(new Dimension(640, 480));
	    getContentPane().add(guiHelper.getTabBar(), BorderLayout.PAGE_START);
	    getContentPane().add(guiHelper.getMenuBar(), BorderLayout.PAGE_START);
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
