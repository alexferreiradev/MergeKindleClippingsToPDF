/**
 * 
 */
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.manageclip.ManageKindleClip;
import main.managepdf.ManageAnotationToPDF;
import model.Anotation;

/**
 * Realiza merge de anota��es do Kindle em arquivos PDF extra�dos do mesmo.
 * 
 * @author Alex
 *
 */
public class MergeKindle {
	
	private static final Logger L = Logger.getLogger("Merge Kindle Project", MergeKindle.class.getSimpleName());

	/**
	 * @param args - argumentos v�lidos s�o:
	 * 	-pdf <nome do pdf>
	 *  -clipfile <path/nome do arquivo clip>
	 */
	public static void main(String[] args) {
		//TODO add extra��o de argumentos
		
		ManageKindleClip manageKindleClip = new ManageKindleClip();
		ManageAnotationToPDF maToPDF = new ManageAnotationToPDF();
		File clipFile = new File("MyClippings.txt");
		String pdfNameFile = "teste";
		
		try {
			List<Anotation> anotations = manageKindleClip.extractInfoFromClipFile(pdfNameFile, clipFile);
			File pdfFile = new File(pdfNameFile);
			maToPDF.createPDFWithInfo(anotations, pdfFile);
			L.log(Level.ALL, "Foi realizado o merge do PDF com sucesso.");
		} catch (FileNotFoundException e) {
			L.log(Level.SEVERE, "N�o foi encontrado o arquivo clip", e);
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			L.log(Level.SEVERE, "Algo est� errado com a cria��o de anota��es");
		}
	}

}
