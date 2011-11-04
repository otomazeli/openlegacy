package org.openlegacy.loaders.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openlegacy.HostEntitiesRegistry;
import org.openlegacy.annotations.screen.ScreenEntity;
import org.openlegacy.loaders.ClassAnnotationsLoader;
import org.openlegacy.terminal.definitions.SimpleScreenEntityDefinition;
import org.openlegacy.terminal.spi.ScreenEntitiesRegistry;

import java.lang.annotation.Annotation;
import java.text.MessageFormat;

public class ScreenEntityAnnotationLoader implements ClassAnnotationsLoader {

	private final static Log logger = LogFactory.getLog(ScreenEntityAnnotationLoader.class);

	public boolean match(Annotation annotation) {
		return annotation.annotationType() == ScreenEntity.class;
	}

	public void load(HostEntitiesRegistry<?, ?> entitiesRegistry, Annotation annotation, Class<?> containingClass) {

		ScreenEntity screenEntity = (ScreenEntity)annotation;
		ScreenEntitiesRegistry screenEntitiesRegistry = (ScreenEntitiesRegistry)entitiesRegistry;

		String screenName = screenEntity.name().length() > 0 ? screenEntity.name() : containingClass.getSimpleName();
		SimpleScreenEntityDefinition screenEntityDefinition = new SimpleScreenEntityDefinition(screenName, containingClass);
		screenEntityDefinition.setName(screenName);
		screenEntityDefinition.setType(screenEntity.screenType());
		logger.info(MessageFormat.format("Screen \"{0}\" was added to the screen registry ({1})", screenName,
				containingClass.getName()));

		screenEntitiesRegistry.add(screenEntityDefinition);
	}

}