create table lib_book(id varchar(50), title varchar(255) not null,
    publishing_date date not null,
    constraint pk_lib_book primary key(id)
);

create table lib_book_detail(book_id varchar(50),
    description blob not null,
    constraint pk_lib_book_detail primary key(book_id),
    constraint fk_lib_book_lib_book_detail foreign key(book_id)
     references lib_book(id)
);

create table lib_book_format(id integer generated always as identity,
    format varchar(64) not null,
    book_id varchar(50) not null,
    constraint pk_lib_book_format primary key (id),
    constraint fk_lib_book_lib_book_format foreign key(book_id)
        references lib_book(id)
);

create table lib_author(id integer generated always as identity,
    name varchar(255) not null,
    constraint pk_lib_author primary key(id)
);

create table lib_book_author(book_id varchar(50), author_id integer,
    constraint pk_lib_book_author primary key(book_id, author_id),
    constraint fk_lib_book_lib_book_author foreign key(book_id) references lib_book(id),
    constraint fk_lib_author_lib_book_author foreign key(author_id) references lib_author(id)
);