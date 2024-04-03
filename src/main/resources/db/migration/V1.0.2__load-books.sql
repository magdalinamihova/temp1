INSERT INTO books (hard_cover, height, width, due_date, id, posted_by_id, genre, name, author, book_title, description, book_description, book_status, file_path, filetype, language, book_key)
VALUES (TRUE, 210, 140, '2024-04-01 10:00:00', nextval('books_seq'), 1, 'Classic', 'Little Women', 'Louisa May Alcott', 'Little Women', 'A novel about the lives of four sisters', 'A heartwarming tale of family and love', 'AVL', 'src/main/resources/images/littlewomencover.jpg', 'jpg', 'EN', 'xxx-key');

INSERT INTO books (hard_cover, height, width, due_date, id, posted_by_id, genre, name, author, book_title, description, book_description, book_status, file_path, filetype, language, book_key)
VALUES (FALSE, 215, 150, '2024-04-02 11:00:00', nextval('books_seq'), 51, 'Romance', 'Pride and Prejudice', 'Jane Austen', 'Pride and Prejudice', 'A story of love and manners in the early 19th century', 'A classic romantic tale', 'AVL', '/path/to/pride_and_prejudice.jpg', 'jpg', 'DE', 'yyy-key');

INSERT INTO books (hard_cover, height, width, due_date, id, posted_by_id, genre, name, author, book_title, description, book_description, book_status, file_path, filetype, language, book_key)
VALUES (TRUE, 220, 160, '2024-04-03 12:00:00', nextval('books_seq'), 101, 'Fantasy', 'The Hobbit', 'J.R.R. Tolkien', 'The Hobbit', 'The prelude to the epic story of The Lord of the Rings', 'A fantasy adventure', 'AVL', '/path/to/the_hobbit.jpg', 'jpg', 'EN', 'zzz-key');
