package nl.avans.festivalplanner.model.simulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

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
		Ellipse2D circleBack = new Ellipse2D.Double(_position.getX() - (_size.getWidth() / 2), _position.getY() - (_size.getHeight() / 2), _size.getWidth(), _size.getHeight());
		g.setColor(new Color(69, 69, 69, 188));
		g.fill(circleBack);

		g.setColor(Color.black);
		g.draw(circleBack);
	}
}
