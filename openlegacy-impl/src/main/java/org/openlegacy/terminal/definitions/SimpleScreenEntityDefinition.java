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
package org.openlegacy.terminal.definitions;

import org.openlegacy.definitions.ActionDefinition;
import org.openlegacy.definitions.support.AbstractEntityDefinition;
import org.openlegacy.terminal.ScreenSize;
import org.openlegacy.terminal.TerminalSnapshot;
import org.openlegacy.terminal.services.ScreenIdentification;
import org.openlegacy.terminal.support.SimpleScreenIdentification;
import org.openlegacy.terminal.support.TerminalPositionContainerComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleScreenEntityDefinition extends AbstractEntityDefinition<ScreenFieldDefinition> implements ScreenEntityDefinition {

	private ScreenIdentification screenIdentification = new SimpleScreenIdentification();
	private NavigationDefinition navigationDefinition;
	private Map<String, ScreenTableDefinition> tableDefinitions = new HashMap<String, ScreenTableDefinition>();
	private Map<String, ScreenPartEntityDefinition> partDefinitions = new HashMap<String, ScreenPartEntityDefinition>();
	private TerminalSnapshot snapshot;
	private List<ActionDefinition> actions = new ArrayList<ActionDefinition>();
	private boolean window;
	private boolean child;

	private TerminalSnapshot accessedFromSnapshot;
	private ScreenEntityDefinition accessedFromScreenDefinition;
	private ScreenSize screenSize;

	public SimpleScreenEntityDefinition(String entityName, Class<?> entityClass) {
		super(entityName, entityClass);
	}

	public ScreenIdentification getScreenIdentification() {
		return screenIdentification;
	}

	public void setScreenIdentification(ScreenIdentification screenIdentification) {
		this.screenIdentification = screenIdentification;
	}

	public NavigationDefinition getNavigationDefinition() {
		return navigationDefinition;
	}

	public void setNavigationDefinition(NavigationDefinition navigationDefinition) {
		this.navigationDefinition = navigationDefinition;
	}

	public Map<String, ScreenTableDefinition> getTableDefinitions() {
		return tableDefinitions;
	}

	public Map<String, ScreenPartEntityDefinition> getPartsDefinitions() {
		return partDefinitions;
	}

	public TerminalSnapshot getSnapshot() {
		return this.snapshot;
	}

	public void setSnapshot(TerminalSnapshot snapshot) {
		this.snapshot = snapshot;
	}

	public List<ActionDefinition> getActions() {
		return actions;
	}

	public boolean isWindow() {
		return window;
	}

	public void setWindow(boolean window) {
		this.window = window;
	}

	public TerminalSnapshot getOriginalSnapshot() {
		return getSnapshot();
	}

	public TerminalSnapshot getAccessedFromSnapshot() {
		return accessedFromSnapshot;
	}

	public void setAccessedFromSnapshot(TerminalSnapshot accessedFromSnapshot) {
		this.accessedFromSnapshot = accessedFromSnapshot;
	}

	public ScreenEntityDefinition getAccessedFromScreenDefinition() {
		return this.accessedFromScreenDefinition;
	}

	public void setAccessedFromScreenDefinition(ScreenEntityDefinition accessedFromScreenDefinition) {
		this.accessedFromScreenDefinition = accessedFromScreenDefinition;
	}

	public ScreenSize getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(ScreenSize screenSize) {
		this.screenSize = screenSize;
	}

	public void setChild(boolean child) {
		this.child = child;
	}

	public boolean isChild() {
		return child;
	}

	public List<ScreenFieldDefinition> getSortedFields() {
		Collection<ScreenFieldDefinition> fields = getFieldsDefinitions().values();

		List<ScreenFieldDefinition> sortedFields = new ArrayList<ScreenFieldDefinition>(fields);
		Collections.sort(sortedFields, TerminalPositionContainerComparator.instance());
		return sortedFields;
	}
}
