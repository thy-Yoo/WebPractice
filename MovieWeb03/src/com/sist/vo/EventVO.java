package com.sist.vo;

//import lombok.Getter;
import lombok.Setter;

/*
 * create table event_main2(
    mno NUMBER,
    event_category VARCHAR2(30), 
	event_poster VARCHAR2(300),
	event_title VARCHAR2(200),
	event_term VARCHAR2(200),
	event_state VARCHAR2(30)
);
 */
//@Getter
//@Setter
public class EventVO {
	
	private int mno;
	private String category;
	private String poster;
	private String title;
	private String term;
	private String state;
	private String content;
	
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
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}
