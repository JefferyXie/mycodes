package com.mywindow.applet.swing;

import java.awt.FlowLayout;
import javax.swing.*;
/*
 * <applet code="SwingApplet" width=300 height=200>
 * </applet>
 */

public class SwingApplet extends JApplet {
	JButton btnUp;
	JButton btnDown;
	JLabel label;
	
	public void init() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					makeUI();
				}
			});
		} catch (Exception ex) {
			System.out.println("Cannot create because of " + ex);
		}
	}
	private void makeUI() {
		setLayout(new FlowLayout());
		
		btnUp = new JButton("Up");
		btnDown = new JButton("Down");
		
		btnUp.addActionListener(ae -> {
			label.setText("You pressed Up");
		});
		btnDown.addActionListener(ae -> {
			label.setText("You pressed Down");
		});
		
		add(btnUp);
		add(btnDown);
		
		label = new JLabel("Press a button");
		add(label);
	}
}
