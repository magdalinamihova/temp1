INSERT INTO books (hard_cover, height, width, due_date, id, posted_by_id, genre, name, author, book_title, description, book_description, book_status, file_path, filetype, language)
VALUES (true, 210, 140, '2024-04-01 10:00:00', nextval('books_seq'), 101, 'Classic', 'Louisa May Alcott', 'Little Women', 'Little Women', 'A novel about the lives of four sisters', 'A heartwarming tale of family and love', 'AVL', 'src/main/resources/images/littlewomencover.jpg', 'jpg', 'EN');
