package com.modcrafting.gui.listener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

public class Format implements DocumentListener{
	StyledDocument sDoc;
	public Format(StyledDocument sDoc2) {
		sDoc =sDoc2;
	}
	
	//Listen for HTML/PHP/Javascript/Java/CascadeStyleshit..other crap and format it all sexy like.
	@Override
	public void changedUpdate(DocumentEvent e) {
	}
	@Override
	public void insertUpdate(DocumentEvent e) {
	}
	@Override
	public void removeUpdate(DocumentEvent e) {
	}
	
	public void TemporaryWorkArea(DocumentEvent e){
		try {
			String change = e.getDocument().getText(e.getOffset(),e.getLength());
			if(change.contains("<")&&change.contains(">")){
				//String syntax = change.substring(change.indexOf("<"), change.indexOf(">"));
				//sDoc.setCharacterAttributes(change.indexOf("<"), syntax.length(), sDoc.getStyle("green"), true);
			}
		} catch (BadLocationException e1) {	}
	}
}
