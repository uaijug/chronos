/* Single line comment */
CREATE TABLE history (
 id int(11) auto_increment,
 description VARCHAR(25) NOT NULL,
 PRIMARY KEY(id)
);

INSERT INTO history (description) VALUES ('Migration Verson 1');
