package com.modcrafting.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import com.modcrafting.gui.listener.Format;

public class TextArea {
	JFrame frame;
	JTextPane textA;
	public TextArea(JFrame f){
		frame=f;
		textA=new JTextPane();
		textA.setBackground(Color.white);
		textA.setForeground(Color.black);
		StyledDocument sDoc = textA.getStyledDocument();
		sDoc.addDocumentListener(new Format(textA));
		Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
        Style regular = sDoc.addStyle("regular", def);
        StyleConstants.setFontFamily(def, "SansSerif");
        Style s = sDoc.addStyle("italic", regular);
        StyleConstants.setItalic(s, true);
        s = sDoc.addStyle("bold", regular);
        StyleConstants.setBold(s, true);
        s = sDoc.addStyle("underline", regular);        
        StyleConstants.setUnderline(s, true);
        Style red = sDoc.addStyle("red", def);
 	    StyleConstants.setForeground(red, Color.red);
 	    red = sDoc.addStyle("redunderline", red);
 	    StyleConstants.setUnderline(red, true);
        Style green = sDoc.addStyle("red", def);
 	    StyleConstants.setForeground(green, Color.green);
 	    green = sDoc.addStyle("redunderline", green);
 	    StyleConstants.setUnderline(green, true);
		//textA.setFont(new Font("Console", Font.PLAIN, 10)); //WTF is a good default editing Font!
		//textA.setLineWrap(true);
		//textA.setWrapStyleWord(true);
		textA.setEditable(true);
		textA.setVisible(true);
		//frame.getContentPane().add(new JScrollPane(textA),BorderLayout.CENTER);
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
