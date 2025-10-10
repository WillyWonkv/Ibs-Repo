drop schema libreriafilm;

create schema libreriafilm;

use libreriafilm;

select * from film;
select * from genere;
select * from regista;
select * from attore;
select * from utente;
select * from prestito;
select * from film_attore;
select * from film_genere;

INSERT INTO regista (nome, data_nascita) VALUES
('Christopher Nolan', '1970-07-30'),
('Steven Spielberg', '1946-12-18'),
('Hayao Miyazaki', '1941-01-05');

INSERT INTO genere (nome) VALUES
('Azione'), ('Drammatico'), ('Animazione'), ('Avventura'), ('Fantascienza');

INSERT INTO attore (nome, data_nascita) VALUES
('Leonardo DiCaprio', '1974-11-11'),
('Tom Hanks', '1956-07-09'),
('Christian Bale', '1974-01-30'),
('Emma Watson', '1990-04-15'),
('Hayley Mills', '1946-04-18');

INSERT INTO film (titolo, anno_uscita, durata, regista_id, descrizione, prezzo) VALUES
('Inception', 2010, 148, 1, 'Un ladro che ruba segreti dai sogni altrui.', 14.99),
('The Dark Knight', 2008, 152, 1, 'Batman affronta il Joker in una Gotham in crisi.', 19.99),
('Spirited Away', 2001, 125, 3, 'Una bambina entra in un mondo popolato da spiriti.', 7.99),
('Forrest Gump', 1994, 142, 2, 'Un uomo semplice attraversa eventi storici americani.', 9.99);

INSERT INTO utente (nome, cognome, email, password, data_registrazione, soldi) VALUES
('Luca', 'Bianchi', 'luca.bianchi@example.com', 'password123', '2024-03-10', 125.50),
('Martina', 'Rossi', 'martina.rossi@example.com', 'martyR2024!', '2024-05-22', 580.00),
('Davide', 'Verdi', 'davide.verdi@example.com', 'davidePass!', '2024-06-15', 75.20),
('Giulia', 'Neri', 'giulia.neri@example.com', 'giuNeri$', '2024-07-03', 940.75),
('Andrea', 'Conti', 'andrea.conti@example.com', 'andC2025', '2024-09-28', 250.00),
('Elena', 'Romano', 'elena.romano@example.com', 'elenaR123', '2024-10-05', 320.10),
('Marco', 'Ferrari', 'marco.ferrari@example.com', 'marcoF!', '2025-01-12', 1020.99),
('Serena', 'Gallo', 'serena.gallo@example.com', 'serenaG2025', '2025-02-01', 47.00),
('Alessio', 'Greco', 'alessio.greco@example.com', 'alessio88', '2025-03-18', 680.50),
('Chiara', 'Moretti', 'chiara.moretti@example.com', 'chiaraM!', '2025-05-07', 1540.75);

INSERT INTO film_genere (film_id, genere_id) VALUES
(1, 5), (1, 1), -- Inception → Fantascienza, Azione
(2, 1), (2, 5), -- The Dark Knight → Azione, Fantascienza
(3, 3),         -- Spirited Away → Animazione
(4, 2);         -- Forrest Gump → Drammatico

INSERT INTO film_attore (film_id, attore_id) VALUES
(1, 1),
(1, 3),
(2, 3),
(2, 1),
(4, 2);

INSERT INTO prestito (data_prestito, data_restituzione, film_id, utente_id) VALUES
('2025-01-10', '2025-01-20', 1, 3),  
('2025-01-15', '2025-01-25', 2, 1),  
('2025-02-01', NULL, 3, 4),        
('2025-02-10', '2025-02-18', 4, 2),   
('2025-03-02', '2025-03-12', 1, 5),  
('2025-03-10', NULL, 2, 6),          
('2025-03-20', '2025-03-30', 3, 7),   
('2025-04-05', '2025-04-15', 4, 8),   
('2025-04-12', NULL, 1, 9),        
('2025-04-25', '2025-05-05', 2, 10),  
('2025-05-01', '2025-05-10', 3, 1),  
('2025-05-15', '2025-05-25', 4, 3),
('2025-06-10', '2025-06-18', 1, 2),
('2025-07-01', '2025-07-11', 3, 7);
