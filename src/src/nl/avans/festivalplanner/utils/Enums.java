package nl.avans.festivalplanner.utils;

import nl.avans.festivalplanner.settings.SettingsController;

public class Enums 
{
	
	public static enum Languages
	{
		dutch("Dutch"),
		english("English");
		
		private String _s;
		Languages(String s){_s = s;}
		
		public String toString(){ return _s; }
	}

	public static enum States
	{
		Running,
		Stopped;
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
		Visitors("Bezoekers", "Visitors"),
        Popularity("Populariteit", "Popularity"),
		NoImageAvailable("Geen Afbeelding \nbeschikbaar", "No image \navailable"),
		FestivalName("Festival naam", "Festival name"),
		Date("Datum", "Date"),
		Tickets("Kaartjes", "Tickets"),
		NewArtist("Nieuwe artiest", "New artist"),
		DateFormat("dd-MM-yyyy", "MM/dd/yyyy"),
		AboutMessage("Software geschreven door: 23TI1A3", "Software written by: 23TI1A3"),
		About("Over", "About"),
		Close("Sluiten", "Close"),
		File("Bestand", "File"),
		Settings("Instellingen", "Settings"),
		Help("Help", "Help"),
		FileExistsWantToOverride("Bestand bestaat al. Wilt u het overschrijven?", "File already exists. Do you want to override it?"),
		Warning("Waarschuwing", "Warning"),
		FileSavedSucces("Het bestand is opgeslagen.", "The file has been saved."),
		FileDoesNotExist("Bestand bestaat niet.", "File does not exist"),
		FileOpenedSucces("Het bestand is geladen.", "File has been loaded."),
		LoadMap("Nieuwe kaart openen", "Open new map"),
		Language("Taal", "Language"),
		ChangeMap("Kaart aanpassen", "Change map"),
		RemoveMap("Kaart verwijderen", "Remove map"),
		Dutch("Nederlands", "Dutch"),
		English("Engels", "English"),
		EditAct("Act aanpassen", "Edit act"),
		BeginTime("Begin tijd", "Begin time"),
		EndTime("Eind tijd", "End time"),
		Delete("Verwijderen", "Delete"),
		StartSimulation("Start simulatie", "Start simulation"),
		Controls("Besturing", "Controls"),
		Start("Start", "Start"),
		Stop("Stop", "Stop"),
		Stalls("Kraampjes", "Stalls"),
		Facilities("Faciliteiten", "Facilities"),
		Remaining("Overige", "Remaining"),
		Area("Gebied","Area"),
		Building("Gebouw","Building"),
		Drinkstand("Drankkraam","Drinkstand"),
		Element("Element","Element"),
		Entrance("Ingang", "Entrance"),
		Foodstand("Eetkraam", "Foodstand"),
		Infostand("Informatiekraam","Infostand"),
		Intersection("Kruispunt", "Intersection"),
		People("Persoon", "People"),
		Toilet("Toilet","Toilet"),
		Signpost("Wegwijzer","Signpost"),
		Speed("Snelheid", "Speed"),
		Unknown("Onbekend", "Unknown"),
		Remove("Verwijderen", "Remove");

		
		private String _nl;
		private String _eng;
		
		Text(String nl, String eng)
		{
			this._nl = nl;
			this._eng = eng;
		}
		
		public static Text parse(String text){
			for (Text txt : Text.values()){
				//Als een van de twee voorkomt:
				if (txt._eng.equalsIgnoreCase(text) || txt._nl.equalsIgnoreCase(text))
					return txt;
			}
			return Text.Unknown; 
		}
		
		public String toString()
		{
			if(SettingsController.Instance().getLanguage() == Languages.dutch)
				return this._nl;
			else
				return this._eng;
		}
		
		
	}
}

