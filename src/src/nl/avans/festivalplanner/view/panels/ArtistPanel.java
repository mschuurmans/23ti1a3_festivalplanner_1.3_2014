package nl.avans.festivalplanner.view.panels;

import javax.swing.JButton;

import nl.avans.festivalplanner.utils.Enums.Text;
import nl.avans.festivalplanner.view.Panel;

public class ArtistPanel extends Panel
{
	private static final long serialVersionUID = -1604149373455109150L;
	
	private JButton _addArtist;
	private JButton _removeArtist;
	private JButton _save;
	private JButton _cancel;
	private JButton _changeImage;
	private JButton _removeImage;
	
	public ArtistPanel()
	{
		_addArtist = new JButton(Text.AddArtist.toString());
		add(_addArtist);
	}
}
