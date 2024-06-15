# Java-filmorate
Pet project for study Java, SQL, Spring and more.

#Data Base ER Schema
![Schema](sprint11_db_scheme.png)

https://app.quickdatabasediagrams.com/#/

'''''
USERS
-----
user_id PK int IDENTITY
email varchar(50)
login varchar(25)
name varchar(20)
birthday date

FRIENDS
-----
user_id int FK >- USERS.user_id
friend_id bigint FK >- USERS.user_id

LIKES
-----
film_id FK >- FILMS.film_id
user_id FK >- USERS.user_id

FILMS
-----
film_id PK int IDENTITY
name varchar
description varchar(200)
release_date date
duration int 
mpa_id FK - MOTION_PICTURE_ASSOCIATION.mpa_id

MOTION_PICTURE_ASSOCIATION
-----
mpa_id PK int IDENTITY
mpa_name varchar(10)

FILM_GENRE
-----
film_id FK >- FILMS.film_id
genre_id FK >- GENRE.genre_id

GENRE
-----
genre_id PK int IDENTITY
name varchar(30)
'''''
