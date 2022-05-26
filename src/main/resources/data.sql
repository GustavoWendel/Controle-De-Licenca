create table table_cliente (
    id_cliente  bigserial
        constraint table_cliente_pkey
            primary key,
    cnpj        varchar(255),
    email       varchar(255),
    nome        varchar(255),
);