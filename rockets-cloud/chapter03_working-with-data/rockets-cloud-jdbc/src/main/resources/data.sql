delete from Component_Ref;
delete from Rocket;
delete from Rocket_Order;
delete from Component;

insert into Component (id, name, type)
     values ('PMK1C', 'PODS MK1 Cockpit', 'PODS');

insert into Component (id, name, type)
     values ('PMK2C', 'PODS MK2 Cockpit', 'PODS');

insert into Component (id, name, type)
     values ('PMK1CP', 'PODS MK1 Command Pod', 'PODS');

insert into Component (id, name, type)
     values ('PMK2CP', 'PODS MK2 Command Pod', 'PODS');

insert into Component (id, name, type)
     values ('RWI', 'RW Inline', 'REACTION_WHEELS');

insert into Component (id, name, type)
     values ('RWA', 'RW Advanced', 'REACTION_WHEELS');

insert into Component (id, name, type)
     values ('ELVT30', 'LV-T30', 'ENGINES');

insert into Component (id, name, type)
     values ('ELVT45', 'LVT-45', 'ENGINES');

insert into Component (id, name, type)
     values ('EREL10', 'RE-L10', 'ENGINES');

insert into Component (id, name, type)
     values ('EREI5', 'RE-I5', 'ENGINES');

insert into Component (id, name, type)
     values ('FFLT100', 'FL-T100', 'FUEL_TANKS');

insert into Component (id, name, type)
     values ('FFLT200', 'FL-T200', 'FUEL_TANKS');

insert into Component (id, name, type)
     values ('FFLT800', 'FL-T400', 'FUEL_TANKS');

insert into Component (id, name, type)
     values ('FR12', 'R-12', 'FUEL_TANKS');