-- This file contains all the queries that can be entered to be create the database information.

create schema `library`;

create table `library`.`user_types` (
    `user_type_id`   int            not null,
    `user_type`      varchar(255)   not null,
    primary key (`user_type_id`),
    unique (`user_type`)
);

insert into `library`.`user_types` (`user_type_id`, `user_type`)
values (0, 'Demo'),
       (1, 'Customer'),
       (2, 'Standard'),
       (3, 'Administrator');

create table `library`.`users` (
    `user_id`        int            not null,
    `user_type_id`   int            not null,
    `first_name`     varchar(127)   not null,
    `last_name`      varchar(127)   not null,
    `postal_code`    varchar(10)    not null,
    primary key (`user_id`),
    foreign key (`user_type_id`) references `user_types`(`user_type_id`)
);

-- Names and Postcodes have been randomly generated. This is not real information.
insert into `library`.`users`
values (1, 0, 'Demo', 'Account', 'AB123CD'),
       (2, 3, 'Admin', 'Account', 'JI832HU'),
       (3, 2, 'Standard', 'Account', 'IU921ED'),
       (4, 1, 'Axel', 'Vazquez', 'PA686EL'),
       (5, 1, 'Alexa', 'Haley', 'ME104TB'),
       (6, 1, 'Edward', 'Cooper', 'ME43NX'),
       (7, 1, 'Stevie', 'Spence', 'DN40SU'),
       (8, 1, 'Celine', 'Lynch', 'EH193GF'),
       (9, 1, 'Aryan', 'Frazier', 'CF390NB'),
       (10, 1, 'Keiran', 'Blanchard', 'HA11SZ'),
       (11, 1, 'Kristen', 'Bray', 'LL198ED'),
       (12, 1, 'John', 'Bridges', 'TW200SL'),
       (13, 1, 'Olivia', 'French', 'DD109NR'),
       (14, 1, 'Allan', 'Smith', 'KT151TL');

create table `library`.`employee_status` (
    `status_id`   int            not null,
    `status`      varchar(255)   not null,
    primary key (`status_id`),
    unique (`status`)
);

insert into `library`.`employee_status`
values (-1, 'Disabled'),
       (0, 'Not Approved'),
       (1, 'Active');

create table `library`.`employees` (
    `employee_id`   int            not null   auto_increment,
    `user_id`       int            not null,
    `username`      varchar(255)   not null,
    `password`      varchar(255)   not null,
    `status_id`     int            not null,
    primary key (`employee_id`),
    foreign key (`user_id`) references `users`(`user_id`),
    foreign key (`status_id`) references `employee_status`(`status_id`),
    unique (`username`)
);

insert into `library`.`employees` (`user_id`, `username`, `password`, `status_id`)
values (1, 'demo', 'password', 1),
       (2, 'admin', '6oUpZ6k$^iw4J&', 1),
       (3, 'user', 'SqpQPP&9jWHmr4g', 1);

create table `library`.`item_types` (
    `item_type_id`   int            not null,
    `item_type`      varchar(255)   not null,
    primary key (`item_type_id`),
    unique (`item_type`)
);

insert into `library`.`item_types`
values (1, 'Books'),
       (2, 'Films');

create table `library`.`item_subtypes` (
    `item_subtype_id`    int            not null,
    `item_type_id`       int            not null,
    `item_subtype`       varchar(255)   not null,
    primary key (`item_subtype_id`),
    foreign key (`item_type_id`) references `item_types`(`item_type_id`),
    unique (`item_subtype`)
);

insert into `library`.`item_subtypes`
values (11, 1, 'Audio Book'),
       (12, 1, 'Physical Book'),
       (13, 1, 'Large Print Book'),
       (14, 1, 'Electronic Book'),
       (21, 2, 'DVD'),
       (22, 2, 'Blu Ray');

create table `library`.`books` (
    `isbn`               varchar(13)    not null,
    `title`              varchar(255)   not null,
    `author`             varchar(255)   not null,
    `publication_year`   int            not null,
    `publisher`          varchar(255)   not null,
    `image_url`          varchar(511)   not null,
    primary key (`isbn`)
);

-- Books From Jack Edwards.
insert into `library`.`books`
values ('9780063021426',
        'Babel: An Arcane History',
        'R.F. Kuang',
        2022,
        'Harper Voyager',
        'https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1677361825i/57945316.jpg'),
       ('9781250855091',
        'The Atlas Paradox',
        'Olivie Blake',
        2022,
        'Tor Books',
        'https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1665848955i/59808248.jpg'),
       ('9781250313102',
        'Hell Bent',
        'Leigh Bardugo',
        2023,
        'Flatiron Books',
        'https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1648744814i/60652997.jpg'),
       ('9781250888167',
        'Alone With You in the Ether',
        'Olivie Blake',
        2023,
        'Tor Books',
        'https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1661863300i/61126612.jpg'),
       ('9781593767136',
        'The Novelist: A Novel',
        'Jordan Castro',
        2022,
        'Soft Skull',
        'https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1636218008i/59147138.jpg'),
       ('9781785632921',
        'Sour Grapes',
        'Dan Rhodes',
        2022,
        'Lightning Books',
        'https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1641047678i/59131324.jpg'),
       ('9780008477790',
        'Treacle Walker',
        'Alan Garner',
        2021,
        'Fourth Estate',
        'https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1622410947i/58205835.jpg');

create table `library`.`films` (
    `film_id`            varchar(13)    not null,
    `title`              varchar(255)   not null,
    `director`           varchar(255)   not null,
    `release_year`       int            not null,
    `distributor`        varchar(255)   not null,
    `duration_minutes`   int            not null,
    `image_url`          varchar(511)   not null,
    primary key (`film_id`)
);

-- Films From Rotten Tomatoes.
-- https://editorial.rottentomatoes.com/guide/popular-movies/ [12/04/2023]
insert into `library`.`films`
values ('2305001',
		'Dungeons & Dragons: Honor Among Thieves',
        'Jonathan M. Goldstein, John Francis Daley',
        2023,
        'Paramount Pictures',
        134,
        'https://resizing.flixster.com/IwFK1YWt1-pAzhnGdY9KTLLjEQM=/fit-in/180x240/v2/https://resizing.flixster.com/eWLvyboOEGZRS2JiBDZ6eRD4Hps=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzLzU2MTc5YzE5LTc3YjktNDIyMS05ZWZhLTNlNzBjZmZkM2JlMS5qcGc='),
       ('2305002',
        'John Wick: Chapter 4',
        'Chad Stahelski',
        2023,
        'Lionsgate',
        169,
        'https://resizing.flixster.com/AplxvuaxmELPdwmen0mu3uivAlA=/fit-in/180x240/v2/https://resizing.flixster.com/ayiSi8HrUS3_i-Jkru1P2oMM58Q=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzLzhiODAwYWIyLTM1MmItNGI1ZS1iOWQzLTdjZjAzMzI1MDc2Yy5qcGc='),
       ('2305003',
        'Tetris',
        'Jon S. Baird',
        2023,
        'Apple TV+',
        118,
        'https://resizing.flixster.com/dLBUfur1S2S0UixSLuYlRzkbtlY=/fit-in/180x240/v2/https://resizing.flixster.com/CgZKFQ_w0OKHyNaytfTLouVx_IQ=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzLzNmMmM2N2IwLWU1NTgtNGFiMS05NjAzLWYyYTU0MjU1OTMwNi5wbmc='),
       ('2305004',
        'Murder Mystery 2',
        'Jeremy Garelick',
        2023,
        'Netflix',
        90,
        'https://resizing.flixster.com/2Svpxqw2w2mSmxzgYKEreo7EfSw=/fit-in/180x240/v2/https://resizing.flixster.com/gEGUzfZgI78x2GWQOLnxe1Lhtmo=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzLzExNjYzYzI3LWEzMTItNGNjMC1iODlmLWU4MjEwYjNhM2NlMi5qcGc='),
       ('1912001',
        'I See You',
        'Adam Randall',
        2019,
        'Saban Films',
        96,
        'https://resizing.flixster.com/vfmLC_IkgMU9LLyS5BMQXbikbGw=/fit-in/180x240/v2/https://flxt.tmsimg.com/assets/p17297689_p_v8_ad.jpg'),
       ('2207001',
        'Nope',
        'Jordan Peele',
        2022,
        'Universal Pictures',
        135,
        'https://resizing.flixster.com/8g2KAMyH-BM43wTdkeduePP5lhM=/fit-in/180x240/v2/https://resizing.flixster.com/6EeYrywKueOFue9uRSQqLxfGKI0=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzLzA0NzZhZTk0LTI3NjctNGZiNi04Yjg4LWRiN2RkNTJiYWZlNy5qcGc='),
       ('1905001',
        'Dragged Across Concrete',
        'S. Craig Zahler',
        2019,
        'Summit Entertainment, Lionsgate Films',
        159,
        'https://resizing.flixster.com/j7Gfw41lUNEIJVdxdIcYttoAxxk=/fit-in/180x240/v2/https://flxt.tmsimg.com/assets/p16531609_p_v8_ac.jpg');

create table `library`.`item_copies` (
    `item_copies_id`     int           not null   auto_increment,
    `item_id`            varchar(13)   not null,
    `item_subtype_id`    int           not null,
    `copies_available`   int           not null,
    primary key (`item_copies_id`),
    foreign key (`item_subtype_id`) references `item_subtypes`(`item_subtype_id`)
);

insert into `library`.`item_copies` (`item_id`, `item_subtype_id`, `copies_available`)
values ('9780063021426', 11, 10),
       ('9780063021426', 12, 10),
       ('9780063021426', 13, 10),
       ('9780063021426', 14, 10),
       ('9781250855091', 11, 10),
       ('9781250855091', 12, 10),
       ('9781250855091', 13, 10),
       ('9781250855091', 14, 10),
       ('9781250313102', 11, 10),
       ('9781250313102', 12, 10),
       ('9781250313102', 13, 10),
       ('9781250313102', 14, 10),
       ('9781250888167', 11, 10),
       ('9781250888167', 12, 10),
       ('9781250888167', 13, 10),
       ('9781250888167', 14, 10),
       ('9781593767136', 11, 10),
       ('9781593767136', 12, 10),
       ('9781593767136', 13, 10),
       ('9781593767136', 14, 10),
       ('9781785632921', 11, 10),
       ('9781785632921', 12, 10),
       ('9781785632921', 13, 10),
       ('9781785632921', 14, 10),
       ('9780008477790', 11, 10),
       ('9780008477790', 12, 10),
       ('9780008477790', 13, 10),
       ('9780008477790', 14, 10),
       ('2305001', 21, 5),
       ('2305001', 22, 5),
       ('2305002', 21, 5),
       ('2305002', 22, 5),
       ('2305003', 21, 5),
       ('2305003', 22, 5),
       ('2305004', 21, 5),
       ('2305004', 22, 5),
       ('1912001', 21, 5),
       ('1912001', 22, 5),
       ('2207001', 21, 5),
       ('2207001', 22, 5),
       ('1905001', 21, 5),
       ('1905001', 22, 5);

create table `library`.`loans` (
    `loan_id`           bigint        not null,
    `customer_id`       int           not null,
    `item_id`           varchar(13)   not null,
    `item_subtype_id`   int           not null,
    `loan_date`         date          not null,
    `return_date`       date          not null,
    `returned`          tinyint       not null,
    primary key (`loan_id`),
    foreign key (`customer_id`) references `users`(`user_id`),
    foreign key (`item_subtype_id`) references `item_subtypes`(`item_subtype_id`)
);

insert into `library`.`loans`
values (2023042810183091, 3, '2305002', 22, '2023-04-28', '2023-05-10', 0),
       (2023042810192542, 13,'9781250313102', 12, '2023-04-27', '2023-05-27', 0);