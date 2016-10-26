package main.manageclip;

import java.io.File;
import java.util.List;

public interface ManageClip {
	
	/**
	 * Cria uma lista de anota��es que referem a um PDF encontradas no arquivo que cont�m todas marca��es.
	 * @param pdfNameFile - Pdf 
	 * @param clipFile
	 * @return
	 */
	public List<?> createAnotationsFromPDF(String pdfNameFile, File clipFile);

}
