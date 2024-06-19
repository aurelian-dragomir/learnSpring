create table process(id integer generated always as identity,
    name varchar(255) not null,
    constraint pk_process primary key(id)
);

create table thread(id integer generated always as identity)