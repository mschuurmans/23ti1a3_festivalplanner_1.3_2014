<<<<<<< HEAD
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
		Stages("Podium", "Stage"),
		Info("Info", "Info"),
		Schedule("Agenda", "Schedule"),
		Map("Kaart", "Map"),
		AddStage("Voeg podium toe", "Add stage"),
		RemoveStage("Verwijder podium", "Delete stage"),
		StageSize("Oppervlak (podium)", "Size (stage)"),
		FieldSize("Oppervlak (veld)", "Size (field)"),
		SizeDef("L x B [m]", "L x W [m]"),
		Capacity("Capaciteit", "Capacity"),
		People("Bezoekers", "Visitors"),
		NoImageAvailable("Geen Afbeelding \nbeschikbaar", "No image \navailable"),
		NewArtist("Nieuwe artiest", "New artist");

		
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
=======
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
		Stages("Podium", "Stage"),
		Info("Info", "Info"),
		Schedule("Agenda", "Schedule"),
		Map("Kaart", "Map"),
		AddStage("Voeg podium toe", "Add stage"),
		RemoveStage("Verwijder podium", "Delete stage"),
		StageSize("Oppervlak (podium)", "Size (stage)"),
		FieldSize("Oppervlak (veld)", "Size (field)"),
		SizeDef("L x B [m]", "L x W [m]"),
		Capacity("Capaciteit", "Capacity"),
		People("Bezoekers", "Visitors"),
		NoImageAvailable("Geen Afbeelding \nbeschikbaar", "No image \navailable");

		
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
>>>>>>> 67151ebe38d61bd277ba0fa632e7bc35e2e2317e
