package com.modcrafting.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import com.modcrafting.gui.Window;

public class FileMenuLoad implements ActionListener{
	Window window;
	JFrame frame;
	JFileChooser fc;
	public FileMenuLoad(JFrame f, Window w){
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
        int returnVal = fc.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
            File file = fc.getSelectedFile();
			StringBuilder builder = new StringBuilder();
			BufferedReader list = new BufferedReader(new FileReader(file));
			String t;
				while ((t = list.readLine()) != null){
						builder.append(t);
						builder.append("\n");
				}
				list.close();
				window.textA.setText(builder.toString());
			} catch (IOException e1) {
	            	window.showError("Unable to read file");
			}
        } else if(returnVal != JFileChooser.CANCEL_OPTION){
        	window.showError("Unable to write to file");
        }

	}

}
