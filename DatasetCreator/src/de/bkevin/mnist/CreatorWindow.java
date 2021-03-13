package de.bkevin.mnist;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class CreatorWindow extends JFrame {
	
	Container c;
	Board b;
	JButton green, red, blue, gradient;
	public static int counter = 0;
	public static JTextField export;
	public static JTextField name;
	JLabel info;

	public CreatorWindow() {
		c = getContentPane();
		b = new Board();
		
		//buttons
		export = new JTextField(new File("").getAbsolutePath(), 25);
		name = new JTextField("filename",6);
		info = new JLabel();
		info.setText("RightClick to save | MiddleClick to restart");

		
		//add
		b.add(export);
		b.add(name);
		b.add(info);

		c.add(b);
		
	}
	

	
	
	public static void main(String[] args) {
		CreatorWindow frame = new CreatorWindow();
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setTitle("Kevin's cool DatasetCreator :)");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

}