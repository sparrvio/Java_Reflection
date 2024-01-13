CREATE TABLE IF NOT EXISTS "user"(
	user_id SERIAL PRIMARY KEY NOT NULL,
	login VARCHAR(20) NOT NULL UNIQUE,
	"password" VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS chatroom(
	chatroom_id SERIAL PRIMARY KEY NOT NULL,
	chatroom_name VARCHAR(20) NOT NULL UNIQUE,
	chatroom_owner VARCHAR(20) NOT NULL REFERENCES "user"(login)
);

CREATE TABLE IF NOT EXISTS "message"(
	message_id SERIAL PRIMARY KEY NOT NULL,
	message_author VARCHAR(20) NOT NULL REFERENCES "user"(login),
	message_room VARCHAR(20) NOT NULL REFERENCES chatroom(chatroom_name),
	"message_text" VARCHAR(1000),
	message_data_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);