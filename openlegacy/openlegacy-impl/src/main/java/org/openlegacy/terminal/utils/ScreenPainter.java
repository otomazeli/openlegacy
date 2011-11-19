package org.openlegacy.terminal.utils;

import org.openlegacy.terminal.ScreenPosition;
import org.openlegacy.terminal.ScreenSize;
import org.openlegacy.terminal.TerminalField;
import org.openlegacy.terminal.TerminalScreen;
import org.openlegacy.terminal.spi.TerminalSendAction;

import java.util.Collection;
import java.util.List;

/**
 * A textual utility which format terminal screen in to a preventable text which is very comfort for debugging purposes
 * 
 */
public class ScreenPainter {

	public static String paint(TerminalScreen terminalScreen, boolean decorated) {
		return paint(terminalScreen, null, decorated);
	}

	public static String paint(TerminalScreen terminalScreen, TerminalSendAction terminalSendAction, boolean decorated) {
		String text = terminalScreen.getText();
		String newline = System.getProperty("line.separator");
		int rows = terminalScreen.getSize().getRows();
		StringBuilder out = new StringBuilder();
		if (decorated) {
			generateColumnNumbers(terminalScreen, newline, out);
		}
		for (int i = 0; i < rows; i++) {
			int beginIndex = i * terminalScreen.getSize().getColumns();
			if (decorated) {
				out.append(i + 1);
				if (decorated) {
					if ((i + 1) < 10) {
						out.append(" ");
					}
					out.append("|");
				}
			}
			if (decorated) {
				out.append(text.substring(beginIndex, beginIndex + terminalScreen.getSize().getColumns()));
				out.append("|");
			}
			out.append(newline);
		}
		if (decorated) {
			out.append("___________________________________________________________________________________");
		}
		if (decorated) {
			out.append(newline);
			generateColumnNumbers(terminalScreen, newline, out);
		}

		drawFieldsSeperators(terminalScreen, out);
		drawEditableFields(terminalScreen, out, terminalScreen.getEditableFields(), '[', ']');

		if (terminalSendAction != null) {
			drawEditableFields(terminalScreen, out, terminalSendAction.getModifiedFields(), '*', '*');
			drawCursor(terminalScreen, out, terminalSendAction);
		}
		return out.toString();

	}

	private static void drawCursor(TerminalScreen terminalScreen, StringBuilder out, TerminalSendAction terminalSendAction) {
		if (terminalSendAction.getCursorPosition() == null) {
			return;
		}
		int cursorPainterLocation = calculatePositionOnPainter(terminalSendAction.getCursorPosition(), terminalScreen.getSize());
		if (out.charAt(cursorPainterLocation) == ' ') {
			out.setCharAt(cursorPainterLocation, '#');
		}

	}

	private static void drawFieldsSeperators(TerminalScreen terminalScreen, StringBuilder out) {
		List<ScreenPosition> attributes = terminalScreen.getFieldSeperators();
		for (ScreenPosition screenPosition : attributes) {
			if (screenPosition.getColumn() > 0) {
				int bufferLocation = calculatePositionOnPainter(screenPosition, terminalScreen.getSize());
				out.setCharAt(bufferLocation, '^');
			}
		}

	}

	private static void generateColumnNumbers(TerminalScreen hostScreen, String newline, StringBuilder out) {
		StringBuilder headerLine1 = new StringBuilder("    ");
		StringBuilder headerLine2 = new StringBuilder("    ");
		for (int i = 2; i <= hostScreen.getSize().getColumns(); i++) {
			if (i % 2 == 0) {
				headerLine1.append(i % 10);
				if ((i / 10) > 0) {
					headerLine2.append(i / 10);
				} else {
					headerLine2.append(" ");
				}
			} else {
				headerLine1.append(" ");
				headerLine2.append(" ");
			}

		}
		out.append(headerLine1 + newline);
		out.append(headerLine2 + newline);
		out.append("___________________________________________________________________________________");
		out.append(newline);
	}

	private static void drawEditableFields(TerminalScreen terminalScreen, StringBuilder out, Collection<TerminalField> fields,
			char leftMark, char rightMark) {
		for (TerminalField terminalField : fields) {
			// +6 - line numbers + |
			int beforeInputBufferLocation = calculatePositionOnPainter(terminalField.getPosition(), terminalScreen.getSize()) - 1;
			out.setCharAt(beforeInputBufferLocation, leftMark);
			int afterInputBufferLocation = beforeInputBufferLocation + terminalField.getLength() + 1;
			out.setCharAt(afterInputBufferLocation, rightMark);

			String value = terminalField.getValue();
			for (int i = 0; i < value.length(); i++) {
				int inputBufferLocation = beforeInputBufferLocation + 1;
				out.setCharAt(inputBufferLocation + i, value.charAt(i));
			}
		}
	}

	private static int calculatePositionOnPainter(ScreenPosition screenPosition, ScreenSize screenSize) {
		int fieldStartBufferLocation = (screenSize.getColumns() + 6) // 6- line numbers + | + NL
				// -1 - 0 base, +3 - header
				* (screenPosition.getRow() - 1 + 3) + screenPosition.getColumn() - 1;
		return fieldStartBufferLocation;
	}

}