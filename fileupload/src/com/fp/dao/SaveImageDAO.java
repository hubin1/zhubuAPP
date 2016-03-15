package com.fp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fp.util.ConnectionFactory;

/**
 * 保存图片信息到数据库的类
 * 
 * @author louis
 *
 */
public class SaveImageDAO {
	private Connection con;

	public SaveImageDAO() {
		this.con = ConnectionFactory.stack.get();
	}

	public void save(String fileName, String pos, int udid) {
		String sql = "INSERT INTO examine_image(filename, posondisk, updatetime, udid) value(?,?,?,?)";
		try {
			PreparedStatement ps = this.con.prepareStatement(sql);

			ps.setString(1, fileName);
			ps.setString(2, pos);
			long mill = new java.util.Date().getTime();
			ps.setDate(3, new Date(mill));
			ps.setInt(4, udid);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
