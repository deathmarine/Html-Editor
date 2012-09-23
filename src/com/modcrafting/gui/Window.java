package com.modcrafting.gui;

import java.awt.Dimension;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.modcrafting.gui.listener.WListener;

public class Window{
	public JFrame frame;
	public Window(){
		frame = new JFrame("");
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize=new Dimension((int)(screenSize.width/2),(int)(screenSize.height/2));
		int x=(int)(frameSize.width/2);
		int y=(int)(frameSize.height/2);
		frame.setBounds(x,y,frameSize.width,frameSize.height);
		frame.addWindowListener(new WListener(frame));
		createMenu();
		frame.setVisible(true);
	}
	public void createMenu(){
		JMenuBar mbar = new JMenuBar();
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		mbar.add(menu);
		JMenuItem menuItem = new JMenuItem("New", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Describing making something new");
		menuItem.addActionListener(null);
		menu.add(menuItem);
		menuItem = new JMenuItem("Open File...", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Describing making something new");
		menuItem.add(new PopupMenu());
		menu.add(menuItem);
	
		//Download page from internet
		menuItem = new JMenuItem("Open URL...", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Opens a url to edit.");
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog(null, "Enter the url to open.", "Open Url...", 1); 
			}
		});
		menu.add(menuItem);
		
	menuItem = new JMenuItem("Save", KeyEvent.VK_T);
	menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
	menuItem.getAccessibleContext().setAccessibleDescription("Describing making something new");
	menu.add(menuItem);
	menuItem = new JMenuItem("Save as...", KeyEvent.VK_T);
	menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
	menuItem.getAccessibleContext().setAccessibleDescription("Describing making something new");
	menu.add(menuItem);
	frame.setJMenuBar(mbar);
	}
}
