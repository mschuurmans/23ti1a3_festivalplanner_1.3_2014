package nl.avans.festivalplanner.model.simulator;

import java.awt.Dimension;
import java.awt.Graphics2D;

public class Toilet extends Building
{
	private static final long serialVersionUID = 3124071536360108221L;

	public Toilet(Dimension size, Vector position)
	{
		super(size, position);
		super.setImage("bin/TOILET.png");
	}
	
	public void draw(Graphics2D g)
	{
		//This method only calls "super.draw()", the building-class will draw the image for it.
		super.draw(g);
	}
	
	public void update()
	{
		super.update();
	}
}
