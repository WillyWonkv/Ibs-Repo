drop schema film_service;

create schema film_service;
use film_service;

select * from film;
select * from genere;
select * from regista;
select * from attore;
select * from film_attore;
select * from film_genere;

INSERT INTO regista (id, nome, dataNascita) VALUES
(1, 'Christopher Nolan', '1970-07-30'),
(2, 'Francis Ford Coppola', '1939-04-07'),
(3, 'Quentin Tarantino', '1963-03-27'),
(4, 'Steven Spielberg', '1946-12-18'),
(5, 'Martin Scorsese', '1942-11-17'),
(6, 'James Cameron', '1954-08-16'),
(7, 'Ridley Scott', '1937-11-30'),
(8, 'Peter Jackson', '1961-10-31');

INSERT INTO attore (id, nome, dataNascita) VALUES
(1, 'Leonardo DiCaprio', '1974-11-11'),
(2, 'Matthew McConaughey', '1969-11-04'),
(3, 'Al Pacino', '1940-04-25'),
(4, 'Robert De Niro', '1943-08-17'),
(5, 'Tom Hanks', '1956-07-09'),
(6, 'Brad Pitt', '1963-12-18'),
(7, 'Morgan Freeman', '1937-06-01'),
(8, 'Natalie Portman', '1981-06-09'),
(9, 'Scarlett Johansson', '1984-11-22'),
(10, 'Samuel L. Jackson', '1948-12-21'),
(11, 'Kate Winslet', '1975-10-05'),
(12, 'Christian Bale', '1974-01-30'),
(13, 'Emma Stone', '1988-11-06'),
(14, 'Denzel Washington', '1954-12-28'),
(15, 'Morgan Freeman', '1937-06-01');

INSERT INTO genere (id, nome) VALUES
(1, 'Azione'),
(2, 'Drammatico'),
(3, 'Fantascienza'),
(4, 'Commedia'),
(5, 'Thriller'),
(6, 'Avventura'),
(7, 'Horror'),
(8, 'Fantasy'),
(9, 'Giallo'),
(10, 'Animazione'),
(11, 'Documentario');

INSERT INTO film (id, titolo, descrizione, annoUscita, durata, prezzo, regista_id) VALUES
(1, 'Inception', 'Un ladro che ruba segreti dai sogni', 2010, 148, 4.99, 1),
(2, 'Interstellar', 'Un viaggio oltre le stelle per salvare l\'umanità', 2014, 169, 5.99, 1),
(3, 'Il Padrino', 'La storia di una famiglia mafiosa', 1972, 175, 3.99, 2),
(4, 'Jurassic Park', 'Un parco con dinosauri clonati va fuori controllo', 1993, 127, 4.50, 4),
(5, 'The Wolf of Wall Street', 'La vita sregolata di un broker di successo', 2013, 180, 4.99, 5),
(6, 'Titanic', 'La tragica storia del famoso transatlantico', 1997, 195, 5.50, 6),
(7, 'Alien', 'Un’astronave viene attaccata da una creatura mortale', 1979, 117, 3.99, 7),
(8, 'Il Signore degli Anelli', 'Un’epica avventura per distruggere un anello malvagio', 2001, 178, 6.99, 8),
(9, 'Django Unchained', 'Un ex schiavo cerca vendetta nel sud degli Stati Uniti', 2012, 165, 4.75, 3),
(10, 'Saving Private Ryan', 'Una missione per salvare un soldato durante la Seconda Guerra Mondiale', 1998, 169, 4.25, 4);

INSERT INTO film_attore (film_id, attore_id) VALUES
(1, 1),  -- Inception → Leonardo DiCaprio
(2, 2),  -- Interstellar → Matthew McConaughey
(3, 3),  -- Il Padrino → Al Pacino
(3, 4),  -- Il Padrino → Robert De Niro
(4, 5),  -- Jurassic Park → Tom Hanks (ipotetico)
(5, 6),  -- The Wolf of Wall Street → Brad Pitt (ipotetico)
(6, 11), -- Titanic → Kate Winslet
(7, 7),  -- Alien → Morgan Freeman (ipotetico)
(8, 8),  -- Il Signore degli Anelli → Natalie Portman (ipotetico)
(9, 9),  -- Django Unchained → Scarlett Johansson (ipotetico)
(10, 10),-- Saving Private Ryan → Samuel L. Jackson (ipotetico)
(1, 12), -- Inception → Christian Bale (ipotetico)
(2, 13), -- Interstellar → Emma Stone (ipotetico)
(5, 14), -- The Wolf of Wall Street → Denzel Washington (ipotetico)
(8, 15); -- Il Signore degli Anelli → Morgan Freeman

INSERT INTO film_genere (film_id, genere_id) VALUES
(1, 1),  -- Inception → Azione
(1, 3),  -- Inception → Fantascienza
(2, 3),  -- Interstellar → Fantascienza
(3, 2),  -- Il Padrino → Drammatico
(4, 6),  -- Jurassic Park → Avventura
(5, 2),  -- The Wolf of Wall Street → Drammatico
(5, 4),  -- The Wolf of Wall Street → Commedia
(6, 2),  -- Titanic → Drammatico
(6, 4),  -- Titanic → Commedia (ipotetico)
(7, 7),  -- Alien → Horror
(8, 8),  -- Il Signore degli Anelli → Fantasy
(9, 5),  -- Django Unchained → Thriller
(10, 1); -- Saving Private Ryan → Azione



