package nl.avans.festivalplanner.view.panels;


import java.awt.Color;
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

	private JButton _addStage;
	private JButton _removeStage;
	private JButton _save;
	private JButton _cancel; 
	private JLabel _name, _capacity, _stageSize, _fieldSize;
	private JTextField _nameText, _stageText;
	private JList<Stage> _stageList;
	private JComboBox<String> _comboCapacity, _comboStageL, comboStageW, _comboFieldL, _comboFieldW;
	

	private GUIHelper _guiHelper;

	public StagePanel()
	{
		super();
		_guiHelper = new GUIHelper();
		
		//JTabbedPane tabs = _guiHelper.getTabBar();
		
		
		int width = ApplicationView.WIDTH;
		int height = ApplicationView.HEIGHT;
		
		int beginWidthGroup = (int) Utils.getPercentOfValue(width, 25);
		int beginHeightGroup = (int) Utils.getPercentOfValue(height, 10);
		
		int endWidthGroup = (int) -beginWidthGroup + width - Utils.getPercentOfValue(width, 5);
		int endHeightGroup = (int) -beginHeightGroup + height - Utils.getPercentOfValue(height, 28);
		
		int widthGroup = (int) (endWidthGroup - beginWidthGroup);
		int heightGroup = (int) (endHeightGroup - beginHeightGroup);
		


		JPanel groupBox = _guiHelper.getGroupBox(Text.Stages.toString());
		groupBox.setBounds(beginWidthGroup, beginHeightGroup, endWidthGroup, endHeightGroup);
		
		_save = new JButton(Text.Save.toString());
		_save.setBounds(Utils.getPercentOfValue(widthGroup, 80), Utils.getPercentOfValue(heightGroup, 80), 100, 25);
		
		_cancel = new JButton(Text.Cancel.toString());
		//_cancel.setBounds(200, 200, 100, 25);
		
		
		_nameText = new JTextField();
		//_nameText.setBounds(30, 30, 100, 25);
		
		_capacity = new JLabel(Text.Capacity.toString());
		
		_fieldSize = new JLabel(Text.FieldSize.toString());
		
		_stageSize = new JLabel(Text.StageSize.toString());
		
		_stageText = new JTextField();
		
		_stageList = new JList<Stage>();
		_stageList.setBounds(Utils.getPercentOfValue(width, 5), beginHeightGroup, -Utils.getPercentOfValue(width, 5) + width - Utils.getPercentOfValue(width, 80), -Utils.getPercentOfValue(height, 11) + height - Utils.getPercentOfValue(height, 39));
		Border border = BorderFactory.createLineBorder(Color.gray, 1); 
		_stageList.setBorder(border);
		
		_addStage = new JButton(Text.AddStage.toString());
		_addStage.setBounds(Utils.getPercentOfValue(width, 5), Utils.getPercentOfValue(height, 72), -Utils.getPercentOfValue(width, 5) + width - Utils.getPercentOfValue(width, 80), 25);
		
		_removeStage = new JButton(Text.RemoveStage.toString());
		_removeStage.setBounds(Utils.getPercentOfValue(width, 5), Utils.getPercentOfValue(height, 77), -Utils.getPercentOfValue(width, 5) + width - Utils.getPercentOfValue(width, 80), 25);
		
		_comboCapacity = new JComboBox<String>();
		_comboCapacity.addItem("500");
		_comboCapacity.addItem("1000");
		_comboCapacity.setBounds(50, 50, 100, 20);
		 
		
		
		//groupBox.add(_nameText);
		//groupBox.add(_name);
		groupBox.add(_save);
		
		//groupBox.add(_cancel);
		//groupBox.add(_comboCapacity);
		add(_addStage);
		add(_removeStage);
		add(groupBox);
		add(_stageList);
		
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
