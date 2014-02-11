/**
 * 
 */
package nl.avans.festivalplanner.view.panels;

import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
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

/**
 * @author Jordy Sipkema
 * 
 */
public class InfoPanel extends Panel
{
	private static final long serialVersionUID = 2879106751679669257L;

	private JButton _saveButton = new JButton(Text.Save.toString());
	private JButton _cancelButton = new JButton(Text.Cancel.toString());

	private JLabel _nameLabel = null;
	private JLabel _dateLabel = null;
	private JLabel _ticketsLabel = null;
	private JTextField _nameInput = null;
	private JSpinner _dateInput = null;
	private SpinnerDateModel _spinnerDateModel = new SpinnerDateModel();
	private JSpinner _ticketsInput = null;
	private SpinnerNumberModel _spinnerNumberModel = new SpinnerNumberModel(
			1000, 0, 100_000, 1000); // Default, min, max, stepsize

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

	@Override
	public Panel getPanel()
	{
		return this;
	}

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

		_nameLabel = new JLabel(Text.FestivalName.toString() + ":", JLabel.LEFT);
		_nameLabel.setBounds(posX, posY, 100, 20);
		posX += incrementX; // Due to new column
		_nameInput = new JTextField();
		_nameInput.setBounds(posX, posY, 200, 20);

		posY += incrementY; // Due to new row
		posX = startPosX; // Resetting to column 1
		_dateLabel = new JLabel(Text.Date.toString() + ":", JLabel.LEFT);
		_dateLabel.setBounds(posX, posY, 100, 20);
		posX += incrementX; // Due to new column
		_dateInput = new JSpinner(_spinnerDateModel);
		_dateInput.setEditor(new JSpinner.DateEditor(_dateInput,
				Text.DateFormat.toString()));
		_dateInput.setBounds(posX, posY, 200, 20);

		posY += incrementY; // Due to new row
		posX = startPosX; // Resetting to column 1
		_ticketsLabel = new JLabel(Text.Tickets.toString() + ":", JLabel.LEFT);
		_ticketsLabel.setBounds(posX, posY, 100, 20);
		posX += incrementX; // Due to new column
		_ticketsInput = new JSpinner();
		_ticketsInput.setModel(_spinnerNumberModel);
		_ticketsInput.setBounds(posX, posY, 200, 20);

		_saveButton.setBounds(769, height - 133, 100, 25); // height - 133 = 467
		_saveButton.addActionListener(this);
		_cancelButton.setBounds(879, height - 133, 100, 25);
		_cancelButton.addActionListener(this);

		add(_nameLabel);
		add(_dateLabel);
		add(_ticketsLabel);
		add(_nameInput);
		add(_dateInput);
		add(_ticketsInput);
		add(_saveButton);
		add(_cancelButton);
	}

}
