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
	
	SELECT * FROM admin;
	
	ALTER Table admin MODIFY `password` VARCHAR(32) NOT NULL;
	UPDATE admin set `password` = (MD5('123456')) WHERE `name` = "Peter";
	
	Insert INTO admin VALUES(1,"David",'310103',MD5("19980405"),null);
	Insert INTO admin VALUES(4,"Lance",'310110',MD5("19980224"),null);
	Insert INTO admin VALUES(5,"Henry",'310195',MD5("19951212"),null);
	
	DESC admin;
	
	
	empno,`name`,accountno,`password`