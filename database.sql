create database m3exam;
use m3exam;
create table city(
	id int auto_increment primary key,
    name nvarchar(255) not null,
    country nvarchar(255) not null,
    area double not null,
    population int,
    gdp double not null,
    description nvarchar(255)
);
create table country(
	id int auto_increment primary key,
    name nvarchar(255) not null
);
insert into country values(1,"Việt Nam");
insert into country values(2,"France");
insert into country values(3,"Chinese");