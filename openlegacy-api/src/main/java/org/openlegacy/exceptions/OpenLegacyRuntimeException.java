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
package org.openlegacy.exceptions;

/**
 * The root exception for all open legacy exception types. Extends RuntimeException for simpler exception handing
 * 
 */
public class OpenLegacyRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OpenLegacyRuntimeException(Exception e) {
		super(e);
	}

	public OpenLegacyRuntimeException(String s) {
		super(s);
	}

	public OpenLegacyRuntimeException(String s, Exception e) {
		super(s, e);
	}
}
