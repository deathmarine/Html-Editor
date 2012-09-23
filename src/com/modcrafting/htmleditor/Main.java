package com.modcrafting.htmleditor;

import java.io.IOException;

import javax.swing.JFrame;

import com.modcrafting.gui.Splash;
import com.modcrafting.gui.Window;
import com.modcrafting.htmleditor.debug.Console;

public class Main {
	public static Splash s;
	public static void main(String[] args) {
		for(String arg:args){
			if(arg.equalsIgnoreCase("-debug")){
				Console c=new Console();
				try {
					c.init();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		}
		//Throw my sexy splash
		s = new Splash("splash.jpg",new JFrame());
		launch();
	}
	public static void launch(){
		new Window();
		s.close();
	}
}
