CREATE TABLE IF NOT EXISTS mpa
(
    id   INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE IF NOT EXISTS genres
(
    id   int generated by default as identity primary key,
    name varchar
);

CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    email    varchar      NOT NULL,
    login    varchar(255) NOT NULL,
    name     varchar(255) ,
    birthday DATE
);

CREATE TABLE IF NOT EXISTS films
(
    id          INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name        varchar(255)            NOT NULL,
    description varchar(200)            NOT NULL,
    releaseDate date,
    duration    int,
    mpa_id      int references mpa (id)
);

CREATE TABLE IF NOT EXISTS likes
(
    film_id int references films (id),
    user_id int references users (id)
);

CREATE TABLE IF NOT EXISTS film_genres
(
    film_id  int references films (id),
    genre_id int references genres (id)
);

CREATE TABLE IF NOT EXISTS friends
(
    user_id BIGINT  NOT NULL REFERENCES users (id),
    friend_id   BIGINT  NOT NULL REFERENCES users (id)
);