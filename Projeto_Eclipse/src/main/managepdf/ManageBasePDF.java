package main.managepdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Define funções para gerenciar em um PDF.
 * @author Alex
 *
 * @param <T> - Tipo da informação a ser gerenciada.
 */
public interface ManageBasePDF<T>{
	
	/**
	 * Cria um PDF com anotações.
	 * @param infos - lista de anotações a serem adicionadas a um PDF
	 * @param pdfFile - O pdf sem as anotações
	 * @return pdf com anotações
	 * @throws FileNotFoundException - Pdf nao foi encontrado
	 */
	public File createPDFWithInfo(List<T> objects, File pdfFile) throws FileNotFoundException;

}
