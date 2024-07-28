# CRUD app by Servlet

Stack:
<li>
Servlet API
<li>
Hibernate
<li>
Postgres


### GET METHOD

| METHOD | PATH            | DESCRIPTION         |
|--------|-----------------|---------------------|
| GET    | /api/direction  | get all directions  |
| GET    | api/direction/* | get direction by id |
| GET    | api/audience/*  | get audience by id  |
| GET    | api/student     | get students        |
| GET    | api/student/*   | get student by id   |

### POST METHOD

| METHOD | PATH                  | DESCRIPTION          |
|--------|-----------------------|----------------------|
| POST   | /api/direction        | add new Direction    |
| POST   | api/student           | add new Student      |
| POST   | api/audience          | add new Audience     |

### PUT METHOD

| METHOD | PATH             | DESCRIPTION      |
|--------|------------------|------------------|
| PUT    | /api/direction/* | update Direction |
| PUT    | api/"/audience/* | update Audience  |
| PUT    | api/student/*    | update Student   |

### DELETE METHOD

| METHOD | PATH             | DESCRIPTION      |
|--------|------------------|------------------|
| DELETE | /api/direction/* | delete Direction |
| DELETE | api/audience/*   | delete Audience  |
| DELETE | api/student/*    | delete Student   |


 