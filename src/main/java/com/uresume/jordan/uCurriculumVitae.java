package com.uresume.jordan;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.uresume.jordan.containers.Education;
import com.uresume.jordan.containers.Job;
import com.uresume.jordan.util.JTextAreaPlaceholder;
import com.uresume.jordan.util.JTextFieldPlaceholder;
import com.uresume.jordan.util.Strings;
import com.uresume.jordan.util.Util;
import com.uresume.jordan.windows.EducationWindow;
import com.uresume.jordan.windows.HeadshotWindow;
import com.uresume.jordan.windows.JobsWindow;
import com.uresume.jordan.windows.SkillsWindow;

public class uCurriculumVitae {
	
	public JFrame window;
	public JPanel panel;
	
	public HashMap<String, Job> jobs = new HashMap<String, Job>();
	public HashMap<String, Education> education = new HashMap<String, Education>();
	
	public JLabel fullNameL;
	public JLabel jobTitleL;
	public JLabel emailL;
	public JLabel phoneL;
	public JLabel websiteL;
	public JLabel profileL;
	public JLabel jobsL;
	public JLabel skillsL;
	public JLabel educationL;
	
	public JTextFieldPlaceholder fullNameF;
	public JTextFieldPlaceholder jobTitleF;
	public JTextFieldPlaceholder emailF;
	public JTextFieldPlaceholder phoneF;
	public JTextFieldPlaceholder websiteF;
	
	public JScrollPane profileScrollPane;
	public JTextAreaPlaceholder profileF;
	
	public JList<String> jobsF;
	public JList<String> skillsF;
	public JList<String> educationF;
	
	public DefaultListModel<String> jobsModel;
	public DefaultListModel<String> skillsModel;
	public DefaultListModel<String> educationModel;
	
	public JScrollPane jobsFPane;
	public JScrollPane skillsFPane;
	public JScrollPane educationFPane;
	
	public JButton jobsB;
	public JButton skillsB;
	public JButton educationB;
	public JButton headshot;
	public JButton generate;
	
	public uCurriculumVitae() {
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setPreferredSize(Util.prefferedSize);
		
		load();
		
		window = new JFrame(Strings.APPLICATION_NAME + " " + Strings.VERSION);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(panel);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setVisible(true);
		
		window.requestFocusInWindow();
	}
	
	private void load() {
		generate = new JButton("Generate");
		generate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					generate();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		headshot = new JButton("Choose Headshot");
		
		headshot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HeadshotWindow(window);
			}
		});
		
		fullNameL = new JLabel("Full Name:");
		fullNameF = new JTextFieldPlaceholder("First M Last");
		
		jobTitleL = new JLabel("Job Title:");
		jobTitleF = new JTextFieldPlaceholder("Job Title");
		
		emailL = new JLabel("Email:");
		emailF = new JTextFieldPlaceholder("email@company.com");
		
		phoneL = new JLabel("Phone Number:");
		phoneF = new JTextFieldPlaceholder("555-555-5555");
		
		websiteL = new JLabel("Website:");
		websiteF = new JTextFieldPlaceholder("www.website.com");
		
		profileL = new JLabel("Profile:");
		profileF = new JTextAreaPlaceholder(Strings.LORUM_IPSUM);
		profileScrollPane = new JScrollPane(profileF);
		profileF.setLineWrap(true);
		profileF.setWrapStyleWord(true);
		
		Font f = new Font("Arial", Font.BOLD, 14);
		
		jobsL = new JLabel("Work Experience:");
		
		jobsModel = new DefaultListModel<String>();
		jobsF = new JList<String>();
		jobsF.setModel(jobsModel);
		jobsB = new JButton("+");
		jobsB.setFont(f);
		
		jobsFPane = new JScrollPane(jobsF);
		
		jobsB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new JobsWindow(window, jobsF, jobs);
			}
		});
		
		skillsL = new JLabel("Skills:");
		skillsModel = new DefaultListModel<String>();
		skillsF = new JList<String>();
		skillsF.setModel(skillsModel);
		skillsB = new JButton("+");
		skillsB.setFont(f);
		
		skillsFPane = new JScrollPane(skillsF);
		
		skillsB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new SkillsWindow(window, skillsF);
			}
		});
		
		educationL = new JLabel("Education:");
		educationModel = new DefaultListModel<String>();
		educationF = new JList<String>();
		educationF.setModel(educationModel);
		educationB = new JButton("+");
		educationB.setFont(f);
		
		educationFPane = new JScrollPane(educationF);
		
		educationB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new EducationWindow(window, educationF, education);
			}
		});
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 25, 0 , 25);
		panel.add(fullNameL, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 25, 10 , 25);
		panel.add(fullNameF, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 0 , 25);
		panel.add(jobTitleL, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 10 , 25);
		panel.add(jobTitleF, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(0, 25, 0 , 25);
		panel.add(emailL, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets = new Insets(0, 25, 10 , 25);
		panel.add(emailF, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.insets = new Insets(0, 0, 0 , 25);
		panel.add(phoneL, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.insets = new Insets(0, 0, 10 , 25);
		panel.add(phoneF, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 25, 0 , 25);
		panel.add(websiteL, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 25, 10 , 25);
		panel.add(websiteF, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 25, 0 , 25);
		panel.add(profileL, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridheight = 2;
		gbc.ipady = 40;
		gbc.gridx = 0;
		gbc.gridy = 11;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 25, 10 , 25);
		panel.add(profileScrollPane, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 13;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 25, 0 , 25);
		panel.add(jobsL, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridheight = 2;
		gbc.ipady = 40;
		gbc.gridx = 0;
		gbc.gridy = 14;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 25, 5 , 25);
		panel.add(jobsFPane, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 16;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 25, 10 , 25);
		panel.add(jobsB, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 17;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 25, 0 , 25);
		panel.add(skillsL, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridheight = 2;
		gbc.ipady = 40;
		gbc.gridx = 0;
		gbc.gridy = 18;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 25, 5 , 25);
		panel.add(skillsFPane, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 20;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 25, 10 , 25);
		panel.add(skillsB, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 21;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 25, 0 , 25);
		panel.add(educationL, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridheight = 2;
		gbc.ipady = 40;
		gbc.gridx = 0;
		gbc.gridy = 22;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 25, 5 , 25);
		panel.add(educationFPane, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 24;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 25, 5 , 25);
		panel.add(educationB, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 25;
		gbc.insets = new Insets(10, 25, 5 , 25);
		panel.add(headshot, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 25;
		gbc.insets = new Insets(10, 25, 5 , 25);
		panel.add(generate, gbc);
	}
	
	private void generate() throws IOException {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		
		File dir = null;
		
		if (chooser.showOpenDialog(window) == JFileChooser.APPROVE_OPTION) {
			dir = chooser.getSelectedFile();
		} else {
			return;
		}
		
		InputStream cvStream = getClass().getResourceAsStream("/cv.html");
		
		File cvFile = new File(dir.getAbsolutePath() + "\\cv.html");
		File headshotFile = new File(dir.getAbsolutePath() + "\\headshot.jpg");
		
		if (cvFile.exists()) {
			cvFile.delete();
		}
		
		if (headshotFile.exists()) {
			headshotFile.delete();
		}
		
		cvFile.getParentFile().mkdirs();
		headshotFile.getParentFile().mkdirs();
		
		cvFile.createNewFile();
		headshotFile.createNewFile();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(cvFile));
		BufferedReader br = new BufferedReader(new InputStreamReader(cvStream));
		String str = null;
		while ((str = br.readLine()) != null) {
			if (str.trim().equalsIgnoreCase("%JOBS%")) {
				ArrayList<String> keys = new ArrayList<String>(jobs.keySet());
				for (int i = 0;i<jobs.keySet().size();i++) {
					Job job = jobs.get(keys.get(i));
					bw.write("<article>" + "\n");
					bw.write("<h2>" + job.jobTitle + "</h2>" + "\n");
					bw.write("<p class=\"subDetails\">" + job.jobDate + "</p>" + "\n");
					bw.write("<p>" + job.jobDescription + "</p>" + "\n");
					bw.write("</article>" + "\n");
				}
			} else if (str.trim().equalsIgnoreCase("%SKILLS%")) {
				for (int i = 0;i<skillsModel.size();i++) {
					String skill = skillsModel.get(i);
					bw.write("<li>" + skill + "</li>" + "\n");
				}
			} else if (str.trim().equalsIgnoreCase("%EDUCATIONS%")) {
				ArrayList<String> keys = new ArrayList<String>(education.keySet());
				for (int i = 0;i<education.keySet().size();i++) {
					Education ed = education.get(keys.get(i));
					bw.write("<article>" + "\n");
					bw.write("<h2>" + ed.schoolName + "</h2>" + "\n");
					bw.write("<p class=\"subDetails\">" + ed.degree + "</p>" + "\n");
					bw.write("<p>" + ed.summary + "</p>" + "\n");
					bw.write("</article>" + "\n");
				}
			} else {
				str = str.replaceAll("%JOBTITLE%", jobTitleF.getText());
				str = str.replaceAll("%NAME%", fullNameF.getText());
				str = str.replaceAll("%EMAIL%", emailF.getText());
				str = str.replaceAll("%PHONE%", phoneF.getText());
				str = str.replaceAll("%WEBSITE%", websiteF.getText());
				str = str.replaceAll("%PROFILE%", profileF.getText());
				bw.write(str + "\n");
			}
		}
		bw.close();
		br.close();
		
		if (Util.headshotImage != null) {
			ImageIO.write(Util.headshotImage, "jpg", headshotFile);
		} else {
			InputStream is = getClass().getResourceAsStream("/headshot.jpg");
			try {
				BufferedImage i = ImageIO.read(is);
				i = resize(i, 128, 128);
				ImageIO.write(i, "jpg", headshotFile);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		JOptionPane.showMessageDialog(window, "Your Curriculum Vitae was generated and stored at " + dir.getAbsolutePath() + "\n\n" + "Thanks to Thomas Hardy for the Free CV Template! http://www.thomashardy.me.uk/");
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
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		new uCurriculumVitae();
	}

}
