/**
 * 
 */
package main.managepdf;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pdfclown.documents.Document;
import org.pdfclown.documents.Page;
import org.pdfclown.documents.Pages;
import org.pdfclown.documents.contents.ITextString;
import org.pdfclown.documents.contents.TextChar;
import org.pdfclown.documents.interaction.annotations.TextMarkup;
import org.pdfclown.documents.interaction.annotations.TextMarkup.MarkupTypeEnum;
import org.pdfclown.files.SerializationModeEnum;
import org.pdfclown.tools.TextExtractor;
import org.pdfclown.util.math.Interval;
import org.pdfclown.util.math.geom.Quad;

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
		File newPdfFile = new File(pdfFile.getPath().concat(MERGED_PDF_PREFIX_NAME).concat(pdfFile.getName()));
		@SuppressWarnings("resource")
		Document document = new org.pdfclown.files.File(pdfFile.getAbsolutePath()).getDocument();
		Pages pages = document.getPages();
		for (Anotation anotation : anotations) {
			// TODO Busca pagina pelo a.position
			TextExtractor textExtractor = new TextExtractor(false, true);
			final Page page = pages.get(3);
//				String position = anotation.getPosition();
//				Pattern patternPos = Pattern.compile("(\\d*\\-)(\\d*)");
				Pattern pattern = Pattern.compile("(".concat(anotation.getText())+"){1,1}");
				Map<Rectangle2D, List<ITextString>> extracted = textExtractor.extract(page);
				final Matcher matcher = pattern.matcher(TextExtractor.toString(extracted));
				/**
				 * Código copiado da API PDFClown
				 */
				// Highlight the text pattern matches!
				textExtractor.filter(extracted, new TextExtractor.IIntervalFilter() {
					@Override
					public boolean hasNext() {
						return matcher.find();
					}

					@SuppressWarnings({ "unchecked", "rawtypes" })
					@Override
					public Interval next() {
						return new Interval(matcher.start(), matcher.end());
					}

					@Override
					public void process(@SuppressWarnings("rawtypes") Interval interval, ITextString match) {
						// Defining the highlight box of the text pattern
						// match...
						List<Quad> highlightQuads = new ArrayList<>();
							/*
							 * NOTE: A text pattern match may be split across
							 * multiple contiguous lines, so we have to define a
							 * distinct highlight box for each text chunk.
							 */
							Rectangle2D textBox = null;
							for (TextChar textChar : match.getTextChars()) {
								Rectangle2D textCharBox = textChar.getBox();
								if (textBox == null) {
									textBox = (Rectangle2D) textCharBox.clone();
								} else {
									if (textCharBox.getY() > textBox.getMaxY()) {
										highlightQuads.add(Quad.get(textBox));
										textBox = (Rectangle2D) textCharBox.clone();
									} else {
										textBox.add(textCharBox);
									}
								}
							}
							highlightQuads.add(Quad.get(textBox));
						
						// Highlight the text pattern match!
						new TextMarkup(page, null, MarkupTypeEnum.Highlight, highlightQuads);
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				});
		}
		document.getFile().save("novo_pdf.pdf",SerializationModeEnum.Standard);
		document.getFile().close();
		
		return newPdfFile;
	}

}
