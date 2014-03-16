package nl.avans.festivalplanner.model.simulator;

import java.awt.Dimension;
import java.awt.Graphics2D;

public class Entrance extends Building
{
	private static final long serialVersionUID = -6593759721524329078L;

	public Entrance(Dimension size, Vector position)
	{
		super(size, position);
		super.setImage("bin/entrance.png");
	}
	
	public void draw(Graphics2D g)
	{
		//TODO: set Entrance-building image (super-call to setImage)
		super.draw(g);
	}
	
	public void update()
	{
		super.update();
	}
}
