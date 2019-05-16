create table if not exists Ingredient (
  id varchar(4) not null,
  name varchar(25) not null,
  type varchar(10) not null
);



create table if not exists Shawerma (
  id identity,
  name varchar(50) not null,
  createdAt timestamp not null
);



create table if not exists Shawerma_Ingredients (
  shawerma bigint not null,
  ingredient varchar(4) not null
);

alter table Shawerma_Ingredients
add foreign key (shawerma) references Shawerma(id);

alter table Shawerma_Ingredients
add foreign key (ingredient) references Ingredient(id);



create table if not exists Shawerma_Order (
  id identity,
  name varchar(50) not null,
  street varchar(50) not null,
  city varchar(50) not null,
  state varchar(2) not null,
  zip varchar(10) not null,
  ccNumber varchar(16) not null,
  ccExpiration varchar(5) not null,
  ccCVV varchar(3) not null,
  placedAt timestamp not null
);



create table if not exists Shawerma_Order_Shawermas (
  shawermaOrder bigint not null,
  shawerma bigint not null
);

alter table Shawerma_Order_Shawermas
add foreign key (shawermaOrder) references Shawerma_Order(id);

alter table Shawerma_Order_Shawermas
add foreign key (shawerma) references shawerma(id);