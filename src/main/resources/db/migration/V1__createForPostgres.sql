DROP TABLE IF EXISTS borrowers;
DROP TABLE IF EXISTS books;

CREATE TABLE IF NOT EXISTS books (
  id serial PRIMARY KEY,
  title varchar(100) NOT NULL,
  price integer NOT NULL,
  stock integer NOT NULL
);

CREATE TABLE IF NOT EXISTS borrowers (
  id serial PRIMARY KEY,
  name varchar(30) NOT NULL,
  book_id integer REFERENCES books(id)
);