package nl.avans.festivalplanner.model.simulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import nl.avans.festivalplanner.utils.Enums.Text;
import nl.avans.festivalplanner.utils.*;
import nl.avans.festivalplanner.model.FestivalHandler;
public class People extends Element
{
	private boolean debug = true;
	private int _timeUntillNewDest;
	private int _x1, _x2, _y1, _y2;
	private int _destX, _destY;
	private Area _destination;
	

	public People(Dimension size, Vector position)
	{
		super(size, position); 
		newDestination();
	}
	
	public void draw(Graphics2D g)
	{
		if(debug)
		{
			drawBackCanvas(g);
		}
		if (_timeUntillNewDest <= 0)
		{
			newDestination();
		}
		
		//TODO: this arraylist needs to be filled
		//FestivalHandler.Instance().getElementsOnTerrain();
		
		//goTo(_destination);
		
		_x1 = _position.getX() - (_size.width/2);
		_x2 = _position.getX() + (_size.width/2);
		_y1 = _position.getY() - (_size.height/2);
		_y2 = _position.getY() + (_size.height/2);
		g.draw(new Rectangle2D.Double(_x1, _x2, _y1, _y2));
	}
	
	public void update()
	{
		goTo();
	}
	
	private void newDestination()
	{
		//TODO: this arraylist needs to be filled
		ArrayList<Area> allAreas = new ArrayList<Area>();
		
		Random rand = new Random();
		int dest = rand.nextInt(allAreas.size())+1;
		_destination = allAreas.get(dest);
		_timeUntillNewDest = rand.nextInt(6000) + 600;
	}	

	/**
	 * Draws a small area around the person when debug mode is enabled.
	 * Usefull for checking if collision is working.
	 * @Author Michiel Schuurmans
	 */
	private void drawBackCanvas(Graphics2D g)
	{
		int height = (int)_size.getHeight();
		int width = (int)_size.getWidth();
		
		Utils.drawAreaBackground(g,_x1,_y1,width,height);
	}
	
	private void goTo()
	{
		if(_x1 > _destination.getPosition().getX() + _destination.getSize().width/2)
		{
			move(-1, 0);
		} 
		else if (_x2 < _destination.getPosition().getX()- _destination.getSize().width/2)
		{
			move(1, 0);
		}
		
		if(_y1 > _destination.getPosition().getY() + _destination.getSize().height/2)
		{
			move(0, -1);
		} 
		else if (_y2 < _destination.getPosition().getY() - _destination.getSize().height/2)
		{
			move(0, 1);
		}
	}
	
	private void move(int xMove, int yMove)
	{
		boolean collision = false;
		if (xMove < 0 || xMove == 0)
		{
			for (int i = 0; i < FestivalHandler.Instance().getElementsOnTerrain().size(); i++)
			{
				if(_x1 + xMove == FestivalHandler.Instance().getElementsOnTerrain().get(i)._position.getX()-FestivalHandler.Instance().getElementsOnTerrain().get(i)._size.width/2 &&
						_position != FestivalHandler.Instance().getElementsOnTerrain().get(i)._position)
					collision = true;
			}
		} 
		else
		{
			for (int i = 0; i < FestivalHandler.Instance().getElementsOnTerrain().size(); i++)
			{
				if(_x2 + xMove == FestivalHandler.Instance().getElementsOnTerrain().get(i)._position.getX()+FestivalHandler.Instance().getElementsOnTerrain().get(i)._size.width/2)
					collision = true;
			}
		}		
		
		if (yMove < 0 || yMove == 0)
		{
			for (int i = 0; i < FestivalHandler.Instance().getElementsOnTerrain().size(); i++)
			{
				if(_y1 + yMove == FestivalHandler.Instance().getElementsOnTerrain().get(i)._position.getY()-FestivalHandler.Instance().getElementsOnTerrain().get(i)._size.height/2 &&
						_position != FestivalHandler.Instance().getElementsOnTerrain().get(i)._position)
					collision = true;
			}
		} 
		else
		{
			for (int i = 0; i < FestivalHandler.Instance().getElementsOnTerrain().size(); i++)
			{
				if(_y2 + yMove == FestivalHandler.Instance().getElementsOnTerrain().get(i)._position.getY()+FestivalHandler.Instance().getElementsOnTerrain().get(i)._size.height/2)
					collision = true;
			}
		}
		
		if (!collision)
		{
			_timeUntillNewDest--;
			setPosition(new Vector(_position.getX()+xMove, _position.getY()+yMove));
		}
		else
		{
			// TODO: catch collision
		}
	}
}
