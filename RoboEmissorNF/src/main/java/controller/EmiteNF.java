package controller;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import model.Pedido;

public class EmiteNF {
	public static void emiteNF(List<Pedido> lista, boolean pdf, int computador) {
		Robo r = new Robo();
		// le as configurações para determinar a posição x e y

		for (int i = 0; i < lista.size(); i++) {
			r.keyPress(KeyEvent.VK_F10);
			r.delay(300);
			r.keyPress(KeyEvent.VK_F8);
			r.keyType(lista.get(i).getNumPedido());
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyPress(KeyEvent.VK_ENTER);
			r.delay(3000);
			r.keyPress(KeyEvent.VK_ESCAPE);
			r.hotKey(KeyEvent.VK_ALT, KeyEvent.VK_M);
			Utilitarios.copiarParaAreaTransferencia(lista.get(i).getObs());
			r.hotKey(KeyEvent.VK_CONTROL, KeyEvent.VK_V);

			if (isConsumidorFinal(lista.get(i)))
				r.mouseClick(LerConfig.getConsumidorX(computador), LerConfig.getConsumidorY(computador));

			// envia NF
			r.mouseClick(LerConfig.getEnviaNFX(computador), LerConfig.getEnviaNFY(computador));
			r.delay(2000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyPress(KeyEvent.VK_ENTER);
			r.delay(10000);
			// Imprime
			r.hotKey(KeyEvent.VK_ALT, KeyEvent.VK_I);
			r.delay(1500);
			r.keyPress(KeyEvent.VK_ENTER);
			r.delay(3000);

			if (pdf)
				salvarPDF(lista.get(i));
			r.keyPress(KeyEvent.VK_ESCAPE);
			r.delay(7000);
			r.keyPress(KeyEvent.VK_ESCAPE);
			r.delay(1000);

		}

	}

	private static void emiteBoleto() {
		//lista de clientes não emite boleto
		String[] cliSemBol = {"1817", "1818", "1819", "1820", "1821", "1822", "1823", "1824", "1825",
				"1124","1677","1665","1488","1455"};
		Boolean emite = true;
		for(int i=0; i<cliSemBol.length;i++)
			if (cliSemBol[i].equals(p.getCodCliente()))
				emite = false;
		if (emite)
			emiteBoleto(p, tipoFaturamento);
	}

	private static void salvarPDF(Pedido p) {
		try {
			String diretorio = null;
			Robo r = new Robo();
			r.delay(3000);
			r.keyType("Nota Fiscal");
			r.keyPress(KeyEvent.VK_F4);
			r.delay(2000);
			r.keyPress(KeyEvent.VK_ESCAPE);
			r.setAutoDelay(10);			
			
			Utilitarios.copiarParaAreaTransferencia(diretorio);			
			
			r.hotKey(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
			r.setAutoDelay(50);
			r.delay(2000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.delay(500);
			r.hotKey(KeyEvent.VK_ALT, KeyEvent.VK_L);
			r.delay(2000);
			
		} catch (Exception e) {		
			e.printStackTrace();
			
		}

	}

	private static boolean isConsumidorFinal(Pedido p) {
		if (p.getIe().equals("") || p.getIe().equals("ISENTO"))
			return true;
		else
			return false;
	}

}
