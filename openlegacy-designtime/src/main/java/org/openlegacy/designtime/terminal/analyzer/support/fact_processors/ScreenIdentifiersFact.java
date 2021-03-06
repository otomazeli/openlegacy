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
package org.openlegacy.designtime.terminal.analyzer.support.fact_processors;

import org.openlegacy.designtime.terminal.analyzer.ScreenFact;
import org.openlegacy.terminal.TerminalField;

import java.util.List;

public class ScreenIdentifiersFact implements ScreenFact {

	private List<TerminalField> identifiers;

	public ScreenIdentifiersFact(List<TerminalField> identifiers) {
		this.identifiers = identifiers;
	}

	public List<TerminalField> getIdentifiers() {
		return identifiers;
	}
}
