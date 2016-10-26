package main.managepdf;

import java.io.File;
import java.util.List;

public interface ManageBasePDF {
	
	/**
	 * Cria um PDF com anotações.
	 * @param anotations - lista de anotações a serem adicionadas a um PDF
	 * @param pdfFile - O pdf sem as anotações
	 * @return pdf com anotações
	 */
	public File createPDFWithAnotations(List<?> anotations, File pdfFile);

}
