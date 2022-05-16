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
			
			JOptionPane.showMessageDialog(null, "Antes de Continuar, verifique a Impressora Pa");
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
			
			if (tipo_faturamento == Main.FINALIZAR_SYS)
				System.exit(0);
			
			//se for Daki, fazer somente os pedidos
			if (tipo_faturamento == Main.DAKI) {
				emiteDaki();
			} else {
				
				ImagineRobot rb = new ImagineRobot();
				rb.setAutoDelay(100);
				loginSistema(rb, "valente","1234");
				String dirExcel;
				if (computador == Main.PC_SERV)
					dirExcel = "E:/";
				else
					dirExcel = "Z:/";
				dirExcel += "Faturamento/_faturar.xls";
				List<Pedido> listaPedidos = ImagineExcel.readXLS(dirExcel);
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
			ImagineRobot r = new ImagineRobot();
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
			List<Pedido> lsDaki = ImagineExcel.readDAKI(dirDaki);
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
				ImagineUtil.copyToTransferArea(lsDaki.get(i).getCodCliente());
				r.hotKey(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyPress(KeyEvent.VK_ENTER);
				r.delay(2000);
				
				//Clique na Observação do Pedido				
				
				r.mouseClick(MousePosition.getObsPedX(computador), MousePosition.getObsPedY(computador), InputEvent.BUTTON1_DOWN_MASK);
				ImagineUtil.copyToTransferArea(lsDaki.get(i).getNumPedido());
				
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
			ImagineRobot r = new ImagineRobot();
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

	private static boolean loginSistema(ImagineRobot rb, String usuario, String senha) {
		try {
			
			rb.delay(1000);
			rb.keyPress(KeyEvent.VK_WINDOWS);
			rb.delay(1000);
			rb.keyType("INFOSIS G");
			rb.delay(3000);
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.delay(10000);
			rb.setAutoDelay(10);
			rb.keyType(usuario);
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyType(senha);
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.delay(3000);
			return true;
		}
		catch (Exception ex) {
			return false;
		}
	}	
	
	private static boolean abreEmissor(ImagineRobot r) {
		try {
			r.keyPress(KeyEvent.VK_F11);
			r.delay(7000);
			r.keyPress(KeyEvent.VK_ESCAPE);
			r.delay(3000);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private static boolean criaDiretorio(String caminhoPasta) {
		try {
			Path path = Paths.get(caminhoPasta);
		    if (!Files.exists(path)) {
		    	
		    	Files.createDirectory(path);
		    	System.out.println("New Directory created !   " + caminhoPasta);
		    } 
		    else {	    
		    	System.out.println("Directory already exists");
		    }
		    return true;
		}
		catch (Exception e) {
			// TODO: handle exception7
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
			return false;
		}
	    
	}

	private static boolean salvarPDF(String diretorio) {
		try {
			ImagineRobot r = new ImagineRobot();
			r.delay(3000);
			r.keyType("Nota Fiscal");
			r.keyPress(KeyEvent.VK_F4);
			r.delay(2000);
			r.keyPress(KeyEvent.VK_ESCAPE);
			r.setAutoDelay(10);
			
			//Copia para area de transferencia
			StringSelection stringSelection = new StringSelection(diretorio);
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);	
			
			//r.keyType(diretorio);
			r.hotKey(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
			r.setAutoDelay(50);
			r.delay(2000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.delay(500);
			r.hotKey(KeyEvent.VK_ALT, KeyEvent.VK_L);
			r.delay(2000);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	private static boolean emiteNotaFiscal(Pedido p, String diretorio, int tipoFaturamento) {
		try {
			ImagineRobot r = new ImagineRobot();
			r.delay(1000);
			r.keyPress(KeyEvent.VK_F10);
			r.delay(300);
			r.keyPress(KeyEvent.VK_F8);
			r.keyType(p.getNumPedido());
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyPress(KeyEvent.VK_ENTER);
			r.delay(3000);
			r.keyPress(KeyEvent.VK_ESCAPE);
			r.hotKey(KeyEvent.VK_ALT, KeyEvent.VK_M);
			
			
			
			StringSelection stringSelection = new StringSelection(p.getObs() + " - ");
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);			
		    r.hotKey(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
			
			
			
			
			//verifica se é consumidor final
			if(p.getIe().equals("") || p.getIe().equals("ISENTO"))
				r.mouseClick(MousePosition.getConsumidorX(computador), MousePosition.getConsumidorY(computador), InputEvent.BUTTON1_DOWN_MASK);
			
			//envia NF
			r.mouseClick(MousePosition.getEnviaNFX(computador), MousePosition.getEnviaNFY(computador), InputEvent.BUTTON1_DOWN_MASK);
			r.delay(2000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyPress(KeyEvent.VK_ENTER);
			r.delay(10000);
			r.hotKey(KeyEvent.VK_ALT, KeyEvent.VK_I);
			r.delay(1500);
			r.keyPress(KeyEvent.VK_ENTER);
			r.delay(3000);
			if(tipoFaturamento == Main.PDF)
				salvarPDF(diretorio);			
			r.keyPress(KeyEvent.VK_ESCAPE);
			r.delay(7000);
			r.keyPress(KeyEvent.VK_ESCAPE);
			r.delay(1000);
			
			//lista de clientes não emite boleto
			String[] cliSemBol = {"1817", "1818", "1819", "1820", "1821", "1822", "1823", "1824", "1825",
					"1124","1677","1665","1488","1455"};
			Boolean emite = true;
			for(int i=0; i<cliSemBol.length;i++)
				if (cliSemBol[i].equals(p.getCodCliente()))
					emite = false;
			if (emite)
				emiteBoleto(p, tipoFaturamento);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}	

	private static boolean emiteBoleto(Pedido p, int tipoFaturamento) {
		try {
			ImagineRobot r = new ImagineRobot();			
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
