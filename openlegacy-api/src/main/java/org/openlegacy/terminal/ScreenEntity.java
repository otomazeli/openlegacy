package org.openlegacy.terminal;

/**
 * Interface for a screen entity. Currently used for identifying child screen getters methods calls to perform lazy fetching
 * 
 */
public interface ScreenEntity {

	String getFocusField();

	void setFocusField(String focusField);
}