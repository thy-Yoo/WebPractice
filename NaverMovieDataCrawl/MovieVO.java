package com.yjy.dao;

import lombok.Getter;
import lombok.Setter;

/*
create table naverMovie(
num			NUMBER,
title		VARCHAR2(200 BYTE),
genre		VARCHAR2(200 BYTE),
director	VARCHAR2(200 BYTE),
actor		VARCHAR2(400 BYTE),
ticketsales	VARCHAR2(200 BYTE),
grade		VARCHAR2(200 BYTE),
rate		VARCHAR2(200 BYTE),
runningtime	VARCHAR2(200 BYTE),
releasedate	VARCHAR2(500 BYTE),
key			VARCHAR2(200 BYTE)
);
*/
@Getter
@Setter

public class MovieVO {
	
	private int num;
	private String poster;
	private String title;
	private String genre;
	private String director;
	private String actor;
	private String ticketsales;
	private String grade;
	private String rate;
	private String runningtime;
	private String releasedate;
	private String key;
	
	
	

}
