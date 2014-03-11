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
		System.out.println(_position.toString());
	}

	public void update()
	{
		if(destinationReached())
		{
			newDestination();
		}
		else 
		{
			int posX = 0;
			int posY = 0;
			if(_position.getX() < _destination.getX())
				posX = _position.getX() + (int)(_speed * Math.cos(_destination.getX()));

			else if(_position.getX() > _destination.getX())
				posX = _position.getX() - (int)(_speed * Math.cos(_destination.getX()));

			if(_position.getY() < _destination.getY())
				posY = _position.getY() + (int)(_speed * Math.sin(_destination.getY()));

			else if(_position.getY() > _destination.getY())
				posY = _position.getY() - (int)(_speed * Math.sin(_destination.getY()));

			if(posY<0)
				posY=0;
			if(posX<0)
				posX=0;
			
			_position =new Vector(posX,posY);
		}
		//System.out.println(_speed * Math.cos(_destination.getX());

	}

	private void newDestination()
	{
		_destination = new Vector((int)(Math.random()*500+50), (int)(Math.random()*500+50));
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

	private boolean destinationReached()
	{
		if(_destination == null)
			return true;
		else if(_position == _destination)
			return true;
		else if(_position.getX() > _destination.getX()-25 && _position.getX()<_destination.getX()+25
				&& _position.getY() > _destination.getY()-25 && _position.getY()<_destination.getY()+25)
			return true;
		
		else return false;
	}




}
