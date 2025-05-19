create table payments
(
    id      uuid                 not null
        constraint payments_pk
            primary key,
    user_id uuid                 not null
        constraint payments_pk_2
            unique,
    balance numeric default 0.00 not null
);

create table payment_outbox
(
    id            uuid         not null
        constraint order_outbox_pk
            primary key,
    aggregatetype varchar(255) not null,
    aggregateid   varchar(255) not null,
    type          varchar(255) not null,
    payload       jsonb        not null
);