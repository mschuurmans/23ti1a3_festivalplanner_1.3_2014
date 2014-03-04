package nl.avans.festivalplanner.utils;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.*;
import java.awt.geom.*;
public class Utils 
{

	public static int getPercentOfValue(int maxValue, int percent)
	{
		return (int)((maxValue / 100) * percent);
	}
	
	public static String getTimeString(Calendar gc)
	{
		String timeFormat = "HH:mm";
		return new SimpleDateFormat(timeFormat).format(new Timestamp(gc.getTimeInMillis()));
	}
	
	/**
	 * measures the width of a string
	 * @param s string to be measured
	 * @return width of the string in pixels
	 * @author jack
	 */
	public static int getWidth(String s)
	{
		int width;
		
//		width = s.length() * 3;
		
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
		Font font = new Font("Tahoma", Font.PLAIN, 12);
		width = (int)(font.getStringBounds(s, frc).getWidth());
		
		return width;
	}

	/**
	 * Crops a string to fit the maxWidth that has been passed in
	 * @param s string to be cropped
	 * @param maxWidth: max width(in pixels) the returned string has to fit in.
	 * @return a cropped string that fits the specified maxWidth
	 * @author jack
	 */
	public static String cropString(String s, int maxWidth)
	{
//		System.out.println("StringWidth and maxWidth"); //DEBUGGING PURPOSES
//		System.out.println(getWidth(s));
//		System.out.println(maxWidth);
		
		String string = s;
		
		if(getWidth(string) < maxWidth)
			return string;
			
		while(getWidth(string + "...") > maxWidth)
			string = string.substring(0, string.length() - 1);

		string += "...";
		
		return string;
	}
	
	/**
	 * writes an object to file
	 * @param file where to save the object.
	 * @param object object to be saved.
	 * 
	 * @author michiel
	 * @return
	 */
	public static boolean writeObject(File file, Object object)
	{
		try 
		{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		
			out.writeObject(object);
			out.flush();
			out.close();
			
			System.out.println("File wrote to: " + file.getAbsolutePath());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * reads an object from file
	 * @param file file to load
	 * @author michiel
	 * @return
	 */
	public static Object readObject(File file)
	{
		try
		{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			return in.readObject();
		}
		catch(Exception e)
		{ return null; }
	}

	public static void drawAreaBackground(Graphics2D g, int x, int y , int width, int height)
	{
		Rectangle2D rect = new Rectangle2D.Double(x,y,width,height);
	
		g.setColor(new Color(69,69,69,188));
		g.fill(rect);

		g.setColor(Color.black);
		g.draw(rect);	
	}
}
