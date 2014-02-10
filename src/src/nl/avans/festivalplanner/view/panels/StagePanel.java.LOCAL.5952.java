/*
 * @author Kasper
 */
package nl.avans.festivalplanner.view.panels;


import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.Border;

import nl.avans.festivalplanner.model.Stage;
import nl.avans.festivalplanner.utils.Utils;
import nl.avans.festivalplanner.utils.Enums.Text;
import nl.avans.festivalplanner.view.ApplicationView;
import nl.avans.festivalplanner.view.GUIHelper;
import nl.avans.festivalplanner.view.Panel;

public class StagePanel extends Panel 
{

	private JButton _addStage, _removeStage, _save, _cancel; 
	private JTextField _nameText;
	private JList<Stage> _stageList;
	private JComboBox<String> _comboCapacity, _comboStageL, _comboStageW, _comboFieldL, _comboFieldW;
	

	private GUIHelper _guiHelper;

	public StagePanel()
	{
		super(null);
		_guiHelper = new GUIHelper();
		JPanel _status = _guiHelper.getStatusBar(new JLabel("StatusBar"), this);
		
		//JTabbedPane tabs = _guiHelper.getTabBar();
		
		
		int width = ApplicationView.WIDTH;
		int height = ApplicationView.HEIGHT;
		
		int beginWidthGroup = (int) Utils.getPercentOfValue(width, 25);
		int beginHeightGroup = (int) Utils.getPercentOfValue(height, 10);
		
		int endWidthGroup = (int) -beginWidthGroup + width - Utils.getPercentOfValue(width, 5);
		int endHeightGroup = (int) -beginHeightGroup + height - Utils.getPercentOfValue(height, 28);
		
		int widthGroup = (int) (endWidthGroup - beginWidthGroup);
		int heightGroup = (int) (endHeightGroup - beginHeightGroup);
		
		int startY = 20;
		int workSetHeight = 500;
		int startX = Utils.getPercentOfValue(width, 1);
			
		int groupBoxWidth =  width - startX - 245;

		JPanel _groupBox = _guiHelper.getGroupBox(Text.Stages.toString(), null);
		int groupBoxX = startX + 210;
		_groupBox.setBounds(groupBoxX, startY - 8, groupBoxWidth, workSetHeight + 8); // the -8 and + 8 is for nice allignment of the borders.
		
		
		_save = new JButton(Text.Save.toString());
		_save.setBounds(groupBoxWidth - 220, workSetHeight - 25 ,100,25);
		
		_cancel = new JButton(Text.Cancel.toString());
		_cancel.setBounds(groupBoxWidth - 110, workSetHeight - 25 ,100,25);
		
		
		_nameText = new JTextField();
		_nameText.setBounds(180, 30, 150, 25);
		
		JLabel _name = new JLabel(Text.Name.toString());
		_name.setBounds(20, 30, 150,25);
				
		JLabel _capacity = new JLabel(Text.Capacity.toString());
		_capacity.setBounds(20, 60, 150, 25);
		
		
		_comboCapacity = new JComboBox<String>();
		for(int i=0; i<=100; i++)
		{
		String waarde = "" + (i*1000);
		_comboCapacity.addItem(waarde);
		}
		_comboCapacity.setBounds(180, 60, 100, 25);
		_comboCapacity.setBackground(Color.white);
		
		JLabel _people = new JLabel(Text.People.toString());
		_people.setBounds(295, 60, 150, 25);
		
		JLabel _stageSize = new JLabel(Text.StageSize.toString());
		_stageSize.setBounds(20, 90, 150, 25);
		
		_comboStageL = getComboBox();
		_comboStageL.setBounds(180, 90, 50, 25);
		
		JLabel _xLbl = new JLabel("x");
		_xLbl.setBounds(240, 90, 20, 20);
		
		_comboStageW = getComboBox();
		_comboStageW.setBounds(260, 90, 50, 25);
		
		JLabel _sizeDef = new JLabel(Text.SizeDef.toString());
		_sizeDef.setBounds(320, 90, 50, 25);
						
		JLabel _fieldSize = new JLabel(Text.FieldSize.toString());
		_fieldSize.setBounds(20, 120, 150, 25);
		
		_comboFieldL = getComboBox();
		_comboFieldL.setBounds(180, 120, 50, 25);
		
		JLabel _xLbl_2 = new JLabel("x");
		_xLbl_2.setBounds(240, 120, 20, 20);
		
		_comboFieldW = getComboBox();
		_comboFieldW.setBounds(260, 120, 50, 25);
				
		JLabel _sizeDef_2 = new JLabel(Text.SizeDef.toString());
		_sizeDef_2.setBounds(320, 120, 50, 25);
				
		_stageList = new JList<Stage>();
		_stageList.setBounds(startX, startY, 200, workSetHeight - 60);
		Border border = BorderFactory.createLineBorder(Color.gray, 1); 
		_stageList.setBorder(border);
		
		_addStage = new JButton(Text.AddStage.toString());
		_addStage.setBounds(startX, startY + (workSetHeight - 60) + 5, 200, 25);
		
		_removeStage = new JButton(Text.RemoveStage.toString());
		_removeStage.setBounds(startX, startY + (workSetHeight - 60) + 35, 200, 25);
		
	 	_groupBox.add(_nameText);
		_groupBox.add(_capacity);
		_groupBox.add(_people);
		_groupBox.add(_fieldSize);
		_groupBox.add(_stageSize);
		_groupBox.add(_comboCapacity);
		_groupBox.add(_comboStageL);
		_groupBox.add(_xLbl);
		_groupBox.add(_comboStageW);
		_groupBox.add(_sizeDef);
		_groupBox.add(_comboFieldL);
		_groupBox.add(_xLbl_2);
		_groupBox.add(_comboFieldW);
		_groupBox.add(_sizeDef_2);
		

		_groupBox.add(_name);
		_groupBox.add(_save);
		_groupBox.add(_cancel);
		
		
		
		//groupBox.add(_comboCapacity);
		add(_addStage);
		add(_removeStage);
		add(_groupBox);
		add(_stageList);
		
	}

	/*
	 * for creating a JComboBox. Counts from 0  to 1000 with steps from 100
	 * @author Kasper
	 * @return JComboBox<String>
	 */
	public JComboBox<String> getComboBox()
	{
		JComboBox<String> _comboBox = new JComboBox<String>();
		for(int i=0; i<=100; i++)
			_comboBox.addItem(new String(""+i*10));
		_comboBox.setBackground(Color.white);
		return _comboBox;
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
