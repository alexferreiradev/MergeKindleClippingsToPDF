package managepdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
	 * @throws FileNotFoundException - Pdf nao foi encontrado
	 * @throws IOException - Erro ao tentar salvar PDF com anota��es
	 */
	public File createPDFWithInfo(List<T> objects, File pdfFile) throws FileNotFoundException, IOException;

}
