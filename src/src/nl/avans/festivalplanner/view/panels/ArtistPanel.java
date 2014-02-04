package nl.avans.festivalplanner.view.panels;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import nl.avans.festivalplanner.utils.Enums.Text;
import nl.avans.festivalplanner.utils.Utils;
import nl.avans.festivalplanner.view.ApplicationView;
import nl.avans.festivalplanner.view.GUIHelper;
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
	
	private JList _list;
	
	private GUIHelper _guiHelper;
	
	public ArtistPanel()
	{
		super();
		_guiHelper = new GUIHelper();
		
		int width = ApplicationView.WIDTH;
		int height = ApplicationView.HEIGHT;
		
		
		JPanel groupBox = _guiHelper.getGroupBox(Text.Artist.toString());
		groupBox.setBounds(Utils.getPercentOfValue(width, 30), 50, Utils.getPercentOfValue(width, 70), Utils.getPercentOfValue(height, 70));
		
		
		_addArtist = new JButton(Text.AddArtist.toString());
		add(groupBox);
	}
	
	public Panel getPanel()
	{
		return this;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
