create schema `library`;

create table `library`.`user_types` (
    `user_type_id`   int           not null,
    `user_type`      varchar(45)   not null,

    primary key (`user_type_id`),
    unique index `user_type_unique` (`user_type` asc) visible
);

insert into `library`.`user_types`
values (0, 'Demo'),
       (1, 'Customer'),
       (2, 'Standard'),
       (3, 'Administrator');

create table `library`.`users` (
    `user_id`        int           not null,
    `user_type_id`   int           not null,
    `first_name`     varchar(60)   not null,
    `last_name`      varchar(60)   not null,
    `postal_code`    varchar(10)   not null,

    primary key (`user_id`)
);

insert into `library`.`users`
values (0, 0, 'Demo', 'Account', 'AB123CD'),
       (1, 3, 'Admin', 'Account', 'JI832HU'),
       (2, 2, 'Standard', 'Account', 'IU921ED');

create table `library`.`employees` (
    `employee_id`   int           not null,
    `user_id`       int           not null,
    `username`      varchar(45)   not null,
    `password`      varchar(45)   not null,
    `status`        int           not null,

    primary key (`employee_id`),
    unqiue index `username_unique` (`username` asc) visible
);

insert into `library`.`employees`
values (0, 0, 'demo', 'password', 1),
       (1, 1, 'admin', '6oUpZ6k$^iw4J&', 1),
       (2, 2, 'user', 'SqpQPP&9jWHmr4g', 1);

create table `library`.`item_types` (
    `item_type_id`   int           not null,
    `item_type`      varchar(60)   not null,

    primary key (`item_type_id`),
    unique index `item_type_unque` (`item_type` asc) visible
);

insert into `library`.`item_types`
values (1, 'Books')
       (2, 'Films');

create table `library`.`item_subtypes` (
    `item_subtypes_id`   int           not null,
    `item_type_id`       int           not null,
    `item_subtype`       varchar(60)   not null,

    primary key (`id_subtype_id`),
    unique index `item_subtype_unique` (`item_subtype` asc) visible
)

insert into `library`.`item_subtypes`
values (11, 1, 'Audio Book'),
       (12, 1, 'Physical Book'),
       (13, 1, 'Large Print Book'),
       (14, 1, 'Electronic Book'),
       (21, 2, 'DVD'),
       (22, 2, 'Blu Ray');

create table `library`.`items` (
    `item_id`         int            not null,
    `item_type_id`    int            not null,
    `name`            varchar(120)   not null,
    `release_year`    year(4)        not null,
    `image_url`       varchar(512)   not null,

    primary key (`item_id`)
);

insert into `library`.`items`
values ();

create table `library`.`books` (
    `book_id`   int            not null,
    `item_id`   int            not null,
    `isbn`      varchar(20)    not null,
    `author`    varchar(120)   not null,
    `edition`   int            not null,

    primary key (`book_id`),
    unique index `isbn_unique` (`isbn` asc) visible
);

insert into `library`.`books`
values ();

create table `library`.`films` (
    `film_id`            int            not null,
    `item_id`            int            not null,
    `director`           varchar(120)   not null,
    `lead_actor`         varchar(120)   not null,
    `duration_minutes`   int            not null,

    primary key (`film_id`)
);

insert into `library`.`films`
values ();

create table `library`.`loans` (
    `loan_id`       int       not null,
    `customer_id`   int       not null,
    `loan_date`     date      not null,
    `due_date`      date      not null,
    `returned`      tinyint   not null,

    primary key (`loan_id`)
);

insert into `library`.`loans`
values ();