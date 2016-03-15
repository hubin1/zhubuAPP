package com.fp.dao;

import java.sql.Connection;

public class SaveVideoDAO {
	private Connection con;

	public SaveVideoDAO(Connection con){
		this.con = con;
	}
}
