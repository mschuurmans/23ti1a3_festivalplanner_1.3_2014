package nl.avans.festivalplanner.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimulatorView extends JFrame
{
	private static final long serialVersionUID = -3430797363143834387L;

	public SimulatorView()
	{
		super("SimulatorView");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setContentPane(new JPanel(null));
		
		setSize(1024, 768);
		setVisible(true);
	}
}
