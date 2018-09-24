CREATE TABLE IF NOT EXISTS category(
  id          BIGINT PRIMARY KEY NOT NULL,
  name VARCHAR(20) NOT NULL
  );
  
CREATE TABLE IF NOT EXISTS joke(
  id		BIGINT PRIMARY KEY NOT NULL,
  content	TEXT NOT NULL,
  likes		INT DEFAULT 0,
  dislikes 	INT DEFAULT 0,
  subtract 	INT DEFAULT 0,
  category_id BIGINT NOT NULL,
  FOREIGN KEY (category_id) REFERENCES category(id)
  );