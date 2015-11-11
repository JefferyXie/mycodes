package com.mywindow.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SwingFC implements ActionListener {
	
	public static void main(String args[]) {
		SwingUtilities.invokeLater(() -> {
			new SwingFC();
		});
	}
	
	JTextField tfFirst;
	JTextField tfSecond;
	JButton btnComp;
	JLabel labFirst;
	JLabel labSecond;
	JLabel labResult;
	
	public SwingFC() {
		JFrame frm = new JFrame("Compare Files");
		frm.setLayout(new FlowLayout());
		frm.setSize(300, 200);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tfFirst = new JTextField(14);
		tfSecond = new JTextField(14);
		tfFirst.setActionCommand("fileA");
		tfSecond.setActionCommand("fileB");
		
		btnComp = new JButton("Compare");
		btnComp.addActionListener(this);

		labFirst = new JLabel("First file is: ");
		labSecond = new JLabel("Second file is: ");
		labResult = new JLabel("");
		
		frm.add(labFirst);
		frm.add(tfFirst);
		frm.add(labSecond);
		frm.add(tfSecond);
		frm.add(btnComp);
		frm.add(labResult);
		
		frm.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (tfFirst.getText().isEmpty()) {
			labResult.setText("First file is missing.");
			return;
		}
		if (tfSecond.getText().isEmpty()) {
			labResult.setText("Second file is missing.");
			return;
		}
		try (FileInputStream f1 = new FileInputStream(tfFirst.getText());
			FileInputStream f2 = new FileInputStream(tfSecond.getText())) {
			int i = 0, j = 0;
			do {
				i = f1.read();
				j = f2.read();
				if (i != j) break;
			} while (i != -1 && j != -1);
			if (i != j) {
				labResult.setText("Files are not the same.");
			}
			else {
				labResult.setText("Files compare equal");
			}
		} catch (IOException ex) {
			labResult.setText(ex.getMessage());
		}
	}
}
