CREATE TABLE users (
  DNI VARCHAR(10) NOT NULL,
  name VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  lastname vARCHAR(100) NOT NULL,
  email VARCHAR(200) NOT NULL,
  apikey VARCHAR(80),
  PRIMARY KEY (DNI)
);

CREATE TABLE products (
  ID INTEGER NOT NULL,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(250) NOT NULL,
  price DECIMAL(8,2) NOT NULL,
  stock INTEGER NOT NULL,
  PRIMARY KEY (ID)
);

ALTER TABLE products ADD IMGPATH varchar(300);

UPDATE products SET IMGPATH='pencil.png' WHERE ID=0;
UPDATE products SET IMGPATH='products/gems.png' WHERE ID=1;
UPDATE products SET IMGPATH='products/engTshirt.png' WHERE ID=2;
UPDATE products SET IMGPATH='products/ghostOfAscalon.jpg' WHERE ID=3;
UPDATE products SET IMGPATH='products/edgeOfDestiny.jpg' WHERE ID=4;

CREATE TABLE sales (
  ID INTEGER NOT NULL,
  DNI_USER VARCHAR(10) NOT NULL,
  sale_date date NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (DNI_USER) REFERENCES users(DNI)
);

CREATE TABLE sale_line (
  ID_SALE INTEGER NOT NULL,
  ID_PRODUCT INTEGER NOT NULL,
  quantity INTEGER NOT NULL,
  price DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (ID_SALE, ID_PRODUCT),
  FOREIGN KEY (ID_SALE) REFERENCES sales(ID),
  FOREIGN KEY (ID_PRODUCT) REFERENCES products(ID)
);

INSERT INTO users VALUES
  ('35488513L', 'Martín', 'qwertY', 'Rubio Fernandez', 'martin.rubio@rai.usc.es', NULL),
  ('35488514K', 'Miguel', 'QwertQ', 'Rubio Fernandez', 'miguel.rubio@rai.usc.es', NULL);

INSERT INTO products VALUES
  (0, 'Cambio de nombre', 'Permite el cambio de nombre de uno de los personajes de la cuenta','4.99', 500),
  (1, '100 Gemas', 'Añade 100 gemas al moneddero de la cuenta dentro del juego', '14.99', 10000)
  (2, 'Camiseta ingeniero', 'Presume de tu profesion con esta fabulosa camiseta 100% algodón e increiblemente suave.', '23.00', 10000),
  (3, 'Ghost of Ascalon', 'La primera hisotira original sobre Guild Wars.Dejate llevar por los aclamados escritores Matta Forcebk y jeff Grubb atraves del nacimiento del Filo del Destino', '8.52', 10000),
  (4, 'Edge of Destiny', 'Continuacion de la acamada primera novela, Ghost of Ascalon. Descubre la historia de como una banda de héroes mal avenidos se convierten en leyenda', '8.52', 10000);

INSERT INTO sales VALUES
  (0,'35488513L', DATE(NOW())),
  (1,'35488514K', DATE(NOW()));

INSERT INTO sale_line VALUES
  (0, 0, 1, 4.99),
  (0, 1, 5, 14.99),
  (1, 1, 10, 9.99);
