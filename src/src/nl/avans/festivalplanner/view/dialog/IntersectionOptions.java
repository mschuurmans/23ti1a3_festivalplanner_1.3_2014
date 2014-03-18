package nl.avans.festivalplanner.view.dialog;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import nl.avans.festivalplanner.model.FestivalHandler;
import nl.avans.festivalplanner.model.simulator.Building;
import nl.avans.festivalplanner.model.simulator.Element;

/**
 * the view containing settings for a intersection
 * @Author Michiel Schuurmans
 */
public class IntersectionOptions extends JFrame
{
	private Element _intersect;

	private String[] _columnName = {"Goal", "next target"};

	private DefaultTableModel _tableModel;
	
	private JComboBox _combobox = new JComboBox();

	public IntersectionOptions(Element intersection)
	{
		this._intersect = intersection;
		this._tableModel = new DefaultTableModel(this._columnName, 0);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		init();
		
		setLocationRelativeTo(null);

		setSize(400,400);
		setVisible(true);
	}
	
	private void init()
	{
		JPanel content = new JPanel(new FlowLayout());

		java.util.List<Building> buildings = new ArrayList<Building>();
		
		for(Element e : FestivalHandler.Instance().getElementsOnTerrain())
		{
			if(e instanceof Building){
				buildings.add((Building)e); 
			} 
		}	
		
		this._tableModel.addRow(new Object[]{"Object:", "Bereikbaar via:"});
		
		for(Building b : buildings)
		{
			String elementName = b.toString();
			this._tableModel.addRow(new Object[]{elementName});
		}
		
		
		
		JTable table = new JTable(_tableModel);
		table.setSize(new Dimension(400,400));
	
		for(int idx = 1; idx < table.getRowCount(); idx++)
		{
				TableColumn column = table.getColumnModel().getColumn(1);
				column.setCellEditor(new DefaultCellEditor(_combobox));
		}
		
		//JButton addIntersectionOption = new JButton("Add Option");
		
		content.add(table);

		setContentPane(content);
	}
}
