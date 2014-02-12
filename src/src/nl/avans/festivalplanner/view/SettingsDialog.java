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
	
	JLabel lblLanguage = new JLabel(Text.Language.toString());
	JLabel lblMap = new JLabel(Text.Map.toString());
	JButton btnChangeMap = new JButton(Text.ChangeMap.toString());
	JButton btnRemoveMap = new JButton(Text.RemoveMap.toString());
	
	String[] languages = { Text.Dutch.toString(), Text.English.toString() };
	JComboBox comLanguages;
	
	JLabel imgMap = new JLabel(new ImageIcon(""));
	
	public SettingsDialog(JFrame frame)
	{
		super(frame);
		init();
	}
	
	public void init()
	{
		this.setTitle(Text.Settings.toString());
		this.setSize(300, 400);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		lblLanguage.setBounds(10,20,50,20);
		panel.add(lblLanguage);
		comLanguages = new JComboBox(languages);
		comLanguages.setSelectedIndex(0);
		comLanguages.setBounds(150,20,100,20);
		panel.add(comLanguages);
		lblMap.setBounds(10,50,50,20);
		panel.add(lblMap);
		//this.add(imgMap); //TODO: afbeelding toevoegen
		btnChangeMap.setBounds(150,180,120,25);
		btnRemoveMap.setBounds(150,210,120,25);
		panel.add(btnChangeMap);
		panel.add(btnRemoveMap);
		this.add(panel);
	}
}
