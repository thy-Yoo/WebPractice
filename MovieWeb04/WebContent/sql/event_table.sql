create table event_main2(
    mno NUMBER,
    event_category VARCHAR2(30),
    event_poster VARCHAR2(500),
    event_title VARCHAR2(200),
    event_term VARCHAR2(200),
    event_state VARCHAR2(30),
    event_content VARCHAR2(500)
    

);

drop table event_main2;

create table event_winner_detail(
    mno NUMBER,
    w_category VARCHAR2(30),
    w_title VARCHAR2(200),
    w_release VARCHAR2(100),
    w_content CLOB,
    w_event_title VARCHAR2(200),
    w_gift VARCHAR2(200)
);


create table event_search(
	event_searchdata VARCHAR2(200)
);



