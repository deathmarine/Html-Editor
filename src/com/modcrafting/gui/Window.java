package com.modcrafting.gui;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.modcrafting.gui.listener.FileMenuLoad;
import com.modcrafting.gui.listener.FileMenuSave;
import com.modcrafting.gui.listener.WListener;

public class Window{
	public JFrame frame;
	public TextArea textA;
	public File file;
	public Window(){
		frame = new JFrame("Open Source Web Editor");
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize=new Dimension((int)(screenSize.width/2),(int)(screenSize.height/2));
		int x=(int)(frameSize.width/2);
		int y=(int)(frameSize.height/2);
		frame.setBounds(x,y,frameSize.width,frameSize.height);
		frame.addWindowListener(new WListener());
		createMenu();
		frame.setVisible(true);
		textA=new TextArea(frame);
		textA.setText("");
		
	}
	public void createMenu(){
		JMenuBar mbar = new JMenuBar();
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		mbar.add(menu);
		JMenuItem menuItem = new JMenuItem("New");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Describing making something new");
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				textA.setText("");
			}
			
		});
		menu.add(menuItem);
		menuItem = new JMenuItem("Open File...");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Opens a file to edit.");
		menuItem.addActionListener(new FileMenuLoad(frame,this));
		menu.add(menuItem);
		menuItem = new JMenuItem("Open URL...");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Opens a url to edit.");
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String in = showInput("Enter the url to open.", "Open Url...");
				try {
					URL url = new URL(in);
					URLConnection connection = url.openConnection();
					String line;
					StringBuilder builder = new StringBuilder();
					BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					while((line = reader.readLine()) != null) {
						builder.append(line);
						builder.append("\n");
					}
					textA.setText(builder.toString());
					
				} catch (HeadlessException e1) {
					showError("Unable to open: "+in);
				} catch (MalformedURLException e1) {
					showError("Unable to open: "+in);
				} catch (IOException e1) {
					showError("Unable to read site: "+in);
				}
			}
		});
		menu.add(menuItem);
		
	menuItem = new JMenuItem("Save");
	menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
	menuItem.getAccessibleContext().setAccessibleDescription("Saves a file");
	menuItem.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			if(file!=null){
				//I know sloppy but hey it'll do.
				//Coding, especially java is being as lazy as possible.
				//Pull requests are real lazy asses showing how to be better at being moar lazy LMAO...
	    		try {
					BufferedWriter b = new BufferedWriter(new FileWriter(file,true));
					for(String line:textA.getText().split("\n")){
						b.write(line);
						b.newLine();
					}
					b.close();
				} catch (IOException e1) {
		        	showError("Unable to write to file");
				}
			}else{
				new FileMenuSave(frame, getWindow());
			}
		}
		
	});
	menu.add(menuItem);
	menuItem = new JMenuItem("Save as...");
	menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
	menuItem.getAccessibleContext().setAccessibleDescription("Save as specific file.");
	menuItem.addActionListener(new FileMenuSave(frame, this));
	menu.add(menuItem);
	menuItem = new JMenuItem("Exit");
	menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
	menuItem.getAccessibleContext().setAccessibleDescription("Exit the program.");
	menuItem.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	});
	menu.add(menuItem);
	
	frame.setJMenuBar(mbar);
	}
	public void showError(String message){
		JOptionPane.showMessageDialog(null, message, "Error", 1);
	}
	public String showInput(String title, String message){
		return JOptionPane.showInputDialog(null,message, title,1);
	}
	public void setFile(File f){
		file=f;
	}
	public File getFile(){
		return file;
	}
	public Window getWindow(){
		return this;
	}
}
