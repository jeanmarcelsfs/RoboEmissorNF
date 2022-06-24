package controller;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Robo {
	private int autoDelay = 70;
	
	public void setAutoDelay(int autoDelay) {
		this.autoDelay = autoDelay;
	}
	
	public void delay(int ms) {
		try {
			Robot  r = new Robot();
			r.delay(ms);
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void keyPress(int keyEventCode) {
		try {
			Robot  r = new Robot();
			r.setAutoDelay(autoDelay);
			r.keyPress(keyEventCode);
			r.keyRelease(keyEventCode);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void keyType(String texto) {
		try {
			Robot  r = new Robot();
			r.setAutoDelay(autoDelay);
			texto = texto.toUpperCase();
			Character ch;
			for(int i = 0; i < texto.length(); i++) {
				ch = texto.charAt(i);
				if (ch.equals(':')) {
					//r.keyPress(KeyEvent.VK_COLON);
					//r.keyRelease(KeyEvent.VK_COLON);
					System.out.println("o sistema não suporta :  - verifique o KeyType do Robo");
				}
				else {
					r.keyPress(ch.hashCode());
					r.keyRelease(ch.hashCode());
				}
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void hotKey(int keyCode1, int KeyCode2) {
		try {
			Robot  r = new Robot();
			r.setAutoDelay(autoDelay);
			r.keyPress(keyCode1);
			r.keyPress(KeyCode2);
			r.keyRelease(keyCode1);
			r.keyRelease(KeyCode2);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void hotKey(int keyCode1, int KeyCode2, int KeyCode3) {
		try {
			Robot  r = new Robot();
			r.setAutoDelay(autoDelay);
			r.keyPress(keyCode1);
			r.keyPress(KeyCode2);
			r.keyPress(KeyCode3);
			r.keyRelease(keyCode1);
			r.keyRelease(KeyCode2);
			r.keyRelease(KeyCode3);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mouseClick(int x, int y) {
		try {
			Robot r = new Robot();
			r.mouseMove(x, y);
			r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
	}
}
