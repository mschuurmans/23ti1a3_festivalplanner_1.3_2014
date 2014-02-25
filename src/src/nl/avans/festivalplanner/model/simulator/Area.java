/**
 * 
 */
package nl.avans.festivalplanner.model.simulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * @author Jordy Sipkema & Michiel Schuurmans
 * @version 18-02-2014
 */
public class Area extends Element
{
	
	public Area(Dimension _size, Vector _position)
	{
		super(_size, _position);
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		
		Rectangle2D rect = new Rectangle2D.Double(_position.getX() - (_size.getWidth() / 2), _position.getY()- (_size.getHeight() / 2), _size.getWidth(), _size.getHeight());
		
		g.setColor(new Color(11f, 0f, 0f, 0.74f));
		g.fill(rect);
		
		g.setColor(Color.black);
		g.draw(rect);
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		
	}

}
