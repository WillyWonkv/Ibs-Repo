drop schema login_project;

create schema login_project;
use login_project;

select * from user;
select * from user_role;
select * from role;
select * from permission;
select * from role_permission;
select * from film;
select * from genre;
select * from film_genre;

INSERT INTO role (name) VALUES 
('ADMIN'), 
('USER'), 
('MANAGER');

INSERT INTO permission (name) VALUES 
('VIEW'),
('CREATE'),
('UPDATE'),
('DELETE');

-- Associa permessi al ruolo ADMIN
INSERT INTO role_permission (role_id, permission_id)
VALUES 
(1, 1), -- ADMIN -> VIEW
(1, 2), -- ADMIN -> CREATE
(1, 3), -- ADMIN -> UPDATE
(1, 4), -- ADMIN -> DELETE
(2, 1), -- USER -> VIEW
(3, 1),	-- MANAGER -> VIEW 
(3, 2), -- MANAGER -> CREATE
(3, 3); -- MANAGER -> UPDATE

INSERT INTO genre (id, name) VALUES
(1, 'Action'),
(2, 'Adventure'),
(3, 'Animation'),
(4, 'Comedy'),
(5, 'Crime'),
(6, 'Documentary'),
(7, 'Drama'),
(8, 'Family'),
(9, 'Fantasy'),
(10, 'History'),
(11, 'Horror'),
(12, 'Music'),
(13, 'Mystery'),
(14, 'Romance'),
(15, 'Science Fiction'),
(16, 'TV Movie'),
(17, 'Thriller'),
(18, 'War'),
(19, 'Western');

