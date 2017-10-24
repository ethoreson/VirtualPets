# _Virtual Pets_

#### _A two day project_

### By _**Emilie Thoreson**_

## Setup/Installation Requirements

* In command line, open new tab (alt + T on Epicodus computers)
* 'postgres'
* After seeing LOG message, open another tab
* 'psql'
* In PSQL:
* CREATE DATABASE virtual_pets;
* \c virtual_pets;
* CREATE TABLE persons (id serial PRIMARY KEY, name varchar, email varchar);
* CREATE TABLE monsters (id serial PRIMARY KEY, name varchar, personID int, birthday timestamp, lastate timestamp, lastslept timestamp, lastplayed timestamp, type varchar, lastWater timestamp, lastKindling timestamp);
* CREATE TABLE communities (id SERIAL PRIMARY KEY, name varchar, description varchar);
* CREATE TABLE communities_persons (id serial PRIMARY KEY, community_id int, person_id int);
* CREATE DATABASE virtual_pets_test WITH TEMPLATE virtual pets;
*In original command line tab, run 'gradle test', or 'gradle run' & 'gradle build' to see web app in action.

## Known Bugs

_No known bugs._

## Support and contact details

_Contact Emilie at thoresonemilie@gmail.com_

## Technologies Used

_HTML, CSS, Java, Gradle, Spark, JUnit Testing, Postgres_

### License

*MIT license*

Copyright (c) 2017 **_Emilie Thoreson_**
