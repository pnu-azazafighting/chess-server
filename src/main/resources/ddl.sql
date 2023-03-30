DROP TABLE IF EXISTS chessdb.games;
DROP TABLE IF EXISTS chessdb.users;

create table chessdb.users
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null,
    uuid varchar(255) null
);

create table chessdb.games
(
    id         bigint auto_increment
        primary key,
    uuid       varchar(255) null,
    player1_id bigint       null,
    player2_id bigint       null,
    constraint FKp3rwwk871fnhxnb5w6cn50bfq
        foreign key (player1_id) references chessdb.users (id),
    constraint FKt3h7cejsfr7mdg8ol5jlvi2pg
        foreign key (player2_id) references chessdb.users (id)
);

create index games_uuid_index
    on chessdb.games (uuid);

create index users_uuid_index
    on chessdb.users (uuid);

