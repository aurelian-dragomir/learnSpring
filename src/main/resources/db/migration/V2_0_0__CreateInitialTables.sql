create table person(id integer generated always as identity, name varchar2(255) not null,
constraint pk_person primary key(id));
