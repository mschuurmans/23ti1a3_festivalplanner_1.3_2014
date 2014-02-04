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
		hello("Hallo", "Hello"),
		AddArtist("Artiest toevoegen", "Add artist"),
		RemoveArtist("Artiest verwijderen", "Delete artist"),
		Save("Opslaan", "Save"),
		Cancel("Annuleren", "Cancel"),
		ChangeImage("Foto aanpassen", "Change picture"),
		RemoveImage("Foto verwijderen", "Remove picture"),
		Name("Naam", "Name"),
		Genre("Genre", "Genre"),
		Comments("Opmerking", "Comments"),
		Optional("Optioneel", "Optional"),
		CommentsOptional("Opmerking (Optioneel)", "Comments (Optional)"),
		Artist("Artiest", "Artist"),
		Stages("Podium", "Stage");
		
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
