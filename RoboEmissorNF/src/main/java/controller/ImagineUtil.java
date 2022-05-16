package controller;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ImagineUtil {
	public static void copyToTransferArea(String s) {
		try {
			StringSelection stringSelection = new StringSelection(s);
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
			
			System.out.println("Copiado para Area de Transferencia - " + s);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
