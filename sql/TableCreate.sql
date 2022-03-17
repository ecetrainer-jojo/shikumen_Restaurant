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
	
	-- Here is all the info about the admin
	SELECT * FROM admin;
	
	ALTER Table admin MODIFY `password` VARCHAR(32) NOT NULL;
	UPDATE admin set `password` = (MD5('123456')) WHERE `name` = "Peter";
	
	Insert INTO admin VALUES(1,"David",'310103',MD5("19980405"),null);
	Insert INTO admin VALUES(4,"Lance",'310110',MD5("19980224"),null);
	Insert INTO admin VALUES(5,"Henry",'310195',MD5("19951212"),null);
	
	
-- Here is all the info about the seat TABLE
-- status could be "occupied" "empty" "Booked"
-- dining time could be null as empty
CREATE TABLE seat(
seatID int PRIMARY KEY,
`status` VARCHAR(32) NOT NULL DEFAULT("Empty"),
diningTime TIMESTAMP, 
servant VARCHAR(10) NOT NULL DEFAULT(""));

Insert INTO seat VALUES(1,"empty",null,"");
Insert INTO seat VALUES(2,"empty",null,"");
Insert INTO seat VALUES(3,"empty",null,"");
Insert INTO seat VALUES(4,"empty",null,"");
Insert INTO seat VALUES(5,"empty",null,"");
Insert INTO seat VALUES(6,"empty",null,"");
Insert INTO seat VALUES(7,"empty",null,"");

DESC seat;

AlTER Table seat ADD customerName varchar(32) NOT NULL DEFAULT("");


-- below is the info about the dishes 
-- Int dishID will be Prmary key,
-- dishName, dishType and price

CREATE TABLE dishes(
dishID int PRIMARY KEY AUTO_INCREMENT,
dishName VARCHAR(64) NOT NULL DEFAULT(""),
dishType VARCHAR(32) NOT NULL DEFAULT(""),
price double NOT NULL DEFAULT(0.0));

AlTER Table dishes ADD Mandarin_Name VARCHAR(32); 

INSERT INTO dishes Values(null,"Shaoxing Cold Chicken (绍兴白斩鸡)","Cold Dishes",24.99);
INSERT INTO dishes Values(null,"Shanghai Fried Fish (本帮熏鱼)","Cold Dishes",15.99);
INSERT INTO dishes Values(null,"Sweet and Sour Spare Rib (糖醋小排)","Cold Dishes",16.99);
INSERT INTO dishes Values(null,"Shanghai Chicken In Drunk (上海醉鸡)","Cold Dishes",19.99);
INSERT INTO dishes Values(null,"Vegetable Chicken in Gravy Sause (肉汁素鸡)","Cold Dishes",9.99);

INSERT INTO dishes Values(null,"Snow Pea Leaf in Broth (上汤豆苗)","Hot Dish",19.99);
INSERT INTO dishes Values(null,"Crispy Duck in Lotus (荷叶香酥鸭)","Hot Dish",28.99);
INSERT INTO dishes Values(null,"Crab Meat Tofu (蟹粉豆腐)","Hot Dish",24.99);
INSERT INTO dishes Values(null,"Fish Fillet in Wine Sause (糟熘鱼片)","Hot Dish",21.99);
INSERT INTO dishes Values(null,"House Special Braised Pork (招牌红烧肉)","Hot Dish",25.99);
INSERT INTO dishes Values(null,"Eel in Sizzling Oil (响油鳝糊)","Hot Dish",54.99);


INSERT INTO dishes Values(null,"Shanghai Pan-Fried Bun (生煎馒头)","Dim Sum",9.99);
INSERT INTO dishes Values(null,"Shanghai Steamed Pork Bun (鲜肉小笼包)","Dim Sum",10.99);
INSERT INTO dishes Values(null,"Diced Pork Spring Roll (肉丝春卷)","Dim Sum",8.99);
INSERT INTO dishes Values(null,"Shanghai Vegatable Wonton Soup (上海大馄饨)","Dim Sum",15.99);

INSERT INTO dishes Values(null,"Bamboo Shoot in Caseerole (腌笃鲜)","Soup",38.99);
INSERT INTO dishes Values(null,"Hot and Sour Soup (酸辣汤)","Soup",18.99);
INSERT INTO dishes Values(null,"Salted Pork and Winter Melon Soup (咸肉冬瓜汤)","Soup",15.99);

INSERT INTO dishes Values(null,"Sprite (雪碧)","Drink",3.00);
INSERT INTO dishes Values(null,"Coke (可乐)","Drink",3.00);
INSERT INTO dishes Values(null,"Soy Bean Milk (豆浆)","Drink",3.00);


-- below is the info about the bills (transactions)
-- Int bill ID will be primary key
-- seatID records which seat actually order the meal 
-- dishID records the dish number 
-- prices will reflect the price of that single dish (finally we will group by to collect)

-- The java bill class will have (dishID, amount, price) for each seatID

CREATE TABLE bills(
billID int PRIMARY KEY AUTO_INCREMENT,
seatID int NOT NULL,
dishID int NOT NULL,
amount int NOT NULL DEFAULT(1),
price double NOT NULL DEFAULT(0.0));


