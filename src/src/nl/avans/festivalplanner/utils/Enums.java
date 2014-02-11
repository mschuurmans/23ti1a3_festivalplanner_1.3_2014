
package nl.avans.festivalplanner.utils;

import nl.avans.festivalplanner.utils.Enums.Languages;

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
		FestivalName("Festival naam", "Festival name"),
		Date("Datum", "Date"),
		Tickets("Kaartjes", "Tickets"),
		NewArtist("Nieuwe artiest", "New artist"),
		DateFormat("dd-MM-yyyy", "MM/dd/yyyy"),
		AboutMessage("Software geschreven door: 23TI1A5", "Software written by: 23TI1A5"),
		About("Over", "About"),
		Close("Sluiten", "Close"),
		File("Bestand", "File"),
		Settings("Instellingen", "Settings"),
		Help("Help", "Help");

		
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

