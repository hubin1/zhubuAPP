package com.ss.util;

import java.util.TreeSet;

/**
 * 用于设定返回状态的常量集合 的类
 * 
 * @author louis
 * @since 2015-12-22
 * @version 1.0
 *
 */
public class DefaultVariables {
	// 可以使用
	public static final int OK = 200;
	// 已经存在
	public static final int EXISTS = 500;
	// 没有找到内容
	public static final int NOTFOUND = 404;
	// 参数传递异常
	public static final int ERROR = 505;

	// 全局用来查找 user 信息的标记
	public static final String USERINFO = "user";
	public static final String USERINFO_DETAIL = "detail";
	/**
	 * 全局用来查找 org 社团信息的标记
	 */
	public static final String ORGINFO = "org";
	// 一次性验证码
	public static final String ONCECODE = "oncecode";
	// 注册时候用来请求的原始 phone
	public static final String ORIPHONE = "oriphone";

	// 初始设定每页显示的条数
	public static final int PAGE_SIZE = 16;

	// 视频格式定义
	public static final String[] SUFFIX = { ".mp4", ".mov", ".m4v", ".3gp" };

	public static final int VIDEO = 1;
	public static final int IMAGE = 2;

	// 常用的位置设定
	public static String video_position_actual;
	public static String image_position_actual;
	public static String video_img_position;
	public static String icon_position_actual;
	public static String other_files_folder;

	// 排序方式（视频）
	public final static int ALL = 1;
	public final static int NORL = 2;
	public final static int NEWER = 3;
	public final static int HOTEST = 4;

	// 确认按钮
	public final static int CONFIRMED = 1;
	public final static int UNCONFIRM = 0;

	// 需要被过滤的请求
	public final static TreeSet<String> SETS = new TreeSet<String>();

}
