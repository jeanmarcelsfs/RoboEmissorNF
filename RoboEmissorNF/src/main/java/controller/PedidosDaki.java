package controller;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JOptionPane;

import model.Pedido;

public class PedidosDaki {
	public static void emiteDaki(int computador) {
		try {
			Robo r = new Robo();			
			//r.delay(1000);
			r.keyPress(KeyEvent.VK_F10);
			r.delay(7000);
			String dirDaki;
			if (computador == Main.PC_SERV)
				dirDaki = "E:/";
			else
				dirDaki = "Z:/";
			dirDaki += "Faturamento/_daki.xls";
			List<Pedido> lsDaki = LerExcel.readDAKI(dirDaki);
			boolean firstRow = true;
			r.setAutoDelay(200);
			for(int i=0; i<lsDaki.size();i++) {
				r.keyPress(KeyEvent.VK_F5);
				if (firstRow) {
					r.keyPress(KeyEvent.VK_TAB);
					r.keyPress(KeyEvent.VK_RIGHT);
					r.keyPress(KeyEvent.VK_TAB);
					r.keyPress(KeyEvent.VK_RIGHT);
					r.keyPress(KeyEvent.VK_TAB);
					r.keyPress(KeyEvent.VK_RIGHT);
					firstRow = false;
				}
				r.hotKey(KeyEvent.VK_SHIFT, KeyEvent.VK_HOME);
				r.delay(500);
				//r.keyType(lsDaki.get(i).getCodCliente());
				Utilitarios.copiarParaAreaTransferencia(lsDaki.get(i).getCodCliente());
				r.hotKey(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyPress(KeyEvent.VK_ENTER);
				r.delay(2000);
				
				//Clique na Observação do Pedido				
				
				r.mouseClick(LerConfig.getObsPedX(computador), LerConfig.getObsPedY(computador));
				Utilitarios.copiarParaAreaTransferencia(lsDaki.get(i).getNumPedido());
				
				//r.keyType(lsDaki.get(i).getNumPedido());
				r.delay(1000);
				//
				r.hotKey(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyPress(KeyEvent.VK_6);
				r.keyPress(KeyEvent.VK_ENTER);
				r.delay(1000);
				if (lsDaki.get(i).getQtde().equals("25"))
					r.keyType("10");
				else if (lsDaki.get(i).getQtde().equals("50"))
					r.keyType("20");
				else {	
					JOptionPane.showMessageDialog(null, "Ero ao reconhecer a qtde DAKI");
					System.exit(0);
				}
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyType("35");
				r.keyPress(KeyEvent.VK_ENTER);				
				r.keyPress(KeyEvent.VK_F12);
				r.delay(500);
				r.keyType("1234");
				r.keyPress(KeyEvent.VK_ENTER);
				r.delay(4000);
				r.keyPress(KeyEvent.VK_TAB);
				//r.keyType("18"); // 35 Dias
				r.keyType("20"); // 40 Dias
				r.hotKey(KeyEvent.VK_ALT, KeyEvent.VK_C);
				r.delay(2000);
				r.keyPress(KeyEvent.VK_ESCAPE);
				r.delay(2000);
				r.keyPress(KeyEvent.VK_ESCAPE);
				r.delay(1000);
			}
			JOptionPane.showMessageDialog(null, "TERMINEI OS PEDIDOS");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
