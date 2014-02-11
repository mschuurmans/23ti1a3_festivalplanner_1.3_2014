package nl.avans.festivalplanner.utils;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
		{
			string = string.substring(0, string.length() - 1);
		}
		string += "...";
		
		return string;
	}
}
