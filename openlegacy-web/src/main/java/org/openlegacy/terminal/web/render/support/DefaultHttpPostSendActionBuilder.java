package org.openlegacy.terminal.web.render.support;

import org.openlegacy.terminal.TerminalActionMapper;
import org.openlegacy.terminal.TerminalField;
import org.openlegacy.terminal.TerminalSendActionBuilder;
import org.openlegacy.terminal.TerminalSnapshot;
import org.openlegacy.terminal.actions.TerminalActions;
import org.openlegacy.terminal.spi.TerminalSendAction;
import org.openlegacy.terminal.support.SimpleTerminalSendAction;
import org.openlegacy.terminal.utils.FieldsQuery;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class DefaultHttpPostSendActionBuilder implements TerminalSendActionBuilder<HttpServletRequest> {

	@Inject
	private TerminalActionMapper terminalActionMapper;

	public TerminalSendAction buildSendAction(TerminalSnapshot terminalSnapshot, HttpServletRequest httpRequest) {
		String keyboardKey = httpRequest.getParameter(TerminalHtmlConstants.KEYBOARD_KEY);
		Object command = TerminalActions.getCommand(keyboardKey, terminalActionMapper);
		TerminalSendAction sendAction = new SimpleTerminalSendAction(command);

		String terminalCursor = httpRequest.getParameter(TerminalHtmlConstants.TERMINAL_CURSOR_HIDDEN);
		sendAction.setCursorPosition(HtmlNamingUtil.toPosition(terminalCursor));

		List<TerminalField> editableFields = FieldsQuery.queryFields(terminalSnapshot,
				FieldsQuery.EditableFieldsCriteria.instance());
		for (TerminalField terminalField : editableFields) {
			String value = httpRequest.getParameter(HtmlNamingUtil.getFieldName(terminalField));
			if (!terminalField.getValue().equals(value)) {
				terminalField.setValue(value);
				sendAction.getModifiedFields().add(terminalField);
			}
		}
		return sendAction;
	}
}