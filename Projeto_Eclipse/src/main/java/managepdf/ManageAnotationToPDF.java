/**
 * 
 */
package managepdf;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
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
import org.pdfclown.documents.interaction.navigation.page.PageLabel;
import org.pdfclown.files.SerializationModeEnum;
import org.pdfclown.tools.TextExtractor;

import model.Anotation;

/**
 * Gerencia anotações em PDF.
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
		Collection<PageLabel> values = document.getPageLabels().values();
		int pageNBase = 1;
		if (values.size() > 0)
			pageNBase = values.iterator().next().getNumberBase(); 
	
		for (Anotation anotation : anotations) {
			String position = anotation.getPosition();			
			TextExtractor textExtractor = new TextExtractor(false, true);
			long pageNumber = 0;
			for (final Page page : pages){
				pageNumber ++;
				if (!position.isEmpty()){
					int pageLabelNumber = page.getIndex() + pageNBase;
					if (pageLabelNumber < Integer.valueOf(position))
						continue;
					else if (pageLabelNumber > Integer.valueOf(position))
						break;
				}

				Pattern pattern = Pattern.compile("(".concat(anotation.getText().replaceAll(" ",""))+")+");
				Map<Rectangle2D, List<ITextString>> extracted = null;
				try {
					extracted = textExtractor.extract(page);
				} catch (Exception e) {
					Logger.getGlobal().log(Level.WARNING, "Page "+pageNumber+" não foi analizada para adicionar marcações: PDFClown is limmited to Unicode Character");
					continue;
				}
				final Matcher matcher = pattern.matcher(TextExtractor.toString(extracted));
				textExtractor.filter(extracted, new IIntervalFilterImplementation(matcher, page, pageNumber));
			}
		}
		document.getFile().save(newPdfFile.getName(),SerializationModeEnum.Standard);
		document.getFile().close();
		
		return newPdfFile;
	}

}
