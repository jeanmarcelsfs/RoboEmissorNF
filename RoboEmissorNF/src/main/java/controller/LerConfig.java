package controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import model.Pedido;

public class LerConfig {
	
	public static int getConsumidorX(int computador){
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
	}
	
	public static int getConsumidorY(int computador){
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
						pos = Integer.parseInt(dados[4]);
				}
								
			}
			return pos;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static int getEnviaNFX(int computador){
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
	}
	
	public static int getEnviaNFY(int computador){
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
	}
	
	public static int getImpressaoPedX(int computador){
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
	}
	
	public static int getImpressaoPedY(int computador){
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
	}
	
	public static int getObsPedX(int computador){
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
	}
	
	public static int getObsPedY(int computador){
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
	}
	
	public static boolean isClienteBoleto(Pedido p) {
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
	}

}
