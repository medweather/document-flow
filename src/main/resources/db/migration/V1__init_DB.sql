create table `firm` (
    `id` int not null auto_increment,
    `name` varchar(255) not null,
    `username` varchar(255) not null,
    `password` varchar(255) not null,
    `active` bit not null default 0,
    primary key (`id`),
    unique index `id_unique` (`id` asc) visible
);

create table `document` (
    `id` int not null auto_increment,
    `title` varchar(255) not null,
    `about` varchar(2048),
    `author_id` int not null,
    `other_side_id` int null,
    `firm_id` int null,
    `sign` bit not null default 0,
    `sign_other_side` bit not null default 0,
    primary key (`id`),
    unique index `document_id_unique` (`id` asc) visible
);

create table `restriction` (
    `id` int not null auto_increment,
    `time_start` int default 7,
    `time_finish` int default 21,
    `count_doc_flow` int default 10,
    `count_create_doc` int default 5,
    `period_create_doc` int default 1,
    `count_flow_bet_both_sides` int default 2,
    primary key (`id`)
);

create table `hibernate_sequence` (`next_val` bigint);
insert into `hibernate_sequence` values ( 1 );
insert into `hibernate_sequence` values ( 1 );

alter table `document`
add index `document_author_id_fk` (`author_id` asc) visible,
add index `document_other_side_id_fk` (`other_side_id` asc) visible,
add index `document_firm_id_fk` (`firm_id` asc) visible;
;
alter table `document`
add constraint `document_author_fk`
    foreign key (`author_id`)
    references `firm` (`id`)
    on delete cascade
    on update no action,
add constraint `document_other_side_fk`
    foreign key (`other_side_id`)
    references `firm` (`id`)
    on delete cascade
    on update no action,
add constraint `document_firm_fk`
    foreign key (`firm_id`)
    references `firm` (`id`)
    on delete cascade
    on update no action;