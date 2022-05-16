package controller;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Pedido;

public class Main {
	
	private static final int PDF = 0;
	private static final int NOTA_IMPRESSA = 1;
	private static final int AOYAMA = 2;
	private static final int DAKI = 3;
	private static final int FINALIZAR_SYS = 4;
	
	public static final int PC_SERV = 0;
	public static final int PC_EVERTON = 1;
	public static final int PC_JEAN = 2;
	
	private static int computador = 3;//3 fecha sistema 
	
		
	public static void main(String[] args) {
		try {
			JFrame jFrame = new JFrame();
			jFrame.setLocationRelativeTo(null);
			jFrame.setVisible(true);
			
			JOptionPane.showMessageDialog(jFrame, "Verifique a Impressora Padrao");
			String[] opPc = {
					"1 - SERVIDOR\n",
					"2 - FATURAMENTO (Everton)\n",
					"3 - JEAN",
					"4 - FINALIZAR SISTEMA"
					};
			JComboBox jcPC = new JComboBox(opPc);
			JOptionPane.showMessageDialog(null, jcPC, "Selecione o Computador!"
					+ "", JOptionPane.QUESTION_MESSAGE);
			computador = jcPC.getSelectedIndex();
			if (computador == 3)
				System.exit(0);
			
			//seleciona a opção de faturamento
			//JOptionPane.showMessageDialog(null, "ATENÇÃO, ANTES DE CONTINUAR VERIFIQUE A IMPRESSORA PADRÃO");
			String[] options = {
					"1 - Somente PDF (Logistica)",
					"2 - Notas Impressas\n",
					"3 - Notas Aoyama\n",
					"4 - Emissão de Pedidos DAKI",
					"5 - CANCELAR"
					};
			JComboBox jc = new JComboBox(options);
			JOptionPane.showMessageDialog(null, jc, "SELECIONE 1 OPÇÃO", JOptionPane.QUESTION_MESSAGE);
			int tipo_faturamento = Main.FINALIZAR_SYS;
			tipo_faturamento = jc.getSelectedIndex();
			System.out.println("Opção selecionada no comboBox: "+tipo_faturamento);
			
			if (tipo_faturamento == Main.FINALIZAR_SYS) {
				jFrame.dispose();
				System.exit(0);				
			}
			
			LoginSistema.logarSistema("valente","1234");
			
			
			//se for Daki, fazer somente os pedidos
			if (tipo_faturamento == Main.DAKI) {
				emiteDaki();
			} else {	
				
				loginSistema(rb, "valente","1234");
				String dirExcel;
				if (computador == Main.PC_SERV)
					dirExcel = "E:/";
				else
					dirExcel = "Z:/";
				dirExcel += "Faturamento/_faturar.xls";
				List<Pedido> listaPedidos = LerExcel.readXLS(dirExcel);
				abreEmissor(rb);
				Calendar calendar = Calendar.getInstance();
				
				
				
				for (int i=0; i<listaPedidos.size(); i++) {
					String dirPDF;
					if (computador == Main.PC_SERV)
						dirPDF = "E:/";
					else
						dirPDF = "Z:/";
					dirPDF += "Faturamento/Vendas/Frigorifico/";
					dirPDF += calendar.get(Calendar.YEAR)+"/"+formataMes()+"/";
					dirPDF += formataDia();					
					dirPDF += "/"+listaPedidos.get(i).getNomeCliente();
					dirPDF += " - "+listaPedidos.get(i).getCodCliente();
					dirPDF += " - PED_"+listaPedidos.get(i).getNumPedido();
					System.out.println("Caminho para criar: "+dirPDF);
					
					if (tipo_faturamento == Main.PDF)
						criaDiretorio(dirPDF);	
					if (tipo_faturamento == Main.NOTA_IMPRESSA) {
						emitePedido(listaPedidos.get(i));
					}
					emiteNotaFiscal(listaPedidos.get(i), dirPDF, tipo_faturamento);
					
				}
				//fecha emissor
				rb.delay(2000);
				rb.hotKey(KeyEvent.VK_ALT, KeyEvent.VK_F);				
			}//else
			JOptionPane.showMessageDialog(null, "EMISSÃO CONCLUIDA");
		}
		catch (Exception ex){
			JOptionPane.showMessageDialog(null, "Erro no Main\n"+ex);
		}
	}
	
	private static void emiteDaki() {
		try {
			Robo r = new Robo();
			loginSistema(r, "VALENTE", "1234");
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
				Utilitarios.copyToTransferArea(lsDaki.get(i).getCodCliente());
				r.hotKey(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyPress(KeyEvent.VK_ENTER);
				r.delay(2000);
				
				//Clique na Observação do Pedido				
				
				r.mouseClick(MousePosition.getObsPedX(computador), MousePosition.getObsPedY(computador), InputEvent.BUTTON1_DOWN_MASK);
				Utilitarios.copyToTransferArea(lsDaki.get(i).getNumPedido());
				
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

	private static void emitePedido(Pedido pedido) {
		try {
			//fecha emissor
			Robo r = new Robo();
			r.hotKey(KeyEvent.VK_ALT, KeyEvent.VK_F);
			r.delay(1000);
			r.keyPress(KeyEvent.VK_F10);
			r.delay(8000);
			
			r.hotKey(KeyEvent.VK_ALT, KeyEvent.VK_Z);
			r.delay(500);
			r.keyType(pedido.getNumPedido());
			r.keyPress(KeyEvent.VK_ENTER);
			r.delay(1000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.delay(5000);
			
			//Clique no Imprimir Pedido
			
			r.mouseClick(MousePosition.getImpPedX(computador), MousePosition.getImpPedY(computador), InputEvent.BUTTON1_DOWN_MASK);
			r.delay(2000);
			r.keyPress(KeyEvent.VK_ESCAPE);
			
			//fecha janela vendas
			r.keyPress(KeyEvent.VK_ESCAPE);
			r.keyPress(KeyEvent.VK_ENTER);
			
			abreEmissor(r);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}	

	private static boolean emiteBoleto(Pedido p, int tipoFaturamento) {
		try {
			Robo r = new Robo();			
			r.hotKey(KeyEvent.VK_ALT, KeyEvent.VK_O);
			r.delay(2000);
			r.keyPress(KeyEvent.VK_B);
			r.delay(4000);
			
			//r.keyPress(KeyEvent.VK_ENTER);// <<< TEMPORARIO, CAIXA ABERTO
			
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
			if(tipoFaturamento == Main.PDF) {
				r.keyType("Boleto");
				r.keyPress(KeyEvent.VK_ENTER);
				r.delay(2000);
			}
			r.keyPress(KeyEvent.VK_ESCAPE);
			r.delay(1000);
			r.hotKey(KeyEvent.VK_ALT, KeyEvent.VK_F);
			r.delay(1000);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
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
				s = "06 - Junho/";
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
			for (int i =1; i<10; i++) {
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
}// Fim da Classe
