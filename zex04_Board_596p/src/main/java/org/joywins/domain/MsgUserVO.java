package org.joywins.domain;
//...464p.

public class MsgUserVO {

	private String uid;
	private String upw;
	private String uname;
	private int upoint_ttl;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpw() {
		return upw;
	}
	public void setUpw(String upw) {
		this.upw = upw;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getUpoint_ttl() {
		return upoint_ttl;
	}
	public void setUpoint_ttl(int upoint_ttl) {
		this.upoint_ttl = upoint_ttl;
	}
	
	@Override
	public String toString() {
		return "UserVO [uid=" + uid 
				   + ", upw=" + upw 
				   + ", uname=" + uname 
				   + ", upoint_ttl=" + upoint_ttl + "]";
	}
	
	
}
