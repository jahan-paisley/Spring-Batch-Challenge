create table STORE_ORDER
(
    ID            INT auto_increment
        primary key,
    ORDER_ID      VARCHAR(20)   not null,
    ORDER_DATE    DATE          not null,
    SHIP_DATE     DATE          not null,
    SHIP_MODE     VARCHAR(20),
    QUANTITY      INT           not null,
    DISCOUNT      DECIMAL(3, 2),
    PROFIT        DECIMAL(6, 2) not null,
    PRODUCT_ID    VARCHAR(20)   not null,
    CUSTOMER_NAME VARCHAR(255)  not null,
    CATEGORY      VARCHAR(255)  not null,
    CUSTOMER_ID   VARCHAR(20)   not null,
    PRODUCT_NAME  VARCHAR(255)
);
