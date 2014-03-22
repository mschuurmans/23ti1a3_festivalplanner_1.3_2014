/**
 * 
 */
package nl.avans.festivalplanner.model.simulator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import nl.avans.festivalplanner.model.FestivalHandler;
import nl.avans.festivalplanner.utils.AssetManager;

/**
 * @author Jordy Sipkema
 * @version 18-02-2014
 */
public class Building extends Area
{
	private static final long serialVersionUID = 6945847681614919930L;
	
	private String _image = "";
	private boolean isHovered;
	private int timerSinceHovered;
	
	/**
	 * @param _size The size of the building
	 * @param _position The vector representation of this building.
	 */
	public Building(Dimension _size, Vector _position)
	{
		super(_size, _position);
	}
	
	public void setHovered(boolean b)
	{
		this.isHovered = b;
	}

	/* (non-Javadoc)
	 * @see nl.avans.festivalplanner.model.simulator.Element#draw()
	 */
	@Override
	public void draw(Graphics2D g)
	{
		super.draw(g);

		if(FestivalHandler.Instance().getElementsOnTerrain().contains(this))
		{
			if(!_image.trim().equals(""))
			{
				Image img = AssetManager.Instance().getImage(_image);

				int x = (int)(_position.getX() - (_size.getWidth() / 2));
				int y = (int)(_position.getY() - (_size.getHeight() *1.5));
				int height = (int)_size.getHeight();
				int width = (int)_size.getWidth();

				if(_rotation != null)
					g.transform(_rotation);
				
				g.drawImage(img, x,y,width,height, null);
				
				if(hasBeenHovered())
				{
					int yOffset = (int)_size.getHeight() /2;
					g.setStroke(new BasicStroke(2));
					g.setColor(Color.white);
					g.drawLine(_position.getX(), _position.getY() - yOffset, _position.getX() + 75, _position.getY() - 75 - yOffset);
					Image rotateImg = AssetManager.Instance().getImage("bin/rotate.png");
					int imgX =  (int)getRotationHandlePosition().getX() ;
					int imgY =  (int)getRotationHandlePosition().getY() ;
					g.drawImage(rotateImg, imgX, imgY, 32, 32, null);
					g.setStroke(new BasicStroke(1));
				}
			}
		}
		else
		{
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

	}
	
	private Point getRotationHandlePosition()
	{
		Point p = new Point(_position.getX() + 75, _position.getY() - 75 - 32 - (int)_size.getHeight() /2);
		
		return p;
	}
	
	public Rectangle getRotationBox()
	{
		Rectangle r = new Rectangle(getRotationHandlePosition(), new Dimension(40, 40));
		
		return r;
	}

	private boolean hasBeenHovered()
	{
		if(isHovered)
		{
			timerSinceHovered = 30;
		}
		
		timerSinceHovered--;
		
		if(timerSinceHovered < 0)
			return false;
					
		else
			return true;
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
