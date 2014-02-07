package nl.avans.festivalplanner.model;

import java.awt.Dimension;
import java.io.Serializable;

public class Stage implements Serializable
{
	private static final long serialVersionUID = 7479523431094662053L;
	
	private String _name;
	private int _capacity;
	private Dimension _sizeStage;
	private Dimension _sizeField;
	
	public Stage(String name, int capacity, Dimension stageSize, Dimension fieldSize)
	{
		this._name = name;
		this._capacity = capacity;
		this._sizeStage = stageSize;
		this._sizeField = fieldSize;
	}
	
	public void setName(String value)
	{
		_name = value;
	}
	
	public String getName()
	{
		return this._name;
	}
	
	public void setCapacity(int value)
	{
		this._capacity = value;
	}
	
	public int getCapacity()
	{
		return this._capacity;
	}
	
	public void setStageSize(Dimension value)
	{
		this._sizeStage = value;
	}
	
	public Dimension getStageSize()
	{
		return this._sizeStage;
	}
	
	public void setFieldSize(Dimension value)
	{
		this._sizeField = value;
	}
	
	public Dimension getFieldSize()
	{
		return this._sizeField;
	}
}
