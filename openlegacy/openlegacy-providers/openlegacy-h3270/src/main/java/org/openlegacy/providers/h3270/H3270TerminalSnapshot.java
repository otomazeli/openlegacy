package org.openlegacy.providers.h3270;

import org.h3270.host.Field;
import org.h3270.host.InputField;
import org.h3270.host.S3270Screen;
import org.openlegacy.terminal.ScreenSize;
import org.openlegacy.terminal.TerminalField;
import org.openlegacy.terminal.TerminalPosition;
import org.openlegacy.terminal.persistance.TerminalPersistedSnapshot;
import org.openlegacy.terminal.support.AbstractSnapshot;
import org.openlegacy.terminal.support.SimpleScreenSize;
import org.openlegacy.terminal.support.SimpleTerminalPosition;
import org.openlegacy.terminal.support.SnapshotUtils;

import java.util.ArrayList;
import java.util.List;

public class H3270TerminalSnapshot extends AbstractSnapshot {

	private S3270Screen screen;
	private int sequence;

	public H3270TerminalSnapshot(S3270Screen screen, int sequence) {
		this.screen = screen;
		this.sequence = sequence;
	}

	public Object getDelegate() {
		return screen;
	}

	public Integer getSequence() {
		return sequence;
	}

	public String getCommand() {
		return null;
	}

	@Override
	protected List<TerminalPosition> initFieldSeperators() {
		List<Field> fields = screen.getFields();
		List<TerminalPosition> fieldSepeators = new ArrayList<TerminalPosition>();
		for (Field field : fields) {
			// don't count fields outside the snapshot
			if (field.getStartX() > 0) {
				fieldSepeators.add(new SimpleTerminalPosition(field.getStartY() + 1, field.getStartX()));
			}
		}
		return fieldSepeators;
	}

	@Override
	protected ScreenSize initScreenSize() {
		return new SimpleScreenSize(screen.getHeight(), screen.getWidth());
	}

	@Override
	protected TerminalPosition initCursorPosition() {
		InputField focusedField = screen.getFocusedField();
		if (focusedField == null) {
			return null;
		}
		return new SimpleTerminalPosition(focusedField.getStartY() + 1, focusedField.getStartX() + 1);
	}

	@Override
	protected String initText() {
		List<TerminalField> fields = getFields();
		StringBuilder buffer = SnapshotUtils.initEmptyBuffer(getSize());
		TerminalField previousField = null;
		for (TerminalField terminalField : fields) {
			SnapshotUtils.placeContentOnBuffer(buffer, terminalField, getSize());
		}
		return buffer.toString();
	}

	@Override
	protected List<TerminalField> initFields() {
		List<Field> h3270Fields = screen.getFields();
		List<TerminalField> fields = new ArrayList<TerminalField>();
		for (Field field : h3270Fields) {
			// start column is 1 based, while field.getStartX() is 0 based
			int startColumn = field.getStartX() + 1; // 0 based -> convert to 1 based
			int endColumn = field.getEndX() + 1; // 0 based -> convert to 1 based
			if (field.isMultiline()) {
				// iterate through the multy-line field lines
				for (int i = field.getStartY(); i <= field.getEndY(); i++) {
					if (i > field.getStartY()) {
						startColumn = 1;
					}
					if (i < field.getEndY()) {
						endColumn = screen.getWidth();
					} else {
						// last row
						endColumn = field.getEndX() + 1; // 0 based -> convert to 1 based
					}
					fields.add(new H3270TerminalField(field, i - field.getStartY(), startColumn, endColumn));
				}
			} else {
				if (field.getEndX() >= 0) {
					fields.add(new H3270TerminalField(field, 0, startColumn, endColumn));
				}
			}
		}
		return fields;
	}

	@Override
	protected void readExternal(TerminalPersistedSnapshot persistedSnapshot) {
		this.sequence = persistedSnapshot.getSequence();
	}

}
