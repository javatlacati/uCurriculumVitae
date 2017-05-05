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

import com.uresume.jordan.containers.Job;
import com.uresume.jordan.util.JTextAreaPlaceholder;
import com.uresume.jordan.util.JTextFieldPlaceholder;
import com.uresume.jordan.util.Strings;

public class JobsWindow {
	
	public JDialog w;
	public JPanel p;
	
	public JLabel jobTitleL;
	public JLabel jobDateL;
	public JLabel jobDescriptionL;
	
	public JTextFieldPlaceholder jobTitleF;
	public JTextFieldPlaceholder jobDateF;
	
	public JScrollPane jobDescriptionPane;
	public JTextAreaPlaceholder jobDescriptionF;
	
	public JButton add;
	
	public Job job;
	
	public JobsWindow(JFrame window, JList<String> jobsF, HashMap<String, Job> jobsList) {
		job = new Job();
		
		p = new JPanel();
		p.setPreferredSize(new Dimension(400, 200));
		p.setLayout(new GridBagLayout());
		
		jobTitleL = new JLabel("Place of Work & Job Title:");
		jobTitleF = new JTextFieldPlaceholder("Place of Work - Job Title");
		
		jobDateL = new JLabel("Job Date:");
		jobDateF = new JTextFieldPlaceholder("April 2017 - June 2017");
		
		jobDescriptionL = new JLabel("Job Description:");
		jobDescriptionF = new JTextAreaPlaceholder(Strings.LORUM_IPSUM);
		jobDescriptionPane = new JScrollPane(jobDescriptionF);
		jobDescriptionF.setLineWrap(true);
		jobDescriptionF.setWrapStyleWord(true);
		
		add = new JButton("Add");
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,10,0,10);
		p.add(jobTitleL, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0,10,10,10);
		p.add(jobTitleF, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(0,10,0,10);
		p.add(jobDateL, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(0,10,10,10);
		p.add(jobDateF, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(0,10,0,10);
		p.add(jobDescriptionL, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridheight = 2;
		gbc.ipady = 40;
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets = new Insets(0,10,10,10);
		p.add(jobDescriptionPane, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.insets = new Insets(0,10,0,10);
		p.add(add, gbc);
		
		w = new JDialog();
		w.setTitle("Add Work Experience");
		w.add(p);
		w.pack();
		w.setLocationRelativeTo(window);
		w.setResizable(false);
		w.setVisible(true);
		
		w.requestFocusInWindow();
		
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				job.jobTitle = jobTitleF.getText();
				job.jobDate = jobDateF.getText();
				job.jobDescription = jobDescriptionF.getText();
				
				DefaultListModel<String> model = (DefaultListModel<String>) jobsF.getModel();
				model.addElement(job.jobTitle);
				jobsF.setModel(model);
				
				jobsList.put(job.jobTitle, job);
				
				w.dispose();
			}
		});
	}

}
