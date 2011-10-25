package org.openlegacy.recognizers;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openlegacy.AbstractTest;
import org.openlegacy.adapter.terminal.SendKeyActions;
import org.openlegacy.terminal.TerminalSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.someorg.examples.screens.MainMenu;
import com.someorg.examples.screens.SignOn;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class RegistryBasedScreensRecognizerTest extends AbstractTest {

	@Autowired
	private TerminalSession terminalSession;

	@Test
	public void testScreenRecognizer() throws IOException {
		SignOn signOn = terminalSession.getEntity(SignOn.class);
		Assert.assertNotNull(signOn);

		terminalSession.doAction(SendKeyActions.ENTER);
		MainMenu mainMenu = terminalSession.getEntity(MainMenu.class);
		Assert.assertNotNull(mainMenu);
	}
}