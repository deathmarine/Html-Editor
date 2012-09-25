package com.modcrafting.htmleditor;

import java.io.IOException;

import javax.swing.JFrame;

import com.modcrafting.gui.Splash;
import com.modcrafting.gui.Window;

public class Main {
	public static Splash s;
	public static String version = "v0.1 Alpha";
	public static void main(String[] args) {
		for(String arg:args){
			if(arg.equalsIgnoreCase("-debug")){
				
			}
		}
		s = new Splash("splash.jpg",new JFrame());
		launch();
	}
	private static void launch(){
		new Window();
		s.close();
	}
}
