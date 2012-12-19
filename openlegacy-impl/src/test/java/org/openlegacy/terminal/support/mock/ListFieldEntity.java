package org.openlegacy.terminal.support.mock;

import org.openlegacy.annotations.screen.Identifier;
import org.openlegacy.annotations.screen.ScreenEntity;
import org.openlegacy.annotations.screen.ScreenField;
import org.openlegacy.annotations.screen.ScreenIdentifiers;
import org.openlegacy.annotations.screen.ScreenListField;

import java.util.List;

import junit.framework.Assert;

@ScreenEntity
@ScreenIdentifiers(identifiers = { @Identifier(row = 2, column = 27, value = "Items") })
public class ListFieldEntity implements org.openlegacy.terminal.ScreenEntity {

	@ScreenListField(fieldLength = 9, count = 3, gaps = { 10, 10 })
	@ScreenField(row = 8, column = 25)
	private List<String> toys;

	public List<String> getToys() {
		return toys;
	}

	public void setToys(List<String> toys) {
		Assert.assertEquals(toys.size(), this.toys.size());
		this.toys = toys;
	}

	public String getFocusField() {
		return null;
	}

	public void setFocusField(String focusField) {

	}
}
