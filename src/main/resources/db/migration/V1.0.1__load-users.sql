INSERT INTO users (id, username, password, first_name, last_name, name, email, phone_number, gender, height, width, description, city, country, filetype, postal_code, street, user_role)
VALUES (nextval('users_seq'), 'natalie_scattorccio', 'nat12345', 'Natalie', 'Scattorccio', 'Nat', 'natalie@example.com', '+1234567890', 'f', 175, 70, 'nat the rat', 'wilderness', 'canada', 'image/jpeg', '10001', 'Main Street', 'A'),
       (nextval('users_seq'), 'shauna_shipman', 'sha12345', 'Shauna', 'Shipman', 'Shauna', 'shauna@example.com', '+1234567890', 'f', 175, 70, 'idk', 'wilderness', 'canada', 'image/jpeg', '10001', 'Main Street', 'A'),
       (nextval('users_seq'), 'isobel_lee', 'iso12345', 'Isobel', 'Lee', 'Isobel', 'isobel@example.com', '+1234567890', 'f', 175, 70, 'nat the rat', 'wilderness', 'canada', 'image/jpeg', '10001', 'Main Street', 'A');