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
package org.openlegacy.terminal.modules.table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openlegacy.modules.table.drilldown.TableScrollStopConditions;
import org.openlegacy.terminal.TerminalSession;
import org.openlegacy.terminal.actions.TerminalAction;
import org.openlegacy.terminal.definitions.ScreenTableDefinition;
import org.openlegacy.terminal.providers.TablesDefinitionProvider;
import org.openlegacy.terminal.table.ScreenTableScroller;
import org.openlegacy.utils.ReflectionUtil;
import org.springframework.util.Assert;

import java.text.MessageFormat;

import javax.inject.Inject;

/**
 * Default terminal session table scroller. Perform scroll down until the given row keys are matched. Uses TableStopCondition to
 * decide if the scrolling is not affective any more (e.g: reach to bottom)
 * 
 * @param <T>
 * 
 */
public class DefaultTableScroller<T> implements ScreenTableScroller<T> {

	@Inject
	private TablesDefinitionProvider tablesDefinitionProvider;

	private TerminalAction defaultNextAction;

	private final static Log logger = LogFactory.getLog(DefaultTableScroller.class);

	@SuppressWarnings("unchecked")
	public T scroll(TerminalSession terminalSession, Class<T> entityClass,
			TableScrollStopConditions<T> tableScrollStopConditions, Object... rowKeys) {

		T beforeScrolllEntity = terminalSession.getEntity(entityClass);

		if (tableScrollStopConditions.shouldStop(beforeScrolllEntity)) {
			logger.debug(MessageFormat.format("Table stop condition met for {0}. stopping scroll", entityClass));
			return null;
		}

		ScreenTableDefinition tableDefinition = ScrollableTableUtil.getSingleScrollableTableDefinition(tablesDefinitionProvider,
				entityClass).getValue();

		TerminalAction nextAction = tableDefinition.getNextScreenAction() != null ? tableDefinition.getNextScreenAction()
				: defaultNextAction;

		Assert.notNull(
				nextAction,
				MessageFormat.format("Next action not defined either as default nor at screen entity {0}",
						tableDefinition.getTableClass()));

		T afterScrolllEntity = (T)terminalSession.doAction(nextAction);

		if (tableScrollStopConditions.shouldStop(beforeScrolllEntity, afterScrolllEntity)) {
			logger.debug(MessageFormat.format("Table stop condition met for {0}. stopping scroll", entityClass));
			return null;
		}

		return afterScrolllEntity;

	}

	public void setDefaultNextAction(Class<? extends TerminalAction> defaultNextAction) {
		this.defaultNextAction = ReflectionUtil.newInstance(defaultNextAction);
	}
}
