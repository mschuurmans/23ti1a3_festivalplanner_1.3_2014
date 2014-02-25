/*
 * @author Kasper
 */
package nl.avans.festivalplanner.view.panels;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import nl.avans.festivalplanner.model.FestivalHandler;
import nl.avans.festivalplanner.model.Stage;
import nl.avans.festivalplanner.utils.Enums.Text;
import nl.avans.festivalplanner.utils.Utils;
import nl.avans.festivalplanner.view.ApplicationView;
import nl.avans.festivalplanner.view.GUIHelper;
import nl.avans.festivalplanner.view.Panel;

public class StagePanel extends Panel 
{

	private JButton _addStage, _removeStage, _save, _cancel; 
	private JLabel _name, _capacity, _people, _stageSize, _fieldSize, _xLbl, _xLbl_2, _sizeDef, _sizeDef_2;
	private JTextField _nameText;
	private final JList<Stage> _stageList;
	private final AbstractListModel<Stage> _abstractModel;
	private ArrayList<Stage> _stageArrayList;
	private JSpinner _spinnerStageL, _spinnerStageW, _spinnerFieldL, _spinnerFieldW, _spinnerCapacity;
	private JButton _changeImage;
	private JButton _removeImage;
	private JLabel _imageLabel;
	private String _imageName;



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



		_nameText = new JTextField();
		_nameText.setBounds(180, 30, 150, 25);

		_name = new JLabel(Text.Name.toString());
		_name.setBounds(20, 30, 150,25);

		_capacity = new JLabel(Text.Capacity.toString());
		_capacity.setBounds(20, 60, 150, 25);

		SpinnerModel spinModelSteps100 = new SpinnerNumberModel(1000, 0, 99999, 100); 
		_spinnerCapacity = new JSpinner(spinModelSteps100);
		_spinnerCapacity.setBounds(180, 60, 100, 25);

		_people = new JLabel(Text.People.toString());
		_people.setBounds(295, 60, 150, 25);

		_stageSize = new JLabel(Text.StageSize.toString());
		_stageSize.setBounds(20, 90, 150, 25);

		_spinnerStageL = getSpinner10Steps();
		_spinnerStageL.setBounds(180, 90, 50, 25);

		_xLbl = new JLabel("x");
		_xLbl.setBounds(240, 90, 20, 20);

		_spinnerStageW = getSpinner10Steps();
		_spinnerStageW.setBounds(260, 90, 50, 25);

		_sizeDef = new JLabel(Text.SizeDef.toString());
		_sizeDef.setBounds(320, 90, 50, 25);

		_fieldSize = new JLabel(Text.FieldSize.toString());
		_fieldSize.setBounds(20, 120, 150, 25);

		_spinnerFieldL = getSpinner10Steps();
		_spinnerFieldL.setBounds(180, 120, 50, 25);

		_xLbl_2 = new JLabel("x");
		_xLbl_2.setBounds(240, 120, 20, 20);

		_spinnerFieldW = getSpinner10Steps();
		_spinnerFieldW.setBounds(260, 120, 50, 25);

		_sizeDef_2 = new JLabel(Text.SizeDef.toString());
		_sizeDef_2.setBounds(320, 120, 50, 25);

		_imageName = "no_image.jpg";

		_imageLabel = new JLabel("");
		_imageLabel.setIcon((Icon)new ImageIcon(getClass().getClassLoader().getResource(_imageName)));
		_imageLabel.setBounds(groupBoxWidth - 220,20, 200, 240);
		//	System.out.println("TEST " + _imageLabel.getText());

		_changeImage = new JButton(Text.ChangeImage.toString());
		_changeImage.setBounds(groupBoxWidth - 220, 275, 210, 25);
		_changeImage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(StagePanel.this);

				if (returnVal == JFileChooser.APPROVE_OPTION)
				{
					File file = fc.getSelectedFile();
					_imageLabel.setIcon((Icon)new ImageIcon(file.getAbsolutePath()));
					_imageName = file.getName();
					System.out.println(file.getAbsolutePath());
					System.out.println(_imageName);
				}

			}
		});

		_removeImage = new JButton(Text.RemoveImage.toString());
		_removeImage.setBounds(groupBoxWidth - 220, 310, 210, 25);
		_removeImage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				_imageName = "no_image.jpg";
				_imageLabel.setIcon((Icon)new ImageIcon(getClass().getClassLoader().getResource(_imageName)));
			}
		});

		_abstractModel = new AbstractListModel<Stage>()
				{
			public Stage getElementAt(int index)
			{
				return _stageArrayList.get(index);
			}

			public int getSize()
			{
				return _stageArrayList.size();
			}
				};


				_stageArrayList = new ArrayList<Stage>();
				_stageList = new JList(_abstractModel);
				_stageList.setBounds(startX, startY, 200, workSetHeight - 60);
				Border border = BorderFactory.createLineBorder(Color.gray, 1); 
				_stageList.setBorder(border);
				_stageList.addListSelectionListener(new ListSelectionListener() {					
					@Override
					public void valueChanged(ListSelectionEvent e) 
					{
						int index = _stageList.getSelectedIndex();
						_nameText.setText(_stageArrayList.get(index).getName());
						_spinnerCapacity.setValue(_stageArrayList.get(index).getCapacity());
						_spinnerStageL.setValue(_stageArrayList.get(index).getStageSize().getHeight());
						_spinnerStageW.setValue(_stageArrayList.get(index).getStageSize().getWidth());
						_spinnerFieldL.setValue(_stageArrayList.get(index).getFieldSize().getHeight());
						_spinnerFieldW.setValue(_stageArrayList.get(index).getFieldSize().getWidth());
						_imageLabel.setIcon((Icon)new ImageIcon(getClass().getClassLoader().getResource(_stageArrayList.get(index).getImageSource())));

					}
				});
				JScrollPane scrollPane = new JScrollPane(_stageList);
				scrollPane.setBounds(startX, startY, 200, workSetHeight - 60);

				_addStage = new JButton(Text.AddStage.toString());
				_addStage.setBounds(startX, startY + (workSetHeight - 60) + 5, 200, 25);
				_addStage.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						String stageName = "New stage " + _stageArrayList.size();
						Stage stage = new Stage(stageName, 0, new Dimension(10, 10), new Dimension(10, 10), "no_image.jpg");
						_stageArrayList.add(stage);
						updateList();
					}
				});

				_removeStage = new JButton(Text.RemoveStage.toString());
				_removeStage.setBounds(startX, startY + (workSetHeight - 60) + 35, 200, 25);
				_removeStage.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						if(!_stageList.isSelectionEmpty())
						{
							_stageArrayList.remove(_stageList.getSelectedValue());
						}
						updateList();
					}
				});

				_save = new JButton(Text.Save.toString());
				_save.setBounds(groupBoxWidth - 220, workSetHeight - 25, 100, 25);
				_save.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						if(!_stageList.isSelectionEmpty())
						{
							int index = _stageList.getSelectedIndex();
							_stageArrayList.get(index).setName(_nameText.getText());
							_stageArrayList.get(index).setCapacity((int)_spinnerCapacity.getValue());
							_stageArrayList.get(index).setStageSize((double)_spinnerStageW.getValue(), (double)_spinnerStageL.getValue());
							_stageArrayList.get(index).setFieldSize((double)_spinnerFieldL.getValue(), (double)_spinnerFieldW.getValue());
							_stageArrayList.get(index).setImageSource(_imageName);
							updateList();
							FestivalHandler.Instance().setStage(_stageArrayList);
						}
					}
				});

				_cancel = new JButton(Text.Cancel.toString());
				_cancel.setBounds(groupBoxWidth - 110, workSetHeight - 25 ,100,25);
				_cancel.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						int index = _stageList.getSelectedIndex();
						_nameText.setText(_stageArrayList.get(index).getName());
						_spinnerCapacity.setValue(_stageArrayList.get(index).getCapacity());
						_spinnerStageL.setValue(_stageArrayList.get(index).getStageSize().getHeight());
						_spinnerStageW.setValue(_stageArrayList.get(index).getStageSize().getWidth());
						_spinnerFieldL.setValue(_stageArrayList.get(index).getFieldSize().getHeight());
						_spinnerFieldW.setValue(_stageArrayList.get(index).getFieldSize().getWidth());
						_imageLabel.setText(_stageArrayList.get(index).getImageSource());
						updateList();
					}
				});

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
				_groupBox.add(_changeImage);
				_groupBox.add(_removeImage);
				_groupBox.add(_imageLabel);



				_groupBox.add(_save);
				_groupBox.add(_cancel);

				add(scrollPane);
				add(_addStage);
				add(_removeStage);
				add(_groupBox);
				loadArtistsFromHandler();



	}


	/*
	 * for creating a JSpinner. Counts from 10  to 1000 with steps from 10
	 * @author Kasper
	 * @return JSpinner
	 */

	public JSpinner getSpinner10Steps()
	{
		SpinnerModel _spinModelSteps10 = new SpinnerNumberModel(10.0, 10, 1000, 10);
		JSpinner _spinner10Steps = new JSpinner(_spinModelSteps10);
		return _spinner10Steps;
	}

	/*
	 * Updates the JList
	 * @author Kasper 
	 */

	public void updateList()
	{
		for(ListDataListener p : _abstractModel.getListDataListeners())
			p.contentsChanged(null);		
	}


	public void loadArtistsFromHandler()
	{
		ArrayList<Stage> stages = FestivalHandler.Instance().getStages();

		if(stages.size() != 0)
		{
			for(Stage stage : stages)
			{
				_stageArrayList.add(stage);
			}
		}
		updateList();
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
