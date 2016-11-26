/**
 * 
 */
package main.managepdf;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pdfclown.documents.Document;
import org.pdfclown.documents.Page;
import org.pdfclown.documents.Pages;
import org.pdfclown.documents.contents.ITextString;
import org.pdfclown.files.SerializationModeEnum;
import org.pdfclown.tools.TextExtractor;

import model.Anotation;

/**
 * Gerencia anota��es em PDF.
 * @author Alex
 *
 */
public class ManageAnotationToPDF implements ManageBasePDF<Anotation> {

	private static final String MERGED_PDF_PREFIX_NAME = "merged_";

	/* (non-Javadoc)
	 * @see main.managepdf.ManageBasePDF#createPDFWithAnotations(java.util.List, java.io.File)
	 */
	@Override
	public File createPDFWithInfo(List<Anotation> anotations, File pdfFile) throws IOException {
		File newPdfFile = new File(MERGED_PDF_PREFIX_NAME.concat(pdfFile.getName()));
		@SuppressWarnings("resource")
		Document document = new org.pdfclown.files.File(pdfFile.getAbsolutePath()).getDocument();
		Pages pages = document.getPages();
		for (Anotation anotation : anotations) {
			// TODO Busca pagina pelo a.position
			TextExtractor textExtractor = new TextExtractor(false, true);
			long pageNumber = 1;
			for (final Page page : pages){
//				String position = anotation.getPosition();
//				Pattern patternPos = Pattern.compile("(\\d*\\-)(\\d*)");
				Pattern pattern = Pattern.compile("(".concat(anotation.getText().replaceAll(" ",""))+"){1,1}");
				Map<Rectangle2D, List<ITextString>> extracted = null;
				try {
					extracted = textExtractor.extract(page);
				} catch (Exception e) {
					Logger.getGlobal().log(Level.WARNING, "Page "+pageNumber+" n�o foi analizada para adicionar marca��es: PDFClown limmited to UnicadeCharacter");
					continue;
				}
				final Matcher matcher = pattern.matcher(TextExtractor.toString(extracted));
				textExtractor.filter(extracted, new IIntervalFilterImplementation(matcher, page, pageNumber));
				pageNumber ++;
			}
		}
		document.getFile().save(newPdfFile.getName(),SerializationModeEnum.Standard);
		document.getFile().close();
		
		return newPdfFile;
	}

}
