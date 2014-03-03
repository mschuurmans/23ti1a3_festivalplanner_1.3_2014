package nl.avans.festivalplanner.model;

import java.awt.Dimension;
import java.io.Serializable;

import nl.avans.festivalplanner.model.simulator.Building;

public class Stage extends Building implements Serializable
{
	private static final long serialVersionUID = 7479523431094662053L;

	private String _name;
	private int _capacity;
	private Dimension _sizeStage;
	private Dimension _sizeField;
	private String _imageSource;

	public Stage()
	{
		super(new Dimension(0,0), null);
	}

	public Stage(String name, int capacity, Dimension stageSize, Dimension fieldSize, String imageSource)
	{
		super(stageSize, null);
		this._name = name;
		this._capacity = capacity;
		this._sizeStage = stageSize;
		this._sizeField = fieldSize;
		this._imageSource = imageSource;
	}
	
	public void setImageSource(String value)
	{
		this._imageSource = value;
	}

	public String getImageSource()
	{
		return this._imageSource;
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

	public void setStageSize(double width, double height)
	{
		Dimension value = new Dimension((int)width,(int) height);
		this._sizeStage = value;
		super.setSize(value);
	}

	public Dimension getStageSize()
	{
		return this._sizeStage;
	}

	public void setFieldSize(double width, double height)
	{
		Dimension value = new Dimension((int)width, (int)height);
		this._sizeField = value;
	}

	public Dimension getFieldSize()
	{
		return this._sizeField;
	}

	public String toString()
	{
		return getName();
	}

	public boolean equals(Stage other) {
		if (!(other instanceof Stage)) {
			return false;
		}

		// Custom equality check here.
		return this._name.equals(other.getName());
	}
}
