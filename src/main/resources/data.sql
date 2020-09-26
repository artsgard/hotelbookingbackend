INSERT INTO client (username, password, first_name, last_name, email, register_date)
VALUES ('js', 'secret', 'Johann Sebastian', 'Bach', 'jsbach@gmail.com', '2019-08-12 09:50:45');

INSERT INTO hotel (name, city, street, phone, email, description, breakfast_included, single_room, double_room, triple_room) 
VALUES ('Florence City Hotel', '50123 Firenze', 'Via S.Antonino, 18', '234354', 'cityhotelflorence@gmail.com',  'A short stroll from the most interesting artistic and cultural sites, the hotel is centrally located, in the most ancient part of Florence. It is also a convenient base for the shopper and enjoys a closeness of 150 meters to the main railway station. All the en-suite rooms are furnished in traditional tuscan style and fully equipped with TV with CNN/SKY and lift is also available.', '15.00', '60.00', '80.00', '90.00');

INSERT INTO hotel (name, city, street, phone, email, description, breakfast_included, single_room, double_room, triple_room) 
VALUES ('Croce Di Malta Hotel','Florence, FI, 50123, Italy', 'Via Della Scala 7', '456723', 'crocedimalta@gmail.com', 'What is around Croce Di Malta Hotel. You will want to pop into the Officina Profumo-Farmaceutica di Santa Maria Novella, rumored to be the world s oldest pharmacy, which still sells the rosewater distillate the monks produced when the Black Death wiped out 70 percent of the population of Florence.', '15.00', '70.00', '80.00', '100.00');

INSERT INTO hotel (name, city, street, phone, email, description, breakfast_included, single_room, double_room, triple_room) 
VALUES ('Plaza Hotel Lucchesi','Florence, FI, 50122, Italy', 'Lungarno Della Zecca Vecchia 38', '6534576', 'lucchesihotel@gmail.com',  'Santa Croce is a bustling, dynamic area hosting many concerts, festivals, and markets. Restaurants and bars are continuously packed with locals as well as tourists; you will be immersed in a lively, cosmopolitan atmosphere. Don not miss the ubiquitous leather shops, dotted around the piazza, for which this area is famous. Situated on the Piazza di Santa Croce, The collection of Santa Croce of art is considered the most important of any church in Florence; the most famous works the Giotto frescoes, Donatello s Annunciation; the memorial to Giovanni Battista Niccolini is said to be the inspiration for the Statue of Liberty. ', '15.00', '110.00', '150.00', '300.00');

INSERT INTO hotel_media (description, link, title, hotel_id) VALUES ('facade','../assets/img/hotelcity.jpg', 'Florence City Hotel', (SELECT id FROM hotel WHERE name='Florence City Hotel'));
INSERT INTO hotel_media (description, link, title, hotel_id) VALUES ('facade','../assets/img/crocedimalta.PNG', 'Croce Di Malta Hotel', (SELECT id FROM hotel WHERE name='Croce Di Malta Hotel'));
INSERT INTO hotel_media (description, link, title, hotel_id) VALUES ('facade','../assets/img/lucchesihotel.PNG', 'Plaza Hotel Lucchesi', (SELECT id FROM hotel WHERE name='Plaza Hotel Lucchesi'));
