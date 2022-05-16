package controller;

import java.awt.event.KeyEvent;

public class Emissor {
	public static void abrirEmissorNF() {
		Robo r = new Robo();
		r.keyPress(KeyEvent.VK_F11);
		r.delay(5000);
		r.keyPress(KeyEvent.VK_ESCAPE);
		r.delay(1500);
	}
	
	public static void fecharEmissorNF() {
		Robo r = new Robo();
		r.hotKey(KeyEvent.VK_ALT, KeyEvent.VK_F);
		r.delay(1000);
	}
}
