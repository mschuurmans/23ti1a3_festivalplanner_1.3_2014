package nl.avans.festivalplanner.view;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

public abstract class Panel extends JPanel implements ActionListener
{
	public Panel()
	{
		super(null);
	}
	
	private static final long serialVersionUID = 3132394452901507984L;

	public abstract Panel getPanel();
}
