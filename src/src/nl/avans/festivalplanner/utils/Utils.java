package nl.avans.festivalplanner.utils;

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
}
