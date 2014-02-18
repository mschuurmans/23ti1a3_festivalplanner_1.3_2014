/**
 * 
 */
package nl.avans.festivalplanner.model.simulator;

import java.awt.Dimension;

/**
 * @author Jordy Sipkema
 * @version 18-02-2014
 */
public class Building extends Area
{
	private String _image = "";
	
	/**
	 * @param _size The size of the building
	 * @param _position The vector representation of this building.
	 */
	public Building(Dimension _size, Vector _position)
	{
		super(_size, _position);
	}
	
	

	/* (non-Javadoc)
	 * @see nl.avans.festivalplanner.model.simulator.Element#draw()
	 */
	@Override
	public void draw()
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see nl.avans.festivalplanner.model.simulator.Element#update()
	 */
	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the image
	 */
	public String getImage()
	{
		return _image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image)
	{
		this._image = image;
	}

}
