INSERT INTO client (username, password, first_name, last_name, email, register_date)
VALUES ('js', 'secret', 'Johann Sebastian', 'Bach', 'jsbach@gmail.com', '2019-08-12 09:50:45');

INSERT INTO hotel (name, city, street, breakfast_included, description, single_room, double_room, triple_room) 
VALUES ('City Hotel','Heerlen', 'Wannerplein 23', true, 'Some Nice Hotel', '60.00', '80.00', '90.00');

INSERT INTO hotel_media (description, link, title, hotel_id) VALUES ('facade','Some-link', 'City Hotel', (SELECT id FROM hotel WHERE name='City Hotel'));
