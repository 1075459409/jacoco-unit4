/**
 * Copyright (C) 2020-2021 org.jacocos
 *
 * This file is part of org.jacocos
 * @author org.jacocos
 * @version 1.0.0
 * 
 **/
package org.jacocos.unitlistener;

import java.io.IOException;

import org.jacoco.agent.rt.IAgent;
import org.jacoco.agent.rt.RT;
import org.junit.runner.Description;

// Based on: https://github.com/SonarSource/sonar-java/blob/master
// /sonar-jacoco-listeners/src/main/java/org/sonar/java/jacoco/JacocoController.java
final class JacocoController {

	private static final String ERROR = "Unable to access JaCoCo Agent - make sure that you use JaCoCo and "
			+ "version not lower than 0.8.6";

	private final IAgent agent;

	private boolean testStarted = false;

	// Visible for testing
	static JacocoController singleton;

	private JacocoController() {
		try {
			this.agent = RT.getAgent();
		} catch (Exception | NoClassDefFoundError e) {
			e.printStackTrace();
			throw new JacocoControllerError(ERROR, e);
		}
	}

	JacocoController(IAgent agent) {
		this.agent = agent;
	}

	public static synchronized JacocoController getInstance() {
		if (singleton == null) {
			singleton = new JacocoController();
		}
		return singleton;
	}

	public synchronized void onTestStart() {
		// System.out.println("JacocoController:onTestStart");
		if (testStarted) {
			throw new JacocoControllerError("Looks like several tests executed in parallel in the same JVM, "
					+ "thus coverage per test can't be recorded correctly.");
		}
		// Dump coverage between tests

		dump("");
		testStarted = true;
	}

	public synchronized void onTestFinish(Description description) {
		// System.out.println("JacocoController:onTestFinish:" + description.getMethodName());
		// Dump coverage for test
		dump(JacocoUtil.getName(description));
		// copyFile(description);
		testStarted = false;
	}


	public synchronized void onTestFinish(String name) {
		// System.out.println("JacocoController:onTestFinish:" + name);
		// Dump coverage for test
		dump(name);

		testStarted = false;
	}



	private void dump(String sessionId) {
		agent.setSessionId(sessionId);
		try {
			agent.dump(true);
		} catch (IOException e) {
			throw new JacocoControllerError(e);
		}
	}

	public static class JacocoControllerError extends Error {
		public JacocoControllerError(String message) {
			super(message);
		}

		public JacocoControllerError(String message, Throwable cause) {
			super(message, cause);
		}

		public JacocoControllerError(Throwable cause) {
			super(cause);
		}
	}

}
