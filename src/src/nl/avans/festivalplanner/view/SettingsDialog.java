package nl.avans.festivalplanner.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.avans.festivalplanner.settings.SettingsController;
import nl.avans.festivalplanner.utils.Enums.Languages;
import nl.avans.festivalplanner.utils.Enums.Text;

public class SettingsDialog extends JDialog
{
	
	JLabel _lblLanguage = new JLabel(Text.Language.toString());
	//JLabel _lblMap = new JLabel(Text.Map.toString());
	//JButton _btnChangeMap = new JButton(Text.ChangeMap.toString());
	//JButton _btnRemoveMap = new JButton(Text.RemoveMap.toString());
	
	String[] _languages = { Text.Dutch.toString(), Text.English.toString() };
	JComboBox _comLanguages;
	
	// JLabel imgMap = new JLabel(new ImageIcon(""));
	
	public SettingsDialog(JFrame frame)
	{
		super(frame);
		init();
	}
	
	public void init()
	{
		this.setTitle(Text.Settings.toString());
		
		this.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		
		_comLanguages = new JComboBox(_languages);
		_comLanguages.setSelectedIndex(0);
				
		if(SettingsController.Instance().getLanguage() == Languages.english)
			_comLanguages.setSelectedIndex(1);
		
		//TODO: afbeelding toevoegen
		
		
		JButton buttonSave = new JButton(Text.Save.toString());
		buttonSave.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(_comLanguages.getSelectedIndex() == 0)
					SettingsController.Instance().setLanguage(Languages.dutch);
				else
					SettingsController.Instance().setLanguage(Languages.english);
				
				System.out.println("New language: " + SettingsController.Instance().getLanguage().toString());
				
				SettingsController.Instance().save();
				
				SettingsDialog.this.setVisible(false);
				SettingsDialog.this.dispatchEvent(new WindowEvent(SettingsDialog.this, WindowEvent.WINDOW_CLOSING));
			}
			
		});
		
		JButton buttonCancel = new JButton(Text.Cancel.toString());
		buttonCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SettingsDialog.this.setVisible(false);
				SettingsDialog.this.dispatchEvent(new WindowEvent(SettingsDialog.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		panel.add(_lblLanguage);
		panel.add(_comLanguages);
		panel.add(buttonCancel);
		panel.add(buttonSave);
		//panel.add(_lblMap);
		//panel.add(_btnChangeMap);
		//panel.add(_btnRemoveMap);
		
		this.add(panel);
		this.pack();
		

		this.setLocationRelativeTo(null);
	}
}
