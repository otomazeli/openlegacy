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

import java.util.Collection;

import javax.inject.Inject;

@Component
public class ArrayFieldsBinder implements ScreenEntityBinder {

	@Inject
	private ScreenFieldsDefinitionProvider fieldMappingsProvider;

	public void populateEntity(Object screenEntity, TerminalSnapshot snapshot) {

		ScreenPojoFieldAccessor fieldAccessor = null;

		Class<? extends Object> class1 = ProxyUtil.getOriginalClass(screenEntity.getClass());

		Collection<ScreenFieldDefinition> fieldDefinitions = fieldMappingsProvider.getFieldsMappingDefinitions(snapshot, class1);

		for (ScreenFieldDefinition fieldDefinition : fieldDefinitions) {
			if (fieldDefinition.getJavaType() != String[].class) {
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

			String[] members = new String[fieldsInList];

			int skip = gapBetweenFields.length == 1 ? 0 : 1;
			for (int i = 0; i < fieldsInList - 1; i++) {
				// System.out.println(snapshot.getText(position, fieldTypeDefinition.getFieldLength()));
				members[i] = snapshot.getText(position, fieldTypeDefinition.getFieldLength()).trim();
				position = position.moveBy(gapBetweenFields[i * skip]);
			}
			members[fieldsInList - 1] = snapshot.getText(position, fieldTypeDefinition.getFieldLength()).trim();
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

			if (fieldDefinition.getJavaType() != String[].class) {
				continue;
			}
			ListFieldTypeDefinition fieldTypeDefinition = (ListFieldTypeDefinition)fieldDefinition.getFieldTypeDefinition();
			Assert.notNull(fieldTypeDefinition, "A field of type List is defined without @ScreenListField annotation");
			TerminalPosition position = fieldDefinition.getPosition();

			@SuppressWarnings("unchecked")
			String[] fieldValue = (String[])fieldAccessor.getFieldValue(fieldDefinition.getName());
			String formater = String.format("%%-%ds", fieldTypeDefinition.getFieldLength() + 1);
			int gaps[] = fieldTypeDefinition.getGaps();
			int skip = gaps.length == 1 ? 0 : 1;
			for (int i = 0; i < fieldTypeDefinition.getCount(); i++) {
				TerminalField field = snapshot.getField(SimpleTerminalPosition.newInstance(position.getRow(),
						position.getColumn()));

				field.setValue(String.format(formater, fieldValue[i]));
				sendAction.getModifiedFields().add(field);

				if (i < fieldTypeDefinition.getCount() - 1) {
					position = position.moveBy(gaps[i * skip]);
				}
			}

		}
	}
}
