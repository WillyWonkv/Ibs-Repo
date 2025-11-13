drop schema login_project;

create schema login_project;
use login_project;

select * from user;
select * from role;
select * from permission;
select * from user_role;
select * from role_permission;

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
(3, 2); -- MANAGER -> CREATE
(3, 3); -- MANAGER -> UPDATE