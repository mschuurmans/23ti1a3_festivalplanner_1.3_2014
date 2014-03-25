package nl.avans.festivalplanner.view;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.*;

public class SimulatorView implements ActionListener
{
	private static final long serialVersionUID = -3430797363143834387L;
	private static JFrame frame;
	public SimulatorView()
	{
		new Timer(30, this).start();	
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

	public void actionPerformed(ActionEvent e)
	{
		// change title?
	}
}
