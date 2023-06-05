create table if not exists Accounts
(
    idx          bigint auto_increment
        primary key,
    user_id      varchar(45)                 not null,
    password     varchar(60)                 not null,
    name         varchar(60) charset utf8mb3 not null,
    nickname     varchar(15) charset utf8mb3 null,
    state        varchar(10)                 not null,
    email        varchar(45)                 not null,
    phone_number varchar(15)                 null,
    constraint id_UNIQUE
        unique (user_id),
    constraint idx_UNIQUE
        unique (idx)
);

create table if not exists Login_Logs
(
    account_idx bigint      not null
        primary key,
    login_date  timestamp   not null,
    ip_address  varchar(30) null,
    constraint fk_Login_Logs_Accounts1
        foreign key (account_idx) references Accounts (idx)
);

create table if not exists Profiles
(
    account_idx bigint       not null
        primary key,
    image_path  varchar(300) null,
    constraint fk_profile_Accounts
        foreign key (account_idx) references Accounts (idx)
);