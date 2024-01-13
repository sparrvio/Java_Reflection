INSERT INTO "user" (user_id, login, "password")
VALUES 	(DEFAULT, 'Leonardo_DiCaprio', '1996-01-01'),
      	(DEFAULT, 'George_Clooney', '1996-01-02'),
     	(DEFAULT, 'Sandra_Bullock', '1996-01-03'),
      	(DEFAULT, 'Kate_Winslet', '1996-01-04'),
      	(DEFAULT, 'Penеlope_Cruz', '1996-01-06'),
      	(DEFAULT, 'Clint_Eastwood', '1996-01-07'),
      	(DEFAULT, 'Martin_Scorsese', '1996-01-08'),
      	(DEFAULT, 'Anthony_Hopkins', '1996-01-09'),
      	(DEFAULT, 'Natalie_Portman', '1996-01-10');
  

INSERT INTO chatroom (chatroom_id, chatroom_name, chatroom_owner)
VALUES 	(DEFAULT, 'chatroom Leo', 'Leonardo_DiCaprio'),
		(DEFAULT, 'chatroom George', 'George_Clooney'),
		(DEFAULT, 'chatroom Sandra', 'Sandra_Bullock'),
		(DEFAULT, 'chatroom Kate', 'Kate_Winslet'),
		(DEFAULT, 'chatroom Penеlope', 'Penеlope_Cruz');
		
INSERT INTO "message" (message_id, message_author, message_room, "message_text", message_data_time)
VALUES 	(DEFAULT, 'Natalie_Portman', 'chatroom Leo', 'Hello, i am Natalie_Portman', DEFAULT),
		(DEFAULT, 'Anthony_Hopkins', 'chatroom Leo', 'Hello, i am Anthony_Hopkins', DEFAULT),
		(DEFAULT, 'Martin_Scorsese', 'chatroom Leo', 'Hello, i am Martin_Scorsese', DEFAULT),
		(DEFAULT, 'Clint_Eastwood', 'chatroom Leo', 'Hello, i am Clint_Eastwood', DEFAULT),
		(DEFAULT, 'Penеlope_Cruz', 'chatroom Leo', 'Hello, i am Penеlope_Cruz', DEFAULT);

SELECT * FROM public."user"
ORDER BY user_id ASC