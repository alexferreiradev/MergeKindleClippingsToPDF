package manageclip;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Define fun��es para gerenciamento de arquivos Clip como o do Kindle, MyClippings.txt.
 * @author Alex
 *
 * @param <T> - Tipo da Informa��o. @see Anotation.
 */
public interface ManageClip<T>{
	
	/**
	 * Extrai informa��es de um arquivo de clips.
	 * @param pdfNameFile - Pdf 
	 * @param clipFile - arquivo que contem informa��es como marca��es, notas e outros.
	 * @return Lista
	 * @throws FileNotFoundException - quando n�o encontra o arquivo clipFile
	 */
	public List<T> extractInfoFromClipFile(String pdfNameFile, File clipFile) throws FileNotFoundException;

}
