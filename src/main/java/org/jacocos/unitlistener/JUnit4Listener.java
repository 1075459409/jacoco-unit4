/**
 * Copyright (C) 2020-2021 org.jacocos
 *
 * This file is part of org.jacocos
 * @author org.jacocos
 * @version 1.0.0
 * 
 **/
package org.jacocos.unitlistener;

import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunListener.ThreadSafe;

/**
 * JUnit listener that instructs JaCoCo to create one session per test.
 * Based on: https://github.com/SonarSource/sonar-java/blob/master/sonar-jacoco-listeners/src/main/java/org/sonar/java/jacoco/JUnitListener.java
 */
@ThreadSafe
public class JUnit4Listener extends RunListener {

	
	protected JacocoController jacoco;

	/**
	 * Called before any tests have been run.
	 * */
	@Override
	public void testRunStarted(Description description) throws java.lang.Exception {
		System.out.println("[JUnitListener4] testRunStarted : " + description.testCount());
	}

	@Override
	public void testStarted(Description description) {
		//System.out.println("[JUnitListener4] testStarted : "+ description.getMethodName());
		getJacocoController().onTestStart();
	}

	@Override
	public void testFinished(Description description) {
		//System.out.println("[JUnitListener4] testFinished : "+ getName(description));
		jacoco.onTestFinish(description);
	}

	protected JacocoController getJacocoController() {
		if (jacoco == null) {
			jacoco = JacocoController.getInstance();
		}
		return jacoco;
	}


}
