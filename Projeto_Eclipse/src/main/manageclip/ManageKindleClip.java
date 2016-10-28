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
import java.util.regex.Pattern;

import model.Anotation;

/**
 * Implementa métodos para gerencimento de arquivos Clip para o Kindle
 * 
 * @author Alex
 *
 */
public class ManageKindleClip implements ManageClip<Anotation> {

	public static final String CLIP_REGISTER_DELIMETER = "==========";
	public static final String HIGHLIGHT_TYPE_TAG = "- Seu destaque";
	public static final String HIGHLIGHT_POSITION_DELIMITER_REGEX = "\\sou\\s";
	public static final String PDF_AUTHOR_DELIMETER_REGEX = "\\s\\(";
	public static final String POSITION_TIME_REGEX = "\\s\\|\\s";
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
			Pattern pattern = Pattern.compile("\r\n");
			String string = scanner.next(pattern);
			StringTokenizer tokenizer = new StringTokenizer(string, NEWLINE_DELIMETER);
			String[] firstLineTokens = tokenizer.nextToken().split(PDF_AUTHOR_DELIMETER_REGEX, 2);
			if (firstLineTokens.length <= 1)
				continue;
			
			String pdfName = firstLineTokens[0];
			String author = firstLineTokens[1];
			if (tokenizer.hasMoreTokens() && pdfName.equals(pdfNameFile)){
				String[] secondLine= tokenizer.nextToken().split(HIGHLIGHT_POSITION_DELIMITER_REGEX, 2);
				if (secondLine.length <= 1)
					continue;
				
				String highlightTag = secondLine[0];
				if (tokenizer.hasMoreTokens() && highlightTag.startsWith(HIGHLIGHT_TYPE_TAG)){
					String[] positionTime = secondLine[1].split(POSITION_TIME_REGEX);
					if (positionTime.length <= 1)
						continue;
					
					String position = positionTime[0];
//					String time = positionTime[1];
					
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
