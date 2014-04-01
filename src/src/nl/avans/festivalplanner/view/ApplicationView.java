package nl.avans.festivalplanner.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import nl.avans.festivalplanner.model.FestivalHandler;
import nl.avans.festivalplanner.settings.SettingsController;
import nl.avans.festivalplanner.utils.Enums.Languages;

public class ApplicationView extends JFrame implements WindowListener
{

	public static final int WIDTH = 1024;
	public static final int HEIGHT = 600;
	private GUIHelper guiHelper = new GUIHelper();
	private Languages guiLanguage = SettingsController.Instance().getLanguage();

	public static void main(String[] args)
	{
		/***
		 * TEST PURPOSES.
		 */

		FestivalHandler.Instance().readFromFile();

		new ApplicationView();

		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			// handle exception
		}
	}

	public ApplicationView()
	{
		addWindowListener(this);
		setTitle("Festival Planner TI1.3 23TI1A3");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		getContentPane().add(guiHelper.getMenuBar(), BorderLayout.PAGE_START);
		getContentPane().add(guiHelper.getTabBar(), BorderLayout.CENTER);

		// getContentPane().add(new ArtistPanel(), BorderLayout.CENTER);

		// getContentPane().add(new StagePanel(), BorderLayout.CENTER);

		// getContentPane().add(guiHelper.getStatusBar(),
		// BorderLayout.PAGE_START);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void update(){
		guiHelper.update();
		getContentPane().removeAll();
		getContentPane().add(guiHelper.getMenuBar(), BorderLayout.PAGE_START);
		getContentPane().add(guiHelper.getTabBar(), BorderLayout.CENTER);
		pack();
	}

	public void showGui(JPanel p)
	{
		getContentPane().add(p, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	
	
	// WindowsListener methods:
	@Override
	public void windowOpened(WindowEvent e)
	{
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
	}

	@Override
	public void windowActivated(WindowEvent e)
	{
		Languages currentLanguage = SettingsController.Instance().getLanguage(); 
		if (this.guiLanguage != currentLanguage){
			this.guiLanguage = currentLanguage;
			update();
			System.out.println("Updating GUI (Language change)");
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
	}
}
