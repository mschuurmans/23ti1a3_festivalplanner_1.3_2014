package nl.avans.festivalplanner.model.simulator;

import java.awt.Dimension;
import java.awt.Graphics2D;

public class Toilet extends Building
{
	public Toilet(Dimension size, Vector position)
	{
		super(size, position);
		//TODO: set Toilet-building image (super-call to setImage)
	}
	
	public void draw(Graphics2D g)
	{
		//This method only calls "Super", the building-class will draw the image for it.
		super.draw(g);
	}
	
	public void update()
	{
		
	}
}
