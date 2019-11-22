DROP DATABASE pandax;
CREATE DATABASE pandax;

--
-- Table structure for table publisher
--

CREATE TABLE publisher
(id int(32) NOT NULL AUTO_INCREMENT
,denomination varchar(128) NOT NULL
,PRIMARY KEY (id)
);

--
-- Table structure for table media_type
--

CREATE TABLE media_type
(id int(32) NOT NULL AUTO_INCREMENT
,label varchar(128) NOT NULL
,PRIMARY KEY (id)
);

--
-- Table structure for table user
--

CREATE TABLE user
(id int(32) NOT NULL AUTO_INCREMENT
,pseudo varchar(128) NOT NULL
,pwd varchar(128) NOT NULL
,firstName varchar(128) NOT NULL
,lastName varchar(128) NOT NULL
,isAdmin boolean NOT NULL DEFAULT 0
,PRIMARY KEY (id)
,CONSTRAINT user_login UNIQUE (pseudo)
);

--
-- Table structure for table media
--

CREATE TABLE media
(id int(32) NOT NULL AUTO_INCREMENT
,title varchar(128) NOT NULL
,published date NOT NULL
,descript text NOT NULL
,imageUrl varchar(128) NOT NULL
,city varchar(128) NOT NULL
,idPublisher int(32) NOT NULL
,idMediaType int(32) NOT NULL
,PRIMARY KEY (id)
,FOREIGN KEY (idPublisher) REFERENCES publisher(id)
,FOREIGN KEY (idMediaType) REFERENCES media_type(id)
);

--
-- Table structure for table comment
--
CREATE TABLE comment
(idUser int(32) NOT NULL
,idMedia int(32) NOT NULL
,id int(32) NOT NULL AUTO_INCREMENT
,dateMade date NOT NULL
,content text NOT NULL
,KEY (id)
,PRIMARY KEY (idUser, idMedia, id)
,FOREIGN KEY (idUser) REFERENCES user(id)
,FOREIGN KEY (idMedia) REFERENCES media(id)
);

--
-- Table structure for table possesion
--

CREATE TABLE possesion
(idUser int(32) NOT NULL
,idMedia int(32) NOT NULL
,dateAcquired date NOT NULL
,PRIMARY KEY (idUser, idMedia)
,FOREIGN KEY (idUser) REFERENCES user(id)
,FOREIGN KEY (idMedia) REFERENCES media(id)
);

--
-- inserts for media type
--
INSERT INTO media_type (label) VALUES 
('Animation'),
('Animation série'),
('Documentaire'),
('Émission TV'),
('Film'),
('Série TV'),
('Bande dessinée'),
('Comic'),
('Livre'),
('Manga'),
('Presse'),
('Album'),
('Album live'),
('Compilation'),
('Jeu vidéo'),
('Peinture'),
('Fond d’écran'),
('Gravure');

--
-- inserts for publisher 
--
INSERT INTO publisher (denomination) VALUES
('Evil Corp.'),
('Metal Records'),
('Studio Ghibli'),
('VALVe'),
('id Software'),
('Motörhead-sued Motörhead label'),
('BLU Corp.'),
('RED Corp.'),
('Weyland-Yutani'),
('CyberDyne Systems'),
('Black Mesa'),
('Aperture Science');

--
-- inserts for user
--
INSERT INTO user (pseudo, pwd, firstName, lastName, isAdmin) VALUES
('louis','lemmy-ftw','Louis','Prud’homme',true),
('melanie','chocolat','Mélanie','Marques',false),
('sebastien','ghost-ftw','Sébastien','Bernard',true),
('anil','rince-colon','Anil','Devadas',true);

--
-- What remains
--

COMMIT;