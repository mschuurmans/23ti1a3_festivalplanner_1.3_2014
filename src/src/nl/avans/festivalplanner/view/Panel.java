package nl.avans.festivalplanner.view;

import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public abstract class Panel extends JPanel implements ActionListener
{
	/**
	 * Constructs a new Panel-object with "null" as layout manager.
	 */
	public Panel()
	{
		super(null);
	}
	
	/**
	 * Constructs a new Panel-object with given layout manager.
	 * @param manager The layout manager for this panel.
	 */
	public Panel(LayoutManager manager)
	{
		super(manager);
	}
	
	private static final long serialVersionUID = 3132394452901507984L;

	/**
	 * Returns an instance of this Panel object.
	 * Implemented by "return this"
	 * @return Panel this
	 */
	public abstract Panel getPanel();

	public void startTimer(int fps)
	{
		new Timer(fps, this).start();
		System.out.println("Timer started with an fps of: " + fps);
	}
}
