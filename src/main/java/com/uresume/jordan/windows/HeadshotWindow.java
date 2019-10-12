package com.uresume.jordan.windows;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.uresume.jordan.util.Util;

public class HeadshotWindow {

	public JDialog w;
	public JPanel p;

	public JLabel label;
	public JLabel display;
	public JButton browse;

	public JFileChooser fileChooser;

	public JButton add;
	
	public BufferedImage headshot;

	public HeadshotWindow(JFrame window) {
		p = new JPanel();
		p.setPreferredSize(new Dimension(400, 400));
		p.setLayout(new GridBagLayout());

		label = new JLabel("Headshot:");
		display = new JLabel();
		browse = new JButton("Browse");
		
		fileChooser = new JFileChooser();
		
		fileChooser.setAcceptAllFileFilterUsed(false);

		add = new JButton("Add");
		
		display.setHorizontalAlignment(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		if (Util.headshotImage == null) {
			InputStream is = getClass().getResourceAsStream("/headshot.jpg");
			try {
				BufferedImage i = ImageIO.read(is);
				i = resize(i, 128, 128);
				ImageIcon icon = new ImageIcon(i);
				display.setIcon(icon);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			BufferedImage i = Util.headshotImage;
			i = resize(i, 128, 128);
			ImageIcon icon = new ImageIcon(i);
			display.setIcon(icon);
		}

		browse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileFilter f = new FileNameExtensionFilter("*.jpg", "jpg");

				fileChooser.addChoosableFileFilter(f);
				
				fileChooser.setFileFilter(f);

				int returnVal = fileChooser.showOpenDialog(window);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						BufferedImage im = ImageIO.read(file);
						im = resize(im, 128, 128);
						ImageIcon icon = new ImageIcon(im);
						display.setIcon(icon);
						
						headshot = im;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,10,0,10);
		p.add(label, gbc);

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridheight = 2;
		gbc.ipady = 100;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0,10,10,10);
		p.add(display, gbc);

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(0,10,0,10);
		p.add(browse, gbc);

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(10,10,0,10);
		p.add(add, gbc);

		w = new JDialog();
		w.setTitle("Select Headshot");
		w.add(p);
		w.pack();
		w.setLocationRelativeTo(window);
		w.setResizable(false);
		w.setVisible(true);

		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Util.headshotImage = headshot;
				
				w.dispose();
			}
		});
	}
	
	private BufferedImage resize(BufferedImage img, int w, int h) {
		BufferedImage dimg = new BufferedImage(w, h, img.getType());  
		Graphics2D g = dimg.createGraphics();  
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
		g.drawImage(img, 0, 0, w, h, 0, 0, img.getWidth(), img.getHeight(), null);  
		g.dispose();  
		return dimg;  
	}

}
