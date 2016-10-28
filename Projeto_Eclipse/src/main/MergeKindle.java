/**
 * 
 */
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
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
	
	public static final String DEFUALT_MY_CLIPPINGS_TXT_NAME = "My Clippings.txt";
	private static final Logger L = Logger.getLogger("Merge Kindle Project", null);

	/**
	 * @param args - argumentos v�lidos s�o:
	 * 	<nome do pdf sem extensao>
	 */
	public static void main(String[] args) {
		//TODO add extra��o de argumentos
		
		ManageKindleClip manageKindleClip = new ManageKindleClip();
		ManageAnotationToPDF maToPDF = new ManageAnotationToPDF();
		String pdfNameFile = args[1];
		String pdfNameFileExt = pdfNameFile.concat(".pdf");
		
		try {
			File clipFile = new File(MergeKindle.class.getResource(DEFUALT_MY_CLIPPINGS_TXT_NAME).toURI());
			File pdfFile = new File(MergeKindle.class.getResource(""+pdfNameFileExt).toURI());
			
			List<Anotation> anotations = manageKindleClip.extractInfoFromClipFile(pdfNameFile, clipFile);
			File createPDFWithInfo = maToPDF.createPDFWithInfo(anotations, pdfFile);
			if (createPDFWithInfo != null)
				L.log(Level.ALL, "Foi realizado o merge do PDF com sucesso.");
			else
				L.log(Level.ALL, "Algum erro ocorreu, o PDF n�o foi gerado.");
		} catch (FileNotFoundException e) {
			L.log(Level.SEVERE, "N�o foi encontrado o arquivo clip", e);
		} catch (NoSuchElementException e) {
			L.log(Level.SEVERE, "Algo est� errado com a cria��o de anota��es.");
		} catch (IOException e) {
			L.log(Level.SEVERE, "Algo est� errado com a cria��o do PDF com anota��es");
		} catch (URISyntaxException e) {
			L.log(Level.SEVERE, "URI mal gerada para File", e);
		}
	}

}
