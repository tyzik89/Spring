create table if not exists Components (
    id varchar(10) not null,
    name varchar(25) not null,
    type varchar(20) not null
);


create table if not exists Rocket (
    id identity,
    name varchar(50) not null,
    createdAt timestamp not null
);


create table if not exists Rocket_Components (
    rocket bigint not null,
    components varchar(10) not null
);


alter table Rocket_Components
    add foreign key (rocket) references Rocket(id);


alter table Rocket_Components
    add foreign key (components) references Components(id);


create table if not exists Rocket_Order (
    id identity,
    deliveryName varchar(50) not null,
    deliveryStreet varchar(50) not null,
    deliveryCity varchar(50) not null,
    deliveryState varchar(2) not null,
    deliveryZip varchar(10) not null,
    ccNumber varchar(16) not null,
    ccExpiration varchar(5) not null,
    ccCVV varchar(3) not null,
    placedAt timestamp not null
);


create table if not exists Rocket_Order_Rockets (
    rocketOrder bigint not null,
    rocket bigint not null
);


alter table Rocket_Order_Rockets
    add foreign key (rocketOrder) references Rocket_Order(id);


alter table Rocket_Order_Rockets
    add foreign key (rocket) references Rocket(id);