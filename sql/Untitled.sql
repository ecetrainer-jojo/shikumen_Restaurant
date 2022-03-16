CREATE TABLE employee(
	empno int PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(32) NOT NULL DEFAULT(''),
	hiredate DATE NOT NULL,
	job VARCHAR(32) NOT NULL,
	salary double NOT NULL DEFAULT(0.0),
	deptno int NOT NULL);
	
	
	
CREATE TABLE admin(
	empno int PRIMARY KEY,
	`name` VARCHAR(32) NOT NULL DEFAULT(''),
	accountno VARCHAR(10) NOT NULL,
	`password` VARCHAR(10) NOT NULL,
	lastlogin TIMESTAMP);
	