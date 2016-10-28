/**
 * 
 */
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import main.manageclip.ManageKindleClip;
import main.managepdf.ManageAnotationToPDF;
import model.Anotation;

/**
 * Realiza merge de anotações do Kindle em arquivos PDF extraídos do mesmo.
 * 
 * @author Alex
 *
 */
public class MergeKindle {
	
	public static final String DEFUALT_MY_CLIPPINGS_TXT_NAME = "My Clippings.txt";
	private static final Logger L = Logger.getLogger("Merge Kindle Project", null);

	/**
	 * @param args - argumentos válidos são:
	 * 	<nome do pdf sem extensao>
	 */
	public static void main(String[] args) {
		Handler handler = new StreamHandler(System.out, new SimpleFormatter());
		L.addHandler(handler);
		L.setUseParentHandlers(false);
		L.setLevel(Level.INFO);
		L.log(Level.INFO, "Merge está abrindo arquivos e extraindo anotações.");
		//TODO add extração de argumentos
		
		ManageKindleClip manageKindleClip = new ManageKindleClip();
		ManageAnotationToPDF maToPDF = new ManageAnotationToPDF();
		String pdfNameFile = args[0];
		String pdfNameFileExt = pdfNameFile.concat(".pdf");
		
		try {
			File clipFile = new File("./"+DEFUALT_MY_CLIPPINGS_TXT_NAME);
			File pdfFile = new File("./"+pdfNameFileExt);
			
			List<Anotation> anotations = manageKindleClip.extractInfoFromClipFile(pdfNameFile, clipFile);
			File createPDFWithInfo = maToPDF.createPDFWithInfo(anotations, pdfFile);
			if (createPDFWithInfo != null)
				L.log(Level.INFO, "Foi realizado o merge do PDF com sucesso.");
			else
				L.log(Level.INFO, "Algum erro ocorreu, o PDF não foi gerado.");
		} catch (FileNotFoundException e) {
			L.log(Level.SEVERE, "Erro ao tentar abrir arquivos: "+e.getMessage(), e);
		} catch (NoSuchElementException e) {
			L.log(Level.SEVERE, "Erro ao tentar extrair anotações: "+e.getMessage());
		} catch (IOException e) {
			L.log(Level.SEVERE, "Erro ao criar PDF com anotações: "+e.getMessage());
		}
	}

}
