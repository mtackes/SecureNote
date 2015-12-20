DROP TABLE notes;
DROP TABLE users;

CREATE TABLE users(
  id INT PRIMARY KEY AUTO_INCREMENT,
  userName VARCHAR(20) UNIQUE NOT NULL,
  # FIXME: Store the password better, dummy!
  password VARCHAR(60)
);

CREATE TABLE notes(
  id INT PRIMARY KEY AUTO_INCREMENT,
  uuid CHAR(36) UNIQUE NOT NULL,
  title VARCHAR(100),
  # Roughly the largest contents size I can hold with this table structure
  contents VARBINARY(65350) NOT NULL,
  owner_id INT,
  FOREIGN KEY fk_noteowner(owner_id) REFERENCES users(id)
    # TODO: Do I really want to cascade delete?
    ON DELETE CASCADE
);
