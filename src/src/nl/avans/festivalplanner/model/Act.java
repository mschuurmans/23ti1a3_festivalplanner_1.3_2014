/**
 * 
 */
package nl.avans.festivalplanner.model;

import java.awt.Dimension;

/**
 * @author Jordy Sipkema
 *
 */
public class Act
{
	private String _name = "";
	private int _capacity = -1;
	private Dimension _sizeStage = null;
	private Dimension _sizeField = null;
	/**
	 * @return the _name
	 */
	public String getName()
	{
		return _name;
	}
	/**
	 * @param _name the _name to set
	 */
	public void setName(String name)
	{
		this._name = name;
	}
	/**
	 * @return the _capacity
	 */
	public int getCapacity()
	{
		return _capacity;
	}
	/**
	 * @param _capacity the _capacity to set
	 */
	public void setCapacity(int capacity)
	{
		this._capacity = capacity;
	}
	/**
	 * @return the _sizeStage
	 */
	public Dimension getSizeStage()
	{
		return _sizeStage;
	}
	/**
	 * @param _sizeStage the _sizeStage to set
	 */
	public void setSizeStage(Dimension sizeStage)
	{
		this._sizeStage = sizeStage;
	}
	/**
	 * @return the _sizeField
	 */
	public Dimension getSizeField()
	{
		return _sizeField;
	}
	/**
	 * @param _sizeField the _sizeField to set
	 */
	public void setSizeField(Dimension sizeField)
	{
		this._sizeField = sizeField;
	}
	
	
	
}
