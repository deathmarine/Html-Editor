package com.modcrafting.gui.listener;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class FileChooserFileFilter extends FileFilter{
	String objType;
	public FileChooserFileFilter(String string){
		objType=string;
	}
	@Override
	public boolean accept(File f) {
		if(f.isDirectory())return false;
		return f.getName().toLowerCase().endsWith(objType.substring(1));
	}

	@Override
	public String getDescription() {
		return objType;
	}

}
