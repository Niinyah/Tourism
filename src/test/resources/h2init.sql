DROP TABLE IF EXISTS touristAttractions CASCADE;
DROP TABLE IF EXISTS attraction_tags CASCADE;

CREATE TABLE touristAttractions (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(150) NOT NULL,
                                    description VARCHAR(150) NOT NULL,
                                    city VARCHAR(150) NOT NULL
);

CREATE TABLE attraction_tags (
                                 attraction_id INT NOT NULL,
                                 tag_name VARCHAR(150) NOT NULL,
                                 PRIMARY KEY (attraction_id, tag_name),
                                 FOREIGN KEY (attraction_id) REFERENCES touristAttractions(id) ON DELETE CASCADE
);

INSERT INTO touristAttractions (name, description, city)
VALUES ('Tivoli', 'Det er en forlystelsespark', 'København');

INSERT INTO attraction_tags (attraction_id, tag_name) VALUES (
(SELECT id from touristAttractions WHERE name = 'Tivoli'),
'Underholdning');