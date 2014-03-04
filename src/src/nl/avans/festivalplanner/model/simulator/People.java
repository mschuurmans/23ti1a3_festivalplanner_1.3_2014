package nl.avans.festivalplanner.model.simulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import nl.avans.festivalplanner.utils.Enums.Text;
import nl.avans.festivalplanner.utils.*;
public class People extends Element
{
	private boolean debug = true;

	public People(Dimension size, Vector position)
	{
		super(size, position);
	}
	
	public void draw(Graphics2D g)
	{
		if(debug)
			drawBackCanvas(g);	 
	}
	
	public void update()
	{
		
	}
	
	private void newDestination()
	{
		
	}	

	/**
	 * Draws a small area around the person when debug mode is enabled.
	 * Usefull for checking if collision is working.
	 * @Author Michiel Schuurmans
	 */
	private void drawBackCanvas(Graphics2D g)
	{
		
		int x = (int)(_position.getX() - (_size.getWidth() /2));
		int y = (int)(_position.getY() - (_size.getHeight() / 3));
		int height = (int)_size.getHeight();
		int width = (int)_size.getWidth();
		
		Utils.drawAreaBackground(g,x,y,width,height);
	}
}
