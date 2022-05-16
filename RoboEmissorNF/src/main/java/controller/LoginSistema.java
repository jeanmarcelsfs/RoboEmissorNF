package controller;

import java.awt.event.KeyEvent;

public class LoginSistema {
	public static void logarSistema(String usuario, String senha) {
		Robo rb = new Robo();		
		rb.keyPress(KeyEvent.VK_WINDOWS);
		rb.delay(1000);
		rb.keyType("INFOSIS G");
		rb.delay(3000);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.delay(7000);
		rb.setAutoDelay(10);
		rb.keyType(usuario);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyType(senha);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.delay(3000);
	}

}
