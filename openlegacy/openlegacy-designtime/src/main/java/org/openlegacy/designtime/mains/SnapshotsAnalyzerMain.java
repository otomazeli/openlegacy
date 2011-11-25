package org.openlegacy.designtime.mains;

import org.openlegacy.designtime.terminal.analyzer.TerminalSnapshotsAnalyzer;
import org.openlegacy.designtime.terminal.analyzer.TerminalSnapshotsLoader;
import org.openlegacy.terminal.TerminalSnapshot;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SnapshotsAnalyzerMain {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Usage:\njava org.openlegacy.designtime.mains.SnapshotsAnalyzerMain screens-resource-folder");
			return;
		}
		String root = args[0];

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/openlegacy-designtime-context.xml");

		TerminalSnapshotsLoader snapshotsLoader = applicationContext.getBean(TerminalSnapshotsLoader.class);
		try {
			List<TerminalSnapshot> snapshots = snapshotsLoader.loadSnapshots(root);
			TerminalSnapshotsAnalyzer snapshotsAnalyzer = applicationContext.getBean(TerminalSnapshotsAnalyzer.class);
			snapshotsAnalyzer.analyzeSnapshots(snapshots);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}