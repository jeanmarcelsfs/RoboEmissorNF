package controller;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import model.Pedido;

public class ImprimePedido {
	public static void imprimePedido(Pedido p, int computador) {
		try {
			//fecha emissor
			Emissor.fecharEmissorNF();
			Robo r = new Robo();
			
			r.keyPress(KeyEvent.VK_F10);
			r.delay(8000);
			
			r.hotKey(KeyEvent.VK_ALT, KeyEvent.VK_Z);
			r.delay(500);
			r.keyType(p.getNumPedido());
			r.keyPress(KeyEvent.VK_ENTER);
			r.delay(1000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.delay(5000);
			
			//Clique no Imprimir Pedido
			
			r.mouseClick(LerConfig.getImpressaoPedX(computador), LerConfig.getImpressaoPedY(computador));
			r.delay(2000);
			r.keyPress(KeyEvent.VK_ESCAPE);
			
			//fecha janela vendas
			r.keyPress(KeyEvent.VK_ESCAPE);
			r.keyPress(KeyEvent.VK_ENTER);
			
			Emissor.abrirEmissorNF();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}	
}
