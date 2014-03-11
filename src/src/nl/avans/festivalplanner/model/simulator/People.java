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
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import nl.avans.festivalplanner.utils.Enums.Text;
import nl.avans.festivalplanner.utils.*;
import nl.avans.festivalplanner.model.FestivalHandler;

public class People extends Element
{
	Vector _destination = null;
	float _speed;
	String _image;

	public People(Vector position, float speed)
	{
		super(new Dimension(16,16), position);
		this._speed = speed;
		_image = "resources\\people.png";

	}

	public void draw(Graphics2D g)
	{
		g.drawImage(AssetManager.Instance().getImage(_image), _position.getX(), _position.getY(), 16, 16, null);
		//System.out.println("tekenen");
		System.out.println("Pos: " + _position.toString());
		System.out.println("Dir: " + _destination.toString());
	}

	public void update()
	{
		int posX = 0;
		int posY = 0;
		if(destinationYReached() && destinationXReached())
		{
			newDestination();
		}

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
		
		if(!destinationYReached())
		{
			int moveY = (int)(_speed * Math.cos(_destination.getY())) + 1;
			if(moveY<=0)
				moveY=1;
			
			System.out.println(moveY);
			
			if(_position.getY() == _destination.getY())
				posY = _position.getY();
			else if(_position.getY() < _destination.getY())
				posY = _position.getY() + moveY;

			else if(_position.getY() > _destination.getY())
				posY = _position.getY() - moveY;
		}
		else{ posY = _destination.getY(); }

		_position =new Vector(posX,posY);	

	}

	private void newDestination()
	{
		int posX = (int)(Math.random()*500)+25;
		int posY = (int)(Math.random()*500)+25;
		_destination = new Vector(posX, posY);
	}	

	/**
	 * Draws a small area around the person when debug mode is enabled.
	 * Usefull for checking if collision is working.
	 * @Author Michiel Schuurmans
	 */
	//	private void drawBackCanvas(Graphics2D g)
	//	{
	//		int height = (int)_size.getHeight();
	//		int width = (int)_size.getWidth();
	//		
	//		Utils.drawAreaBackground(g,_x1,_y1,width,height);
	//	}

	private AffineTransform getTransform() 
	{
		AffineTransform tx = new AffineTransform();
		tx.translate(_position.getX()-8, _position.getY()-8);
		//tx.rotate(_direction, 32, 32);
		return tx;
	}

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




}
