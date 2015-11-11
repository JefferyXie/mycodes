package com.mywindow.applet.awt;

import java.applet.Applet;
import java.awt.Graphics;
/*
 * <applet code="Banner" width=300 height=150>
 * 	<param name=author value="Jeffery">
 * 	<param name=purpose value="Leaning Java applet">
 * 	<param name=version value=10>
 * </applet>
 */

public class Banner extends Applet implements Runnable {
	String msg = " Java Rules the Web ";
	Thread th;
	boolean stopFlag;
	String author;
	String purpose;
	int version;
	
	public void init() {
		th = null;
	}
	public void start() {
		stopFlag = false;
		th = new Thread(this);
		th.start();
		
		author = getParameter("author");
		if (null == author) author = "not found";
		purpose = getParameter("purpose");
		if (null == purpose) purpose = "not found";
		String temp = getParameter("version");
		try {
			version = -1;
			if (null != temp)
				version = Integer.parseInt(temp);
		} catch (Exception ex) { }
	}
	public void run() {
		while (true) {
			try {
				repaint();
				Thread.sleep(300);
				if (stopFlag)
					break;
			} catch (InterruptedException ex) {}
		}
	}
	public void stop() {
		stopFlag = true;
		th = null;
	}
	public void paint(Graphics g) {
		char ch = msg.charAt(0);
		msg = msg.substring(1, msg.length()) + ch;
		g.drawString(msg, 50, 30);
		
		g.drawString("Purpose: " + purpose, 30, 70);
		g.drawString("By: " + author, 30, 90);
		g.drawString("Version: " + version, 30, 110);
		
		showStatus("This is shown in the status window.");
	}
}
