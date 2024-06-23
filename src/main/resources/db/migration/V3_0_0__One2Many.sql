create table post(id   INTEGER generated always AS identity,
    title varchar(255),
    constraint pk_post primary key(id)
);

create table comments(id   INTEGER generated always AS identity,
    text varchar(255) not null,
    post_id integer not null,
    constraint pk_comment primary key(id),
    constraint fk_post_comment foreign key(post_id) references post(id)
);