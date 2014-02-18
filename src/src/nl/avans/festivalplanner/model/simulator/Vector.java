package nl.avans.festivalplanner.model.simulator;

/**
 * A 2-dimensional vector that is represented by integer x,y coordinates.
 *
 * WARNING: If a method/operation produces a double or float value as result, then it will be
 * converted to a integer value. 
 * Example: Scaling a 1,1 vector with coefficient 1.5. (Result: Vector(2,2) )
 * 
 * @author Jordy Sipkema
 * @version 18-02-2014
 */
public class Vector
{
	private int _x = 0;
	private int _y = 0;

	/**
	 * Constructs and initializes a Vector2d from the specified xy coordinates.
	 * 
	 * @param _x
	 *            the x-coordinate
	 * @param _y
	 *            the y-coordinate
	 */
	public Vector(int _x, int _y)
	{
		this._x = _x;
		this._y = _y;
	}

	/**
	 * @return the x-coordinate of this Vector
	 */
	public int getX()
	{
		return _x;
	}

	/**
	 * @param x
	 *            the x-coordinate
	 */
	public void setX(int x)
	{
		this._x = x;
	}

	/**
	 * @return the y-coordinate
	 */
	public int getY()
	{
		return _y;
	}

	/**
	 * @param y
	 *            the y-coordinate to set
	 */
	public void setY(int y)
	{
		this._y = y;
	}

	/**
	 * Returns the sum of this vector with the one specified.
	 * 
	 * @param v
	 *            the vector to be added.
	 * @return this + v
	 */
	public Vector add(Vector v)
	{
		int newX = this.getX() + v.getX();
		int newY = this.getY() + v.getY();
		return new Vector(newX, newY);
	}

	/**
	 * Returns the difference between this vector and the one specified.
	 * 
	 * @param v
	 *            the vector to be subtracted.
	 * @return this - v
	 */
	public Vector subtract(Vector v)
	{
		int newX = this.getX() - v.getX();
		int newY = this.getY() - v.getY();
		return new Vector(newX, newY);
	}

	/**
	 * Returns the product of this vector with the specified coefficient.
	 * 
	 * @param coefficient
	 *            the coefficient multiplier.
	 * @return this * coefficient
	 */
	public Vector times(double coefficient)
	{
		int newX = new Double(this.getX() * coefficient).intValue();
		int newY = new Double(this.getY() * coefficient).intValue();
		return new Vector(newX, newY);
	}

	/**
	 * Calculates the dot-product of this vector and the other vector.
	 * 
	 * @param v
	 *            the other vector
	 * @return dot-product.
	 */
	public int dot(Vector v)
	{
		int dot = (this.getX() * v.getX()) + (this.getY() * v.getY());
		return dot;
	}

	/**
	 * Returns the length of this vector.
	 * 
	 * @return the length of this vector.
	 */
	public double length()
	{
		// Length of a Vector: Sqrt( x^2 + y^2 )
		int Xsq = this.getX() * this.getX(); // Get X squared
		int Ysq = this.getY() * this.getY(); // Get Y squared
		double length = Math.sqrt(Xsq + Ysq);
		return length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + _x;
		result = prime * result + _y;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (_x != other._x)
			return false;
		if (_y != other._y)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		return new Vector(this.getX(), this.getY());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("Vector[%d , %d]", this.getX(), this.getY());
	}

}
