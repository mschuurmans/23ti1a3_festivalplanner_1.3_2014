package nl.avans.festivalplanner.view.panels;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import nl.avans.festivalplanner.view.Panel;

public class MapPanel extends Panel {
	private Image image;
	
	public MapPanel() throws IOException 
	{
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		image = ImageIO.read(ClassLoader.getSystemResourceAsStream("map.jpg"));
	}
	
	protected void paintComponent(Graphics g) 
	{
	    super.paintComponent(g);
	    g.drawImage(image, 0, 0, getWidth(), getHeight(), null); // see javadoc for more info on the parameters            
	}

	@Override
	public Panel getPanel() 
	{
		return this;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
