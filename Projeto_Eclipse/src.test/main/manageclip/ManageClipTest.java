package main.manageclip;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Anotation;

public class ManageClipTest {

	private ManageKindleClip manageKindleClip;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		manageKindleClip = new ManageKindleClip();
	}

	@Test
	public final void testExtractInfoFromClipFileEmpty() throws Exception {
		File clipFile = new File("src/test/main/manageclip/clip_empty.txt");
		String pdfNameFile = "test";
		List<Anotation> anotations = manageKindleClip.extractInfoFromClipFile(pdfNameFile, clipFile);
		assertNotNull(anotations);
		assertTrue(anotations.size() == 0);
	}
	
	/**
	 * Sem uma marcação para um nome de PDF
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testExtractInfoFromClipFileWithoutPDFName() throws Exception {
		File clipFile = new File("src/test/main/manageclip/clip_without_pdf_name.txt");
		String pdfNameFile = "test";
		List<Anotation> anotations = manageKindleClip.extractInfoFromClipFile(pdfNameFile, clipFile);
		assertNotNull(anotations);
		assertTrue(anotations.size() == 0);
	}
	
	/**
	 * PDF: test
	 * Texto: teste de destaque
	 * Clip: 3 anotações
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testExtract3Info() throws Exception {
		File clipFile = new File("src/test/main/manageclip/clip_3_anotations.txt");
		String pdfNameFile = "Ludmila";
		List<Anotation> anotations = manageKindleClip.extractInfoFromClipFile(pdfNameFile, clipFile);
		assertNotNull(anotations);
		assertTrue(anotations.size() == 3);
		
		assertTrue(anotations.get(0).getText().equals("transaction 1"));
		assertTrue(anotations.get(1).getText().equals("transaction 2"));
		assertTrue(anotations.get(2).getText().equals("transaction 3"));
	}

}
