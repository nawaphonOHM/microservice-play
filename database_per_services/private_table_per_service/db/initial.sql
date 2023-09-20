create user customer_service_use with encrypted password 'CUSTOMER_SERVICE_PASSWORD';

create user order_service_use with encrypted password 'ORDER_SERVICE_PASSWORD';

create table customer
(
    id           uuid not null
        constraint "CUSTOMER_pk"
            primary key,
    credit_limit numeric default 0.0
);

alter table customer
    owner to postgres;

grant delete, insert, references, select, trigger, truncate, update on customer to customer_service_use;

create table "ORDER"
(
    id          uuid    not null
        constraint "ORDER_pk"
            primary key,
    customer_id integer not null,
    status      boolean not null,
    total       numeric default 0.0
);

comment on table "ORDER" is 'This table for order service using';

alter table "ORDER"
    owner to postgres;

grant delete, insert, references, select, trigger, truncate, update on "ORDER" to order_service_use;

