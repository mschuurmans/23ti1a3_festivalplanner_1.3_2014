package nl.avans.festivalplanner.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import nl.avans.festivalplanner.model.FestivalHandler;
import nl.avans.festivalplanner.model.simulator.Element;
import nl.avans.festivalplanner.model.simulator.People;
import nl.avans.festivalplanner.model.simulator.Signpost;
import nl.avans.festivalplanner.utils.RouteManager;
import nl.avans.festivalplanner.utils.Enums.Text;

public class SignPostEditor extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -8213996558893852132L;
	private RouteManager manager = RouteManager.instance();
	private Signpost _current = null;

	private JList<Element> _pointers = new JList<>();
	private JList<Element> _otherElements = new JList<>();

	private JButton _addButton = new JButton();
	private JButton _removeButton = new JButton();

	public SignPostEditor(Signpost element)
	{
		if (element != null)
		{
			_current = element;
			RouteManager.instance().addSignpost(element);

			setTitle(Text.SignpostEditPointers.toString());
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			init();

			setLocationRelativeTo(null);
			pack();
			setVisible(true);
		}
	}

	public void init()
	{
		updateLists();

		JPanel content = new JPanel(null);

		_addButton.setText("< < <");
		_addButton.addActionListener(this);
		_addButton.setBounds(160, 180, 65, 25);

		_removeButton.setText("> > >");
		_removeButton.addActionListener(this);
		_removeButton.setBounds(160, 215, 65, 25);

		JLabel _lblCurIdentifier = new JLabel(String.format("%s: %s",
				Text.Current.toString(), _current.toString()));
		JLabel _lblFromThisPoint = new JLabel(
				Text.FromThisPointYouCanGoTo.toString() + ":");
		JLabel _lblCantGoTo = new JLabel(Text.CantGoTo.toString() + ":");

		_lblCurIdentifier.setBounds(10, 10, 500, 20);
		_lblFromThisPoint.setBounds(10, 40, 250, 20);
		_lblCantGoTo.setBounds(225, 40, 150, 20);

		_pointers.setBounds(10, 70, 150, 280);
		_otherElements.setBounds(225, 70, 150, 280);

		content.add(_addButton);
		content.add(_removeButton);
		content.add(_lblCurIdentifier);
		content.add(_lblFromThisPoint);
		content.add(_lblCantGoTo);
		content.add(_pointers);
		content.add(_otherElements);

		this.setMinimumSize(new Dimension(400, 400));
		this.setContentPane(content);
	}

	private void updateLists()
	{
		Set<Element> _oList = new HashSet<>();

		for (Element e : FestivalHandler.Instance().getElementsOnTerrain())
		{
			// Alle elementen ophalen en de elementen die al in deze signpost
			// hangen
			// weer wegstrepen uit de lijst.
			if (!(e instanceof People))
			{
				_oList.add(e);
			}
			_oList.removeAll(_current.getAllPointers());
			_oList.remove(_current);
		}

		// Converteren van Collection -> Array[]
		Element[] listdata = _current.getAllPointers().toArray(new Element[0]);
		Element[] otherdata = _oList.toArray(new Element[0]);

		// JList-data refreshen
		_pointers.setListData(listdata);
		_otherElements.setListData(otherdata);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if (arg0.getSource() == this._addButton)
		{
			Element selected = _otherElements.getSelectedValue();
			if (_current != null && selected != null)
			{
				if (manager.addPointer(_current, selected))
				{
					updateLists();
					_pointers.setSelectedValue(selected, true);
				}
			}
		}

		if (arg0.getSource() == this._removeButton)
		{
			Element selected = _pointers.getSelectedValue();
			if (_current != null && selected != null)
			{
				if (manager.removePointer(_current, selected))
				{
					updateLists();
					_otherElements.setSelectedValue(selected, true);
				}
			}
		}
	}

}
