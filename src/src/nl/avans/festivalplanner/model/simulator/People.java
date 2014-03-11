package nl.avans.festivalplanner.model.simulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import nl.avans.festivalplanner.utils.Enums.Text;
import nl.avans.festivalplanner.utils.*;
import nl.avans.festivalplanner.model.Festival;
import nl.avans.festivalplanner.model.FestivalHandler;

public class People extends Element
{
	Vector _destination;
	float _speed, _direction;
	String _image;

	public People(Vector position, float speed, float direction)
	{
		super(new Dimension(16,16), position);
		this._speed = speed;
		this._direction = direction;
		this._destination = newDestination();
		_image = "resources\\people.png";

	}

	public void draw(Graphics2D g)
	{
		g.drawImage(AssetManager.Instance().getImage(_image), getTransform(), null);
	}

	public void update()
	{
		Vector oldPosition = _position;
		int posX = 0;
		int posY = 0;

		double directionTo = Math.atan2(
				_destination.getY() - _position.getY(), 
				_destination.getX() - _position.getX());
		float directionDifference = (float) (_direction - directionTo);
		if(directionDifference > Math.PI)
			directionDifference -= 2 * Math.PI;
		if(directionDifference < -Math.PI)
			directionDifference += 2 * Math.PI;

		if(Math.abs(directionDifference) < 0.1)
			_direction = (float) directionTo;
		else if(directionDifference < 0)
			_direction += 0.1f;
		else if(directionDifference > 0)
			_direction -= 0.1f;

		//if X and Y destination reached, choose new destination.
		if(destinationYReached() && destinationXReached())
		{
			newDestination();
		}

		//if not X destination reached, get closer
		if(!destinationXReached())
		{
			int moveX = (int)(_speed * Math.cos(_destination.getX())) + 1;
			if(moveX<= 0)
				moveX =1;

			if(_position.getX() == _destination.getX())
				posX = _position.getX();
			else if(_position.getX() < _destination.getX())
				posX = _position.getX() + moveX;

			else if(_position.getX() > _destination.getX())
				posX = _position.getX() - moveX;

		}
		else { posX = _destination.getX(); }

		//if not Y destination reached, get closer
		if(!destinationYReached())
		{
			int moveY = (int)(_speed * Math.cos(_destination.getY())) + 1;
			if(moveY<=0)
				moveY=1;

			if(_position.getY() == _destination.getY())
				posY = _position.getY();
			else if(_position.getY() < _destination.getY())
				posY = _position.getY() + moveY;

			else if(_position.getY() > _destination.getY())
				posY = _position.getY() - moveY;
		}
		else{ posY = _destination.getY(); }

		_position =new Vector(posX,posY);	

		if(hasCollision())
		{
			_position = oldPosition;
			_direction += 0.2f;
		}

	}

	/**
	 * @todo new desination is a new area
	 */
	private Vector newDestination()
	{
		ArrayList<Element> elements = new ArrayList<Element>();
		for(Element e : FestivalHandler.Instance().getElementsOnTerrain())
		{
			if(!(e instanceof People))
				elements.add(e);
		}
		
		if(elements.isEmpty()) //if no stages, get random position in field.
		{
			int posX = (int)(Math.random()*500)+25;
			int posY = (int)(Math.random()*500)+25;
			_destination = new Vector(posX, posY);
		}
		else
		{  
			//visitor will never get a new position, because of collision with stage...
			Collections.shuffle(elements);
			Element goTo = elements.get(0);
			int posX = goTo.getPosition().getX();
			int posY = goTo.getPosition().getY();
			_destination = new Vector(posX, posY);
		}
		return _destination;
	}	


	//rotate in walking direction
	private AffineTransform getTransform() 
	{
		AffineTransform tx = new AffineTransform();
		tx.translate(_position.getX()-8, _position.getY()-8);
		tx.rotate(_direction, 9, 9);
		return tx;
	}

	//Check if destination at X-position is reached, marging of +25
	private boolean destinationXReached()
	{
		if(_destination == null)
			return true;
		else if(_position == _destination)
			return true;
		else if((_position.getX() >= (_destination.getX()-25)) && (_position.getX()<=(_destination.getX()+25)))
			return true;

		else return false;
	}

	//Check if destination at Y-position is reached, marging of +25
	private boolean destinationYReached()
	{
		if(_destination == null)
			return true;
		else if(_position == _destination)
			return true;
		else if((_position.getY() >= (_destination.getY()-25)) && (_position.getY()<=(_destination.getY()+25)))
			return true;

		else return false;

	}

	private boolean hasCollision()
	{
		for(Element other : FestivalHandler.Instance().getElementsOnTerrain())
		{
			if(other.contains(_position.getPoint()))
				if(!other.equals(this)) 
					return true;
		}
		return false;
	}




}
