/**
 * 
 */
package nl.avans.festivalplanner.model.simulator;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;

import nl.avans.festivalplanner.utils.AssetManager;

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
	public void draw(Graphics2D g)
	{
		super.draw(g);

		if(!_image.trim().equals(""))
		{
			Image img = AssetManager.Instance().getImage(_image);

			int x = (int)(_position.getX() - (_size.getWidth() / 2));
			int y = (int)(_position.getY() - (_size.getHeight() / 2));
			int height = (int)_size.getHeight();
			int width = (int)_size.getWidth();

			g.drawImage(img, x,y,width,height, null);
		}
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
