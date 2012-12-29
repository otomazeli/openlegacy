package org.openlegacy.terminal.support.mock;

import org.openlegacy.annotations.screen.Identifier;
import org.openlegacy.annotations.screen.ScreenEntity;
import org.openlegacy.annotations.screen.ScreenField;
import org.openlegacy.annotations.screen.ScreenIdentifiers;
import org.openlegacy.annotations.screen.ScreenListField;

import junit.framework.Assert;

@ScreenEntity
@ScreenIdentifiers(identifiers = { @Identifier(row = 2, column = 28, value = "Items") })
public class ArrayFieldEntity implements org.openlegacy.terminal.ScreenEntity {

	@ScreenListField(fieldLength = 9, count = 3, gaps = { 10 })
	@ScreenField(row = 8, column = 25)
	private String[] toys;

	public String[] getToys() {
		return toys;
	}

	public void setToys(String[] toys) {
		Assert.assertEquals(toys.length, this.toys.length);
		this.toys = toys;
	}

	public String getFocusField() {
		return null;
	}

	public void setFocusField(String focusField) {

	}

}
