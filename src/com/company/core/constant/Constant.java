package com.company.core.constant;

import java.util.HashMap;
import java.util.Map;


/**
 * 全局常量
 * @author Dongfuming
 * @date 2016-5-11 上午11:42:55
 */
public class Constant {
	
	public static String PRIVILEGE_ADMINISTRATION = "administration";  // 行政管理 
	public static String PRIVILEGE_LOGISTICS = "logistics";  // 后勤服务 
	public static String PRIVILEGE_STUDY = "study";  // 在线学习
	public static String PRIVILEGE_TAX = "tax";  // 纳税服务
	public static String PRIVILEGE_SPACE = "space";  // 我的空间

	public static String USER = "LOGIN_USER"; 
	public static String USER_STATE_VALID = "1"; // 有效
	public static String USER_STATE_INVALID = "0"; // 无效
	public static String DEFAULT_USER_PASSWORD = "123456";
	public static String DEFAULT_USER_STATE = USER_STATE_VALID;
	public static String USER_EXCEL_FILE_NAME = "用户列表.xls"; 
	
	public static String ORDER_BY_DESC = "DESC"; // 降序
	public static String ORDER_BY_ASC = "ASC"; // 升序
	
	public static int DEFAULT_PAGE_SIZE = 3;
	public static int DEFAULT_PAGE_NUMBER = 1;
	public static int SESSION_INACTIVE_INTERVAL = 30 * 60; // 以秒为单位
	
	public static String COMPLAIN_STATE_UNDONE = "0"; // 未受理
	public static String COMPLAIN_STATE_DONE = "1"; // 已受理
	public static String COMPLAIN_STATE_INVALID = "2"; // 已失效
	
	public static String INFO_STATE_STOP = "0"; // 停用
	public static String ROLE_STATE_VALID = "1"; // 有效
	public static String ROLE_STATE_INVALID = "0"; // 无效
	
	public static String INFO_TYPE_NOTICE = "notice"; // 通知公告
	public static String INFO_TYPE_POLICY= "policy"; // 政策速递
	public static String INFO_TYPE_GUIDE = "guide"; // 纳税指导
	
	public static Map<String, String> COMPLAIN_STATE_MAP;
	public static Map<String, String> PRIVILEGE_MAP;
	public static Map<String, String> INFO_TYPE_MAP;
	
	static {
		initializePrivilegeMap();
		initializeComplainStateMap();
		initializeInfoTypeMap();
	}
	
	private static void initializePrivilegeMap() {
		PRIVILEGE_MAP = new HashMap<String, String>();
		PRIVILEGE_MAP.put(PRIVILEGE_ADMINISTRATION, "行政管理");
		PRIVILEGE_MAP.put(PRIVILEGE_LOGISTICS, "后勤服务");
		PRIVILEGE_MAP.put(PRIVILEGE_STUDY, "在线学习");
		PRIVILEGE_MAP.put(PRIVILEGE_TAX, "纳税服务");
		PRIVILEGE_MAP.put(PRIVILEGE_SPACE, "我的空间");
	}
	
	private static void initializeComplainStateMap() {
		COMPLAIN_STATE_MAP = new HashMap<String, String>();
		COMPLAIN_STATE_MAP.put(COMPLAIN_STATE_UNDONE, "待受理");
		COMPLAIN_STATE_MAP.put(COMPLAIN_STATE_DONE, "已受理");
		COMPLAIN_STATE_MAP.put(COMPLAIN_STATE_INVALID, "已失效");
	}
	
	private static void initializeInfoTypeMap() {
		INFO_TYPE_MAP = new HashMap<String, String>();
		INFO_TYPE_MAP.put(INFO_TYPE_NOTICE, "通知公告");
		INFO_TYPE_MAP.put(INFO_TYPE_POLICY, "政策速递");
		INFO_TYPE_MAP.put(INFO_TYPE_GUIDE, "纳税指导");
	}
}
