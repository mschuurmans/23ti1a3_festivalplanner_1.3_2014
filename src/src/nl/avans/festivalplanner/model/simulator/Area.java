/**
 * 
 */
package nl.avans.festivalplanner.model.simulator;

import java.awt.Dimension;

/**
 * @author Jordy Sipkema
 * @version 18-02-2014
 */
public class Area extends Element
{
	
	public Area(Dimension _size, Vector _position)
	{
		super(_size, _position);
	}

	/* (non-Javadoc)
	 * @see nl.avans.festivalplanner.model.simulator.Element#getSize()
	 */
	@Override
	public Dimension getSize()
	{
		return super._size;
	}

	/* (non-Javadoc)
	 * @see nl.avans.festivalplanner.model.simulator.Element#setSize(java.awt.Dimension)
	 */
	@Override
	public void setSize(Dimension size)
	{
		super._size = size;
	}

	/* (non-Javadoc)
	 * @see nl.avans.festivalplanner.model.simulator.Element#getPosition()
	 */
	@Override
	public Vector getPosition()
	{
		return super._position;
	}

	/* (non-Javadoc)
	 * @see nl.avans.festivalplanner.model.simulator.Element#setPosition(nl.avans.festivalplanner.model.simulator.Vector)
	 */
	@Override
	public void setPosition(Vector position)
	{
		super._position = position;
	}

	@Override
	public void draw()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		
	}

}
