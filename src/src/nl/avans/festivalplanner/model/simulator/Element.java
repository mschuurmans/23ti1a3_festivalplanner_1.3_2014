/**
 * 
 */
package nl.avans.festivalplanner.model.simulator;

import java.awt.Dimension;
import nl.avans.festivalplanner.model.simulator.Vector;

/**
 * @author Jordy Sipkema
 * @version 18-02-2014
 */
public abstract class Element
{
	protected Dimension _size = null;
	protected Vector _position = null;

	/*
	 * Constructor for subclasses only.
	 */
	protected Element(Dimension _size, Vector _position)
	{
		super();
		this._size = _size;
		this._position = _position;
	}

	/**
	 * Draws this element.
	 */
	public abstract void draw();

	/**
	 * Updates this element.
	 */
	public abstract void update();

	/**
	 * @return the size
	 */
	public abstract Dimension getSize();

	/**
	 * @param size
	 *            the size to set
	 */
	public abstract void setSize(Dimension size);

	/**
	 * @return the position
	 */
	public abstract Vector getPosition();

	/**
	 * @param position
	 *            the position to set
	 */
	public abstract void setPosition(Vector position);

}
