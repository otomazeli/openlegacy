package org.openlegacy.terminal.support.binders;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openlegacy.AbstractTest;
import org.openlegacy.exceptions.SessionEndedException;
import org.openlegacy.terminal.TerminalSession;
import org.openlegacy.terminal.actions.TerminalActions;
import org.openlegacy.terminal.support.mock.ListFieldEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ContextConfiguration("ListFieldTest-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ListFieldTest extends AbstractTest {

	@Test
	public void tesListField() {

		TerminalSession terminalSession = newTerminalSession();

		List<String> expectedResult = new ArrayList<String>();
		expectedResult.add("Domino");
		expectedResult.add("Cube");
		expectedResult.add("Sevivon");

		ListFieldEntity listLineScreen = terminalSession.getEntity(ListFieldEntity.class);

		Assert.assertEquals(expectedResult, listLineScreen.getToys());

	}

	@Test
	public void testSendListField() {
		List<String> listNewValues = new ArrayList<String>();
		listNewValues.add("Domino1");
		listNewValues.add("Cube1");
		listNewValues.add("Sevivon1");
		TerminalSession terminalSession = newTerminalSession();

		ListFieldEntity listScreenEntity = terminalSession.getEntity(ListFieldEntity.class);
		listScreenEntity.setToys(listNewValues);

		try {
			terminalSession.doAction(TerminalActions.ENTER(), listScreenEntity);
		} catch (SessionEndedException e) {
			// OK
		}
	}

	@Test
	public void testArrayField() {

		TerminalSession terminalSession = newTerminalSession();

		String[] expectedResult = new String[3];
		expectedResult[0] = "Domino";
		expectedResult[1] = "Cube";
		expectedResult[2] = "Sevivon";

		ListFieldEntity listLineScreen = terminalSession.getEntity(ListFieldEntity.class);

		Assert.assertArrayEquals(expectedResult, listLineScreen.getToys().toArray(new String[listLineScreen.getToys().size()]));

	}

	@Test
	public void testSendArrayField() {
		String[] arrayNewValues = new String[3];
		arrayNewValues[0] = "Domino1";
		arrayNewValues[1] = "Cube1";
		arrayNewValues[2] = "Sevivon1";
		TerminalSession terminalSession = newTerminalSession();

		ListFieldEntity listScreenEntity = terminalSession.getEntity(ListFieldEntity.class);
		listScreenEntity.setToys(Arrays.asList(arrayNewValues));

		try {
			terminalSession.doAction(TerminalActions.ENTER(), listScreenEntity);
		} catch (SessionEndedException e) {
			// OK
		}
	}
}
