package org.openlegacy.support.annotation_loaders;

import org.openlegacy.HostEntitiesRegistry;
import org.openlegacy.annotations.screen.ChildScreenEntity;
import org.openlegacy.terminal.definitions.SimpleChildScreenDefinition;
import org.openlegacy.terminal.spi.ScreenEntitiesRegistry;

import java.lang.annotation.Annotation;

public class ChildScreenAnnotationLoader implements FieldAnnotationsLoader {

	public boolean match(Annotation annotation) {
		return annotation.annotationType() == ChildScreenEntity.class;
	}

	@SuppressWarnings({ "rawtypes" })
	public void load(HostEntitiesRegistry entitiesRegistry, String fieldName, Annotation annotation, Class<?> containingClass) {
		ScreenEntitiesRegistry screenEntitiesRegistry = (ScreenEntitiesRegistry)entitiesRegistry;
		ChildScreenEntity childScreenEntityAnnotation = (ChildScreenEntity)annotation;

		SimpleChildScreenDefinition ChildScreenDefinition = new SimpleChildScreenDefinition(fieldName);
		ChildScreenDefinition.setFetchMode(childScreenEntityAnnotation.fetchMode());
		ChildScreenDefinition.setStepInto(childScreenEntityAnnotation.stepInto());

		screenEntitiesRegistry.get(containingClass).getChildScreenDefinitions().put(fieldName, ChildScreenDefinition);
	}

}
