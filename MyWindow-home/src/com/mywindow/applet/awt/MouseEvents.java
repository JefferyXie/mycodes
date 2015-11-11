package com.mywindow.applet.awt;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
/*
 * <applet code="MouseEvents" width=300 height=200>
 * </applet>
 */

// This applet is both the source and listener. This works because Component which supplies
// the addMouseListener() and addMouseMotionListener() methods, is a superclass of Applet.
// Being both the source and listener for events is a common situation for applets.
public class MouseEvents extends Applet implements MouseListener, MouseMotionListener {
	String msg = "";
	int mouseX = 0, mouseY = 0;
	
	public void init() {
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	// MouseListener methods
	public void mouseClicked(MouseEvent me) {
		getMousePos(me);
		msg = "Mouse clicked";
		repaint();
	}
	public void mousePressed(MouseEvent me) {
		getMousePos(me);
		msg = "Mouse pressed";
		repaint();
	}
    public void mouseReleased(MouseEvent me) {
    	getMousePos(me);
    	msg = "Mouse released";
    	repaint();
    }
    public void mouseEntered(MouseEvent me) {
    	getMousePos(me);
    	msg = "Mouse entered";
    	repaint();
    }
    public void mouseExited(MouseEvent me) {
    	getMousePos(me);
    	msg = "Mouse existed";
    	repaint();
    }
    // MouseMotionListener methods
    public void mouseDragged(MouseEvent me) {
    	getMousePos(me);
    	msg = "Mouse dragged";
    	repaint();
    	showStatus("Dragging mouse at (" + mouseX + ", " + mouseY + ")");
    }
    public void mouseMoved(MouseEvent me) {
    	getMousePos(me);
    	msg = "Mouse moved";
    	repaint();
    	showStatus("Moving mouse at (" + mouseX + ", " + mouseY + ")");
    }
    public void paint(Graphics g) {
    	g.drawString(msg, mouseX, mouseY);
    }
	private void getMousePos(MouseEvent me) {
		mouseX = me.getX();
		mouseY = me.getY();
	}
}
