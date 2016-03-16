package com.org.inspection.server.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.haocom.util.DBAccess;
import com.haocom.util.TimeStamp;
import com.org.inspection.server.info.InspectionResult;
import com.org.inspection.server.info.UserInfo;


/**
 * 系统管理器. <br>
 * 系统管理器.
 * <p>
 * Copyright: Copyright (c) 2009-9-9 下午03:26:35
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: gaowei@c-platform.com
 * <p>
 * Version: 1.0
 */
public class ServerSysManager extends Thread {

	/** 对象实例 */
	private static ServerSysManager instance;

	/** 获取对象实例 */
	public static ServerSysManager getInstance() {
		return instance;
	}

	/**
	 * 初始化对象实例
	 * 
	 * @throws Exception
	 */
	public synchronized static void init() throws Exception {
		if (instance != null) {
			return;
		}
		instance = new ServerSysManager();
		instance.start();

	}

	/** 数据库连接器. */
	private final DBAccess dba;

	/** Logger. */
	private final Logger logger;

	/**
	 * 对象创建
	 */
	private ServerSysManager() throws Exception {
		logger = Logger.getLogger(ServerSysManager.class);

		dba = new DBAccess();

		setName("VGOPSysManager");

	}

	/**
	 * 停止当前线程
	 */
	public void shutdown() {
		instance.interrupt();

	}
	
	
	/**
	 * 查找user信息
	 * @throws Exception 
	 */
	public UserInfo getUserInfoByPositon(String longitude, String latitude) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer buffer = new StringBuffer(100);
		buffer.setLength(0);
		buffer.append(" select user.id, user.create_time, user.change_pwd_time, user.lock_status, user.login_name, user.remark, user.status, user.update_time, user.user_name, user.user_pwd, user.terminal_id, user.dept_id, user.position_name ");
		buffer.append("  from t_sys_user user,t_ch_user_position t  ");
		buffer.append("  where user.id = t.user_id  ");
		buffer.append("  and t.longitude = ? ");
		buffer.append("  and t.latitude = ? ");
		
		Connection conn = dba.getConnection();
		UserInfo userInfo = null;
		
		try{
			pstmt = conn.prepareStatement(buffer.toString());
			pstmt.setString(1, longitude);
			pstmt.setString(2, latitude);
			rs = pstmt.executeQuery();
			if(rs.next()){
				userInfo = new UserInfo();
				userInfo.setId(rs.getInt("id"));
				userInfo.setCreate_time(rs.getString("create_time"));
				userInfo.setChange_pwd_time(rs.getString("change_pwd_time"));
				userInfo.setLock_status(rs.getInt("lock_status"));
				userInfo.setLogin_name(rs.getString("login_name"));
				userInfo.setRemark(rs.getString("remark"));
				userInfo.setStatus(rs.getInt("status"));
				userInfo.setUpdate_time(rs.getString("update_time"));
				userInfo.setUser_name(rs.getString("user_name"));
				userInfo.setUser_pwd(rs.getString("user_pwd"));
				userInfo.setTerminal_id(rs.getString("terminal_id"));
				userInfo.setDept_id(rs.getInt("dept_id"));
				userInfo.setPosition_name(rs.getString("position_name"));
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.info("getUserInfoByPositon"+e);
			throw e;
		}finally{
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			}
			catch (Exception ex) {
				// TODO: handle exception
			}

			dba.freeConnection(conn);
		}
		
		return userInfo;
	}
	
	
	/**
	 * 插入singin（签到）表
	 * @throws Exception 
	 */
	
	public void insertintoSignin(String userId, String longitude, String latitude, String status) throws Exception{
		PreparedStatement pstmt = null;
		StringBuffer buffer = new StringBuffer(100);
		buffer.setLength(0);
		buffer.append(" insert into  t_ch_sign_in ");
		buffer.append("  (sign_in_time, status, user_id, longitude, latitude)  ");
		buffer.append("  values(?,?,?,?,?)  ");		
		Connection conn = dba.getConnection();
		String time = nowdate2string();
		try{
			
			pstmt = conn.prepareStatement(buffer.toString());
			int i = 1;
			pstmt.setString(i++,time);
			pstmt.setInt(i++, Integer.parseInt(status));
			pstmt.setInt(i++, Integer.parseInt(userId));
			pstmt.setString(i++, longitude);
			pstmt.setString(i++, latitude);
			pstmt.execute();
		}catch(Exception e){
			e.printStackTrace();
			logger.info("insertintoSignin"+e);
			throw e;
		}finally{
			
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			}
			catch (Exception ex) {
				// TODO: handle exception
			}

			dba.freeConnection(conn);
		}
	}
	
	/**
	 * 检查签到状态
	 * @return
	 * @throws Exception 
	 */
	
	public boolean checkSignin (String userId) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		StringBuffer buffer = new StringBuffer(100);
		buffer.setLength(0);
		buffer.append(" select t.id ");
		buffer.append("  from t_ch_sign_in t  ");
		buffer.append("  where user_id = ?  ");
		buffer.append("  and  sign_in_time > ? ");
		buffer.append("  and  sign_in_time < ? ");
		
		
		//日期转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		//日期加一天
		Date t1 = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(t1);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        //当前时间
        Date t2 = cal.getTime();
        
        String t11 = sdf.format(t1);
        String t22 = sdf.format(t2);
        
        Date t3 = sdf.parse(t11);
        Date t4 = sdf.parse(t22);
        
        String beforeTime = sdf1.format(t3);
        String afterTime = sdf1.format(t4);
        
        Connection conn = dba.getConnection();
		
		try{
			pstmt = conn.prepareStatement(buffer.toString());
			pstmt.setInt(1, Integer.parseInt(userId));
			pstmt.setString(2, beforeTime);
			pstmt.setString(3, afterTime);
			rs = pstmt.executeQuery();
			if(rs.next()){
				flag = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info("checkSignin"+e);
			throw e;
		}finally{
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			}
			catch (Exception ex) {
			}
			dba.freeConnection(conn);
		}
		
		return flag;
	}
	
	
	/**
	 * 根据巡检点编号，查找二维码图片地址
	 * @return
	 * @throws Exception 
	 */
	
	public void insertResult(InspectionResult result) throws Exception{
		PreparedStatement pstmt = null;
		StringBuffer buffer = new StringBuffer(100);
		buffer.setLength(0);
		buffer.append(" insert into t_ch_inspection_result ");
		buffer.append("  (option_id, status, inspection_result, pic_path, inspection_type, update_time, update_user_id)  ");
		buffer.append("  values (?,?,?,?,?,?,?) ");
		
		Connection conn = dba.getConnection();
		result.setUpdate_time(nowdate2string());
		
		try{
			pstmt = conn.prepareStatement(buffer.toString());
			int i=1;
			pstmt.setInt(i++, result.getOption_id());
			pstmt.setInt(i++, result.getStatus());
			pstmt.setString(i++, result.getInspection_result());
			pstmt.setString(i++, result.getPic_path());
			pstmt.setInt(i++, result.getInspection_type());
			pstmt.setString(i++, result.getUpdate_time());
			pstmt.setInt(i++, result.getUpdate_user_id());
			pstmt.execute();
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info("insertResult"+e);
			throw e;
		}finally{
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			}
			catch (Exception ex) {
			}
			dba.freeConnection(conn);
		}

	}
	
	
	public int insertOption(String option_name,int pontId) throws Exception{
		PreparedStatement pstmt = null;
		StringBuffer buffer = new StringBuffer(100);
		buffer.setLength(0);
		buffer.append(" insert into  t_ch_inspection_option ");
		buffer.append("  (point_id, option_name, update_time)  ");
		buffer.append("  values(?,?,?)  ");	
		String time = nowdate2string();
		Connection conn = dba.getConnection();
		int optionId = -1;
		try{
			
			pstmt = conn.prepareStatement(buffer.toString(),Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			pstmt.setInt(i++, pontId);
			pstmt.setString(i++, option_name);
			pstmt.setString(i++, time);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys(); //获取结果 
			if(rs.next()){
				optionId = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.info("insertintoSignin"+e);
			throw e;
		}finally{
			
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			}
			catch (Exception ex) {
				// TODO: handle exception
			}

			dba.freeConnection(conn);
		}
		return optionId;
	}
	
	
	public int getPointId (String pointNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer buffer = new StringBuffer(100);
		buffer.setLength(0);
		buffer.append(" select t.id ");
		buffer.append("  from t_ch_inspection_point t  ");
		buffer.append("  where t.point_no = ?  ");
		int pointId = -1;
        Connection conn = dba.getConnection();
		
		try{
			pstmt = conn.prepareStatement(buffer.toString());
			pstmt.setString(1, pointNo);
			rs = pstmt.executeQuery();
			if(rs.next()){
				pointId =  rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info("checkSignin"+e);
			throw e;
		}finally{
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			}
			catch (Exception ex) {
			}
			dba.freeConnection(conn);
		}
		return pointId;
	}
	
	
	
	private String nowdate2string(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		long time = System.currentTimeMillis();
		Date date = new Date(time);
		return sdf.format(date);
	}
	
	
}
