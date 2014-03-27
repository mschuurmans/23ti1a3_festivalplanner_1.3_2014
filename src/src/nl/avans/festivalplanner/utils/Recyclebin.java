package nl.avans.festivalplanner.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import nl.avans.festivalplanner.model.simulator.Element;
import nl.avans.festivalplanner.model.simulator.Vector;

public class Recyclebin
{
	private boolean _visible;
	private boolean _beingTouched;
	
	private Dimension _size = null;
	private Vector _position = null;

	private Rectangle _boundingBox;
	
	private Element _elementInHand = null;
	
	public Recyclebin()
	{
		_visible = false;
		_beingTouched = false;
		
		this._size = new Dimension(200, 50);
		this._position = new Vector(722/2 - (int)_size.getWidth()/2 , 0);
		
		this._boundingBox = new Rectangle(_position.getPoint(), _size);
	}
	
	public void display()
	{
		this._visible = true;
	}
	
	public void hide()
	{
		this._visible = false;
	}
	
	public void beingTouched(boolean b)
	{
		this._beingTouched = b;
	}
	
	public void setElementInHand(Element element)
	{
		this._elementInHand = element;
	}
	
	public Element getElementInHand()
	{
		return this._elementInHand;
	}
	
	public void draw(Graphics2D g2)
	{			
		if(!_visible)
			return;
		
		boolean debugmethod = false;
		
		int x = _position.getPoint().x;
		int y = _position.getPoint().y;
		
		g2.setColor(Color.white);
		
		if(debugmethod)
		{
			g2.draw(_boundingBox);
			g2.drawString("ElementIsInHand: " + (_elementInHand != null), x, y + 50);
		}
		
		if(_beingTouched)
		{
			g2.setColor(Color.red);
		}

		x += 45;
		y += 10;
		
		g2.setStroke(new BasicStroke(3));
		g2.drawLine(x, y, x+15, y+15);
		g2.drawLine(x, y+15, x+15, y);

		x += 30;
		y += 15;
		
		Font font = new Font("SANS_SERIF", Font.BOLD, 21);
		g2.setFont(font);
		g2.drawString(Enums.Text.Remove.toString(), x, y);
	}
	
	public boolean contains(Point point)
	{
		return _boundingBox.contains(point);
	}
}
