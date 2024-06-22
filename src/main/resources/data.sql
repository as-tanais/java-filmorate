
--insert into users (id, email, login, name) values (1, 'test@test.ru', 'login', 'name');
delete from FILMS;

delete from mpa;

delete
from genres;
insert into mpa(id, name)
values (1, 'G'),
       (2, 'PG'),
       (3, 'PG-13'),
       (4, 'R'),
       (5, 'NC-17');
insert into genres(id, name)
values (1, 'Комедия'),
       (2, 'Драма'),
       (3, 'Мультфильм'),
       (4, 'Триллер'),
       (5, 'Документальный'),
       (6, 'Боевик');



insert into films (id, name, DESCRIPTION, DURATION, MPA_ID) values (1, 'filmname', 'des', 10, 1);