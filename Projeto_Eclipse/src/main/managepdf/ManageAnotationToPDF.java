/**
 * 
 */
package main.managepdf;

import java.io.File;
import java.util.List;

import model.Anotation;

/**
 * Gerencia anotações em PDF.
 * @author Alex
 *
 */
public class ManageAnotationToPDF implements ManageBasePDF<Anotation> {

	private static final String MERGED_PDF_PREFIX_NAME = "merged_";

	/* (non-Javadoc)
	 * @see main.managepdf.ManageBasePDF#createPDFWithAnotations(java.util.List, java.io.File)
	 */
	@Override
	public File createPDFWithInfo(List<Anotation> anotations, File pdfFile) {
		File newPdfFile = new File(pdfFile.getPath().concat(MERGED_PDF_PREFIX_NAME).concat(pdfFile.getName()));
		for (Anotation anotation : anotations) {
			// Open PDF
			// Busca pagina pelo a.position
			String position = anotation.getPosition();
			// Busca texto e cria a anotaçao
			String text = anotation.getText();
		}
		return newPdfFile;
	}

}
