package org.joywins.dto;
/*
 * ...630p.
 * ...VO가 보다 테이블의 구조를 이용해서 작성되는 경우가 많아서 DB에 가까움.
 * ...DTO는 화면에서 전달되는 데이터를 수집하는 용도로 사용하는 경우가 많아
 * ...화면에 가까움.
 * ...스프링은 Controller에 전달되는 데이터에 대해서 검증하는 기능을 추가할 수
 * ...있는데, 이러한 상황에서는 별도의 DTO를 구성해서 사용함.
 */
public class LoginDTO {

	private String uid;
	private String upw;
	private boolean useCookie;
	
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
	public boolean isUseCookie() {
		return useCookie;
	}
	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}
	@Override
	public String toString() {
		return "LoginDTO [uid=" + uid 
				     + ", upw=" + upw 
				     + ", useCookie=" + useCookie + "]";
	}	
	
	
}
