create table customer(id integer generated always as identity,
    name varchar(255) not null,
    constraint pk_customer primary key(id)
);

create table product(id integer generated always as identity,
    name varchar(255) not null,
    constraint pk_product primary key(id)
);

create table customer_product(customer_id integer not null,
    product_id integer not null,
    quantity integer not null,
    constraint pk_customer_product primary key(customer_id, product_id),
    constraint fk_customer_customer_product foreign key(customer_id) references customer(id),
    constraint fk_product_customer_product foreign key (product_id) references product(id)
);