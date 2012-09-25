package com.modcrafting.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.modcrafting.gui.Window;

public class FileMenuSave implements ActionListener {
	Window window;
	JFrame frame;
	JFileChooser fc;
	public FileMenuSave(JFrame f, Window w){
		frame=f;
		window=w;
		fc = new JFileChooser();
		String[] extensions = {"*.phtml","*.asp","*.js","*.java","*.css","*.php","*.htm","*.html"};
        for(String extension:extensions){
        	fc.addChoosableFileFilter(new FileChooserFileFilter(extension));
        }
	}
	@Override
	public void actionPerformed(ActionEvent e) {
        int returnVal = fc.showSaveDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            if(!file.getName().contains(".")){
            	file =new File(fc.getSelectedFile().getParent(),file.getName()+".html");
            	
            }
    		try {
                if(!file.exists()) file.createNewFile();
                window.setFile(file);
				BufferedWriter b = new BufferedWriter(new FileWriter(file,true));
				for(String line:window.textA.getText().split("\n")){
					b.write(line);
					b.newLine();
				}
				b.close();
			} catch (IOException e1) {
				e1.printStackTrace();
	        	window.showError("Unable to write to file");
			}
    		return;
        } else if(returnVal != JFileChooser.CANCEL_OPTION){
        	window.showError("Unable to write to file");
        }

	}

}
