DROP DATABASE IF EXISTS Car_Rental;
CREATE DATABASE Car_Rental;
USE Car_Rental;

drop table if exists user;
drop table if exists store;
drop table if exists car;
drop table if exists rental;
drop table if exists rate;
drop table if exists rentalArchive;

create table user (
    firstName varchar(50) not null,
    lastName varchar(50) not null,
    driversLicense varchar(50) not null,
    username varchar(50) not null,
    password varchar(50) not null,
    admin boolean default false,
    primary key(username)
);

create table rate(
    vehicleType varchar(50) not null,
    price float not null,
    primary key(vehicleType)
);

create table car(
    vehicleID int auto_increment not null,
    vehicleType char(50),
    make char(50) not null,
    model varchar(50) not null,
    primary key(vehicleID),
    foreign key(vehicleType)
        references rate(vehicleType)
        ON DELETE SET NULL
);

create table store (
    storeID varchar(25) not null,
    vehicleID int not null,
    foreign key(vehicleID)
            references car(vehicleID)
            ON DELETE CASCADE
);

create table rental(
    username varchar(50),
    vehicleID int,
    returnDate date not null,
    vehicleReturned boolean default false,
    updatedOn timestamp not null default current_timestamp on update current_timestamp,
    primary key(VehicleID),
    foreign key(username)
      references user(username)
      ON DELETE CASCADE,
    foreign key(vehicleID)
        references car(vehicleID)
        ON delete cascade
);


create table rentalArchive(
    username varchar(50),
    vehicleID int not null,
    returnDate date not null,
    vehicleReturned boolean default false,
    updatedOn timestamp not null
);

drop procedure if exists spGetUserVehicleAndPrice;
create procedure spGetUserVehicleAndPrice 
(UN varchar(50))
select * 
from rate
where vehicleType in 
    (select vehicleType from car where vehicleID in
        (select vehicleID from rental where username = UN));
        
DROP PROCEDURE
IF EXISTS archive;
delimiter //
CREATE PROCEDURE
archive(IN updatedOn DATE) 
begin 
insert into rentalArchive 
(select * from rental where rental.updatedOn <= updatedOn);
delete from rental where rental.updatedOn <= updatedOn;
end //
delimiter ;


DROP TRIGGER IF EXISTS DeleteCar;
delimiter //
CREATE TRIGGER DeleteCar
AFTER DELETE ON car
FOR EACH ROW
BEGIN
delete from store where VehicleID = Old.VehicleID;
END//
delimiter ;

DROP TRIGGER IF EXISTS AddCar;
delimiter //
CREATE TRIGGER AddCar
AFTER INSERT ON car
FOR EACH ROW
BEGIN
INSERT into store values ('100', New.vehicleID);
END//
delimiter ;


insert into user VALUES('Ronald', 'Kang', 'B0002100', 'ronKang', '123123', false);
INSERT INTO user VALUES ('Anna', 'Li', 'A0003144', 'annaLi', 'abansd', false);
INSERT INTO user VALUES ('Aden', 'Doe', 'C0005104', 'adenDoe', 'alasm', false);

/* new inserts */
insert into user VALUES('Rebecca', 'Song', 'B0002112', 'rebeccaSong', 'qww3beca', true);
INSERT INTO user VALUES ('John', 'Peterson', 'A0007237', 'johnPe', 'jonnypw', false);
INSERT INTO user VALUES ('James', 'Smith', 'C0002391', 'jamesSmith', 'hbjw123', false);
insert into user VALUES('Mary', 'Di', 'B0001242', 'maryDi', 'marsy', true);
INSERT INTO user VALUES ('Maria', 'Kim', 'A0001639', 'mariaKim', 'kimmary', false);
INSERT INTO user VALUES ('Robert', 'Doe', 'C0003750', 'robertDoe', 'royeor', false);


INSERT INTO rate VALUES ('hb', 100);
INSERT INTO rate VALUES ('sd', 150);
INSERT INTO rate VALUES ('mpv', 135);
INSERT INTO rate VALUES ('suv', 200);
INSERT INTO rate VALUES ('cvt', 250);

INSERT INTO car VALUES (122, 'hb', 'Maruti Suzuki', 'Swift'); /*Hatchback*/
INSERT INTO car VALUES (123, 'sd', 'Maruti Suzuki', 'Ciaz'); /*Sedan*/
INSERT INTO car VALUES (124, 'mpv', 'Datsun', 'GO+');
INSERT INTO car VALUES (125, 'suv', 'Land Rover', 'Discovery');
INSERT INTO car VALUES (126, 'cvt', 'Audi', 'A3'); /*Convertible*/

/* new inserts */
INSERT INTO car (vehicleType, make, model)  VALUES ('cvt', 'Audi', 'A3');
INSERT INTO car (vehicleType, make, model)  VALUES ('hb', 'Mazda', 'Mazda3'); /*Hatchback*/
INSERT INTO car (vehicleType, make, model)  VALUES ('suv', 'Hyundai', 'Tucson');
INSERT INTO car (vehicleType, make, model)  VALUES ('sd', 'Kia', 'Rio'); /*Sedan*/
INSERT INTO car (vehicleType, make, model)  VALUES ('sd', 'Hyundai', 'Accent'); /*Sedan*/
INSERT INTO car (vehicleType, make, model)  VALUES ('sd', 'Nissan', 'Versa'); /*Sedan*/
INSERT INTO car (vehicleType, make, model)  VALUES ('mpv', 'Datsun', 'GO+');
INSERT INTO car (vehicleType, make, model)  VALUES ('suv', 'Honda', 'CR-V');
INSERT INTO car (vehicleType, make, model)  VALUES ('hb', 'Volkswagen', 'Golf'); /*Hatchback*/

INSERT INTO rental VALUES ('ronKang', 122, '2021-12-31', false, CURRENT_DATE());
INSERT INTO rental VALUES ('annaLi', 123, '2021-12-31', false, CURRENT_DATE());
INSERT INTO rental VALUES ('annaLi', 126, '2021-12-31', false, CURRENT_DATE());

/* new inserts */
INSERT INTO rental VALUES ('adenDoe', 127, '2021-12-30', false, CURRENT_DATE());
INSERT INTO rental VALUES ('adenDoe', 128, '2021-12-29', false, CURRENT_DATE());
INSERT INTO rental VALUES ('mariaKim', 130, '2021-12-26', false, CURRENT_DATE());
INSERT INTO rental VALUES ('robertDoe', 129, '2021-12-31', false, CURRENT_DATE());
INSERT INTO rental VALUES ('robertDoe', 131, '2021-12-29', false, CURRENT_DATE());
INSERT INTO rental VALUES ('maryDi', 132, '2021-12-20', false, CURRENT_DATE());
