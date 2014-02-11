package nl.avans.festivalplanner.view.panels;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import nl.avans.festivalplanner.view.Panel;

public class MapPanel extends Panel implements ActionListener {
	private Image _image;
	private final JFileChooser _fc = new JFileChooser(new File("resources"));

	public MapPanel() throws IOException {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		_image = ImageIO.read(new File("resources/map.jpg"));
		JButton _mapButton = new JButton("Load new map");
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
			File file = _fc.getSelectedFile();
		}
	}
}
