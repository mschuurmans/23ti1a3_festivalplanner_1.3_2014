package nl.avans.festivalplanner.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SimulatorView
{
	private static final long serialVersionUID = -3430797363143834387L;
	private static JFrame frame;
	public SimulatorView()
	{
		SwingUtilities.invokeLater(new Runnable() 
		{ 
			public void run()
			{
				frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				
				SimulatorPanel simPanel = new SimulatorPanel();
				
				frame.getContentPane().setLayout(new BorderLayout());
				frame.getContentPane().add(simPanel.getSimulator(), BorderLayout.CENTER);
				frame.getContentPane().add(simPanel.getToolbar(), BorderLayout.EAST);

				frame.pack();
				frame.setSize(1024, 768);
				frame.setVisible(true);
				
				frame.setLocationRelativeTo(null);
			}
		});
	}
}
