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
package org.openlegacy.terminal.support;

import org.openlegacy.exceptions.UnableToLoadSnapshotException;
import org.openlegacy.terminal.TerminalSnapshot;
import org.openlegacy.terminal.TerminalSnapshotsLoader;
import org.openlegacy.terminal.persistance.TerminalPersistedSnapshot;
import org.openlegacy.utils.XmlSerializationUtil;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

@Component
public class DefaultTerminalSnapshotsLoader implements TerminalSnapshotsLoader {

	public List<TerminalSnapshot> loadSnapshots(String root, final String... fileNames) throws UnableToLoadSnapshotException {
		final boolean allFiles = fileNames.length > 0;

		File rootFolder = new File(root);
		File[] files = rootFolder.listFiles(new FilenameFilter() {

			public boolean accept(File dir, String name) {
				if (allFiles) {
					for (String fileName : fileNames) {
						if (name.equals(fileName)) {
							return true;
						}
					}
				} else {
					return name.endsWith(".xml");
				}
				return false;
			}
		});

		List<TerminalSnapshot> snapshots = new ArrayList<TerminalSnapshot>();
		for (File file : files) {
			TerminalSnapshot terminalSnapshot = load(file.getAbsolutePath());
			snapshots.add(terminalSnapshot);
		}
		return snapshots;
	}

	public TerminalSnapshot load(String path) {
		TerminalSnapshot terminalSnapshot;
		try {
			terminalSnapshot = XmlSerializationUtil.deserialize(TerminalPersistedSnapshot.class, new FileInputStream(path));
		} catch (FileNotFoundException e) {
			throw (new UnableToLoadSnapshotException(e));
		} catch (JAXBException e) {
			throw (new UnableToLoadSnapshotException(e));
		}
		return terminalSnapshot;
	}
}
