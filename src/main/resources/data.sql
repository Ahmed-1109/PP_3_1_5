CREATE TABLE if not exists users
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    first_name TEXT                  NOT NULL,
    last_name  TEXT                  NOT NULL,
    email      TEXT                  NOT NULL,
    password   VARCHAR(255)          NULL,
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uc_users_email UNIQUE (email(40))
);

CREATE TABLE if not exists roles
(
    id   BIGINT NOT NULL,
    name VARCHAR(255)          NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

create table if not exists users_roles
(
    users_id bigint not null,
    roles_id bigint not null,
    primary key (users_id, roles_id),
    FOREIGN KEY (users_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (roles_id) REFERENCES roles (id) ON DELETE CASCADE ON UPDATE CASCADE
);

insert ignore into users (id, email, first_name, last_name, password)
values (NULL, 'admin@mail.ru', 'admin', 'admin', '$2a$12$Ruw4eTi74OwUTDHfJcbVHunr2r/ADfWd4ya2rCS5lEVGAgAXqvaTG'),
       (NULL, 'user@mail.ru', 'user', 'user', '$2a$12$gDipmFGHQ85eTlOM.TsZmuWvWA6H/BgvCvvMVyfrnb600D.MXIj5S');

insert ignore into roles (id, name)
values (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');

insert ignore into users_roles (users_id, roles_id)
values (1, 1),
       (2, 2);

