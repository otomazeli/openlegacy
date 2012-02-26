package org.openlegacy.designtime.terminal.analyzer.support;

import freemarker.template.TemplateException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openlegacy.designtime.terminal.analyzer.TerminalSnapshotsLoader;
import org.openlegacy.designtime.terminal.generators.ScreenEntityJavaGenerator;
import org.openlegacy.designtime.terminal.model.ScreenEntityDesigntimeDefinition;
import org.openlegacy.terminal.TerminalSnapshot;
import org.openlegacy.terminal.definitions.ScreenEntityDefinition;
import org.openlegacy.terminal.definitions.ScreenFieldDefinition;
import org.openlegacy.terminal.definitions.ScreenTableDefinition;
import org.openlegacy.terminal.definitions.ScreenTableDefinition.ScreenColumnDefinition;
import org.openlegacy.terminal.support.SimpleTerminalPosition;
import org.openlegacy.test.utils.AssertUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import junit.framework.Assert;

@ContextConfiguration("/test-designtime-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DefaultTerminalSnapshotsAnalyzerTest {

	@Inject
	private TerminalSnapshotsLoader snapshotsLoader;

	@Inject
	private DefaultTerminalSnapshotsAnalyzer snapshotsAnalyzer;

	@Inject
	private DefaultTerminalSnapshotsOrganizer snapshotsOrganizer;

	private final static Log logger = LogFactory.getLog(DefaultTerminalSnapshotsAnalyzerTest.class);

	@Test
	public void testBasicAnalisys() {

		Map<String, ScreenEntityDefinition> screenEntitiesDefinitions = analyze("SimpleScreen.xml", "FormScreen.xml");

		Assert.assertEquals(2, screenEntitiesDefinitions.size());

		ScreenEntityDefinition screen1 = screenEntitiesDefinitions.get("SimpleScreen");
		Assert.assertNotNull(screen1);
		Map<String, ScreenFieldDefinition> fieldsDefinitions = screen1.getFieldsDefinitions();
		Assert.assertEquals(2, fieldsDefinitions.size());
		ScreenFieldDefinition fieldA = fieldsDefinitions.get("fieldA");
		Assert.assertNotNull(fieldA);
		Assert.assertTrue(fieldA.isEditable());
		Assert.assertEquals("Field A", fieldA.getDisplayName());
		Assert.assertEquals(SimpleTerminalPosition.newInstance(4, 13), fieldA.getPosition());

		ScreenEntityDefinition screen2 = screenEntitiesDefinitions.get("FormScreen");
		Assert.assertNotNull(screen2);
		fieldsDefinitions = screen2.getFieldsDefinitions();
		Assert.assertEquals(9, fieldsDefinitions.size());
		fieldA = fieldsDefinitions.get("fieldA");
		Assert.assertNotNull(fieldA);

		ScreenFieldDefinition fieldB = fieldsDefinitions.get("fieldB");
		Assert.assertNotNull(fieldB);
		Assert.assertTrue(!fieldB.isEditable());
		Assert.assertEquals("Field B", fieldB.getDisplayName());
	}

	private Map<String, ScreenEntityDefinition> analyze(String... fileNames) {
		snapshotsOrganizer.setMatchingPercent(99);
		List<TerminalSnapshot> snapshots = snapshotsLoader.loadSnapshots(getClass().getResource("mock").getFile(), fileNames);
		Map<String, ScreenEntityDefinition> screenEntitiesDefinitions = snapshotsAnalyzer.analyzeSnapshots(snapshots);
		return screenEntitiesDefinitions;
	}

	@Test
	public void testSessionAnalyzer() throws TemplateException, IOException {
		Map<String, ScreenEntityDefinition> definitions = snapshotsAnalyzer.analyzeTrail(getClass().getResourceAsStream(
				"Session.trail.xml"));
		Assert.assertEquals(3, definitions.size());
	}

	@Test
	public void testBasicTable() {

		Map<String, ScreenEntityDefinition> screenEntitiesDefinitions = analyze("TableScreen.xml");

		Assert.assertEquals(1, screenEntitiesDefinitions.size());

		ScreenEntityDefinition tableScreen = screenEntitiesDefinitions.get("TableScreen");
		Assert.assertNotNull(tableScreen);
		Map<String, ScreenTableDefinition> tablesDefinitions = tableScreen.getTableDefinitions();
		Assert.assertEquals(1, tablesDefinitions.size());
		ScreenTableDefinition table1 = tablesDefinitions.get("TableScreenRecord");

		Assert.assertEquals(5, table1.getStartRow());
		Assert.assertEquals(7, table1.getEndRow());

		ScreenColumnDefinition columnSelction = table1.getColumnDefinition("action");
		Assert.assertNotNull(columnSelction);
		Assert.assertEquals(4, columnSelction.getStartColumn());
		Assert.assertEquals(5, columnSelction.getEndColumn());
		Assert.assertTrue(columnSelction.isEditable());
		Assert.assertEquals("Action", columnSelction.getDisplayName());

		ScreenColumnDefinition columnA = table1.getColumnDefinition("columnA");
		Assert.assertNotNull(columnA);
		Assert.assertEquals(11, columnA.getStartColumn());
		Assert.assertEquals(19, columnA.getEndColumn());
		Assert.assertEquals("Column A", columnA.getDisplayName());
		Assert.assertEquals("Cell 1A", columnA.getSampleValue());

		ScreenColumnDefinition columnB = table1.getColumnDefinition("columnB");
		Assert.assertNotNull(columnB);
		Assert.assertEquals(21, columnB.getStartColumn());
		Assert.assertEquals(29, columnB.getEndColumn());
		Assert.assertEquals("Column B", columnB.getDisplayName());
		Assert.assertEquals("Cell 1B", columnB.getSampleValue());

		ScreenColumnDefinition columnC = table1.getColumnDefinition("column4");
		Assert.assertNotNull(columnC);
		Assert.assertEquals(31, columnC.getStartColumn());
		Assert.assertEquals(39, columnC.getEndColumn());
		Assert.assertEquals("Column4", columnC.getDisplayName());
		Assert.assertEquals("Cell 1C", columnC.getSampleValue());
	}

	@Test
	public void testGenerate() throws TemplateException, IOException {

		Map<String, ScreenEntityDefinition> screenEntitiesDefinitions = analyze("LoginScreen.xml", "MainMenuScreen.xml",
				"SimpleScreen.xml", "FormScreen.xml", "TableScreen.xml", "WindowScreen.xml");

		assertScreenContent(screenEntitiesDefinitions.get("LoginScreen"), "LoginScreen.java.expected");
		assertScreenContent(screenEntitiesDefinitions.get("SimpleScreen"), "SimpleScreen.java.expected");
		assertScreenContent(screenEntitiesDefinitions.get("FormScreen"), "FormScreen.java.expected");
		assertScreenContent(screenEntitiesDefinitions.get("TableScreen"), "TableScreen.java.expected");
		assertScreenContent(screenEntitiesDefinitions.get("WindowScreen"), "WindowScreen.java.expected");
		assertScreenContent(screenEntitiesDefinitions.get("MainMenuScreen"), "MainMenuScreen.java.expected");
	}

	@Test
	public void testGenerateNavigation() throws TemplateException, IOException {

		Map<String, ScreenEntityDefinition> screenEntitiesDefinitions = analyze("LoginScreen.xml", "LoginScreen-out.xml",
				"MainMenuScreen.xml", "MainMenuScreen-out.xml", "SubMenu1Screen.xml", "SubMenu1Screen-out.xml",
				"SimpleScreen.xml");

		assertScreenContent(screenEntitiesDefinitions.get("MainMenuScreen"), "navigation/MainMenuScreen.java.expected");
		assertScreenContent(screenEntitiesDefinitions.get("SubMenu1"), "navigation/SubMenu1.java.expected");
		assertScreenContent(screenEntitiesDefinitions.get("SimpleScreen"), "navigation/SimpleScreen.java.expected");
	}

	@Test
	public void testInventoryAppGenerate() throws TemplateException, IOException {
		snapshotsOrganizer.setMatchingPercent(95);
		List<TerminalSnapshot> snapshots = snapshotsLoader.loadSnapshots(
				getClass().getResource("/apps/inventory/screens").getFile(), "SignOn.xml", "MainMenu.xml", "ItemsList.xml",
				"ItemDetails1.xml", "ItemDetails2.xml");
		Map<String, ScreenEntityDefinition> screenEntitiesDefinitions = snapshotsAnalyzer.analyzeSnapshots(snapshots);
		assertScreenContent(screenEntitiesDefinitions.get("SignOn"), "SignOn.java.expected");

		assertScreenContent(screenEntitiesDefinitions.get("DemoEnvironment"), "inventory/DemoEnvironment.java.expected");

		// table
		assertScreenContent(screenEntitiesDefinitions.get("WorkWithItemMaster"), "inventory/WorkWithItemMaster.java.expected");
		// form1
		assertScreenContent(screenEntitiesDefinitions.get("WorkWithItemMaster1"), "inventory/WorkWithItemMaster1.java.expected");
		// form2
		assertScreenContent(screenEntitiesDefinitions.get("WorkWithItemMaster2"), "inventory/WorkWithItemMaster2.java.expected");
	}

	private void assertScreenContent(ScreenEntityDefinition screen, String expectedResource) throws TemplateException,
			IOException {
		((ScreenEntityDesigntimeDefinition)screen).setPackageName("com.test");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		new ScreenEntityJavaGenerator().generate(screen, baos);

		if (expectedResource == null) {
			logger.info("\n" + new String(baos.toByteArray()));
			return;
		}

		byte[] expectedBytes = IOUtils.toByteArray(getClass().getResourceAsStream(expectedResource));
		AssertUtils.assertContent(expectedBytes, baos.toByteArray());
	}
}