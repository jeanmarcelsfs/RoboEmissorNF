package controller;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	
	private static final int LOGISTICA = 0;
	private static final int NF_IMPRESSA = 1;
	private static final int AOYAMA = 2;
	private static final int DAKI = 3;
	private static final int FINALIZAR_SYS = 4;
	
	public static final int PC_SERV = 0;
	public static final int PC_EVERTON = 1;
	public static final int PC_JEAN = 2;
	
	private static int computador = 3;//3 fecha sistema 
	private static boolean pdf = true;
		
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
			
			
			LoginSistema.logarSistema("valente","1234");
			
			
			switch (tipo_faturamento) {
			case LOGISTICA:
				pdf = true;
				EmiteNF.emiteNF(pdf, computador);
				break;
			case NF_IMPRESSA:
				pdf = false;
				EmiteNF.emiteNF(pdf, computador);
				break;
			case AOYAMA:
				JOptionPane.showMessageDialog(jFrame, "Não Implementado!");
				jFrame.dispose();
				System.exit(0);
				break;
			case DAKI:				
				PedidosDaki.emiteDaki(computador);
				break;
			case 4:
				jFrame.dispose();
				System.exit(0);
				break;
			
			}								
			JOptionPane.showMessageDialog(jFrame, "Emissão Concluida");
			jFrame.dispose();
		}
		catch (Exception ex){
			JOptionPane.showMessageDialog(null, "Erro no Main\n"+ex);
		}
	}
	
}// Fim da Classe
