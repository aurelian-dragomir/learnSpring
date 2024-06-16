create table book(id integer generated always as identity,
    title varchar(255) not null,
    constraint pk_book primary key(id)
);

create table author(id integer generated always as identity,
    name varchar(255) not null,
    constraint pk_author primary key(id)
);

create table book_author(book_id integer not null,
    author_id integer not null,
    constraint pk_book_author primary key(book_id, author_id),
    constraint fk_book_book_author foreign key(book_id) references book(id),
    constraint fk_author_book_author foreign key(author_id) references author(id)
);