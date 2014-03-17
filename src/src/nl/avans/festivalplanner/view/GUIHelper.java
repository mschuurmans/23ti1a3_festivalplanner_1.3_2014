package nl.avans.festivalplanner.view;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import nl.avans.festivalplanner.model.FestivalHandler;
import nl.avans.festivalplanner.utils.Enums.Text;
import nl.avans.festivalplanner.view.panels.ArtistPanel;
import nl.avans.festivalplanner.view.panels.InfoPanel;
import nl.avans.festivalplanner.view.panels.MapPanel;
import nl.avans.festivalplanner.view.panels.SchedulePanel;
import nl.avans.festivalplanner.view.panels.StagePanel;


public class GUIHelper {

	public static final int XOFFSET = 10; //static public variables for each panel
	public static final int YOFFSET = 20; //for a uniform look troughout the panels
	
	private SettingsDialog dialog = new SettingsDialog(null);

	public GUIHelper()
	{
		
	}
	
	public JTabbedPane getTabBar()
	{
		JTabbedPane tabBar = new JTabbedPane();
		tabBar.setPreferredSize(new Dimension(ApplicationView.WIDTH, ApplicationView.HEIGHT));
		
		JPanel infoPanel = new InfoPanel();
		tabBar.addTab(Text.Info.toString(), infoPanel);
		
		JPanel schedulePanel = new SchedulePanel();
		tabBar.addTab(Text.Schedule.toString(), schedulePanel);
		
		JPanel artistPanel = new ArtistPanel();
		tabBar.addTab(Text.Artist.toString(), artistPanel);
		
		JPanel stagePanel = new StagePanel();
		tabBar.addTab(Text.Stages.toString(), stagePanel);
		
		JPanel mapPanel = new MapPanel();
		tabBar.addTab(Text.Map.toString(), mapPanel);
						
		return tabBar;
	}
	
	/**
	 * @return menuBar of type JMenuBar
	 * @author Jack
	 */
	public JMenuBar getMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu(Text.File.toString()); // ?eng: File
		JMenu help = new JMenu(Text.Help.toString()); // ?eng: Help
		menuBar.add(menu);
		menuBar.add(help);

		
		JMenuItem menu_item_settings = new JMenuItem(Text.Settings.toString());
		menu_item_settings.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				dialog.setVisible(true);
			}
		});
		
		
		JMenuItem menu_item_save = new JMenuItem(Text.Save.toString());
		menu_item_save.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				FestivalHandler.Instance().saveToFile();
			}
		});
		
		JMenuItem menu_item_close = new JMenuItem(Text.Close.toString()); // ?eng: Close
		menu_item_close.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});
		
		menu.add(menu_item_save);
		menu.add(menu_item_close);
		help.add(menu_item_settings);

		JMenuItem help_about = new JMenuItem(Text.About.toString()); // ?eng: About
		
		help_about.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				String about = Text.About.toString(); 
				String aboutMessage = Text.AboutMessage.toString();
				
				JOptionPane.showMessageDialog(null, aboutMessage, about, 1);

			}
		});

		help.add(help_about);
		
		return menuBar;
	}
	
	/**
	 * GetStatusBar creates a statusbar to add to your frame
	 * @param statusLabel a JLabel with the content that has to be shown in the statusbar
	 * @param panel. provide a panel for determining the size of the StatusBar
	 * @return the StatusBar to display
	 * @author Jack
	 */
	public JPanel getStatusBar(JLabel statusLabel,JPanel panel)
	{
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		statusPanel.setPreferredSize(new Dimension(panel.getWidth(), 16));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(statusLabel);
		
		return statusPanel;
	}
	
	public JPanel getGroupBox(String title, LayoutManager layout)
	{
		JPanel box = new JPanel(layout);
		box.setBorder(BorderFactory.createTitledBorder(title));
		return box;
	}
	
	public void update()
	{
		dialog = new SettingsDialog(null);
	}
}
