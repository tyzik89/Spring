-- Import initial data to database with Hibernate

delete from Rocket_Order_Rockets;


delete from Rocket_Components;


delete from Rocket;


delete from Rocket_Order;


delete from Components;


insert into Components (id, name, type) values ('PMK1C', 'PODS MK1 Cockpit', 'PODS');

insert into Components (id, name, type) values ('PMK2C', 'PODS MK2 Cockpit', 'PODS');

insert into Components (id, name, type) values ('PMK1CP', 'PODS MK1 Command Pod', 'PODS');

insert into Components (id, name, type) values ('PMK2CP', 'PODS MK2 Command Pod', 'PODS');

insert into Components (id, name, type) values ('RWI', 'RW Inline', 'REACTION_WHEELS');

insert into Components (id, name, type) values ('RWA', 'RW Advanced', 'REACTION_WHEELS');

insert into Components (id, name, type) values ('ELVT30', 'LV-T30', 'ENGINES');

insert into Components (id, name, type) values ('ELVT45', 'LVT-45', 'ENGINES');

insert into Components (id, name, type) values ('EREL10', 'RE-L10', 'ENGINES');

insert into Components (id, name, type) values ('EREI5', 'RE-I5', 'ENGINES');

insert into Components (id, name, type) values ('FFLT100', 'FL-T100', 'FUEL_TANKS');

insert into Components (id, name, type) values ('FFLT200', 'FL-T200', 'FUEL_TANKS');

insert into Components (id, name, type) values ('FFLT800', 'FL-T400', 'FUEL_TANKS');

insert into Components (id, name, type) values ('FR12', 'R-12', 'FUEL_TANKS');

insert into USER (id, city, fullname, password, phone_number, state, street, username, zip) values (1, 'Voronezh', 'Vladimir', 'a29f02f84ac3f61ac48a4c5809b4a53fd0dc5eeafe1a43f2fa7786a93d3ecfcd50a0a6404fdbf619', '88005553535', '7', 'Kolotushkina 1, 123', 'user', '123456')