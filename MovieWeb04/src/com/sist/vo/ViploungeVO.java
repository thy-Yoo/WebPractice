package com.sist.vo;
/*
 * create table membership_benefit(
    mno NUMBER,
    userid VARCHAR2(30 byte), -- member 테이블에서 비교할 값.
    usergrade VARCHAR2(30 byte), 
    total_point NUMBER, 
    total_ticketnums NUMBER,
    NUMBER의 경우 moviefree+storefree+storediscount+photoplay+50discount 의 값들 더하기 =>temp에 넣고
        -- if(vip_double1==true){ temp++;}
        -- if(vip_double2==true){temp++;}
        -- if(specialday==true){temp++;}
        --if(birthday==true){temp++;}
        --if(specialgift1==true){temp++;}
        --if(specialgift2==true){temp++;}
        --if(svip_room==true){temp++;}
        
    //쿠폰들
    online_moviefree NUMBER DEFAULT 0 NOT NULL,
    online_storefree NUMBER DEFAULT 0 NOT NULL,
    online_storediscount NUMBER DEFAULT 0 NOT NULL,
    online_photoplay NUMBER DEFAULT 0 NOT NULL,
    point_50discount NUMBER DEFAULT 0 NOT NULL,
    vip_double1 NUMBER DEFAULT 0 NOT NULL, //0 or 3퍼센트, 상영일 이전예매
    vip_double2 NUMBER DEFAULT 0 NOT NULL, //or 7퍼센트 , 상영일 이후예매
    vip_refill NUMBER DEFAULT 0 NOT NULL, //*몇퍼센트인지, 
    specialday VARCHAR(20) DEFAULT '없음' NOT NULL, //*에이드 2잔무료 쿠폰이 있는지, 없는지,
    birthday VARCHAR(20) DEFAULT '있음' NOT NULL, //*생일무료콤보 쿠폰이 있는지, 없는지,
    specialgift1 VARCHAR(20) DEFAULT '없음' NOT NULL, //*스페셜기프트쿠폰(SVIP스페셜기프트)이 있는지, 없는지
    specialgift2 VARCHAR(20) DEFAULT '없음' NOT NULL, //*스페셜기프트쿠폰(SVIP원데이프리패스)이 있는지, 없는지
    svip_room VARCHAR(20) DEFAULT '있음' NOT NULL, //*특별관 1만원 발권쿠폰이 있는지, 없는지   
    vip_event VARCHAR(20) DEFAULT '참여불가능' NOT NULL  //*VIP 전용 이벤트 참여 가능한지 아닌지. 
);
 */


public class ViploungeVO {
	int mno;
	String userid;
	String usergrade;
	int total_point;
	int total_ticketnums;
	int online_moviefree;
	int online_storefree;
	int online_storediscount;
	int online_photoplay;
	int point_50discount;
	int vip_double1;
	int vip_double2;
	int vip_refill;
	String specialday;
	String birthday;
	String specialgift1;
	String specialgift2;
	String svip_room;
	String vip_event;
	
	
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsergrade() {
		return usergrade;
	}
	public void setUsergrade(String usergrade) {
		this.usergrade = usergrade;
	}
	public int getTotal_point() {
		return total_point;
	}
	public void setTotal_point(int total_point) {
		this.total_point = total_point;
	}
	public int getTotal_ticketnums() {
		return total_ticketnums;
	}
	public void setTotal_ticketnums(int total_ticketnums) {
		this.total_ticketnums = total_ticketnums;
	}
	public int getOnline_moviefree() {
		return online_moviefree;
	}
	public void setOnline_moviefree(int online_moviefree) {
		this.online_moviefree = online_moviefree;
	}
	public int getOnline_storefree() {
		return online_storefree;
	}
	public void setOnline_storefree(int online_storefree) {
		this.online_storefree = online_storefree;
	}
	public int getOnline_storediscount() {
		return online_storediscount;
	}
	public void setOnline_storediscount(int online_storediscount) {
		this.online_storediscount = online_storediscount;
	}
	public int getOnline_photoplay() {
		return online_photoplay;
	}
	public void setOnline_photoplay(int online_photoplay) {
		this.online_photoplay = online_photoplay;
	}
	public int getPoint_50discount() {
		return point_50discount;
	}
	public void setPoint_50discount(int point_50discount) {
		this.point_50discount = point_50discount;
	}
	public int getVip_double1() {
		return vip_double1;
	}
	public void setVip_double1(int vip_double1) {
		this.vip_double1 = vip_double1;
	}
	public int getVip_double2() {
		return vip_double2;
	}
	public void setVip_double2(int vip_double2) {
		this.vip_double2 = vip_double2;
	}
	public int getVip_refill() {
		return vip_refill;
	}
	public void setVip_refill(int vip_refill) {
		this.vip_refill = vip_refill;
	}
	public String getSpecialday() {
		return specialday;
	}
	public void setSpecialday(String specialday) {
		this.specialday = specialday;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSpecialgift1() {
		return specialgift1;
	}
	public void setSpecialgift1(String specialgift1) {
		this.specialgift1 = specialgift1;
	}
	public String getSpecialgift2() {
		return specialgift2;
	}
	public void setSpecialgift2(String specialgift2) {
		this.specialgift2 = specialgift2;
	}
	public String getSvip_room() {
		return svip_room;
	}
	public void setSvip_room(String svip_room) {
		this.svip_room = svip_room;
	}
	public String getVip_event() {
		return vip_event;
	}
	public void setVip_event(String vip_event) {
		this.vip_event = vip_event;
	}
	
	
	
	
	

}
