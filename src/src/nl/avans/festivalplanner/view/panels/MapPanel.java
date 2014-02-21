package nl.avans.festivalplanner.view.panels;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.*;

import nl.avans.festivalplanner.model.FestivalHandler;
import nl.avans.festivalplanner.utils.Enums.Text;
import nl.avans.festivalplanner.view.Panel;

public class MapPanel extends Panel implements ActionListener, Serializable {
	private Image _image;
	private String _imageSource;
	private File _imageFile;
	private final JFileChooser _fc = new JFileChooser(new File("resources"));
	
	public MapPanel() {
		_imageSource = FestivalHandler.Instance().getMap();
		_imageFile = new File(_imageSource);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		try {
			_image = ImageIO.read(_imageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JButton _mapButton = new JButton(Text.LoadMap.toString());
		_mapButton.addActionListener(this);
		add(_mapButton);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		repaint();
		g.drawImage(_image, 0, 0, getWidth(), getHeight(), null);
		
	}

	@Override
	public Panel getPanel() {
		return this;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int returnVal = _fc.showOpenDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			_imageFile = _fc.getSelectedFile();
			_imageSource = _imageFile.toString();
			try {
				_image = ImageIO.read(_imageFile);
			} catch (IOException e1) {
				System.out.println("ERROR WITH IMAGE FILE");
			}
		}
		FestivalHandler.Instance().setMap(_imageSource);
	}
}
