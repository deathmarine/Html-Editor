package com.modcrafting.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextArea {
	JFrame frame;
	JTextArea textA;
	public TextArea(JFrame f){
		frame=f;
		textA=new JTextArea();
		textA.setBackground(Color.white);
		textA.setForeground(Color.black);
		//textA.setFont(new Font("Console", Font.PLAIN, 10)); //WTF is a good default editing Font!
		textA.setLineWrap(true);
		textA.setWrapStyleWord(true);
		textA.setEditable(true);
		textA.setVisible(true);
		frame.getContentPane().add(new JScrollPane(textA),BorderLayout.CENTER);
	}
	public void setText(String string) {
		textA.setText(string);
	}
	public String getText() {
		return textA.getText();
	}
	public void clear(){
		textA.setText("");
	}
	//Make runnable to auto check HTML/PHP/Javascript/Java/CascadeStyleshit..other crap and format it all sexy like.
}
