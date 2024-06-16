create table student(id   INTEGER generated always AS identity,
    name varchar(255) not null,
    constraint pk_student primary key(id)
);

create table details(id integer,
    age integer,
    constraint pk_student_details primary key(id),
    constraint fk_student_details foreign key(id) references student(id)
);