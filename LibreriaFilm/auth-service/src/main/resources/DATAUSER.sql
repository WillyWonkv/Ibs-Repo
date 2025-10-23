drop schema auth_service;

create schema auth_service;
use auth_service;

select * from utente;
select * from ruolo;
select * from permesso;
select * from ruolo_permesso;
select * from utente_ruolo;

INSERT INTO ruolo (nome) VALUES ('ADMIN'), ('USER'), ('MANAGER');

INSERT INTO permesso (nome) VALUES 
('VIEW'),
('CREATE'),
('UPDATE'),
('DELETE');

-- Associa permessi al ruolo ADMIN
INSERT INTO ruolo_permesso (ruolo_id, permesso_id)
VALUES 
(1, 1), -- ADMIN -> FILM_VIEW
(1, 2), -- ADMIN -> FILM_CREATE
(1, 3), -- ADMIN -> FILM_UPDATE
(1, 4); -- ADMIN -> FILM_DELETE

-- Associa permessi al ruolo USER
INSERT INTO ruolo_permesso (ruolo_id, permesso_id)
VALUES 
(2, 1); -- USER -> FILM_VIEW

INSERT INTO ruolo_permesso (ruolo_id, permesso_id)
VALUES
(3, 1),
(3, 2),
(3, 3);