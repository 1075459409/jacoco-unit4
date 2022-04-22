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

public class JacocoUtil {

	/**
	 * 
	 */
	private JacocoUtil() {
	}

	/**
	 * 
	 * @category 功能
	 * @param description
	 * @return
	 * @Date :2021年6月4日下午3:17:15
	 */
	public static String getName(Description description) {
		return description.getClassName() + "%%" + description.getMethodName();
	}

	public static String getName(final String strData) {
		String result = strData.replaceAll(".*\\[test:", "").replace(")]", "");
		// System.out.println(result);
		String className = result.replaceAll(".*\\(", "");
		String methodName = result.replaceAll("\\(.*", "");
		return className + "%%" + methodName;
	}

}
