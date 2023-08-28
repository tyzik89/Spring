create table if not exists Rocket_Order (
    id identity,
    delivery_name varchar(50) not null,
    delivery_street varchar(50) not null,
    delivery_city varchar(50) not null,
    delivery_state varchar(10) not null,
    delivery_zip varchar(10) not null,
    cc_number varchar(20) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
);

create table if not exists Rocket (
    id identity,
    name varchar(50) not null,
    rocket_order bigint not null,
    rocket_order_key bigint not null,
    created_at timestamp not null
);

create table if not exists Component_Ref (
    component varchar(10) not null,
    rocket bigint not null,
    rocket_key bigint not null
);

create table if not exists Component (
    id varchar(10) not null,
    name varchar(25) not null,
    type varchar(20) not null
);

alter table Rocket
    add foreign key (rocket_order) references Rocket_Order(id);

alter table Component_Ref
    add foreign key (component) references Component(id);