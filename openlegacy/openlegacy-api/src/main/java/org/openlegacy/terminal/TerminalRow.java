package org.openlegacy.terminal;

import java.util.List;

/**
 * A terminal screen interface. Defines common terminal screen properties Legacy vendors needs to implement this class
 */
public interface TerminalRow {

	List<TerminalField> getFields();

	TerminalField getField(int column);

	int getRowNumber();

}