create table if not exists orders
(
    id           uuid                 not null
        constraint orders_pk
            primary key,
    order_id     uuid                 not null
        constraint orders_pk_2
            unique,
    order_status varchar(255)         not null,
    order_name   varchar(255)         not null,
    price        numeric default 0.00 not null
);

create table if not exists order_outbox
(
    id            uuid         not null
        constraint order_outbox_pk
            primary key,
    aggregatetype varchar(255) not null,
    aggregateid   varchar(255) not null,
    type          varchar(255) not null,
    payload       jsonb        not null
);