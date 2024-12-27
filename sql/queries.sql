-- create database shop_cart;

use shop_cart;

/*
Para insertar datos en la tabla product

INSERT INTO product (name, description, brand, category, price, image, status) VALUES
("Assassin's Creed", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus lacinia odio vitae vestibulum vestibulum. Cras vehicula erat.", "Xbox", "VideoGame", 700, "https://http2.mlstatic.com/D_NQ_NP_804148-MLM42305590814_062020-O.jpg", 1),
("The Last of Us", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus lacinia odio vitae vestibulum vestibulum. Cras vehicula erat.", "Play Station", "VideoGame", 1100, "https://m.media-amazon.com/images/I/A1dXfW1yPNL._AC_SX522_.jpg", 1),
("Mario Kart 8 Deluxe", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus lacinia odio vitae vestibulum vestibulum. Cras vehicula erat.", "Nintendo", "VideoGame", 900, "https://m.media-amazon.com/images/I/71zMv5+rx5S._AC_SY445_SX342_.jpg", 1),
("Halo Reach", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus lacinia odio vitae vestibulum vestibulum. Cras vehicula erat.", "Xbox", "VideoGame", 850, "https://m.media-amazon.com/images/I/71WO33apP+L._AC_SX342_SY445_.jpg", 1),
("Control for Xbox One Red", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus lacinia odio vitae vestibulum vestibulum. Cras vehicula erat.", "Xbox", "Controller", 663, "https://m.media-amazon.com/images/I/71Og2yaJKDL.__AC_SY300_SX300_QL70_ML2_.jpg", 1),
("Controller for PlayStation Black", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus lacinia odio vitae vestibulum vestibulum. Cras vehicula erat.", "Play Station", "Controller", 960, "https://m.media-amazon.com/images/I/61oYPSnBRKS._AC_SX679_.jpg", 1),
("Controller for Nintendo Switch - Yoshi", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus lacinia odio vitae vestibulum vestibulum. Cras vehicula erat.", "Nintendo", "Controller", 1200, "https://m.media-amazon.com/images/I/71dS1k41DsL._AC_SX679_.jpg", 1),
("Controller for Nintendo Switch - Pikachu", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus lacinia odio vitae vestibulum vestibulum. Cras vehicula erat.", "Nintendo", "Controller", 813, "https://m.media-amazon.com/images/I/71jfuRXTq-L._AC_SX679_.jpg", 1);

*/

select *
from product;

select *
from detailed_purchase_history;

select *
from purchase_history;

select *
from user;

-- Queries 

-- All the purchases of a certain user 
select p.* 
from user u
inner join purchase_history p 
on u.id_user = p.id_user
where u.id_user = 1;

-- All the products in a certain purchase of a certain user 
select d.*, pro.name, pro.image
from user u
inner join purchase_history p 
on u.id_user = p.id_user
inner join detailed_purchase_history d
on d.id_purchase_history = p.id_purchase_history
inner join product pro
on pro.id_product = d.id_product
where u.id_user = 1 and p.id_purchase_history = 1;

