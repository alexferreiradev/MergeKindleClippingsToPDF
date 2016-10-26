/**
 * 
 */
package main.manageclip;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import model.Anotation;

/**
 * Implementa métodos para gerencimento de arquivos Clip para o Kindle
 * 
 * @author Alex
 *
 */
public class ManageKindleClip implements ManageClip<Anotation> {

	private static final String CLIP_REGISTER_DELIMETER = "=======";
	private static final String HIGHLIGHT_TYPE_TAG = "Seu destaque - ";
	private static final String HIGHLIGHT_DELIMITER_FROM_POSITION = " - ";
	private static final String POSITION_AUTHOR_DELIMETER = "(";
	private static final String NEWLINE_DELIMETER = "\n";

	/* (non-Javadoc)
	 * @see main.manageclip.ManageClip#createAnotationsFromPDF(java.lang.String, java.io.File)
	 */
	@Override
	public List<Anotation> extractInfoFromClipFile(String pdfNameFile, File clipFile) throws FileNotFoundException {
		List<Anotation> anotations = new ArrayList<>();
		Scanner scanner = new Scanner(clipFile);
		scanner.useDelimiter(CLIP_REGISTER_DELIMETER);
		while (scanner.hasNext()) {
			String string = (String) scanner.next();
			StringTokenizer tokenizer = new StringTokenizer(string, NEWLINE_DELIMETER);
			if (tokenizer.hasMoreTokens() && tokenizer.nextToken().equals(pdfNameFile)){
				if (tokenizer.hasMoreTokens() && tokenizer.nextToken(HIGHLIGHT_DELIMITER_FROM_POSITION).startsWith(HIGHLIGHT_TYPE_TAG)){
					String position = tokenizer.nextToken(POSITION_AUTHOR_DELIMETER);
					String author = tokenizer.nextToken();
					String text = tokenizer.nextToken();
					Anotation anotation = new Anotation(pdfNameFile, position, author, text);
					anotations.add(anotation);
				}
			}			
		}
		scanner.close();
		
		return anotations;
	}
}
