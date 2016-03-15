package com.fp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 连接数据库的工厂
 * 
 * @author louis
 *
 */
final public class ConnectionFactory {
	static {
		try {
			Class.forName(Variables.driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static ThreadLocal<Connection> stack = new ThreadLocal<Connection>();
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(Variables.url, Variables.username, Variables.password);
			conn.setAutoCommit(false);
			stack.set(conn);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return conn;
	}
	
	public static void close(){
		try {
			stack.get().commit();
			stack.get().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
