package main.manageclip;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
		File clipFile = new File(getClass().getResource("/test/manageclip/clip_empty.txt").toURI());
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
		File clipFile = new File(getClass().getResource("/test/manageclip/clip_without_pdf_name.txt").toURI());
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
		File clipFile = new File(getClass().getResource("/test/manageclip/clip_3_anotations.txt").toURI());
		String pdfNameFile = "Ludmila";
		List<Anotation> anotations = manageKindleClip.extractInfoFromClipFile(pdfNameFile, clipFile);
		assertNotNull(anotations);
		assertTrue(anotations.size() == 3);
		Logger.getGlobal().log(Level.ALL, "Anotations size: "+anotations.size());
		
		assertTrue(anotations.get(0).getPosition().equals("posição 124-124"));
		assertTrue(anotations.get(1).getPosition().equals("posição 124-125"));
		assertTrue(anotations.get(2).getPosition().equals("posição 124-126"));
		assertTrue(anotations.get(0).getText().equals("transaction 1"));
		assertTrue(anotations.get(1).getText().equals("transaction 2"));
		assertTrue(anotations.get(2).getText().equals("transaction 3"));
	}

}
