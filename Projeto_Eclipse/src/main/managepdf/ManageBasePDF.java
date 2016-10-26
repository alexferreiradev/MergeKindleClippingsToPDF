package main.managepdf;

import java.io.File;
import java.util.List;

/**
 * Define fun��es para gerenciar em um PDF.
 * @author Alex
 *
 * @param <T> - Tipo da informa��o a ser gerenciada.
 */
public interface ManageBasePDF<T>{
	
	/**
	 * Cria um PDF com anota��es.
	 * @param infos - lista de anota��es a serem adicionadas a um PDF
	 * @param pdfFile - O pdf sem as anota��es
	 * @return pdf com anota��es
	 */
	public File createPDFWithInfo(List<T> objects, File pdfFile);

}
