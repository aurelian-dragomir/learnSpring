create table cpu(id varchar(255), brand varchar(255) not null,
    constraint pk_cpu primary key(id)
);

create table cpu_details(cpu_id varchar(255), no_of_cores integer not null,
    constraint pk_cpu_details primary key(cpu_id),
    constraint fk_cpu_cpu_details foreign key(cpu_id) references cpu(id)
);