package com.ss.pojo;

import java.util.Date;

public class Baseinfo_Detail {
    private Integer duid;

    private Integer buid;

    private String nick;

    private String name;

    private Byte sex;

    private String special;

    private String hobby;

    private String school;

    private String major;

    private Date studytime;

//    private String icon;
//    
//    private String ipos;

//    public String getIpos() {
//		return ipos;
//	}
//
//	public void setIpos(String ipos) {
//		this.ipos = ipos;
//	}

	public Integer getDuid() {
        return duid;
    }

    public void setDuid(Integer duid) {
        this.duid = duid;
    }

    public Integer getBuid() {
        return buid;
    }

    public void setBuid(Integer buid) {
        this.buid = buid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick == null ? null : nick.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special == null ? null : special.trim();
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby == null ? null : hobby.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public Date getStudytime() {
        return studytime;
    }

    public void setStudytime(Date studytime) {
        this.studytime = studytime;
    }

//    public String getIcon() {
//        return icon;
//    }
//
//    public void setIcon(String icon) {
//        this.icon = icon == null ? null : icon.trim();
//    }
}