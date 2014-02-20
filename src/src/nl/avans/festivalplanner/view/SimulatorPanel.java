package nl.avans.festivalplanner.view;

import java.awt.event.ActionEvent;

public class SimulatorPanel extends Panel
{
	private static final long serialVersionUID = -3533223589206092760L;

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
	}

	@Override
	public Panel getPanel()
	{
		return this;
	}

	public class Simulator extends JPanel implements ActionListener
	{
		public Simulator(Dimension dim)
		{
			setPrefferedSize(dim);
			Timer timer = new Timer(30, this);
			timer.start();
		}

		@Override
		public void actionPerforment(ActionEvent event)
		{
			repaint();
		}
                
		@Override
		pubic void paintComponent(Graphics g)
		{
			super.paintComponent(g);
		}
	}

}
