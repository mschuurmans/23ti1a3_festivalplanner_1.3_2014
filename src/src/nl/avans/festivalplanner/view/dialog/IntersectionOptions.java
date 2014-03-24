package nl.avans.festivalplanner.view.dialog;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import nl.avans.festivalplanner.model.FestivalHandler;
import nl.avans.festivalplanner.model.simulator.Area;
import nl.avans.festivalplanner.model.simulator.Building;
import nl.avans.festivalplanner.model.simulator.Element;
import nl.avans.festivalplanner.utils.RouteManager;

/**
 * the view containing settings for a intersection
 * @Author Michiel, Jordy, Roald
 */
public class IntersectionOptions extends JFrame implements ItemListener
{


	private static final long serialVersionUID = 6391150348108184954L;

	private DefaultTableModel _tableModel;
	private JTable _table;
	private String _name;
	private String[] _columnName = {"Goal", "next target"};

	public IntersectionOptions(Element intersection)
	{
		Element intersect = intersection;

		this._tableModel = new DefaultTableModel(this._columnName, 0);
		this._name = intersect.toString();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		init();

		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	private void init()
	{
		JPanel content = new JPanel(new FlowLayout());

		java.util.List<Building> buildings = new ArrayList<Building>();
		java.util.List<Area> areas = new ArrayList<Area>();


		for(Element e : FestivalHandler.Instance().getElementsOnTerrain())
		{
			if(e instanceof Building){
				buildings.add((Building)e);
			}
			if(e instanceof Area){
				areas.add((Area)e);
			}
		}		

		this._tableModel.addRow(new Object[]{"Object:", "Bereikbaar via:"});

		for(Building b : buildings)
		{
			String elementName = b.toString();
			this._tableModel.addRow(new Object[]{elementName});
		}

		_table = new JTable(_tableModel);
		for(int idx = 1; idx < _table.getRowCount(); idx++)
		{
			JComboBox<String> _combobox = new JComboBox<String>();
			_combobox.addItemListener(this);
			for(Area a : areas)
			{
				String areaName = a.toString();
				_combobox.addItem(areaName);
			}
			
			TableColumn column = _table.getColumnModel().getColumn(1);
			column.setCellEditor(new DefaultCellEditor(_combobox));
		}

		JLabel nameLbl = new JLabel("Current: \n" + _name);		
		content.add(_table);
		content.add(nameLbl);


		setContentPane(content);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		
		System.out.println("Stage changed");
		try{
			
			int rowNumber = _table.getSelectedRow();
			Object value = _table.getValueAt(rowNumber, 0);


			String s = null;
			if(value instanceof String)
			{
				s = (String)value;
			}

			Element destination = null;
			Element through = null;

			Object throughValue = ((JComboBox)arg0.getSource()).getSelectedItem();
			String t = null;
			if(throughValue instanceof String)
			{
				t = (String)throughValue;
			}

			for(Element e : FestivalHandler.Instance().getElementsOnTerrain())
			{
				if(e.toString().equalsIgnoreCase(s))
				{
					destination = e;
				}
				if(e.toString().equalsIgnoreCase(t))
				{
					through = e;
				}
			}
			if(through != null)
			((JComboBox)arg0.getSource()).setSelectedItem(RouteManager.instance().getNodeAllMapping(through));

			if(destination != null && through != null)
			{
				RouteManager.instance().addNode(destination, through);
				System.out.println("saved");

			}
		}
		catch(Exception e)
		{
			//System.out.println("Nothing to save");
		}
	}
}
