-- CS4400: Introduction to Database Systems (Summer 2022)
-- Phase II: Create Table & Insert Statements [v0] Saturday, May 28, 2022 @ 6:00am (Local/Berlin, GE)

-- Team __
-- Yash Gupta (ygupta46)
-- Mark Glinberg (mglinberg3)
-- Avya Manchanda (amanchanda6)
-- Jared Stanton (jstanton32)

-- Directions:
-- Please follow all instructions for Phase II as listed on Canvas.
-- Fill in the team number and names and GT usernames for all members above.
-- Create Table statements must be manually written, not taken from an SQL Dump file.
-- This file must run without error for credit.

-- ------------------------------------------------------
-- CREATE TABLE STATEMENTS AND INSERT STATEMENTS BELOW
-- ------------------------------------------------------
DROP DATABASE IF EXISTS cs4400_phase2_team4;
CREATE DATABASE IF NOT EXISTS cs4400_phase2_team4;
use cs4400_phase2_team4;

DROP TABLE IF EXISTS ingredient;
CREATE TABLE ingredient (
    barcode varchar(40) primary key,
    iname varchar(100),
    weight int
);
INSERT INTO ingredient VALUES ('bv_4U5L7M', 'balsamic vinegar', 4);		
INSERT INTO ingredient VALUES ('clc_4T9U25X', 'caviar', 5);	
INSERT INTO ingredient VALUES ('ap_9T25E36L', 'foie gras', 4);		
INSERT INTO ingredient VALUES ('pr_3C6A9R', 'prosciutto', 6);
INSERT INTO ingredient VALUES ('ss_2D4E6L', 'saffron', 3);	
INSERT INTO ingredient VALUES ('hs_5E7L23M', 'truffles', 3);		

DROP TABLE IF EXISTS location;
CREATE TABLE location (
	label varchar(40) primary key,
    x_coordinate int,
    y_coordinate int,
    space int check (space >= 0) 
);
INSERT INTO location VALUES ('airport', -2, -9, 4);	
INSERT INTO location VALUES ('avalon', 2, 16, 5);		
INSERT INTO location VALUES ('buckhead', 3, 8, 4);	
INSERT INTO location VALUES ('highpoint', 7, 0, 2);	
INSERT INTO location VALUES ('mercedes', 1, 1, 2);	
INSERT INTO location VALUES ('midtown', 1, 4, 3);	
INSERT INTO location VALUES ('plaza', 5, 12, 20);	
INSERT INTO location VALUES ('southside', 3, -6, 3);	

DROP TABLE IF EXISTS users;
CREATE TABLE users (
	username varchar(40) primary key,
	first_name varchar(100),
    last_name varchar(100),
    birthdate date,
    address varchar(500)
);
INSERT INTO users VALUES ('agarcia7', 'Alejandro', 'Garcia', '1966-10-29', '710 Living Water Drive');				
INSERT INTO users VALUES ('awilson5', 'Aaron', 'Wilson', '1963-11-11', '220 Peachtree Street');				
INSERT INTO users VALUES ('bsummers4', 'Brie', 'Summers', '1976-02-09', '5105 Dragon Star Circle');				
INSERT INTO users VALUES ('cjordan5', 'Clark', 'Jordan', '1966-06-05', '77 Infinite Stars Road');				
INSERT INTO users VALUES ('ckann5', 'Carrot', 'Kann', '1972-09-01', '64 Knights Square Trail');				
INSERT INTO users VALUES ('csoares8', 'Claire', 'Soares', '1965-09-03', '706 Living Stone Way');				
INSERT INTO users VALUES ('echarles19', 'Ella', 'Charles', '1974-05-06', '22 Peachtree Street');				
INSERT INTO users VALUES ('eross10', 'Erica', 'Ross', '1975-04-02', '22 Peachtree Street');				
INSERT INTO users VALUES ('fprefontaine6', 'Ford', 'Prefontaine', '1961-01-28', '10 Hitch Hikers Lane');				
INSERT INTO users VALUES ('hstark16', 'Harmon', 'Stark', '1971-10-27', '53 Tanker Top Lane');				
INSERT INTO users VALUES ('jstone5', 'Jared', 'Stone', '1961-01-06', '101 Five Finger Way');				
INSERT INTO users VALUES ('lrodriguez5', 'Lina', 'Rodriguez', '1975-04-02', '360 Corkscrew Circle');				
INSERT INTO users VALUES ('mrobot1', 'Mister', 'Robot', '1988-11-02', '10 Autonomy Trace');				
INSERT INTO users VALUES ('mrobot2', 'Mister', 'Robot', '1988-11-02', '10 Clone Me Circle');				
INSERT INTO users VALUES ('rlopez6', 'Radish', 'Lopez', '1999-09-03', '8 Queens Route');				
INSERT INTO users VALUES ('sprince6', 'Sarah', 'Prince', '1968-06-15', '22 Peachtree Street');				
INSERT INTO users VALUES ('tmccall5', 'Trey', 'McCall', '1973-03-19', '360 Corkscrew Circle');				


DROP TABLE IF EXISTS employees;
CREATE TABLE employees (
	taxID varchar(40) check (taxID like '___-__-____'),
	hired date,
    salary int check (salary >= 0) ,
    experience int check (experience >= 0) ,
    username varchar(40),
    foreign key (username) references users(username),
    primary key (username),
    unique key (taxID)
   );
INSERT INTO employees VALUES ('999-99-9999', '2019-03-17', 41000, 24, 'agarcia7');				
INSERT INTO employees VALUES ('111-11-1111', '2020-03-15', 46000, 9, 'awilson5');				
INSERT INTO employees VALUES ('000-00-0000', '2018-12-06', 35000, 17, 'bsummers4');						
INSERT INTO employees VALUES ('640-81-2357', '2019-08-03', 46000, 27, 'ckann5');				
INSERT INTO employees VALUES ('888-88-8888', '2019-02-25', 57000, 26, 'csoares8');				
INSERT INTO employees VALUES ('777-77-7777', '2021-01-02', 27000, 3, 'echarles19');				
INSERT INTO employees VALUES ('444-44-4444', '2020-04-17', 61000, 10, 'eross10');				
INSERT INTO employees VALUES ('121-21-2121', '2020-04-19', 20000, 5, 'fprefontaine6');				
INSERT INTO employees VALUES ('555-55-5555', '2018-07-23', 59000, 20, 'hstark16');								
INSERT INTO employees VALUES ('222-22-2222', '2019-04-15', 58000, 20, 'lrodriguez5');				
INSERT INTO employees VALUES ('101-01-0101', '2015-05-27', 38000, 8, 'mrobot1');				
INSERT INTO employees VALUES ('010-10-1010', '2015-05-27', 38000, 8, 'mrobot2');				
INSERT INTO employees VALUES ('123-58-1321', '2017-02-05', 64000, 51, 'rlopez6');							
INSERT INTO employees VALUES ('333-33-3333', '2018-10-17', 33000, 29, 'tmccall5');				


DROP TABLE IF EXISTS owners;
CREATE TABLE owners (
	username varchar(40) primary key,
    foreign key (username) references users(username)
);
INSERT INTO owners VALUES ('cjordan5');
INSERT INTO owners VALUES ('jstone5');		
INSERT INTO owners VALUES ('sprince6');		

DROP TABLE IF EXISTS pilot;
CREATE TABLE pilot (
	username varchar(40) primary key,
	licenseID int,
    experience int check (experience >= 0) ,
    foreign key (username) references employees(username)
);
INSERT INTO pilot VALUES ('agarcia7', '610623', 38);			
INSERT INTO pilot VALUES ('awilson5', '314159', 41);			
INSERT INTO pilot VALUES ('bsummers4', '411911', 35);			
INSERT INTO pilot VALUES ('csoares8', '343563', 7);			
INSERT INTO pilot VALUES ('echarles19', '236001', 10);			
INSERT INTO pilot VALUES ('fprefontaine6', '657483', 2);			
INSERT INTO pilot VALUES ('lrodriguez5', '287182', 67);			
INSERT INTO pilot VALUES ('mrobot1', '101010', 18);			
INSERT INTO pilot VALUES ('rlopez6', '235711', 58);			
INSERT INTO pilot VALUES ('tmccall5', '181633', 10);			
	

DROP TABLE IF EXISTS worker;
create table worker (
	username varchar(40) primary key,
    foreign key (username) references employees(username)
);
INSERT INTO worker VALUES ('ckann5');		
INSERT INTO worker VALUES ('csoares8');		
INSERT INTO worker VALUES ('echarles19');			
INSERT INTO worker VALUES ('eross10');		
INSERT INTO worker VALUES ('hstark16');		
INSERT INTO worker VALUES ('mrobot2');		
INSERT INTO worker VALUES ('tmccall5');		
	

DROP TABLE IF EXISTS service;
CREATE TABLE service (
    ID varchar(40) primary key,
    service_name varchar(100),
    location varchar(40),
    mgrID varchar(40),
    foreign key (location) references location(label),
    foreign key (mgrID) references worker(username)
);
INSERT INTO service VALUES ('hf', 'Herban Feast', 'southside', 'hstark16');
INSERT INTO service VALUES ('osf', 'On Safari Foods', 'southside', 'eross10');
INSERT INTO service VALUES ('rr', 'Ravishing Radish', 'avalon', 'echarles19');

DROP TABLE IF EXISTS drone;
CREATE TABLE drone (
	serviceID varchar(40),
    tag int,
    fuel int check (fuel >= 0) ,
    capacity int check (capacity >= 0) ,
    sales double check (sales >= 0) ,
    pilot varchar(40),
    swarmTag int,
    swarmServiceID varchar(40),
    hover varchar(40),
    primary key (serviceID, tag),
    foreign key (serviceID) references service(id),
    foreign key (pilot) references pilot(username),
    foreign key (swarmServiceID, swarmTag) references drone(serviceID, tag),
    foreign key (hover) references location(label)
);
INSERT INTO drone VALUES ('hf', 1, 100, 6, 0,'fprefontaine6', NULL,NULL,'southside');				
INSERT INTO drone VALUES ('hf', 5, 27, 7, 100,'fprefontaine6',NULL,NULL,'buckhead');				
INSERT INTO drone VALUES ('hf', 8, 100, 8, 0,'bsummers4',NULL,NULL,'southside');				
INSERT INTO drone VALUES ('hf', 11, 25, 10, 0,NULL,5,'hf','buckhead');				
INSERT INTO drone VALUES ('hf', 16, 17, 5, 40,'fprefontaine6',NULL,NULL,'buckhead');				
INSERT INTO drone VALUES ('osf', 1, 100, 9, 0,'awilson5', NULL,NULL,'airport');				
INSERT INTO drone VALUES ('osf', 2, 75, 7, 0,NULL,1,'osf','airport');				
INSERT INTO drone VALUES ('rr', 3, 100, 5, 50,'agarcia7',NULL,NULL,'avalon');				
INSERT INTO drone VALUES ('rr', 7, 53, 5, 100,'agarcia7',NULL,NULL,'avalon');				
INSERT INTO drone VALUES ('rr', 8, 100, 6, 0,'agarcia7',NULL,NULL,'highpoint');				
INSERT INTO drone VALUES ('rr', 11, 90, 6, 0,NULL,8,'rr','highpoint');				

				

DROP TABLE IF EXISTS restaurant;
CREATE TABLE restaurant (
	restaurant_name varchar(40) primary key,
    rating int check (rating between 1 and 5),
    spent int check (spent >= 0) ,
    location varchar(40),
    funder varchar(40),
    foreign key (location) references location(label),
    foreign key (funder) references owners(username)
);
INSERT INTO restaurant VALUES ('Bishoku', 5, 10, 'plaza', NULL);				
INSERT INTO restaurant VALUES ('Casi Cielo', 5, 30, 'plaza', NULL);				
INSERT INTO restaurant VALUES ('Ecco', 3, 0, 'buckhead', 'jstone5');				
INSERT INTO restaurant VALUES ('Fogo de Chao', 4, 30, 'buckhead', NULL);				
INSERT INTO restaurant VALUES ('Hearth', 4, 0, 'avalon', NULL);				
INSERT INTO restaurant VALUES ('Il Giallo', 4, 10, 'mercedes', 'sprince6');				
INSERT INTO restaurant VALUES ('Lure', 5, 20, 'midtown', 'jstone5');				
INSERT INTO restaurant VALUES ('Micks', 2, 0, 'southside', NULL);				
INSERT INTO restaurant VALUES ('South City Kitchen', 5, 30, 'midtown', 'jstone5');				
INSERT INTO restaurant VALUES ('Tre Vele', 4, 10, 'plaza', NULL);				

DROP TABLE IF EXISTS contain;
CREATE TABLE contain (
	serviceID varchar(40),
    tag int,
    barcode varchar(40),
    price int check (price >= 0) ,
    quantity int check (quantity >= 0) ,
    primary key (serviceID, tag, barcode), 
    foreign key (serviceID, tag) references drone(serviceID, tag),
    foreign key (barcode) references ingredient(barcode)
);
INSERT INTO contain VALUES ('rr', 3, 'clc_4T9U25X', 28, 2);		
INSERT INTO contain VALUES ('hf', 5, 'clc_4T9U25X', 30, 1);		
INSERT INTO contain VALUES ('osf', 1, 'pr_3C6A9R', 20, 5);		
INSERT INTO contain VALUES ('hf', 8, 'pr_3C6A9R', 18, 4);		
INSERT INTO contain VALUES ('osf', 1, 'ss_2D4E6L', 23, 3);		
INSERT INTO contain VALUES ('hf', 11, 'ss_2D4E6L', 19, 3);		
INSERT INTO contain VALUES ('hf', 1, 'ss_2D4E6L', 27, 6);		
INSERT INTO contain VALUES ('osf', 2, 'hs_5E7L23M', 14, 7);		
INSERT INTO contain VALUES ('rr', 3, 'hs_5E7L23M', 15, 2);		
INSERT INTO contain VALUES ('hf', 5, 'hs_5E7L23M', 17, 4);		


DROP TABLE IF EXISTS works_for;
CREATE TABLE works_for (
	username varchar(40),
    serviceID varchar(40),
    primary key (username, serviceID),
    foreign key (username) references employees(username),
    foreign key (serviceID) references service(ID)
);
INSERT INTO works_for VALUES ('agarcia7', 'rr');		
INSERT INTO works_for VALUES ('awilson5', 'osf');		
INSERT INTO works_for VALUES ('bsummers4', 'hf');		
INSERT INTO works_for VALUES ('ckann5', 'osf');		
INSERT INTO works_for VALUES ('echarles19', 'rr');		
INSERT INTO works_for VALUES ('eross10', 'osf');		
INSERT INTO works_for VALUES ('fprefontaine6', 'hf');		
INSERT INTO works_for VALUES ('hstark16', 'hf');		
INSERT INTO works_for VALUES ('mrobot1', 'osf');	
INSERT INTO works_for VALUES ('mrobot1', 'rr');			
INSERT INTO works_for VALUES ('rlopez6', 'rr');		
INSERT INTO works_for VALUES ('tmccall5', 'hf');		



