package org.joywins.domain;
//...463p.추가.

import java.util.Date;

public class MsgPointsVO {

	private String uid;
	private Integer mid;
	private int upoint;
	private String activity;
	private Date act_date;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public int getUpoint() {
		return upoint;
	}
	public void setUpoint(int upoint) {
		this.upoint = upoint;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public Date getAct_date() {
		return act_date;
	}
	public void setAct_date(Date act_date) {
		this.act_date = act_date;
	}
	
	@Override
	public String toString() {
		return "UserPointsVO [uid=" + uid 
						 + ", mid=" + mid 
						 + ", upoint=" + upoint 
						 + ", activity=" + activity
						 + ", act_date=" + act_date + "]";
	}

}
