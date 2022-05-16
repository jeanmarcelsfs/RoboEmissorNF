package controller;

import java.awt.event.KeyEvent;

public class AbrirEmissorNF {
	public static void abrirEmissorNF() {
		Robo r = new Robo();
		r.keyPress(KeyEvent.VK_F11);
		r.delay(5000);
		r.keyPress(KeyEvent.VK_ESCAPE);
		r.delay(1500);
	}
}
