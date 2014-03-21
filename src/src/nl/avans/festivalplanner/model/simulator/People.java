package nl.avans.festivalplanner.model.simulator;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import nl.avans.festivalplanner.utils.*;
import nl.avans.festivalplanner.model.FestivalHandler;

public class People extends Element
{
	private static final long serialVersionUID = -6083060574056023471L;
	Vector _destination;
	Vector _nextDestination;
	Element _destinationElement, _nextDestinationElement;
	float _speed, _direction;
	String _image;

	public People(Vector position, float speed, float direction)
	{
		super(new Dimension(16,16), position);
		this._speed = 1;
		this._direction = direction;
		this._destination = newDestination();
		this._destinationElement = null;
		this._nextDestinationElement = null;
		_image = "bin/people.png";

	}

	public void draw(Graphics2D g)
	{
		g.drawImage(AssetManager.Instance().getImage(_image), getTransform(), null);
	}

	//rotate in walking direction
	private AffineTransform getTransform() 
	{
		AffineTransform tx = new AffineTransform();
		tx.translate(_position.getX()-8, _position.getY()-8);
		tx.rotate(_direction, 9, 9);
		return tx;
	}

	public void update()
	{
		Vector oldPosition = _position;
		int posX = 0;
		int posY = 0;

		if(_nextDestination == null || _nextDestinationElement == null)
		{newNextDestination();}

		double directionTo = Math.atan2(
				_nextDestination.getY() - _position.getY(), 
				_nextDestination.getX() - _position.getX());
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



		if(destinationYReached() && destinationXReached())
		{
			newDestination();
		}

		if(nextDestinationReached())
		{
			newNextDestination();
		}


		if(!nextDestinationXReached())
		{
			int moveX = (int)((_speed * Math.cos(_nextDestination.getX())) + 1);
			if(moveX<= 0)
				moveX = 1;

			if(_position.getX() == _nextDestination.getX())
				posX = _position.getX();
			else if(_position.getX() < _nextDestination.getX())
				posX = _position.getX() + moveX;

			else if(_position.getX() > _nextDestination.getX())
				posX = _position.getX() - moveX;

		}
		else { posX = _nextDestination.getX(); }

		//if not Y destination reached, get closer
		if(!nextDestinationYReached())
		{
			int moveY = (int)((_speed * Math.cos(_nextDestination.getY())) + 1);
			if(moveY<=0)
				moveY=1;
			//			System.out.println(moveY);
			if(_position.getY() == _nextDestination.getY())
				posY = _position.getY();
			else if(_position.getY() < _nextDestination.getY())
				posY = _position.getY() + moveY;

			else if(_position.getY() > _nextDestination.getY())
				posY = _position.getY() - moveY;
		}
		else{ posY = _nextDestination.getY(); }

		_position =new Vector(posX,posY);	


		//Collision checking
		if(hasCollision())
		{
			_position.setX(oldPosition.getX()+(int)(10*(Math.random()-0.5)));
			_position.setY(oldPosition.getY()+(int)(10*(Math.random()-0.5)));
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
			//Un-comment als Area is geimplementeerd.
			//if(!(e instanceof People) && (e instanceof Area) && !(e instanceof Building)) 
			if(!(e instanceof People))
				elements.add(e);
		}


		try{
		int size = elements.size();
		int i = (((int)(Math.random() * size))); // get a random index.
		Element goTo = elements.get(i);
		_destination = goTo.getRandomPosition();
		_destinationElement = goTo;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return _destination;
	}

	private Vector newNextDestination()
	{	
		if(_nextDestinationElement != null)
		{
			try{
				_nextDestinationElement = RouteManager.instance().getNextDestination(_destinationElement, _nextDestinationElement);
				_nextDestination = _nextDestinationElement.getRandomPosition();
			}
			catch(Exception e)
			{
				_nextDestinationElement = _destinationElement;
				_nextDestination = _destination;

			}
		}

		else
		{
			_nextDestinationElement = _destinationElement;
			_nextDestination = _destination;

		}

		return _nextDestination;
	}


	private boolean nextDestinationReached()
	{
		if(nextDestinationYReached() && nextDestinationXReached())
			return true;
		else return false;
	}

	private boolean nextDestinationYReached()
	{
		if(_nextDestination == null)
			return true;		
		else if(_position == _nextDestination)
			return true;
		else if((_position.getY() >= (_nextDestination.getY()-25)) && (_position.getY()<=(_nextDestination.getY()+25)))
			return true;
		else return false;
	}

	private boolean nextDestinationXReached()
	{
		if(_nextDestination == null)
			return true;		
		else if(_position == _nextDestination)
			return true;
		else if((_position.getX() >= (_nextDestination.getX()-25)) && (_position.getX()<=(_nextDestination.getX()+25)))
			return true;
		else return false;
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
			if(other instanceof People)
				if(other.contains(_position.getPoint()))
					if(!other.equals(this) ) 
						return true;
		}
		return false;
	}

	private boolean hasCollisionBuilding()
	{
		for(Element other : FestivalHandler.Instance().getElementsOnTerrain())
		{
			if(other instanceof Building)
			{
				if(other.contains(_position.getPoint()))
					if(!other.equals(this))
						return true;
			}
		}
		return false;
	}

	private boolean hasCollisionArea()
	{
		for(Element other : FestivalHandler.Instance().getElementsOnTerrain())
		{
			if(other instanceof Area && !(other instanceof Building))
				if(other.contains(_position.getPoint()))
					if(!other.equals(this) ) 
						return true;
		}
		return false;
	}




}
