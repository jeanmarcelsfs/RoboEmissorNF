package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.Pedido;

public class LerConfig {
	
	public static int getConsumidorX(int computador){
		/*
		 * não esta realizando a leitura corretament
		 * 
		try {
			String pc = null;
			if (computador == Main.PC_EVERTON)
				pc = "fat";
			else if (computador == Main.PC_JEAN || computador == Main.PC_SERV)
				pc = "srv";
			FileInputStream fileInputStream = new FileInputStream(new File(".\\Config.txt"));
			Scanner scanner = new Scanner(fileInputStream, "UTF-8");
			int pos = 0;
			while(scanner.hasNext()) {
				String linha = scanner.nextLine();
				String[] dados = linha.split(".");
				if(dados[0].equals(pc)) {
					if (dados[1].equals("consumidor"))
						pos = Integer.parseInt(dados[3]);
				}
								
			}
			return pos;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		*/
		if (computador == Main.PC_EVERTON)
			return 598;
		else if (computador == Main.PC_JEAN || computador == Main.PC_SERV)
			return 321;
		else
			return 0;
		
	}
	
	public static int getConsumidorY(int computador){
		/*
		 * 
		 * try {
		 
			String pc = null;
			if (computador == Main.PC_EVERTON)
				pc = "fat";
			else if (computador == Main.PC_JEAN || computador == Main.PC_SERV)
				pc = "srv";
			FileInputStream fileInputStream = new FileInputStream(new File(".\\Config.txt"));
			Scanner scanner = new Scanner(fileInputStream, "UTF-8");
			int pos = 0;
			while(scanner.hasNext()) {
				String linha = scanner.nextLine();
				String[] dados = linha.split(".");
				if(dados[0].equals(pc)) {
					if (dados[1].equals("consumidor"))
						pos = Integer.parseInt(dados[4]);
				}
								
			}
			return pos;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		
		*/
		
		if (computador == Main.PC_EVERTON)
			return 326;
		else if (computador == Main.PC_JEAN || computador == Main.PC_SERV)
			return 171;
		else
			return 0;
	}
	
	public static int getEnviaNFX(int computador){
		/*
		 * 
		 * 
		 
		try {
			String pc = null;
			if (computador == Main.PC_EVERTON)
				pc = "fat";
			else if (computador == Main.PC_JEAN || computador == Main.PC_SERV)
				pc = "srv";
			FileInputStream fileInputStream = new FileInputStream(new File(".\\Config.txt"));
			Scanner scanner = new Scanner(fileInputStream, "UTF-8");
			int pos = 0;
			while(scanner.hasNext()) {
				String linha = scanner.nextLine();
				String[] dados = linha.split(".");
				if(dados[0].equals(pc)) {
					if (dados[1].equals("enviaNF"))
						pos = Integer.parseInt(dados[3]);
				}
								
			}
			return pos;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		*/
		if (computador == Main.PC_EVERTON)
			return 1412;
		else if (computador == Main.PC_JEAN || computador == Main.PC_SERV)
			return 1130;
		else
			return 0;
	}
	
	public static int getEnviaNFY(int computador){
		/*
		 * 
		 * 
		 
		try {
			String pc = null;
			if (computador == Main.PC_EVERTON)
				pc = "fat";
			else if (computador == Main.PC_JEAN || computador == Main.PC_SERV)
				pc = "srv";
			FileInputStream fileInputStream = new FileInputStream(new File(".\\Config.txt"));
			Scanner scanner = new Scanner(fileInputStream, "UTF-8");
			int pos = 0;
			while(scanner.hasNext()) {
				String linha = scanner.nextLine();
				String[] dados = linha.split(".");
				if(dados[0].equals(pc)) {
					if (dados[1].equals("enviaNF"))
						pos = Integer.parseInt(dados[4]);
				}
								
			}
			return pos;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		*/
		if (computador == Main.PC_EVERTON)
			return 293;
		else if (computador == Main.PC_JEAN || computador == Main.PC_SERV)
			return 136;
		else
			return 0;
	}
	
	public static int getImpressaoPedX(int computador){
		/*
		 * 
		 *
		
		try {
			String pc = null;
			if (computador == Main.PC_EVERTON)
				pc = "fat";
			else if (computador == Main.PC_JEAN || computador == Main.PC_SERV)
				pc = "srv";
			FileInputStream fileInputStream = new FileInputStream(new File(".\\Config.txt"));
			Scanner scanner = new Scanner(fileInputStream, "UTF-8");
			int pos = 0;
			while(scanner.hasNext()) {
				String linha = scanner.nextLine();
				String[] dados = linha.split(".");
				if(dados[0].equals(pc)) {
					if (dados[1].equals("imp_ped"))
						pos = Integer.parseInt(dados[3]);
				}
								
			}
			return pos;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		*/
		
		if (computador == Main.PC_EVERTON)
			return 225;
		else if (computador == Main.PC_JEAN || computador == Main.PC_SERV)
			return 225;
		else
			return 0;
	}
	
	public static int getImpressaoPedY(int computador){
		/*
		 * 
		 * 
		 
		try {
			String pc = null;
			if (computador == Main.PC_EVERTON)
				pc = "fat";
			else if (computador == Main.PC_JEAN || computador == Main.PC_SERV)
				pc = "srv";
			FileInputStream fileInputStream = new FileInputStream(new File(".\\Config.txt"));
			Scanner scanner = new Scanner(fileInputStream, "UTF-8");
			int pos = 0;
			while(scanner.hasNext()) {
				String linha = scanner.nextLine();
				String[] dados = linha.split(".");
				if(dados[0].equals(pc)) {
					if (dados[1].equals("imp_ped"))
						pos = Integer.parseInt(dados[4]);
				}
								
			}
			return pos;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		*/
		
		if (computador == Main.PC_EVERTON)
			return 46;
		else if (computador == Main.PC_JEAN || computador == Main.PC_SERV)
			return 43;
		else
			return 0;
	}
	
	public static int getObsPedX(int computador){
		
		/*
		 * 
		 * 
		 *
		try {
			String pc = null;
			if (computador == Main.PC_EVERTON)
				pc = "fat";
			else if (computador == Main.PC_JEAN || computador == Main.PC_SERV)
				pc = "srv";
			FileInputStream fileInputStream = new FileInputStream(new File(".\\Config.txt"));
			Scanner scanner = new Scanner(fileInputStream, "UTF-8");
			int pos = 0;
			while(scanner.hasNext()) {
				String linha = scanner.nextLine();
				String[] dados = linha.split(".");
				if(dados[0].equals(pc)) {
					if (dados[1].equals("obs_ped"))
						pos = Integer.parseInt(dados[3]);
				}
								
			}
			return pos;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		*/
		if (computador == Main.PC_EVERTON)
			return 60;
		else if (computador == Main.PC_JEAN || computador == Main.PC_SERV)
			return 94;
		else
			return 0;
	}
	
	public static int getObsPedY(int computador){
		/*
		 * 
		 * 
		 *
		
		try {
			String pc = null;
			if (computador == Main.PC_EVERTON)
				pc = "fat";
			else if (computador == Main.PC_JEAN || computador == Main.PC_SERV)
				pc = "srv";
			FileInputStream fileInputStream = new FileInputStream(new File(".\\Config.txt"));
			Scanner scanner = new Scanner(fileInputStream, "UTF-8");
			int pos = 0;
			while(scanner.hasNext()) {
				String linha = scanner.nextLine();
				String[] dados = linha.split(".");
				if(dados[0].equals(pc)) {
					if (dados[1].equals("obs_ped"))
						pos = Integer.parseInt(dados[4]);
				}
								
			}
			return pos;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		*/
		
		if (computador == Main.PC_EVERTON)
			return 903;
		else if (computador == Main.PC_JEAN || computador == Main.PC_SERV)
			return 581;
		else
			return 0;
	}
	
	public static boolean isClienteBoleto(Pedido p) {
		/*
		 * 
		 * 
		 *
		try {
			boolean resultado = true;
			FileInputStream fileInputStream = new FileInputStream(new File(".\\Config.txt"));
			Scanner scanner = new Scanner(fileInputStream, "UTF-8");
			int pos = 0;
			while(scanner.hasNext()) {
				String linha = scanner.nextLine();
				String[] dados = linha.split(",");
				for(int i = 0; i < dados.length; i++) {
					if (p.getCodCliente().equals(dados[i])) {
						resultado = false;
					}						
				}								
			}
			return resultado;
		}
		catch (Exception e) {
			return false;
		}
		*/
		
		boolean comBoleto = true;
		Integer lista[] = {1817,1818,1819,1820,1821,1822,1823,1824,1825,1124,1677,1665,1488,1455};
		for(int i=0; i<lista.length; i++) {
			if (p.getCodCliente().equals(lista[i].toString()))
				comBoleto = false;
		}
		return comBoleto;
	}

}
