package manageclip;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Define funções para gerenciamento de arquivos Clip como o do Kindle, MyClippings.txt.
 * @author Alex
 *
 * @param <T> - Tipo da Informação. @see Anotation.
 */
public interface ManageClip<T>{
	
	/**
	 * Extrai informações de um arquivo de clips.
	 * @param pdfNameFile - Pdf 
	 * @param clipFile - arquivo que contem informações como marcações, notas e outros.
	 * @return Lista
	 * @throws FileNotFoundException - quando não encontra o arquivo clipFile
	 */
	public List<T> extractInfoFromClipFile(String pdfNameFile, File clipFile) throws FileNotFoundException;

}
