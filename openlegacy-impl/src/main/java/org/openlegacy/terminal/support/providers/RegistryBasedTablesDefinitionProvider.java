/*******************************************************************************
 * Copyright (c) 2012 OpenLegacy Inc.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     OpenLegacy Inc. - initial API and implementation
 *******************************************************************************/
package org.openlegacy.terminal.support.providers;

import org.openlegacy.terminal.definitions.ScreenEntityDefinition;
import org.openlegacy.terminal.definitions.ScreenTableDefinition;
import org.openlegacy.terminal.providers.TablesDefinitionProvider;
import org.openlegacy.terminal.services.ScreenEntitiesRegistry;

import java.io.Serializable;
import java.util.Map;

import javax.inject.Inject;

/**
 * A table definition provider based on open legacy @ScreenTable annotation
 * 
 */
public class RegistryBasedTablesDefinitionProvider implements TablesDefinitionProvider, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ScreenEntitiesRegistry screenEntitiesRegistry;

	// private DefaultScreenEntitiesRegistry screenEntitiesRegistry;

	public Map<String, ScreenTableDefinition> getTableDefinitions(Class<?> screenEntity) {
		ScreenEntityDefinition screenEntityDefinition = screenEntitiesRegistry.get(screenEntity);
		return screenEntityDefinition.getTableDefinitions();
	}
}
