package nl.avans.festivalplanner.model;

import java.io.Serializable;

public class Artist implements Serializable
{
	private static final long serialVersionUID = -6600526177584346069L;

	private int _id;
	private String _name;
	private String _genre;
	private String _comment;
	
	public void setId(int value)
	{
		this._id = value;
	}
	
	public int getId()
	{
		return this._id;
	}
	
	public void setName(String value)
	{
		this._name = value;
	}
	
	public String getName()
	{
		return this._name;
	}
	public void setGenre(String value)
	{
		this._genre = value;
	}
	
	public String getGenre()
	{
		return this._genre;
	}
	public void setComment(String value)
	{
		this._comment = value;
	}
	
	public String getComment()
	{
		return this._comment;
	}
	
	public String toString()
	{
		return this._name;
	}
}
