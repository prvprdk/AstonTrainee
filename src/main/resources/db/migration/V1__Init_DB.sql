create sequence global_seq;

create table IF NOT EXISTS direction (
          id int  primary key default nextval ('global_seq'),
          name varchar (30)
);
create table IF NOT EXISTS class (
            id int  primary key default nextval ('global_seq'),
            name varchar (30),
            id_direction int,
            CONSTRAINT fk_direction FOREIGN key (id_direction) REFERENCES direction (id) ON   DELETE CASCADE
);
create table IF NOT EXISTS student (
         id int  primary key default nextval ('global_seq'),
         name varchar (30)
);

create table IF NOT EXISTS class_student (
            id_class int,
            id_student int,
            PRIMARY key (id_class, id_student),
            CONSTRAINT fk_student FOREIGN KEY (id_student) REFERENCES student (id) ON   DELETE CASCADE,
            CONSTRAINT fk_class FOREIGN KEY (id_class) REFERENCES class (id) ON   DELETE CASCADE
    );

