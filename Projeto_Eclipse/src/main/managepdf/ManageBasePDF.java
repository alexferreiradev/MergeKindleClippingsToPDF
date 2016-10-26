package main.managepdf;

import java.io.File;
import java.util.List;

public interface ManageBasePDF {
	
	/**
	 * Cria um PDF com anota��es.
	 * @param anotations - lista de anota��es a serem adicionadas a um PDF
	 * @param pdfFile - O pdf sem as anota��es
	 * @return pdf com anota��es
	 */
	public File createPDFWithAnotations(List<?> anotations, File pdfFile);

}
