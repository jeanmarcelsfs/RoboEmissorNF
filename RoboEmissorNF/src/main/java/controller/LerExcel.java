
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

import model.Pedido;

public class LerExcel {
	public static List<Pedido> readXLS(String filePath) {
		try {
			List<Pedido> listaPedidos = new ArrayList<Pedido>();		
			FileInputStream file = new FileInputStream(new File(filePath)); 
		    HSSFWorkbook workbook = new HSSFWorkbook(file);
		    HSSFSheet sheet = workbook.getSheetAt(0);	
		    //pula as 2 primeiras linhas
			for (int i=2; i <= sheet.getLastRowNum(); i++){
				Pedido p = new Pedido();
				DataFormatter formatter = new DataFormatter();
				p.setNumPedido(String.format("%.0f", sheet.getRow(i).getCell(0).getNumericCellValue()));
				p.setCodCliente(String.format("%.0f", sheet.getRow(i).getCell(5).getNumericCellValue()));
				p.setNomeCliente(formatter.formatCellValue(sheet.getRow(i).getCell(6)));
				p.setObs(formatter.formatCellValue(sheet.getRow(i).getCell(8)));
				p.setIe(formatter.formatCellValue(sheet.getRow(i).getCell(38)));
				
				listaPedidos.add(p);
				
		        System.out.println("Pedido: "+p.getNumPedido()+" / "
		        		+ "Cod_cli: "+p.getCodCliente()+" / "
		        				+ "Cliente: "+p.getNomeCliente()+ " / "
		        						+ "Obs: "+p.getObs()+" / "
		        								+ "IE: "+p.getIe());
		        
		    }
			return listaPedidos;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//
			return null;
		}
	}
	
	
	public static List<Pedido> readDAKI(String filePath) {
		try {
			List<Pedido> listaPedidos = new ArrayList<Pedido>();		
			FileInputStream file = new FileInputStream(new File(filePath)); 
		    HSSFWorkbook workbook = new HSSFWorkbook(file);
		    HSSFSheet sheet = workbook.getSheetAt(0);	
		    //pula as 2 primeiras linhas
			for (int i=2; i <= sheet.getLastRowNum(); i++){
				Pedido p = new Pedido();
				DataFormatter formatter = new DataFormatter();				
				p.setNumPedido(formatter.formatCellValue(sheet.getRow(i).getCell(0)));
				p.setCodCliente(formatter.formatCellValue(sheet.getRow(i).getCell(3)));
				p.setQtde(formatter.formatCellValue(sheet.getRow(i).getCell(7)));
				
				listaPedidos.add(p);
				
		        System.out.println("Pedido: "+p.getNumPedido()+" / "
		        		+ "Cod_cli: "+p.getCodCliente()+" / ");
		        
		    }
			return listaPedidos;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

}
