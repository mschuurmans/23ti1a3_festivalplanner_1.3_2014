package nl.avans.festivalplanner.utils;

public class Enums 
{
	public static enum Languages
	{
		dutch,
		english
	}
	
	public static enum Text
	{
		hello("Hallo", "Hello");
		
		private String _nl;
		private String _eng;
		private Languages _lang = Languages.dutch;
		
		Text(String nl, String eng)
		{
			this._nl = nl;
			this._eng = eng;
		}
		
		public String toString()
		{
			if(_lang == Languages.dutch)
				return this._nl;
			else
				return this._eng;
		}
	}
}
