package main.managepdf;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;

import org.pdfclown.documents.Page;
import org.pdfclown.documents.contents.ITextString;
import org.pdfclown.documents.contents.TextChar;
import org.pdfclown.documents.interaction.annotations.TextMarkup;
import org.pdfclown.documents.interaction.annotations.TextMarkup.MarkupTypeEnum;
import org.pdfclown.tools.TextExtractor;
import org.pdfclown.util.math.Interval;
import org.pdfclown.util.math.geom.Quad;

/**
 * Criado de cópia do site PDFCLown
 * @author Alex
 *
 */
public class IIntervalFilterImplementation implements TextExtractor.IIntervalFilter {
	private final Matcher matcher;
	private final Page page;
	private long pageNumber;
	
	IIntervalFilterImplementation(Matcher matcher, Page page, long pageNumber) {
		this.matcher = matcher;
		this.page = page;
		this.pageNumber = pageNumber;
		Logger.getGlobal().log(Level.INFO, "Processando página: "+pageNumber);
	}

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
		Logger.getGlobal().log(Level.INFO, "Processando marcação: "+match.getText());
		/**
		 * Código copiado da API PDFClown
		 */
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
		Logger.getGlobal().log(Level.INFO, "Marcação: "+match.getText()+" feita na página: "+pageNumber);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
