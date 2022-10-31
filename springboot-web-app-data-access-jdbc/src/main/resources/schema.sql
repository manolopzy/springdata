DROP TABLE IF EXISTS equip;
CREATE TABLE equip
(
id varchar(36) not null primary key,
description varchar(255) not null,
created timestamp,
modified timestamp,
completed boolean
);