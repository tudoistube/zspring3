package org.joywins.domain;
//...174p.
import java.util.Date;
/***
 * create table ZTBL_BOARD
(
	BNO	int not null auto_increment
    ,TITLE	varchar(200) not null
    ,CONTENT text null
    ,WRITER	varchar(50) not null
    ,REGDATE	timestamp not null	default	now()
    #,UPDATEDATE	timestamp	default	now()
    ,VIEW_COUNT	int default 0
    ,primary key(BNO)
);
 * @author Administrator
 *
 */
public class BoardVO {

	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private int view_count;
	private int reply_count; //...added since 502p.
	
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}	
	public int getReply_count() {
		return reply_count;
	}
	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
	}
	
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title 
				+ ", content=" + content + ", writer=" + writer 
				+ ", regdate=" + regdate + ", view_count=" + view_count + "]";
	}
		
	
}
