package org.joywins.domain;

//...463p.
import java.util.Date;

public class MsgVO {

	private Integer mid;
	private String receiver_id;
	private String sender_id;
	private String message;
	private Date open_date;
	private Date send_date;

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getReceiver_id() {
		return receiver_id;
	}

	public void setReceiver_id(String receiver_id) {
		this.receiver_id = receiver_id;
	}

	public String getSender_id() {
		return sender_id;
	}

	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getOpen_date() {
		return open_date;
	}

	public void setOpen_date(Date open_date) {
		this.open_date = open_date;
	}

	public Date getSend_date() {
		return send_date;
	}

	public void setSend_date(Date send_date) {
		this.send_date = send_date;
	}

	@Override
	public String toString() {
		return "MessageVO [mid=" + mid 
					  + ", receiver_id=" + receiver_id 
					  + ", sender_id=" + sender_id 
					  + ", message=" + message 
					  + ", open_date=" + open_date 
					  + ", send_date=" + send_date + "]";
	}
	
	

}
