create sequence global_seq start 1 increment 1;

create table IF NOT EXISTS direction (
          id int  primary key default nextval ('global_seq'),
          name varchar (30)
);
create table IF NOT EXISTS audience (
            id int  primary key default nextval ('global_seq'),
            name varchar (30),
            direction_id int,
            CONSTRAINT fk_direction FOREIGN key (direction_id) REFERENCES direction (id) ON   DELETE CASCADE
);
create table IF NOT EXISTS student (
         id int  primary key default nextval ('global_seq'),
         name varchar (30)
);

create table IF NOT EXISTS audience_student (
            id_audience int,
            id_student int,
            PRIMARY key (id_audience, id_student),
            CONSTRAINT fk_student FOREIGN KEY (id_student) REFERENCES student (id) ON   DELETE CASCADE,
            CONSTRAINT fk_class FOREIGN KEY (id_audience) REFERENCES audience (id) ON   DELETE CASCADE
    );

