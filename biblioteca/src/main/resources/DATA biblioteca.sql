
insert into biblioteca.categoria (nome) values
("fantapolitica"),
("narrativa"),
("romanzo"),
("realismo magico"),
("fantasy"),
("saggio"),
("post-apocalittico"),
("giallo"),
("thriller"),
("avventura");

/*| Titolo                                  | Autore                   | Anno | ISBN          | Categoria                |
| --------------------------------------- | ------------------------ | ---- | ------------- | ------------------------ |
| 1. 1984                                 | George Orwell            | 1949 | 9780451524935 | Distopia / Fantapolitica |
| 2. Il nome della rosa                   | Umberto Eco              | 1980 | 9788807883691 | Giallo storico           |
| 3. Il piccolo principe                  | Antoine de Saint-Exupéry | 1943 | 9780156013987 | Narrativa per ragazzi    |
| 4. Orgoglio e pregiudizio               | Jane Austen              | 1813 | 9780141439518 | Romanzo classico         |
| 5. Cent'anni di solitudine              | Gabriel García Márquez   | 1967 | 9780307474728 | Realismo magico          |
| 6. Il signore degli anelli              | J.R.R. Tolkien           | 1954 | 9780618640157 | Fantasy                  |
| 7. La strada                            | Cormac McCarthy          | 2006 | 9780307387899 | Post-apocalittico        |
| 8. Sapiens: Da animali a dèi            | Yuval Noah Harari        | 2011 | 9780099590088 | Saggio storico           |
| 9. Il codice Da Vinci                   | Dan Brown                | 2003 | 9780307474278 | Thriller / Mistero       |
| 10. Harry Potter e la pietra filosofale | J.K. Rowling             | 1997 | 9780747532743 | Fantasy / Young Adult    |*/


insert into biblioteca.libro (anno_pubblicazione, autore, isbn, titolo, categoria_id, is_prestito) values
('1949','George Orwell','9780451524935','1984','1', false),
('1980','Uberto Eco','9788807883691','Il nome della rosa','8', false),
('1943','Antoine de Sanit-Exupery','9780156013987','Il piccolo principe','2', true),
('1813','Jane Austen','9780141439518','Orgoglio e pregiudizio','3', true),
('1967','Gabriel Garcia marquez','9780307474728','Cent anni di solitudine','4', false),
('1954','J.R.R Tolkien','9780618640157','Il signore degli anelli','5', true),
('2006','Cormac McCarthy','9780307387899','La strada','7', false),
('2011','Yuval Noah Harari','9780099590088','Sapiens: Da animali a dèi ','6', true),
('2003','Dan Brown','9780307474278','Il codice Da Vinci','9', true),
('1997','J.K. Rowling','9780747532743','Harry Potter e la pietra filosofale','5', false);

insert into biblioteca.utente (nome, cognome, email, password, data_registrazione) values
('Marco', 'Rossi', 'marco.rossi@example.com', 'password123', '2025-09-15'),
('Laura', 'Bianchi', 'laura.bianchi@example.com', 'laura2025', '2025-09-18'),
('Giulia', 'Verdi', 'giulia.verdi@example.com', 'g1uLia!', '2025-09-20'),
('Luca', 'Neri', 'luca.neri@example.com', 'lucaSecure', '2025-09-22'),
('Francesca', 'Russo', 'francesca.russo@example.com', 'fraPass99', '2025-09-25'),
('Davide', 'Gallo', 'davide.gallo@example.com', 'davide123', '2025-09-27'),
('Chiara', 'Moretti', 'chiara.moretti@example.com', 'ch!ara88', '2025-09-28'),
('Andrea', 'Romano', 'andrea.romano@example.com', 'andreaROM2025', '2025-09-29'),
('Elisa', 'Conti', 'elisa.conti@example.com', 'elisa*secure', '2025-09-30'),
('Federico', 'Marini', 'federico.marini@example.com', 'fedM@rini', '2025-10-01');

insert into biblioteca.prestito (data_prestito, data_restituzione, libro_id, utente_id) values
('2025-10-22',null,'3','1'),
('2024-05-10','2025-01-10','5','2'),
('2025-03-15','2025-05-25','10','8'),
('2023-02-14','2023-11-15','5','3'),
('2024-11-29',null,'8','10'),
('2020-12-27','2021-03-10','7','10'),
('2015-04-23','2016-05-09','7','3'),
('2025-05-05',null,'4','5'),
('2024-12-08',null,'9','7'),
('2025-09-10',null,'6','8');

