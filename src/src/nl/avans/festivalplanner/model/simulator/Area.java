/**
 * 
 */
package nl.avans.festivalplanner.model.simulator;

import java.awt.Dimension;
import java.awt.Graphics2D;
import nl.avans.festivalplanner.utils.*;

/**
 * @author Jordy Sipkema & Michiel Schuurmans
 * @version 18-02-2014
 */
public class Area extends Element
{
	private static final long serialVersionUID = -5443225580617847513L;

	public Area(Dimension _size, Vector _position)
	{
		super(_size, _position);
	}

	@Override
	public void draw(Graphics2D g)
	{
		int x = (int)(_position.getX() - (_size.getWidth() /2));
		int y = (int)(_position.getY() - (_size.getHeight() / 2));
		int height = (int)_size.getHeight();
		int width = (int)_size.getWidth();
		
		Utils.drawAreaBackground(g,x,y,width,height);
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		
	}
}
