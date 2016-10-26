package main.manageclip;

import java.io.File;
import java.util.List;

public interface ManageClip {
	
	/**
	 * Cria uma lista de anotações que referem a um PDF encontradas no arquivo que contém todas marcações.
	 * @param pdfNameFile - Pdf 
	 * @param clipFile
	 * @return
	 */
	public List<?> createAnotationsFromPDF(String pdfNameFile, File clipFile);

}
