# CRUD app by Servlet

Stack:
<li>
Servlet API
<li>
JDBC
<li>
Postgres
<li>
FlyWay

### GET METHOD

| METHOD | PATH            | DESCRIPTION         |
|--------|-----------------|---------------------|
| GET    | /api/direction  | get all directions  |
| GET    | api/direction/* | get direction by id |
| GET    | api/class/*     | get class by id     |
| GET    | api/student     | get students        |
| GET    | api/student/*   | get student by id   |

### POST METHOD

| METHOD | PATH                  | DESCRIPTION          |
|--------|-----------------------|----------------------|
| POST   | /api/direction        | add new Direction    |
| POST   | api/student           | add new Student      |
| POST   | api/class             | add new Class        |
| POST   | api/addStudentToClass | add Student to Class |

### PUT METHOD

| METHOD | PATH             | DESCRIPTION      |
|--------|------------------|------------------|
| PUT    | /api/direction/* | update Direction |
| PUT    | api/"/class/*    | update Class     |
| PUT    | api/student/*    | update Student   |

### DELETE METHOD

| METHOD | PATH             | DESCRIPTION      |
|--------|------------------|------------------|
| DELETE | /api/direction/* | delete Direction |
| DELETE | api/class/*      | delete Class     |
| DELETE | api/student/*    | delete Student   |


 