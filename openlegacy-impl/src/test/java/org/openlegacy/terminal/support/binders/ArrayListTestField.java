package org.openlegacy.terminal.support.binders;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openlegacy.AbstractTest;
import org.openlegacy.exceptions.SessionEndedException;
import org.openlegacy.terminal.TerminalSession;
import org.openlegacy.terminal.actions.TerminalActions;
import org.openlegacy.terminal.support.mock.ArrayFieldEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("ArrayFieldTest-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ArrayListTestField extends AbstractTest {

	@Test
	public void testListField() {

		TerminalSession terminalSession = newTerminalSession();

		String[] expectedResult = new String[3];
		expectedResult[0] = "Domino";
		expectedResult[1] = "Cube";
		expectedResult[2] = "Sevivon";

		ArrayFieldEntity lineScreen = terminalSession.getEntity(ArrayFieldEntity.class);
		String[] actual = lineScreen.getToys();
		Assert.assertEquals(expectedResult.length, actual.length);
		int itemsToCompare = Math.min(3, actual.length);
		for (int i = 0; i < itemsToCompare; i++) {
			Assert.assertEquals(expectedResult[i], actual[i]);
		}
	}

	@Test
	public void testSendListField() {
		String[] listNewValues = new String[3];
		listNewValues[0] = "Domino1";

		listNewValues[1] = "Cube2";
		listNewValues[2] = "Sevivon3";
		TerminalSession terminalSession = newTerminalSession();

		ArrayFieldEntity screenEntity = terminalSession.getEntity(ArrayFieldEntity.class);
		screenEntity.setToys(listNewValues);

		try {
			terminalSession.doAction(TerminalActions.ENTER(), screenEntity);
		} catch (SessionEndedException e) { // OK
		}
	}
}
