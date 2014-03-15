package nl.avans.festivalplanner.view.dialog;

import nl.avans.festivalplanner.model.*;
import nl.avans.festivalplanner.model.simulator.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

/**
 * the view containing settings for a intersection
 * @Author Michiel Schuurmans
 */
public class IntersectionOptions extends JFrame
{
	private Element _intersect;

	private String[] _columnName = {"Goal", "next target"};

	private DefaultTableModel _tableModel;

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

		java.util.List<Stage> stages = new ArrayList<Stage>();
		
		for(Element e : FestivalHandler.Instance().getElementsOnTerrain())
		{
			if(e instanceof Stage)
				stages.add((Stage)e);
		}		

		this._tableModel.addRow(new Object[]{"test", "test"});

		JTable table = new JTable(this._tableModel);
		table.setSize(new Dimension(400,400));
		content.add(table);

		setContentPane(content);
	}
}
