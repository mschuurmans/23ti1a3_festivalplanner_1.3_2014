package nl.avans.festivalplanner.model.simulator;

import java.util.*;
import nl.avans.festivalplanner.utils.Enums.States;

public class SimulatorControl
{
	private States _state = States.Stopped;

	private int _hour = 0;
	private int _minute = 0;
	
	private int _speed = 1;

	public void setState(States value)
	{
		this._state = value;
	}	

	public States getState()
	{
		return this._state;
	}

	public void setSpeed(int value)
	{
		this._speed = value;
	}

	public void update()
	{
		updateTime();
	}

	public String getTime()
	{
		return "" + _hour + ":" + _minute;
	}

	private void updateTime()
	{
		if((_minute + _speed) >= 60)
		{
			if((_hour + 1) == 24)
				_hour = 0;
			else
				_hour++;

			_minute = 0;
		}
		else
			_minute+=_speed;
	}
}
