package controller;

import java.awt.MouseInfo;

import javax.swing.JOptionPane;

public class MousePosition {	
	
	//Everton
	private static final int ENVIA_NF_EVERTON_X = 1412;
	private static final int ENVIA_NF_EVERTON_Y = 293;
	
	private static final int CONSUMIDOR_EVERTON_X = 598;
	private static final int CONSUMIDOR_EVERTON_Y = 326;
	
	private static final int IMP_PED_EVERTON_X = 225;
	private static final int IMP_PED_EVERTON_Y = 46;
	
	private static final int OBS_PED_EVERTON_X = 60;
	private static final int OBS_PED_EVERTON_Y = 903;
	//Servidor
	private static final int ENVIA_NF_SRV_X = 1130;
	private static final int ENVIA_NF_SRV_Y = 136;
	
	private static final int CONSUMIDOR_SRV_X = 321;
	private static final int CONSUMIDOR_SRV_Y = 171;
	
	private static final int IMP_PED_SRV_X = 225;
	private static final int IMP_PED_SRV_Y = 43;
	
	private static final int OBS_PED_SRV_X = 94;
	private static final int OBS_PED_SRV_Y = 581;
	
	
	public static int getEnviaNFX(int computador) {
		switch (computador) {
			case Main.PC_EVERTON: {
				return ENVIA_NF_EVERTON_X;
			}
			case Main.PC_SERV:{
				return ENVIA_NF_SRV_X;
			}
			case Main.PC_JEAN:{
				return ENVIA_NF_SRV_X;
			}
			default:{
				throw new IllegalArgumentException("Unexpected value: " + computador);
			}
	
		}
	}
	
	public static int getEnviaNFY(int computador) {
		switch (computador) {
			case Main.PC_EVERTON: {
				return ENVIA_NF_EVERTON_Y;
			}
			case Main.PC_SERV:{
				return ENVIA_NF_SRV_Y;
			}
			case Main.PC_JEAN:{
				return ENVIA_NF_SRV_Y;
			}
			default:{
				throw new IllegalArgumentException("Unexpected value: " + computador);
			}
	
		}
	}
	
	public static int getConsumidorX(int computador) {
		switch (computador) {
			case Main.PC_EVERTON: {
				return CONSUMIDOR_EVERTON_X;
			}
			case Main.PC_SERV:{
				return CONSUMIDOR_SRV_X;
			}
			case Main.PC_JEAN:{
				return CONSUMIDOR_SRV_X;
			}
			default:{
				throw new IllegalArgumentException("Unexpected value: " + computador);
			}
	
		}
	}
	
	public static int getConsumidorY(int computador) {
		switch (computador) {
			case Main.PC_EVERTON: {
				return CONSUMIDOR_EVERTON_Y;
			}
			case Main.PC_SERV:{
				return CONSUMIDOR_SRV_Y;
			}
			case Main.PC_JEAN:{
				return CONSUMIDOR_SRV_Y;
			}
			default:{
				throw new IllegalArgumentException("Unexpected value: " + computador);
			}
	
		}
	}
	
	
	public static int getImpPedX(int computador) {
		switch (computador) {
			case Main.PC_EVERTON: {
				return IMP_PED_EVERTON_X;
			}
			case Main.PC_SERV:{
				return IMP_PED_SRV_X;
			}
			case Main.PC_JEAN:{
				return IMP_PED_SRV_X;
			}
			default:{
				throw new IllegalArgumentException("Unexpected value: " + computador);
			}
	
		}
	}
	
	public static int getImpPedY(int computador) {
		switch (computador) {
			case Main.PC_EVERTON: {
				return IMP_PED_EVERTON_Y;
			}
			case Main.PC_SERV:{
				return IMP_PED_SRV_Y;
			}
			case Main.PC_JEAN:{
				return IMP_PED_SRV_Y;
			}
			default:{
				throw new IllegalArgumentException("Unexpected value: " + computador);
			}
	
		}
	}
	
	public static int getObsPedX(int computador) {
		switch (computador) {
			case Main.PC_EVERTON: {
				return OBS_PED_EVERTON_X;
			}
			case Main.PC_SERV:{
				return OBS_PED_SRV_X;
			}
			case Main.PC_JEAN:{
				return OBS_PED_SRV_X;
			}
			default:{
				throw new IllegalArgumentException("Unexpected value: " + computador);
			}
	
		}
	}
	
	public static int getObsPedY(int computador) {
		switch (computador) {
			case Main.PC_EVERTON: {
				return OBS_PED_EVERTON_Y;
			}
			case Main.PC_SERV:{
				return OBS_PED_SRV_Y;
			}
			case Main.PC_JEAN:{
				return OBS_PED_SRV_Y;
			}
			default:{
				throw new IllegalArgumentException("Unexpected value: " + computador);
			}
	
		}
	}
	
	
	
	
	
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "POSICIONE O MOUSE NO LOCAL DESEJADO E AGUARDE 5 SEGUNDOS"
				+ "\n PARA OBTER A POSIÇÃO X / Y");
		ImagineRobot r = new ImagineRobot();
		r.delay(5000);
		String posicao = "Posicao X=";
		posicao += MouseInfo.getPointerInfo().getLocation().getX();
		posicao += " / Y=";
		posicao += MouseInfo.getPointerInfo().getLocation().getY();
		JOptionPane.showMessageDialog(null, posicao);
	}
	
	
}
