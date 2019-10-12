package com.uresume.jordan.util;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class JTextFieldPlaceholder extends JTextField implements FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String placeholderText;
	
	public JTextFieldPlaceholder(String text) {
		addFocusListener(this);
		setPlaceholderText(text);
		placeholderText = text;
	}
	
	public JTextFieldPlaceholder() {
		this.addFocusListener(this);
	}
	
	public void setPlaceholderText(String text) {
		setForeground(Color.GRAY);
		setText(text);
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (getForeground() == Color.GRAY) {
			setForeground(Color.BLACK);
			setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (getText() == null) {
			setPlaceholderText(placeholderText);
		}
		
		if (getText().trim().equalsIgnoreCase("")) {
			setPlaceholderText(placeholderText);
		}
	}

}
