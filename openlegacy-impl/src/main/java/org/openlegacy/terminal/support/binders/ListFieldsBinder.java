package org.openlegacy.terminal.support.binders;

import org.openlegacy.definitions.ListFieldTypeDefinition;
import org.openlegacy.definitions.support.SimpleListFieldTypeDefinition;
import org.openlegacy.terminal.ScreenEntity;
import org.openlegacy.terminal.ScreenEntityBinder;
import org.openlegacy.terminal.ScreenPojoFieldAccessor;
import org.openlegacy.terminal.TerminalField;
import org.openlegacy.terminal.TerminalPosition;
import org.openlegacy.terminal.TerminalSendAction;
import org.openlegacy.terminal.TerminalSnapshot;
import org.openlegacy.terminal.definitions.ScreenFieldDefinition;
import org.openlegacy.terminal.providers.ScreenFieldsDefinitionProvider;
import org.openlegacy.terminal.support.SimpleTerminalPosition;
import org.openlegacy.terminal.utils.SimpleScreenPojoFieldAccessor;
import org.openlegacy.utils.ProxyUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

@Component
public class ListFieldsBinder implements ScreenEntityBinder {

	@Inject
	private ScreenFieldsDefinitionProvider fieldMappingsProvider;

	public void populateEntity(Object screenEntity, TerminalSnapshot snapshot) {

		ScreenPojoFieldAccessor fieldAccessor = null;

		Class<? extends Object> class1 = ProxyUtil.getOriginalClass(screenEntity.getClass());

		Collection<ScreenFieldDefinition> fieldDefinitions = fieldMappingsProvider.getFieldsMappingDefinitions(snapshot, class1);

		for (ScreenFieldDefinition fieldDefinition : fieldDefinitions) {
			if (fieldDefinition.getJavaType() != List.class) {
				continue;
			}
			// lazy creation - mostly not used
			if (fieldAccessor == null) {
				fieldAccessor = new SimpleScreenPojoFieldAccessor(screenEntity);
			}

			SimpleListFieldTypeDefinition fieldTypeDefinition = (SimpleListFieldTypeDefinition)fieldDefinition.getFieldTypeDefinition();
			Assert.notNull(fieldTypeDefinition, "A field of type List is defined without @ScreenListField annotation");
			int fieldsInList = fieldTypeDefinition.getCount();
			int[] gapBetweenFields = fieldTypeDefinition.getGaps();
			TerminalPosition position = fieldDefinition.getPosition();

			List<String> members = new ArrayList<String>();
			for (int i = 0; i < fieldsInList - 1; i++) {
				// System.out.println(snapshot.getText(position, fieldTypeDefinition.getFieldLength()));
				members.add(snapshot.getText(position, fieldTypeDefinition.getFieldLength()).trim());
				position = position.moveBy(gapBetweenFields[i]);
			}
			members.add(snapshot.getText(position, fieldTypeDefinition.getFieldLength()).trim());
			fieldAccessor.setFieldValue(fieldDefinition.getName(), members);

		}

	}

	public void populateSendAction(TerminalSendAction sendAction, TerminalSnapshot snapshot, Object entity) {
		if (entity == null) {
			return;
		}

		Assert.isTrue(entity instanceof ScreenEntity, "screen entity must implement ScreenEntity interface");

		ScreenEntity screenEntity = (ScreenEntity)entity;

		Collection<ScreenFieldDefinition> fieldDefinitions = fieldMappingsProvider.getFieldsMappingDefinitions(snapshot,
				screenEntity.getClass());

		if (fieldDefinitions == null) {
			return;
		}

		ScreenPojoFieldAccessor fieldAccessor = null;

		for (ScreenFieldDefinition fieldDefinition : fieldDefinitions) {
			// lazy creation - mostly not used
			if (fieldAccessor == null) {
				fieldAccessor = new SimpleScreenPojoFieldAccessor(screenEntity);
			}

			if (fieldDefinition.getJavaType() != List.class) {
				continue;
			}
			ListFieldTypeDefinition fieldTypeDefinition = (ListFieldTypeDefinition)fieldDefinition.getFieldTypeDefinition();
			Assert.notNull(fieldTypeDefinition, "A field of type List is defined without @ScreenListField annotation");
			TerminalPosition position = fieldDefinition.getPosition();
			TerminalField field = snapshot.getField(SimpleTerminalPosition.newInstance(position.getRow(), position.getColumn()));

			@SuppressWarnings("unchecked")
			List<String> fieldValue = (List<String>)fieldAccessor.getFieldValue(fieldDefinition.getName());
			String formater = String.format("%%-%ds", fieldTypeDefinition.getFieldLength() + 1);

			String val = "";
			for (int i = 0; i < fieldTypeDefinition.getCount(); i++) {
				val = val + String.format(formater, fieldValue.get(i));
			}
			field.setValue(val);

			sendAction.getModifiedFields().add(field);
		}

	}
}
