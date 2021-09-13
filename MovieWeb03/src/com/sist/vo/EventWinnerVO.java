package com.sist.vo;


/*
 * create table event_winner_detail(
    mno NUMBER,
    w_category VARCHAR2(30),
    w_title VARCHAR2(200),
    w_release VARCHAR2(100),
    w_content CLOB,
    w_event_title VARCHAR2(200),
    w_gift VARCHAR2(200)
);
 */
public class EventWinnerVO {
	int mno;
	private String category;
	private String title;
	private String release;
	private String content;
	private String event_title;
	private String gift;
	
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEvent_title() {
		return event_title;
	}
	public void setEvent_title(String event_title) {
		this.event_title = event_title;
	}
	public String getGift() {
		return gift;
	}
	public void setGift(String gift) {
		this.gift = gift;
	}
	

}
