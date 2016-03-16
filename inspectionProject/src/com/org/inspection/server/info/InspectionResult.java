package com.org.inspection.server.info;

public class InspectionResult {

	
	private int id;
	
	private int option_id;
	
	private int status;
	
	private String inspection_result;
	
	private String pic_path;
	
	private int inspection_type;
	
	private String update_time;
	
	private int update_user_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOption_id() {
		return option_id;
	}

	public void setOption_id(int option_id) {
		this.option_id = option_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getInspection_result() {
		return inspection_result;
	}

	public void setInspection_result(String inspection_result) {
		this.inspection_result = inspection_result;
	}

	public String getPic_path() {
		return pic_path;
	}

	public void setPic_path(String pic_path) {
		this.pic_path = pic_path;
	}

	public int getInspection_type() {
		return inspection_type;
	}

	public void setInspection_type(int inspection_type) {
		this.inspection_type = inspection_type;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public int getUpdate_user_id() {
		return update_user_id;
	}

	public void setUpdate_user_id(int update_user_id) {
		this.update_user_id = update_user_id;
	}
	
	
	
	
}
