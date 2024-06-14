CREATE TABLE person
             (
                          id   INTEGER generated always AS identity,
                          name VARCHAR2(255) NOT NULL,
                          age  INTEGER NOT NULL,
                          gender VARCHAR(6) check(gender in ('MALE','FEMALE')),
                          CONSTRAINT pk_person PRIMARY KEY(id)
             );