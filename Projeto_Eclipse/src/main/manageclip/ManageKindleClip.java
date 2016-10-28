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

	public static final String CLIP_REGISTER_DELIMETER = "==========\r\n";
	public static final String HIGHLIGHT_TYPE_TAG = "- Seu destaque";
	public static final String HIGHLIGHT_POSITION_DELIMITER = " ou ";
	public static final String PDF_AUTHOR_DELIMETER = " ";
	public static final String POSITION_TIME_DELIMETER = " \\| ";
	public static final String NEWLINE_DELIMETER = "\r\n";

	/* (non-Javadoc)
	 * @see main.manageclip.ManageClip#createAnotationsFromPDF(java.lang.String, java.io.File)
	 */
	@Override
	public List<Anotation> extractInfoFromClipFile(String pdfNameFile, File clipFile) throws FileNotFoundException {
//		if (pdfNameFile.endsWith(".pdf"))
//			pdfNameFile = Pattern.compile("(.*)\\.pdf").matcher(pdfNameFile).group();
		List<Anotation> anotations = new ArrayList<>();
		Scanner scanner = new Scanner(clipFile, "utf-8");
		scanner.useDelimiter(CLIP_REGISTER_DELIMETER);
		while (scanner.hasNext()) {
			String string = scanner.next();
			StringTokenizer tokenizer = new StringTokenizer(string, NEWLINE_DELIMETER, false);
			String pdfName = tokenizer.nextToken(PDF_AUTHOR_DELIMETER);
			String author = tokenizer.nextToken(" "+NEWLINE_DELIMETER);
			if (tokenizer.hasMoreTokens() && pdfName.equals(pdfNameFile)){
				String[] split = tokenizer.nextToken(NEWLINE_DELIMETER).split(HIGHLIGHT_POSITION_DELIMITER);
				String highlightTag = split[0];
				if (tokenizer.hasMoreTokens() && highlightTag.startsWith(HIGHLIGHT_TYPE_TAG)){
					String position = split[1].split(POSITION_TIME_DELIMETER)[0]; 
					String text = tokenizer.nextToken(NEWLINE_DELIMETER);
					Anotation anotation = new Anotation(pdfNameFile, position, author, text);
					anotations.add(anotation);
				}
			}			
		}
		scanner.close();
		
		return anotations;
	}
}
