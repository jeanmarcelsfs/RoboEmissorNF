package controller;

import java.awt.event.KeyEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;

import model.Pedido;

public class EmiteNF {
	public static void emiteNF(boolean pdf, int computador) {

		Emissor.abrirEmissorNF();
		List<Pedido> lista = LerExcel.readXLS(getDirExcel(computador));
		Robo r = new Robo();		
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
			//obs da NF
			r.hotKey(KeyEvent.VK_ALT, KeyEvent.VK_M);
			Utilitarios.copiarParaAreaTransferencia(lista.get(i).getObs());
			r.hotKey(KeyEvent.VK_CONTROL, KeyEvent.VK_V);

			if (isConsumidorFinal(lista.get(i)))
				r.mouseClick(LerConfig.getConsumidorX(computador), LerConfig.getConsumidorY(computador));

			// envia NF
			int posX = LerConfig.getEnviaNFX(computador);
			int posY = LerConfig.getEnviaNFY(computador);
			r.mouseClick(posX, posY);
			System.out.println("Clicou em "+posX+", "+posY);
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
				salvarPDF(lista.get(i), computador);
			else
				ImprimePedido.imprimePedido(lista.get(i), computador);
			
			r.keyPress(KeyEvent.VK_ESCAPE);
			r.delay(7000);
			r.keyPress(KeyEvent.VK_ESCAPE);
			r.delay(1000);
			emiteBoleto(lista.get(i), pdf);

		}
		Emissor.fecharEmissorNF();

	}

	private static String getDirExcel(int computador) {
		String dirExcel = null;
		if (computador == Main.PC_SERV)
			dirExcel = "E:/";
		else
			dirExcel = "Z:/";
		dirExcel += "Faturamento/_faturar.xls";
		return dirExcel;
	}

	private static String getDirPedido(Pedido p, int computador) {
		Calendar calendar = Calendar.getInstance();
		String dirPDF = null;
		if (computador == Main.PC_SERV)
			dirPDF = "E:/";
		else
			dirPDF = "Z:/";
		dirPDF += "Faturamento/Vendas/Frigorifico/";
		dirPDF += calendar.get(Calendar.YEAR) + "/" + formataMes() + "/";
		dirPDF += formataDia();
		dirPDF += "/" + p.getNomeCliente();
		dirPDF += " - " + p.getCodCliente();
		dirPDF += " - PED_" + p.getNumPedido();
		System.out.println("Caminho para criar: " + dirPDF);
		criaDiretorio(dirPDF);
		return dirPDF;
	}

	private static void emiteBoleto(Pedido p, boolean pdf) {
		try {
			if (isClienteBoleto(p)) {
				Robo r = new Robo();
				r.hotKey(KeyEvent.VK_ALT, KeyEvent.VK_O);
				r.delay(2000);
				r.keyPress(KeyEvent.VK_B);
				r.delay(4000);

				// r.keyPress(KeyEvent.VK_ENTER);// <<< TEMPORARIO, CAIXA ABERTO

				r.keyPress(KeyEvent.VK_F12);
				r.keyPress(KeyEvent.VK_F8);
				r.keyType(p.getNumPedido());
				r.keyPress(KeyEvent.VK_ENTER);
				r.delay(1000);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyPress(KeyEvent.VK_ENTER);
				r.delay(4000);
				r.hotKey(KeyEvent.VK_ALT, KeyEvent.VK_G);
				r.delay(5000);
				r.keyPress(KeyEvent.VK_ENTER);
				r.delay(5000);
				r.hotKey(KeyEvent.VK_ALT, KeyEvent.VK_I);
				r.delay(1000);
				r.keyPress(KeyEvent.VK_ENTER);
				r.delay(3000);
				if (pdf) {
					r.keyType("Boleto");
					r.keyPress(KeyEvent.VK_ENTER);
					r.delay(2000);
				}
				r.keyPress(KeyEvent.VK_ESCAPE);
				r.delay(1000);
				r.hotKey(KeyEvent.VK_ALT, KeyEvent.VK_F);
				r.delay(1000);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private static boolean isClienteBoleto(Pedido p) {
		return LerConfig.isClienteBoleto(p);
	}

	private static void salvarPDF(Pedido p, int computador) {
		try {
			String diretorio = getDirPedido(p, computador);
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

	private static String formataMes() {
		try {
			Calendar c = Calendar.getInstance();
			String s = null;
			int mes = c.get(Calendar.MONTH);
			switch (mes) {
			case 0:
				s = "01 - Janeiro";
				break;
			case 1:
				s = "02 - Fevereiro";
				break;
			case 2:
				s = "03 - Março";
				break;
			case 3:
				s = "04 - Abril";
				break;
			case 4:
				s = "05 - Maio";
				break;
			case 5:
				s = "06 - Junho";
				break;
			case 6:
				s = "07 - Julho";
				break;
			case 7:
				s = "08 - Agosto";
				break;
			case 8:
				s = "09 - Setembro";
				break;
			case 9:
				s = "10 - Outubro";
				break;
			case 10:
				s = "11 - Novembro";
				break;
			case 11:
				s = "12 - Dezembro";
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + mes);
			}
			return s;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	private static String formataDia() {
		try {
			Calendar c = Calendar.getInstance();
			String s = "";
			int dia = c.get(Calendar.DAY_OF_MONTH);
			for (int i = 1; i < 10; i++) {
				if (dia == i)
					s = "0";
			}
			s += dia;
			return s;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	private static void criaDiretorio(String caminhoPasta) {
		try {
			Path path = Paths.get(caminhoPasta);
			if (!Files.exists(path)) {

				Files.createDirectory(path);
				System.out.println("New Directory created !   " + caminhoPasta);
			} else {
				System.out.println("Directory already exists");
			}
		} catch (Exception e) {
			// TODO: handle exception7
			e.printStackTrace();

		}

	}

}
