package com.modcrafting.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ActionMap;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.undo.UndoManager;

import com.modcrafting.gui.listener.Format;

public class TextArea {
	JFrame frame;
	JTextPane textA;
	final UndoManager undo = new UndoManager();
	public TextArea(JFrame f){
		frame=f;
		textA=new JTextPane();
		textA.setBackground(Color.white);
		textA.setForeground(Color.black);
		textA.setEditable(true);
		textA.setVisible(true);
		frame.getContentPane().add(new JScrollPane(textA),BorderLayout.CENTER);
		Document doc = textA.getDocument();
		doc.addUndoableEditListener(new UndoableEditListener() {
		    public void undoableEditHappened(UndoableEditEvent evt) {
		        undo.addEdit(evt.getEdit());
		    }
		});

	}
	//This and Formatting the listener is probably the last things to do.
	public void stylize(){
		StyledDocument sDoc = textA.getStyledDocument();
		sDoc.addDocumentListener(new Format(sDoc));
		
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
 	    
        Style green = sDoc.addStyle("green", def);
 	    StyleConstants.setForeground(green, Color.green);
 	    
 	    green = sDoc.addStyle("greenunderline", green);
 	    StyleConstants.setUnderline(green, true);
		
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
	public ActionMap getActionMap(){
		return textA.getActionMap();
	}
	public UndoManager getUndoMan(){
		return undo;
	}
}
