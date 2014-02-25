package nl.avans.festivalplanner.view.panels;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import nl.avans.festivalplanner.model.Festival;
import nl.avans.festivalplanner.model.FestivalHandler;
import nl.avans.festivalplanner.utils.Utils;
import nl.avans.festivalplanner.utils.Enums.Text;
import nl.avans.festivalplanner.view.ApplicationView;
import nl.avans.festivalplanner.view.Panel;
import nl.avans.festivalplanner.view.SimulatorView;

/**
 * An implementation of JPanel which is used to display general information
 * about a Festival-object.
 * 
 * @author Jordy Sipkema
 * @version 13-02-2014
 */
public class InfoPanel extends Panel
{
	private static final long serialVersionUID = 2879106751679669257L;

	private JButton _saveButton = new JButton(Text.Save.toString()); // ?eng:
																		// Save
	private JButton _cancelButton = new JButton(Text.Cancel.toString()); // ?eng:
																			// Cancel
	private JButton _startSimButton = new JButton(Text.StartSimulation.toString());
	
	private JButton _changeImageButton = new JButton(
			Text.ChangeImage.toString());
	private JButton _removeImageButton = new JButton(
			Text.RemoveImage.toString());

	private JLabel _nameLabel = null;
	private JLabel _dateLabel = null;
	private JLabel _ticketsLabel = null;
	private JLabel _imageLabel = null;
	private JTextField _nameInput = null;
	private JSpinner _dateInput = null;
	private SpinnerDateModel _spinnerDateModel = new SpinnerDateModel();
	private JSpinner _ticketsInput = null;
	private SpinnerNumberModel _spinnerNumberModel = new SpinnerNumberModel(
			1000, 0, 1_000_000, 1000); // Default, min, max, stepsize

	/**
	 * Creates a new InfoPanel. This panel is used to display and edit the
	 * festival data.
	 */
	public InfoPanel()
	{
		super();
		initialize();
		updateView();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.avans.festivalplanner.view.Panel#getPanel()
	 */
	@Override
	public Panel getPanel()
	{
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this._saveButton)
		{
			saveFestival();
			updateView();
		}
		else if (e.getSource() == this._cancelButton)
		{
			updateView();
		}
		else if (e.getSource() == _changeImageButton)
		{
			JFileChooser fc = new JFileChooser();

			int returnVal = fc.showOpenDialog(InfoPanel.this);

			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = fc.getSelectedFile();
				changeFestivalImage(file.getAbsolutePath());
			}
		}
		else if (e.getSource() == _removeImageButton)
		{
			changeFestivalImage("no_image.jpg");
		}
		else if (e.getSource() == _startSimButton){
			new SimulatorView();
		}
	}

	/**
	 * Fills the panel's components with the corresponding Festival data. The
	 * data is gathered from the FestivalHandler-singleton.
	 */
	private void updateView()
	{
		Festival fest = FestivalHandler.Instance().getFestival();

		if (fest != null)
		{
			if (fest.getDate() != null)
			{
				_nameInput.setText(fest.getName());
				_dateInput.getModel().setValue(fest.getDate().getTime());
				_ticketsInput.getModel().setValue(
						new Integer(fest.getTickets()));
			}
			else
			{
				_nameInput.setText("No festival loaded!");
				_ticketsInput.getModel().setValue(0);
			}
		}
	}

	/**
	 * Draws the FestivalImage (as stored in Festival) on the image jpanel.
	 */
	private void drawFestivalImage()
	{
		this.changeFestivalImage(FestivalHandler.Instance().getFestival().getImageSoure());
	}

	/**
	 * Updates the festival image source. Afterwards it draws the FestivalImage
	 * (as stored in Festival) on the image jpanel.
	 * 
	 * @param source
	 *            The source (Project-resource, file-path or URL) of the image
	 *            file.
	 */
	private void changeFestivalImage(String source)
	{
		URL artistImageUrl = null;

		try
		{
			if (!source.trim().equals(""))
				artistImageUrl = getClass().getClassLoader()
						.getResource(source);
			if (artistImageUrl == null && source != null)
			{
				if (new File(source).exists())
				{
					artistImageUrl = new File(source).toURI().toURL();
				}
			}
		}
		catch (Exception e)
		{
		}

		boolean succes = false;

		if (artistImageUrl != null)
		{
			try
			{
				BufferedImage originalImage = ImageIO.read(artistImageUrl);
				// Scale the image to fit the JPanel's size.
				Image scaledImage = originalImage.getScaledInstance(
						(int) _imageLabel.getSize().getWidth(),
						(int) _imageLabel.getSize().getHeight(),
						Image.SCALE_SMOOTH);

				_imageLabel.setIcon(new ImageIcon(scaledImage));

				succes = true;
			}
			catch (IOException e)
			{
			}
		}

		if (succes)
		{
			FestivalHandler.Instance().getFestival().setImageSoure(source);
		}
		else
		{
			changeFestivalImage("no_image.jpg");
		}
	}

	/**
	 * Saves the modified festival data.
	 */
	private void saveFestival()
	{
		// Converting Date to GregorianCalendar
		GregorianCalendar c = new GregorianCalendar();
		c.setTime((Date) this._dateInput.getValue());

		// Getting Festival-object
		Festival f = FestivalHandler.Instance().getFestival();

		// Setting the new data
		f.setName(this._nameInput.getText());
		f.setTicket(Integer.parseInt(this._ticketsInput.getValue().toString()));
		f.setDate(c);

	}

	/**
	 * Initializes the panel's components with default data. Use updateView to
	 * set the data.
	 * 
	 * @see infoPanel#updateView()
	 */
	private void initialize()
	{
		int width = ApplicationView.WIDTH;
		int height = ApplicationView.HEIGHT;
		int startPosX = Utils.getPercentOfValue(width, 1);
		int posX = startPosX;
		int posY = 20;
		int incrementY = 25;
		int incrementX = 100;

		_nameLabel = new JLabel(Text.FestivalName.toString() + ":", JLabel.LEFT); // ?eng:
																					// Festivalname
		_nameLabel.setBounds(posX, posY, 100, 20);
		posX += incrementX; // Due to new column
		_nameInput = new JTextField();
		_nameInput.setBounds(posX, posY, 200, 20);

		_imageLabel = new JLabel();
		_imageLabel.setBounds(769, posY, 225, 225);

		posY += incrementY; // Due to new row
		posX = startPosX; // Resetting to column 1
		_dateLabel = new JLabel(Text.Date.toString() + ":", JLabel.LEFT); // ?eng:
																			// Date
		_dateLabel.setBounds(posX, posY, 100, 20);
		posX += incrementX; // Due to new column
		_dateInput = new JSpinner(_spinnerDateModel);
		_dateInput.setEditor(new JSpinner.DateEditor(_dateInput,
				Text.DateFormat.toString()));
		_dateInput.setBounds(posX, posY, 200, 20);

		posY += incrementY; // Due to new row
		posX = startPosX; // Resetting to column 1
		_ticketsLabel = new JLabel(Text.Tickets.toString() + ":", JLabel.LEFT); // ?eng:
																				// Tickets
		_ticketsLabel.setBounds(posX, posY, 100, 20);
		posX += incrementX; // Due to new column
		_ticketsInput = new JSpinner();
		_ticketsInput.setModel(_spinnerNumberModel);
		_ticketsInput.setBounds(posX, posY, 200, 20);

		_saveButton.setBounds(769, height - 133, 100, 25); // height - 133 = 467
		_saveButton.addActionListener(this);
		_cancelButton.setBounds(879, height - 133, 100, 25);
		_cancelButton.addActionListener(this);

		_startSimButton.setBounds(769, height-167, 210, 25);
		_startSimButton.addActionListener(this);
		
		_changeImageButton.setBounds(769, 287, 210, 25);
		_changeImageButton.addActionListener(this);
		_removeImageButton.setBounds(769, 322, 210, 25);
		_removeImageButton.addActionListener(this);

		add(_nameLabel);
		add(_dateLabel);
		add(_ticketsLabel);
		add(_imageLabel);
		add(_nameInput);
		add(_dateInput);
		add(_ticketsInput);
		add(_saveButton);
		add(_cancelButton);
		add(_changeImageButton);
		add(_removeImageButton);
		add(_startSimButton);

		this.drawFestivalImage();
	}

}
