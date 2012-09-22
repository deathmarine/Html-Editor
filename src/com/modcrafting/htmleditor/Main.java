package com.modcrafting.htmleditor;

import java.io.IOException;

import com.modcrafting.htmleditor.debug.Console;

public class Main {

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
	}

}
