package nl.avans.festivalplanner.view.panels;


import java.awt.event.ActionEvent;

import javax.swing.*;

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
		
		int width = ApplicationView.WIDTH;
		int height = ApplicationView.HEIGHT;
		
		int widthGroup = (int)(Utils.getPercentOfValue(width, 70) - Utils.getPercentOfValue(width, 30));
		int heightGroup = (int)(Utils.getPercentOfValue(height, 70) - Utils.getPercentOfValue(height, 30));

		JPanel groupBox = _guiHelper.getGroupBox(Text.Stages.toString());
		groupBox.setBounds(Utils.getPercentOfValue(width, 30), Utils.getPercentOfValue(height, 25), Utils.getPercentOfValue(width, 85), Utils.getPercentOfValue(height, 85));

		_save = new JButton(Text.Save.toString());
		_save.setBounds(Utils.getPercentOfValue(widthGroup, 80), Utils.getPercentOfValue(heightGroup, 80), 100, 25);
		
		_cancel = new JButton(Text.Cancel.toString());
		//_cancel.setBounds(200, 200, 100, 25);
		
		_name = new JLabel(Text.Name.toString());
		//_name.setBounds(10, 10, 50, 100);
		
		_nameText = new JTextField();
		//_nameText.setBounds(30, 30, 100, 25);
		
		_capacity = new JLabel(Text.Capacity.toString());
		
		_fieldSize = new JLabel(Text.FieldSize.toString());
		
		_stageSize = new JLabel(Text.StageSize.toString());
		
		_stageText = new JTextField();
		
		_stageList = new JList<Stage>();
		_stageList.setBounds(50, 50, 100, 500);
		
		_comboCapacity = new JComboBox<String>();
		_comboCapacity.addItem("500");
		_comboCapacity.addItem("1000");
		_comboCapacity.setBounds(50, 50, 100, 20);
		 
		groupBox.add(_nameText);
		groupBox.add(_name);
		groupBox.add(_save);
		groupBox.add(_cancel);
		groupBox.add(_comboCapacity);
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
