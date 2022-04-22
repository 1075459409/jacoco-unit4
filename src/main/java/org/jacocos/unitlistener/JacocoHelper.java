/**
 * Copyright (C) 2020-2021 org.jacocos
 *
 * This file is part of org.jacocos
 * @author org.jacocos
 * @version 1.0.0
 * 
 **/
package org.jacocos.unitlistener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**

 * 描    述: jacoco收集覆盖率统计辅助类
 * ================================================
 */
public class JacocoHelper {

	// ec文件的路径
	private static String DEFAULT_COVERAGE_FILE_PATH = "/coverage.ec";

	// 项目路径
	private static String PROJECT_PATH;

	/**
	 * 初始化
	 * @param projectPath  '项目路径' + '/app/build/outputs/code-coverage/'
	 * @param isDebug 是否打开log
	 */
	public static void init(String projectPath, boolean isDebug) {
		PROJECT_PATH = projectPath + "/app/build/outputs/code-coverage/";

	}

	/**
	 * 生成ec文件
	 *
	 * @param isNew 是否重新创建ec文件
	 */
	public static void generateEcFile(boolean isNew) {

		OutputStream out = null;
		File mCoverageFilePath = new File(DEFAULT_COVERAGE_FILE_PATH);
		try {
			if (isNew && mCoverageFilePath.exists()) {
				System.out.println("JacocoHelper_generateEcFile: 清除旧的ec文件");
				mCoverageFilePath.delete();
			}
			if (!mCoverageFilePath.exists()) {
				mCoverageFilePath.createNewFile();
			}
			out = new FileOutputStream(mCoverageFilePath.getPath(), true);
			Object agent = Class.forName("org.jacoco.agent.rt.RT").getMethod("getAgent").invoke(null);
			out.write((byte[]) agent.getClass().getMethod("getExecutionData", boolean.class).invoke(agent, false));
		} catch (Exception e) {
			System.out.println("JacocoHelper_generateEcFile: " + e.getMessage());
		} finally {
			if (out == null)
				return;
			try {
				out.close();
				System.out.println("JacocoHelper_generateEcFile: " + mCoverageFilePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(getAdbPullCmd());
	}

	/**
	 * 导出jacoco生成的ec文件到项目相关目录下
	 * @return adb 命令
	 */
	public static String getAdbPullCmd() {
		String adb = "adb pull " + DEFAULT_COVERAGE_FILE_PATH + " " + PROJECT_PATH;
		System.out.println("导出ec文件命令行: " + adb);
		return adb;
	}
}