delete from Shawerma_Order_Shawermas;
delete from Shawerma_Ingredients;
delete from Shawerma;
delete from Shawerma_Order;
delete from Ingredient;

insert into Ingredient (id, name, type)
values ('WCOM', 'Обычный лаваш', 'WRAP');

insert into Ingredient (id, name, type)
values ('WCHE', 'Сырная простыня', 'WRAP');

insert into Ingredient (id, name, type)
values ('PBEE', 'Говядина', 'PROTEIN');

insert into Ingredient (id, name, type)
values ('PCHI', 'Курица', 'PROTEIN');

insert into Ingredient (id, name, type)
values ('VSAL', 'Салат', 'VEGGIES');

insert into Ingredient (id, name, type)
values ('VHAL', 'Халапенью', 'VEGGIES');

insert into Ingredient (id, name, type)
values ('CMOZ', 'Моцарелла', 'CHEESE');

insert into Ingredient (id, name, type)
values ('CMAA', 'Маасдам', 'CHEESE');

insert into Ingredient (id, name, type)
values ('SSAL', 'Сальса', 'SAUCE');

insert into Ingredient (id, name, type)
values ('SMAY', 'Майонез', 'SAUCE');