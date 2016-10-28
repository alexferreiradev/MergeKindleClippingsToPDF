package main.managepdf;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.manageclip.ManageKindleClip;
import model.Anotation;

public class ManagePdfTest {

	private ManageAnotationToPDF manageAnotationToPDF;
	private ManageKindleClip manageKindleClip;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		manageAnotationToPDF = new ManageAnotationToPDF();
		manageKindleClip = new ManageKindleClip();
	}

	@Test
	public final void testCreatePDFWithInfo() throws IOException, URISyntaxException {
		File clipFile = new File(getClass().getResource("/test/managepdf/clip_3_anotations_to_match_pdf.txt").toURI());
		String pdfNameFile = "pdf_ingles_sem_anotation";
		String pdfNameFileExt = pdfNameFile.concat(".pdf");
		File pdfFile = new File(getClass().getResource("/test/managepdf/"+pdfNameFileExt).toURI());
		
		List<Anotation> anotations = manageKindleClip.extractInfoFromClipFile(pdfNameFile, clipFile);
		File createPDFWithInfo = manageAnotationToPDF.createPDFWithInfo(anotations, pdfFile);
		assertNotNull(createPDFWithInfo);		
	}
	
	@Test
	public final void testRealClipAndPDF() throws IOException, URISyntaxException {
		File clipFile = new File(getClass().getResource("/test/managepdf/My Clippings.txt").toURI());
		String pdfNameFile = "Piga_comentado";
		String pdfNameFileExt = pdfNameFile.concat(".pdf");
		File pdfFile = new File(getClass().getResource("/test/managepdf/"+pdfNameFileExt).toURI());
		
		List<Anotation> anotations = manageKindleClip.extractInfoFromClipFile(pdfNameFile, clipFile);
		File createPDFWithInfo = manageAnotationToPDF.createPDFWithInfo(anotations, pdfFile);
		assertNotNull(createPDFWithInfo);		
	}
	
	/**
	 * Testar a busca por anotações no PDF
	 */
	@Test
	public final void testAnotationSearching(){
		
	}

}
