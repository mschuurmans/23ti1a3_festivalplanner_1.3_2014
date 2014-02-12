package nl.avans.festivalplanner.view;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.avans.festivalplanner.utils.Enums.Text;

public class SettingsDialog extends JDialog
{
	
	JLabel _lblLanguage = new JLabel(Text.Language.toString());
	JLabel _lblMap = new JLabel(Text.Map.toString());
	JButton _btnChangeMap = new JButton(Text.ChangeMap.toString());
	JButton _btnRemoveMap = new JButton(Text.RemoveMap.toString());
	
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
		this.setSize(300, 290);
		this.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		_comLanguages = new JComboBox(_languages);
		_comLanguages.setSelectedIndex(0);
				
		//TODO: afbeelding toevoegen
		
		_lblLanguage.setBounds(10,20,50,20);
		_comLanguages.setBounds(120,20,150,20);
		_lblMap.setBounds(10,50,50,20);
		_btnChangeMap.setBounds(120,180,150,25);
		_btnRemoveMap.setBounds(120,210,150,25);
		
		panel.add(_lblLanguage);
		panel.add(_comLanguages);
		panel.add(_lblMap);
		panel.add(_btnChangeMap);
		panel.add(_btnRemoveMap);
		
		this.add(panel);
	}
}
