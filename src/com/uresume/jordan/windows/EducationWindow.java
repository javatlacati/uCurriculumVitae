package com.uresume.jordan.windows;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.uresume.jordan.containers.Education;
import com.uresume.jordan.util.JTextAreaPlaceholder;
import com.uresume.jordan.util.JTextFieldPlaceholder;
import com.uresume.jordan.util.Strings;

public class EducationWindow {
	
	public JDialog w;
	public JPanel p;
	
	public JLabel schoolL;
	public JLabel degreeL;
	public JLabel descriptionL;
	
	public JTextFieldPlaceholder schoolF;
	public JTextFieldPlaceholder degreeF;
	
	public JScrollPane descriptionPane;
	public JTextAreaPlaceholder descriptionF;
	
	public JButton add;
	
	public Education education;
	
	public EducationWindow(JFrame window, JList<String> educationF, HashMap<String, Education> educationMap) {
		education = new Education();
		
		p = new JPanel();
		p.setPreferredSize(new Dimension(400, 200));
		p.setLayout(new GridBagLayout());
		
		schoolL = new JLabel("School:");
		schoolF = new JTextFieldPlaceholder("Tennessee Tech University");
		
		degreeL = new JLabel("Degree:");
		degreeF = new JTextFieldPlaceholder("Bachelors");
		
		descriptionL = new JLabel("Description:");
		descriptionF = new JTextAreaPlaceholder(Strings.LORUM_IPSUM);
		descriptionPane = new JScrollPane(descriptionF);
		descriptionF.setLineWrap(true);
		descriptionF.setWrapStyleWord(true);
		
		add = new JButton("Add");
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,10,0,10);
		p.add(schoolL, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0,10,10,10);
		p.add(schoolF, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(0,10,0,10);
		p.add(degreeL, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(0,10,10,10);
		p.add(degreeF, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(0,10,0,10);
		p.add(descriptionL, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridheight = 2;
		gbc.ipady = 40;
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets = new Insets(0,10,10,10);
		p.add(descriptionPane, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.insets = new Insets(0,10,0,10);
		p.add(add, gbc);
		
		w = new JDialog();
		w.setTitle("Add Education");
		w.add(p);
		w.pack();
		w.setLocationRelativeTo(window);
		w.setResizable(false);
		w.setVisible(true);
		
		w.requestFocusInWindow();
		
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				education.schoolName = schoolF.getText();
				education.degree = degreeF.getText();
				education.summary = descriptionF.getText();
				
				DefaultListModel<String> model = (DefaultListModel<String>) educationF.getModel();
				model.addElement(education.schoolName);
				educationF.setModel(model);
				
				educationMap.put(education.schoolName, education);
				
				w.dispose();
			}
		});
	}

}
