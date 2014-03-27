package nl.avans.festivalplanner.model.simulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import nl.avans.festivalplanner.utils.*;
import nl.avans.festivalplanner.model.Act;
import nl.avans.festivalplanner.model.FestivalHandler;
import nl.avans.festivalplanner.model.Stage;

/**
 * @author Kasper Balink
 * @version 27-03-2014
 */

public class People extends Element
{

	private static final long serialVersionUID = -6083060574056023471L;
	Vector _destination;
	Vector _nextDestination;
	Element _destinationElement, _nextDestinationElement;
	float _speed, _direction;
	String _image;
	private final int _MINWAITTIME = 5;
	private final int _MAXWAITTIME = 50;

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
			if (waitRandomTime())
			{
				newDestination();
			}
		}

		if(nextDestinationReached())
		{
			if (waitRandomTime())
			{
				newNextDestination();
			}
		}

		//Collision checking
		while(hasCollisionBuilding())
		{	
			if((_position.getY() > _destination.getY()))
			{
				_position.setX(_position.getX()-1);
			}
			if((_position.getY() < _destination.getY()))
			{
				_position.setX(_position.getX()+1);
			}
			
			if((_position.getX() > _destination.getX()))
			{
				_position.setY(_position.getY()+1);
			}
			if((_position.getX() < _destination.getX()))
			{
				_position.setY(_position.getY()-1);
			}
			_direction += 0.2f;
		}

		if(hasCollision())
		{

			_position.setX(oldPosition.getX()-(int)(10*(Math.random()-0.5)));
			_position.setY(oldPosition.getY()+(int)(10*(Math.random()-0.5)));
			//_position = oldPosition;
			_direction += 0.2f;
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
			for (int index = 0; index < size; index++)
			{
				if (elements.get(index) instanceof Stage)
				{
					int _timeHour = FestivalHandler.Instance().getControls().getHour();
					//int _timeMinute = FestivalHandler.Instance().getControls().getMinute();
					//GregorianCalendar _time = new GregorianCalendar();
					//_time.set(2014, 2, 1, _timeHour, _timeMinute);
					for (Act _a : FestivalHandler.Instance().getActs())
					{
						if (_a.getStartTime().get(Calendar.HOUR_OF_DAY) <= _timeHour &&
								(_a.getEndTime().get(Calendar.HOUR_OF_DAY) > _timeHour ||
										(_a.getEndTime().get(Calendar.HOUR_OF_DAY) < 12 &&
												_a.getEndTime().get(Calendar.HOUR_OF_DAY) + 12 > _timeHour))
												&&	_a.getStage().equals(elements.get(index)))
						{
							//System.out.println("Popularity yeah!");
							for (int pop = 1; pop < _a.getArtist().getPopularity()*5; pop++)
							{
								//System.out.println(_a.getStartTime().get(Calendar.HOUR_OF_DAY) + " - " + _a.getEndTime().get(Calendar.HOUR_OF_DAY));
								//System.out.println("it's now: " + _timeHour + " " + _a.getArtist().getName() + " is popular!!!");
								//System.out.println("Popularity yeah!");
								elements.add(elements.get(index));
							}
						}
					}
					//elements.get(index).
				}
			}
			int i = (((int)(Math.random() * elements.size()))); // get a random index.
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
				System.out.println("Current Reached: " + _nextDestinationElement.toString());
				//_nextDestinationElement = FestivalHandler.Instance().getNextElement(_nextDestinationElement, _destinationElement);
				_nextDestinationElement = RouteManager.instance().getNextDestination(_destinationElement, _nextDestinationElement);

				_nextDestination = _nextDestinationElement.getRandomPosition();
				System.out.println("Next step: " + _nextDestinationElement.toString() + " - Destination: " + _destinationElement.toString());
			}
			catch(Exception e)
			{
				_nextDestinationElement = _destinationElement;
				_nextDestination = _destination;
				e.printStackTrace();
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
		else if((_position.getY() >= (_nextDestination.getY()-15)) && (_position.getY()<=(_nextDestination.getY()+15)))
			return true;
		else return false;
	}

	private boolean nextDestinationXReached()
	{
		if(_nextDestination == null)
			return true;		
		else if(_position == _nextDestination)
			return true;
		else if((_position.getX() >= (_nextDestination.getX()-15)) && (_position.getX()<=(_nextDestination.getX()+15)))
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
		else if((_position.getX() >= (_destination.getX()-15)) && (_position.getX()<=(_destination.getX()+15)))
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
		else if((_position.getY() >= (_destination.getY()-15)) && (_position.getY()<=(_destination.getY()+15)))
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
				Building b = (Building) other;
				if(b.getImagePosition().contains(_position.getPoint()))
					if(!b.equals(this))
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

	private boolean waitRandomTime()
	{
		int _waitTime = (((int)(Math.random() * (_MAXWAITTIME-_MINWAITTIME)))) + _MINWAITTIME; // get a random index.
		return (_waitTime == _MINWAITTIME);		
	}


}
