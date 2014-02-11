package nl.avans.festivalplanner.view;

import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		this.setLayout(new GridLayout(4, 2));
		this.add(lblLanguage);
		comLanguages = new JComboBox(languages);
		comLanguages.setSelectedIndex(0);
		this.add(comLanguages);
		this.add(lblMap);
		this.add(imgMap);
		this.add(btnChangeMap);
		this.add(btnRemoveMap);
	}
}
