/**
 * 
 */
package manageclip;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Anotation;

/**
 * Implementa métodos para gerencimento de arquivos Clip para o Kindle
 * 
 * @author Alex
 *
 */
public class ManageKindleClip implements ManageClip<Anotation> {

	public static final String UTF_8_ENCODE = "UTF-8";
	public static final String NEWLINE_DELIMETER_REGEX = "(\r\n|[\n])";
	public static final String CLIP_REGISTER_DELIMETER_REGEX = "(==========)"+NEWLINE_DELIMETER_REGEX;
	public static final String HIGHLIGHT_TYPE_TAG = "Seu destaque";
	public static final String PDF_NAME_REGEX = "(.*)\\s\\(";
	public static final String AUTHOR_REGEX = "(.*)\\)";
	public static final String POSITION_REGEX = "\\- Seu destaque.*\\s(\\d*)[\\-\\s\\|].*\\|";

	/* (non-Javadoc)
	 * @see main.manageclip.ManageClip#createAnotationsFromPDF(java.lang.String, java.io.File)
	 */
	@Override
	public List<Anotation> extractInfoFromClipFile(String pdfNameFile, File clipFile) throws FileNotFoundException {
		Matcher matcher = Pattern.compile("(.*).pdf").matcher(pdfNameFile);
		if (matcher.find())
			pdfNameFile = matcher.group(1);
		
		List<Anotation> anotations = new ArrayList<>();
		Scanner scanner = new Scanner(clipFile, UTF_8_ENCODE);
		scanner.useDelimiter(CLIP_REGISTER_DELIMETER_REGEX);
		while (scanner.hasNext()) {
			Scanner token = new Scanner(scanner.next());
			String line = token.nextLine();
			matcher = Pattern.compile(PDF_NAME_REGEX+AUTHOR_REGEX).matcher(line);
			String pdfName = "não encontrado"; 
			String author = "não encontrado";
			if (matcher.find()){
				pdfName = matcher.group(1);
				author = matcher.group(2);
			}else{
				pdfName = line; //Não tem nome do author 
			}
			if (token.hasNext() && pdfName.trim().equals(pdfNameFile)){
				String secondLine = token.nextLine();
				if (token.hasNext() && secondLine.contains(HIGHLIGHT_TYPE_TAG)){
					matcher = Pattern.compile(POSITION_REGEX).matcher(secondLine);
					String position = "não encontrada";
					if (matcher.find())
						position = matcher.group(1);
					
					token.nextLine(); // Pula linha
					String text = token.nextLine();
					Anotation anotation = new Anotation(pdfNameFile, position, author, text);
					anotations.add(anotation);
				}
			}
			token.close();
		}
		scanner.close();
		
		return anotations;
	}
}
