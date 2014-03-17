package nl.avans.festivalplanner.model.simulator;

import java.awt.Dimension;
import java.awt.Graphics2D;

public class Infostand extends Building
{
	private static final long serialVersionUID = 530687727527996766L;

	public Infostand(Dimension _size, Vector _position)
	{
		super(_size, _position);
		super.setImage("bin/INFO.png");
	}
	
	/* (non-Javadoc)
	 * @see nl.avans.festivalplanner.model.simulator.Building#draw()
	 */
	@Override
	public void draw(Graphics2D g)
	{
		//This method only calls "super.draw()", the building-class will draw the image for it.
		super.draw(g);
	}

	/* (non-Javadoc)
	 * @see nl.avans.festivalplanner.model.simulator.Building#update()
	 */
	@Override
	public void update()
	{
		super.update();
	}

}
