package com.company.core.constant;

import java.util.HashMap;
import java.util.Map;


/**
 * 全局常量
 * @author Dongfuming
 * @date 2016-5-11 上午11:42:55
 */
public class Constant {
	
	/*----------------------系统权限集合--------------------------*/
	public static String PRIVILEGE_ADMINISTRATION = "administration";  // 行政管理 
	public static String PRIVILEGE_LOGISTICS = "logistics";  // 后勤服务 
	public static String PRIVILEGE_STUDY = "study";  // 在线学习
	public static String PRIVILEGE_TAX = "tax";  // 纳税服务
	public static String PRIVILEGE_SPACE = "space";  // 我的空间

	public static Map<String, String> PRIVILEGE_MAP;
	
	static {
		PRIVILEGE_MAP = new HashMap<String, String>();
		PRIVILEGE_MAP.put(PRIVILEGE_ADMINISTRATION, "行政管理");
		PRIVILEGE_MAP.put(PRIVILEGE_LOGISTICS, "后勤服务");
		PRIVILEGE_MAP.put(PRIVILEGE_STUDY, "在线学习");
		PRIVILEGE_MAP.put(PRIVILEGE_TAX, "纳税服务");
		PRIVILEGE_MAP.put(PRIVILEGE_SPACE, "我的空间");
	}
}
