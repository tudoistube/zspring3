package org.joywins.domain;

import java.util.Date;

/*
 * ...128p.
	create table ZTBL_TEST_MEMBER
	(
		 user_id	nvarchar(50)	not null
	    ,user_pw	nvarchar(50)	not null
	    ,user_name	nvarchar(50)	not null
	    ,email		nvarchar(100)
		,reg_date 	TIMESTAMP NOT NULL DEFAULT 0
		,update_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP    
	    ,primary key(USER_ID)
	);
 */
public class MemberVO {

	private String user_id;
	private String user_pw;
	private String user_name;
	private String email;
	private Date reg_date;
	private Date update_date;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	@Override
	public String toString() {
		return "XMemberVO [user_id=" + user_id 
					  + ", user_pw=" + user_pw 
					  + ", user_name=" + user_name 
					  + ", email=" + email 
					  + ", reg_date=" + reg_date 
					  + ", update_date=" + update_date + "]";
	}
	
		
	
	
	
}


