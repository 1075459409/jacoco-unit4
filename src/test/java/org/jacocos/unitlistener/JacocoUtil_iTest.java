/**
 * Copyright (C) 2020-2021 org.jacocos
 *
 * This file is part of org.jacocos
 * @author org.jacocos
 * @version 1.0.0
 * 
 **/
package org.jacocos.unitlistener;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * The class <code>JacocoHelper_iTest</code> contains tests for the class <code>{@link JacocoHelper}</code>.
 *
 * @generatedBy at 21-4-24 下午8:13
 * @version $Revision: 1.0 $
 */
public class JacocoUtil_iTest {

	@Test
	public void test_getName()
	{
		String actual=JacocoUtil.getName("[engine:junit-vintage]/[runner:org.jacocos.unitlistener.sample1.Sample1_iTest]/[test:test_getInt_1(org.jacocos.unitlistener.sample1.Sample1_iTest)]");
		System.out.println(actual);
		
		assertEquals("org.jacocos.unitlistener.sample1.Sample1_iTest%%test_getInt_1", actual);
	}
}