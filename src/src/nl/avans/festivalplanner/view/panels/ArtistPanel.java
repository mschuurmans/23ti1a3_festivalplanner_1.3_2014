package nl.avans.festivalplanner.view.panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import nl.avans.festivalplanner.model.Artist;
import nl.avans.festivalplanner.utils.Enums.Text;
import nl.avans.festivalplanner.utils.Utils;
import nl.avans.festivalplanner.view.ApplicationView;
import nl.avans.festivalplanner.view.GUIHelper;
import nl.avans.festivalplanner.view.Panel;

/**
 * @author Michiel Schuurmans
 */
public class ArtistPanel extends Panel
{
	private static final long serialVersionUID = 165737805530990946L;
	
	private JButton _addArtist;
	private JButton _removeArtist;
	private JButton _save;
	private JButton _cancel;
	private JButton _changeImage;
	private JButton _removeImage;
	
	private JLabel _nameLabel;
	private JLabel _genreLabel;
	private JLabel _commentsLabel;
	
	private JLabel _imageLabel;
	
	private JTextField _nameTextField;
	private JTextField _genreTextField;
	private JTextArea _commentTextArea;
	
	private JList _list;
	
	private GUIHelper _guiHelper;
		
	private DefaultListModel<Artist> _defaultListModel;
	
	@SuppressWarnings("unchecked")
	public ArtistPanel()
	{
		super();
		_guiHelper = new GUIHelper();
		
		_defaultListModel = new DefaultListModel<Artist>();
		Artist artist = new Artist();
		artist.setName("MICHIEL");
		
		_defaultListModel.addElement(artist);
		
		int width = ApplicationView.WIDTH;
		int height = ApplicationView.HEIGHT;
		int startY = 20;
		int workSetHeight = 480;
		int startX = Utils.getPercentOfValue(width, 1);
			
		int groupBoxWidth =  width - startX - 245;
		
		_list = new JList(_defaultListModel);
		_list.setBounds(startX, startY, 200, workSetHeight - 60);
		Border border = BorderFactory.createLineBorder(Color.gray, 1); 
		_list.setBorder(border);
		
		_addArtist = new JButton(Text.AddArtist.toString());
		_addArtist.setBounds(startX, startY + (workSetHeight - 60) + 5, 200, 25);
		_addArtist.addActionListener(this);
		
		_removeArtist = new JButton(Text.RemoveArtist.toString());
		_removeArtist.setBounds(startX, startY + (workSetHeight - 60) + 35, 200, 25);
		_removeArtist.addActionListener(this);
		
		JPanel groupBox = _guiHelper.getGroupBox(Text.Artist.toString(), null);
		int groupBoxX = startX + 210;
		groupBox.setBounds(groupBoxX, startY - 8, groupBoxWidth, workSetHeight + 8); // the -8 and + 8 is for nice allignment of the borders.
		
		_cancel = new JButton(Text.Cancel .toString());
		_cancel.setBounds(groupBoxWidth - 110, workSetHeight - 25 ,100,25);
		_cancel.addActionListener(this);
		
		_save = new JButton(Text.Save.toString());
		_save.setBounds(groupBoxWidth - 220, workSetHeight - 25, 100, 25);
		_save.addActionListener(this);
		
		_nameLabel = new JLabel(Text.Name.toString());
		_nameLabel.setBounds(15, 40, 50, 25);
		
		_nameTextField = new JTextField();
		_nameTextField.setBounds(70, 40, 200, 25);
		
		_genreLabel = new JLabel(Text.Genre.toString());
		_genreLabel.setBounds(15, 75, 50, 25);
		
		_genreTextField = new JTextField();
		_genreTextField.setBounds(70, 75, 200, 25);
		
		_commentsLabel = new JLabel(Text.CommentsOptional.toString());
		_commentsLabel.setBounds(15, 110, 200, 25);
		
		_commentTextArea = new JTextArea();
		_commentTextArea.setBounds(15, 145, 255, 250);
		
		URL artistImageUrl = getClass().getClassLoader().getResource("test.jpg");
		
		if(artistImageUrl != null)
		{
			_imageLabel = new JLabel("");
			_imageLabel.setBounds(groupBoxWidth - 220,-10, 250, 300);
			_imageLabel.setIcon((Icon) new ImageIcon(artistImageUrl));
		}
		
		add(_list);
		add(_addArtist);
		add(_removeArtist);
		add(groupBox);
		
		if(_imageLabel != null)
			groupBox.add(_imageLabel);
		
		groupBox.add(_cancel);
		groupBox.add(_save);
		groupBox.add(_nameLabel);
		groupBox.add(_nameTextField);
		groupBox.add(_genreLabel);
		groupBox.add(_genreTextField);
		groupBox.add(_commentsLabel);
		groupBox.add(_commentTextArea);
		
		_list.addListSelectionListener(new ListSelectionListener()
		{

			@Override
			public void valueChanged(ListSelectionEvent event) {
				Object obj = _list.getSelectedValue();
				
				if(obj instanceof Artist)
				{
					System.out.println("Het is een artiest");
				}
			}
			
		});
	}
			
	public Panel getPanel()
	{
		return this;
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		// TODO Auto-generated method stub
		if(event.getSource() == _save)
		{
			// save 
		}
		else if(event.getSource() == _cancel)
		{
			
		}
		else if(event.getSource() == _addArtist)
		{
			Artist artist = new Artist();
			artist.setName(Text.NewArtist + " " + _defaultListModel.getSize());
			_defaultListModel.addElement(artist);
		}
		else if(event.getSource() == _removeArtist)
		{
			
		}
	}
}


