create table project_member(
	id VARCHAR2(20 byte) NOT NULL,
	pwd VARCHAR2(10 byte) NOT NULL,
	sex VARCHAR2(10 byte),
	name VARCHAR2(34 byte) NOT NULL,
	birthday VARCHAR2(20 byte) NOT NULL,
	email VARCHAR2(100 byte) NOT NULL,
	post VARCHAR2(70 byte) NOT NULL,
	add1 VARCHAR2(300 byte) NOT NULL,
	add2 VARCHAR2(300 byte),
	tel VARCHAR2(20 byte) NOT NULL, 
	genre VARCHAR2(20 byte),
	admin CHAR(1 byte) DEFAULT 'n'


);