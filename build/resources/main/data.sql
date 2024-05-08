use mobilelele;

insert into brands (id, brand, created, modified)
values (1, 'Toyota', '1937-3-28 19:30:35', null),
       (2, 'Ford', '1903-6-16 20:20:20', null);

insert into models(category, created, end_year, image_url, modified, name, start_year, brand_id)
values ('CAR', '1968-5-11 10:22:31', '2022', null, null, 'Focus', '1968', 2),
       ('CAR', '1976-7-21 12:31:21', '2023', null, null, 'Fiesta', '1976', 2),
       ('CAR', '1966-8-13 21:34:22', '2024', null, null, 'Corolla', '1966', 1),
       ('CAR', '1982-3-23 20:43:12', '2024', null, null, 'Camry', '1982', 1);