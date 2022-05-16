package controller;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

public class Utilitarios {
	public static void copiarParaAreaTransferencia(String s) {
		try {
			StringSelection stringSelection = new StringSelection(s);
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
			
			System.out.println("Copiado para Area de Transferencia - " + s);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	private static void criaDiretorio(String caminhoPasta) {
		try {
			Path path = Paths.get(caminhoPasta);
		    if (!Files.exists(path)) {
		    	
		    	Files.createDirectory(path);
		    	System.out.println("New Directory created !   " + caminhoPasta);
		    } 
		    else {	    
		    	System.out.println("Directory already exists");
		    }
		    		}
		catch (Exception e) {			
			e.printStackTrace();			
		}
	    
	}

}
