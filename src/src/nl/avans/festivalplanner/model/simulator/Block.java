package nl.avans.festivalplanner.model.simulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Block extends Area
{
	private static final long serialVersionUID = -1477418038039789580L;
	
	public Block()
	{
		super(new Dimension(100,50), new Vector(10,10));
	}
	
	public void draw(Graphics2D g)
	{
		Rectangle2D rect = new Rectangle2D.Double((int)(this._position.getX() - (this._size.getWidth() / 2)), (int)(this._position.getY() - (this._size.getHeight() / 2)) , (int)this._size.getWidth(), (int)this._size.getHeight());
		g.setColor(new Color(10,10,10,10));
		g.setColor(Color.black);
		g.fill(rect);
		//Utils.drawAreaBackground(g, (int)(this._position.getX() - (this._size.getWidth() / 2)), (int)(this._position.getY() - (this._size.getHeight() / 2)), (int)this._size.getWidth(), (int)this._size.getHeight());
	}
}
