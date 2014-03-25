/**
 * 
 */
package nl.avans.festivalplanner.model.simulator;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.awt.Point;
import java.awt.geom.*;

import nl.avans.festivalplanner.utils.Enums.Text;

/**
 * @author Jordy Sipkema & Michiel Schuurmans
 * @version 18-02-2014
 */
public abstract class Element implements Serializable, Cloneable
{
	private static final long serialVersionUID = 4916647167455714614L;

	protected Dimension _size = null;
	protected Vector _position = null;
	protected double _rotation;

	/*
	 * Constructor for subclasses only.
	 */
	protected Element(Dimension _size, Vector _position)
	{
		super();
		this._size = _size;
		this._position = _position;
		this._rotation = 0;
	}
	/**
	 * Draws this element.
	 */
	public abstract void draw(Graphics2D g);

	/**
	 * Updates this element.
	 */
	public abstract void update();

	/**
	 * @return the size
	 */
	public Dimension getSize()
	{
		return this._size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(Dimension size)
	{
		this._size = size;
	}

	/**
	 * @return the position
	 */
	public Vector getPosition()
	{
		return this._position;
	}

	/**
	 * @return random place in position
	 * @author Kasper
	 */

	public Vector getRandomPosition()
	{
		int x = _position.getX() + (int) ((Math.random() - 0.5) * _size.width);
		int y = _position.getY() + (int) ((Math.random() - 0.5) * _size.height);
		Vector vect = new Vector(x, y);
		return vect;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(Vector position)
	{
		this._position = position;
	}

	/**
	 * Checks if the point is inside the element
	 * 
	 * @Author Michiel
	 * @return boolean true if point is inside element
	 */
	public boolean contains(Point point)
	{
		boolean debugMethod = false;

		int x = (int) (_position.getX() - (_size.getWidth() / 2));
		int y = (int) (_position.getY() - (_size.getHeight() / 2));
		int height = (int) _size.getHeight();
		int width = (int) _size.getWidth();

		if (debugMethod)
		{
			System.out.println("X: " + x + " Y: " + y);
			System.out.println("PointX: " + point.getX() + " PointY: "
					+ point.getY());
		}

		Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);

		return rect.contains(point.getX(), point.getY());
	}

	/**
	 * Drags an object according to the point
	 * 
	 * @Author Michiel
	 */
	public void drag(Point point)
	{
		_position.setY((int) point.getY());
		_position.setX((int) point.getX());
	}
	
	public void rotate()
	{
		_rotation = Math.toRadians(45);
	}

	@Override
	public String toString()
	{
		String className = Text.parse(this.getClass().getSimpleName()).toString();
		return className + " : " + this._position.toString(); 
	}
	
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
}
