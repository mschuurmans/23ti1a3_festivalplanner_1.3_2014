/*
 * @author Kasper
 */
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

	private JButton _addStage, _removeStage, _save, _cancel; 
	private JTextField _nameText;
	private JList<Stage> _stageList;
	private JSpinner _spinnerStageL, _spinnerStageW, _spinnerFieldL, _spinnerFieldW;


	private GUIHelper _guiHelper;

	public StagePanel()
	{
		super(null);
		_guiHelper = new GUIHelper();
		int width = ApplicationView.WIDTH;
		int height = ApplicationView.HEIGHT;

		int startY = 20;
		int workSetHeight = 480;
		int startX = Utils.getPercentOfValue(width, 1);

		int groupBoxWidth =  width - startX - 245;

		JPanel _groupBox = _guiHelper.getGroupBox(Text.Stages.toString(), null);
		int groupBoxX = startX + 210;
		_groupBox.setBounds(groupBoxX, startY - 8, groupBoxWidth, workSetHeight + 8); // the -8 and + 8 is for nice allignment of the borders.


		_save = new JButton(Text.Save.toString());
		_save.setBounds(groupBoxWidth - 220, workSetHeight - 25, 100, 25);

		_cancel = new JButton(Text.Cancel.toString());
		_cancel.setBounds(groupBoxWidth - 110, workSetHeight - 25 ,100,25);


		_nameText = new JTextField();
		_nameText.setBounds(180, 30, 150, 25);

		JLabel _name = new JLabel(Text.Name.toString());
		_name.setBounds(20, 30, 150,25);

		JLabel _capacity = new JLabel(Text.Capacity.toString());
		_capacity.setBounds(20, 60, 150, 25);

		SpinnerModel _spinModelSteps100 = new SpinnerNumberModel(1000, 0, 99999, 100); 
		JSpinner _spinnerCapacity = new JSpinner(_spinModelSteps100);
		_spinnerCapacity.setBounds(180, 60, 100, 25);

		JLabel _people = new JLabel(Text.People.toString());
		_people.setBounds(295, 60, 150, 25);

		JLabel _stageSize = new JLabel(Text.StageSize.toString());
		_stageSize.setBounds(20, 90, 150, 25);

		_spinnerStageL = getSpinner10Steps();
		_spinnerStageL.setBounds(180, 90, 50, 25);

		JLabel _xLbl = new JLabel("x");
		_xLbl.setBounds(240, 90, 20, 20);

		_spinnerStageW = getSpinner10Steps();
		_spinnerStageW.setBounds(260, 90, 50, 25);

		JLabel _sizeDef = new JLabel(Text.SizeDef.toString());
		_sizeDef.setBounds(320, 90, 50, 25);

		JLabel _fieldSize = new JLabel(Text.FieldSize.toString());
		_fieldSize.setBounds(20, 120, 150, 25);

		_spinnerFieldL = getSpinner10Steps();
		_spinnerFieldL.setBounds(180, 120, 50, 25);

		JLabel _xLbl_2 = new JLabel("x");
		_xLbl_2.setBounds(240, 120, 20, 20);

		_spinnerFieldW = getSpinner10Steps();
		_spinnerFieldW.setBounds(260, 120, 50, 25);

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

		_groupBox.add(_name);
		_groupBox.add(_nameText);
		_groupBox.add(_capacity);
		_groupBox.add(_spinnerCapacity);
		_groupBox.add(_people);
		_groupBox.add(_fieldSize);
		_groupBox.add(_stageSize);
		_groupBox.add(_spinnerStageL);
		_groupBox.add(_xLbl);
		_groupBox.add(_spinnerStageW);
		_groupBox.add(_sizeDef);
		_groupBox.add(_spinnerFieldL);
		_groupBox.add(_xLbl_2);
		_groupBox.add(_spinnerFieldW);
		_groupBox.add(_sizeDef_2);


		_groupBox.add(_save);
		_groupBox.add(_cancel);


		add(_addStage);
		add(_removeStage);
		add(_groupBox);
		add(_stageList);

	}


	/*
	 * for creating a JSpinner. Counts from 0  to 1000 with steps from 10
	 * @author Kasper
	 * @return JSpinner
	 */

	public JSpinner getSpinner10Steps()
	{
		SpinnerModel _spinModelSteps10 = new SpinnerNumberModel(10.0, 0, 1000, 10);
		JSpinner _spinner10Steps = new JSpinner(_spinModelSteps10);
		return _spinner10Steps;
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
