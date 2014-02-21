package nl.avans.festivalplanner.view.panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import nl.avans.festivalplanner.model.Artist;
import nl.avans.festivalplanner.model.FestivalHandler;
import nl.avans.festivalplanner.utils.Enums.Text;
import nl.avans.festivalplanner.utils.Utils;
import nl.avans.festivalplanner.view.ApplicationView;
import nl.avans.festivalplanner.view.GUIHelper;
import nl.avans.festivalplanner.view.Panel;

public class ArtistPanel extends Panel
{
	private static final long serialVersionUID = 165737805530990946L;
	
	private boolean debug = true;
	
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
	private Artist _selectedArtist = new Artist();
		
	@SuppressWarnings("unchecked")
	public ArtistPanel()
	{
		super();
		_guiHelper = new GUIHelper();
		_defaultListModel = new DefaultListModel<Artist>();
				
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
		
		JScrollPane scrollPane = new JScrollPane(_list);
		scrollPane.setBounds(startX, startY, 200, workSetHeight - 60);
		
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
		
		_imageLabel = new JLabel("");
		_imageLabel.setBounds(groupBoxWidth - 220,-10, 250, 300);
		
		changeArtistImage("test.jpg");
		
		_changeImage = new JButton(Text.ChangeImage.toString());
		_changeImage.setBounds(groupBoxWidth - 220, 275, 210, 25);
		_changeImage.addActionListener(this);
		
		_removeImage = new JButton(Text.RemoveImage.toString());
		_removeImage.setBounds(groupBoxWidth - 220, 310, 210, 25);
		_removeImage.addActionListener(this);
		
		add(scrollPane);
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
		groupBox.add(_changeImage);
		groupBox.add(_removeImage);
		
		_list.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent event) {
				Object obj = _list.getSelectedValue();
				
				if(obj instanceof Artist)
				{
					updateView((Artist)obj);
					changeArtistImage(((Artist)obj).getImageSource());
				}
			}
		});
		
		// finally load data into the list
		loadArtistsFromHandler();
	}
			
	public void updateView(Artist artist)
	{
		_selectedArtist = artist;
		_nameTextField.setText(artist.getName());
		_genreTextField.setText(artist.getGenre());
		_commentTextArea.setText(artist.getComment());
		
		//changeArtistImage(artist.getImageSource());
		
		if(debug)
			System.out.println("View is aangepast.");
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
			if(_list.getSelectedIndex() != -1)
			{
				Artist artist = new Artist();
				artist.setName(_nameTextField.getText());
				artist.setGenre(_genreTextField.getText());
				artist.setComment(_commentTextArea.getText());
				artist.setImageSource(_selectedArtist.getImageSource());
				_defaultListModel.setElementAt(artist, _list.getSelectedIndex());
				
				if(debug)
					System.out.println("Waardes zijn geupdate.");
				
				updateView(artist);
			}							
			
			saveArtists();
			
		}
		else if(event.getSource() == _cancel)
		{
			updateView(_selectedArtist);
		}
		else if(event.getSource() == _addArtist)
		{
			Artist artist = new Artist();
			artist.setName(Text.NewArtist + " " + _defaultListModel.getSize());
			_defaultListModel.addElement(artist);
			
			_list.setSelectedIndex(_defaultListModel.size());
			
			saveArtists();
		}
		else if(event.getSource() == _removeArtist)
		{
			Object obj = _list.getSelectedValue();
			int index = _list.getSelectedIndex();
			if(obj instanceof Artist)
			{
				_defaultListModel.removeElement(obj);
				
				if((index - 1) >= 0)
					_list.setSelectedIndex(index - 1);
			}
		}
		else if(event.getSource() == _changeImage)
		{
			 JFileChooser fc = new JFileChooser();
			 
			 int returnVal = fc.showOpenDialog(ArtistPanel.this);

	        if (returnVal == JFileChooser.APPROVE_OPTION)
	        {
	            File file = fc.getSelectedFile();
	            
	            changeArtistImage(file.getAbsolutePath());
	            
	            if(debug)
	            	System.out.println("File geladen: " + file.getAbsolutePath());
	        } 
	        else
	        {
	        	if(debug)
	        		System.out.println("Open command geannuleerd.");
	        }
		}
		else if(event.getSource() == _removeImage)
		{
			changeArtistImage("no_image.jpg");
		}
	}
	
	public void saveArtists()
	{
		List<Artist> artists = new ArrayList<Artist>();
		
		for(int i = 0; i < _defaultListModel.size(); i++)
		{
			Artist artist = _defaultListModel.get(i);
			artists.add(artist);
		}
		
		if(debug)
			System.out.println("Aantal artiesten in lijst: " + artists.size());
		
		FestivalHandler.Instance().setArtists(artists);
		
		if(debug)
			System.out.println("Artiesten zijn opgeslagen.");
	}
			
	public void loadArtistsFromHandler()
	{
		
		List<Artist> artists = FestivalHandler.Instance().getArtists();
		if(debug)
			System.out.println("Laden gestart van: " + artists.size() + " artiesten");
		

		_defaultListModel.clear();
		
		for(Artist artist : artists)
		{
			_defaultListModel.addElement(artist);
		}
	}
	
	public void changeArtistImage(String source)
	{
		URL artistImageUrl = null;
		
		if(debug)
			System.out.println("Source is: " + source);
		try
		{
			if(!source.trim().equals(""))
				artistImageUrl = getClass().getClassLoader().getResource(source);
		}
		catch(Exception e){}
		
		boolean succes = false;
		
		if(artistImageUrl != null)
		{
			_imageLabel.setIcon((Icon) new ImageIcon(artistImageUrl));
			
			if(debug)
				System.out.println("Icon is aangepast.");
			
			succes = true;
		}
		else if(!source.trim().equals(""))
		{
			try
			{
				_imageLabel.setIcon((Icon) new ImageIcon(source));
				succes = true;
			}
			catch(Exception e){}
		}
		
		if(succes)
			_selectedArtist.setImageSource(source);
		
		if(!succes)
		{
			if(debug)
				System.out.println("IMAGE HAS FAILED");
			
			changeArtistImage("no_image.jpg");
			if(debug)
				System.out.println("Source path is niet gevonden.");
		}
	}
}
