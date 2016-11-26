/**
 * 
 */
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import manageclip.ManageKindleClip;
import managepdf.ManageAnotationToPDF;
import model.Anotation;
import util.CustomLogFormatter;

/**
 * Realiza merge de anota��es do Kindle em arquivos PDF extra�dos do mesmo.
 * 
 * @author Alex
 *
 */
public class MergeKindle {
	
	public static final String DEFUALT_MY_CLIPPINGS_TXT_NAME = "My Clippings.txt";
	private static final Logger L = Logger.getGlobal();

	/**
	 * @param args - argumentos v�lidos s�o:
	 * 	<nome do pdf sem extensao>
	 */
	public static void main(String[] args) {
		Handler handler = new ConsoleHandler();
		handler.setFormatter(new CustomLogFormatter());
		L.addHandler(handler);
		L.setUseParentHandlers(false);
		L.setLevel(Level.INFO);
		L.log(Level.INFO, "Merge est� abrindo arquivos e extraindo anota��es.");
		//TODO add extra��o de argumentos
		
		ManageKindleClip manageKindleClip = new ManageKindleClip();
		ManageAnotationToPDF maToPDF = new ManageAnotationToPDF();
		String pdfNameFile = args[0];
		Matcher matcher = Pattern.compile("(.*).pdf").matcher(pdfNameFile);
		if (matcher.find())
			pdfNameFile = matcher.group(1);
		String pdfNameFileExt = pdfNameFile.concat(".pdf");
		
		try {
			File clipFile = new File("./"+DEFUALT_MY_CLIPPINGS_TXT_NAME);
			File pdfFile = new File("./"+pdfNameFileExt);
			
			List<Anotation> anotations = manageKindleClip.extractInfoFromClipFile(pdfNameFile, clipFile);
			if (anotations.size() == 0){
				Logger.getGlobal().log(Level.WARNING, "N�o foi encontrado nenhuma anota��o para o PDF: "+pdfNameFile);
				return;
			}else
				Logger.getGlobal().log(Level.INFO, "Foi encontrado "+anotations.size()+" anota��es.");
			
			File createPDFWithInfo = maToPDF.createPDFWithInfo(anotations, pdfFile);
			if (createPDFWithInfo != null)
				L.log(Level.INFO, "Foi realizado o merge do PDF com sucesso.");
			else
				L.log(Level.INFO, "Algum erro ocorreu, o PDF n�o foi gerado.");
		} catch (FileNotFoundException e) {
			L.log(Level.SEVERE, "Erro ao tentar abrir arquivos: ", e);
		} catch (NoSuchElementException e) {
			L.log(Level.SEVERE, "Erro ao tentar extrair anota��es: ", e);
		} catch (IOException e) {
			L.log(Level.SEVERE, "Erro ao criar PDF com anota��es: ", e);
		}
	}

}
