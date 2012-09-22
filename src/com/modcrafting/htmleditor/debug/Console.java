package com.modcrafting.htmleditor.debug;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Console implements Runnable{
	JFrame frame;
	JTextArea text;
	private Thread reader;
	private PipedOutputStream pout;
	private PipedInputStream pin;
	private PipedInputStream pin2;
	boolean run=true;
	public Console(){
		frame = new JFrame("DefaultConsoleTitle");
	}
	public void init() throws IOException{
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize=new Dimension((int)(screenSize.width/2),(int)(screenSize.height/2));
		int x=(int)(frameSize.width/2);
		int y=(int)(frameSize.height/2);
		frame.setBounds(x,y,frameSize.width,frameSize.height);
		text = new JTextArea();
		text.setBackground(Color.white);
		text.setForeground(Color.black);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(new JScrollPane(text),BorderLayout.CENTER);
		frame.setVisible(true);
		try{
			pin = new PipedInputStream();
			pout=new PipedOutputStream(pin);
			System.setOut(new PrintStream(pout,true));
			PipedOutputStream pout2=new PipedOutputStream(pin2);
			System.setErr(new PrintStream(pout2,true));
			System.setIn(new PipedInputStream(pout));
		}catch (java.io.IOException io){
			text.append(io.getMessage());
		}catch (SecurityException se){
			text.append(se.getMessage());
	    }	
		reader=new Thread(this);
		reader.setDaemon(true);	
		reader.start();	
		
	}
	@Override
	public void run() {
		while(run){
			if(pout!=null){
				try {
					if (pin.available()!=0){
						String input= this.readLine(pin);
						text.append(input);
					}
					if (pin2.available()!=0){
						String input= this.readLine(pin2);
						text.append(input);
						
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private synchronized String readLine(PipedInputStream in) throws IOException{
		String input="";												
		while(!input.endsWith("\n")&&!input.endsWith("\r\n")&&run){
			int available=in.available();
			if (available==0) break;
			byte b[]=new byte[available];
			in.read(b);
			input=input+new String(b,0,b.length);	
		}
		return input;
	}
}
