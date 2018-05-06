-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2018-01-30 16:34:56.786

-- tables
-- Table: client
CREATE TABLE client (
    id int  NOT NULL,
    VAT varchar(64)  NOT NULL,
    client_name varchar(255)  NOT NULL,
    address varchar(255)  NULL,
    insert_time timestamp  NOT NULL,
    last_update_time timestamp  NOT NULL,
    CONSTRAINT client_pk PRIMARY KEY (id)
);

-- Table: continuous_service
CREATE TABLE continuous_service (
    id int  NOT NULL,
    name varchar(255)  NOT NULL,
    price_per_unit decimal(8,2)  NOT NULL,
    basic_unit varchar(255)  NOT NULL,
    tax_percentage decimal(8,2)  NOT NULL,
    active_for_sale bool  NOT NULL,
    default_automatic_prolongation_period int  NOT NULL,
    CONSTRAINT continuous_service_pk PRIMARY KEY (id)
);

-- Table: continuous_service_sale_item
CREATE TABLE continuous_service_sale_item (
    id int  NOT NULL,
    quantity_sold decimal(8,2)  NOT NULL,
    price_per_unit decimal(8,2)  NOT NULL,
    price decimal(8,2)  NOT NULL,
    tax_amount decimal(8,2)  NOT NULL,
    sale_id int  NOT NULL,
    service_id int  NOT NULL,
    salesperson_role_id int  NULL,
    start_time timestamp  NOT NULL,
    end_time timestamp  NULL,
    automatic_prolongation bool  NOT NULL,
    automatic_prolongation_period int  NOT NULL,
    CONSTRAINT continuous_service_sale_item_pk PRIMARY KEY (id)
);

-- Table: contract
CREATE TABLE contract (
    id int  NOT NULL,
    insert_time timestamp  NOT NULL,
    client_id int  NOT NULL,
    sale_id int  NOT NULL,
    VAT varchar(64)  NOT NULL,
    client_name varchar(255)  NOT NULL,
    CONSTRAINT contract_pk PRIMARY KEY (id)
);

-- Table: one_time_service
CREATE TABLE one_time_service (
    id int  NOT NULL,
    name varchar(255)  NOT NULL,
    price decimal(8,2)  NOT NULL,
    tax_percentage decimal(8,2)  NOT NULL,
    active_for_sale bool  NOT NULL,
    CONSTRAINT one_time_service_pk PRIMARY KEY (id)
);

-- Table: one_time_service_sale_item
CREATE TABLE one_time_service_sale_item (
    id int  NOT NULL,
    price decimal(8,2)  NOT NULL,
    tax_amount decimal(8,2)  NOT NULL,
    sale_id int  NOT NULL,
    service_id int  NOT NULL,
    salesperson_role_id int  NOT NULL,
    CONSTRAINT one_time_service_sale_item_pk PRIMARY KEY (id)
);

-- Table: product
CREATE TABLE product (
    id int  NOT NULL,
    name varchar(255)  NOT NULL,
    price_per_unit decimal(8,2)  NOT NULL,
    basic_unit varchar(255)  NOT NULL,
    tax_percentage decimal(4,2)  NOT NULL,
    limited bool  NOT NULL,
    active_for_sale bool  NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY (id)
);

-- Table: product_sale_item
CREATE TABLE product_sale_item (
    id int  NOT NULL,
    quantity_sold decimal(8,2)  NOT NULL,
    price_per_unit decimal(8,2)  NOT NULL,
    price decimal(8,2)  NOT NULL,
    tax_amount decimal(8,2)  NOT NULL,
    sale_id int  NOT NULL,
    product_id int  NOT NULL,
    CONSTRAINT sale_item_pk PRIMARY KEY (id)
);

-- Table: role
CREATE TABLE role (
    id int  NOT NULL,
    role_name varchar(100)  NOT NULL,
    CONSTRAINT role_pk PRIMARY KEY (id)
);

-- Table: sale
CREATE TABLE sale (
    id int  NOT NULL,
    time_created timestamp  NOT NULL,
    time_paid timestamp  NULL,
    sale_amount decimal(8,2)  NOT NULL,
    sale_amount_paid decimal(8,2)  NULL,
    tax_amount decimal(4,2)  NOT NULL,
    sale_status_id int  NOT NULL,
    user_has_role_id int  NOT NULL,
    CONSTRAINT sale_pk PRIMARY KEY (id)
);

-- Table: sale_status
CREATE TABLE sale_status (
    id int  NOT NULL,
    status_name varchar(255)  NOT NULL,
    CONSTRAINT sale_status_pk PRIMARY KEY (id)
);

-- Table: status
CREATE TABLE status (
    id int  NOT NULL,
    status_name varchar(100)  NOT NULL,
    is_user_working bool  NOT NULL,
    CONSTRAINT status_pk PRIMARY KEY (id)
);

-- Table: stock
CREATE TABLE stock (
    product_id int  NOT NULL,
    in_stock decimal(8,2)  NOT NULL,
    last_update_time timestamp  NOT NULL,
    CONSTRAINT stock_pk PRIMARY KEY (product_id)
);

-- Table: user_account
CREATE TABLE user_account (
    id int  NOT NULL,
    user_name varchar(100)  NOT NULL,
    email varchar(254)  NOT NULL,
    password varchar(200)  NOT NULL,
    password_salt varchar(50)  NULL,
    password_hash_algorithm varchar(50)  NOT NULL,
    CONSTRAINT user_account_pk PRIMARY KEY (id)
);

-- Table: user_has_role
CREATE TABLE user_has_role (
    id int  NOT NULL,
    role_start_time timestamp  NOT NULL,
    role_end_time timestamp  NULL,
    user_account_id int  NOT NULL,
    role_id int  NOT NULL,
    CONSTRAINT user_has_role_ak_1 UNIQUE (role_start_time, role_id, user_account_id) NOT DEFERRABLE  INITIALLY IMMEDIATE,
    CONSTRAINT user_has_role_pk PRIMARY KEY (id)
);

-- Table: user_has_status
CREATE TABLE user_has_status (
    id int  NOT NULL,
    status_start_time timestamp  NOT NULL,
    status_end_time timestamp  NULL,
    user_account_id int  NOT NULL,
    status_id int  NOT NULL,
    CONSTRAINT user_has_status_ak_1 UNIQUE (status_start_time, user_account_id) NOT DEFERRABLE  INITIALLY IMMEDIATE,
    CONSTRAINT user_has_status_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: contract_client (table: contract)
ALTER TABLE contract ADD CONSTRAINT contract_client
    FOREIGN KEY (client_id)
    REFERENCES client (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: contract_sale (table: contract)
ALTER TABLE contract ADD CONSTRAINT contract_sale
    FOREIGN KEY (sale_id)
    REFERENCES sale (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: invoice_invoice_status (table: sale)
ALTER TABLE sale ADD CONSTRAINT invoice_invoice_status
    FOREIGN KEY (sale_status_id)
    REFERENCES sale_status (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: invoice_item_invoice (table: product_sale_item)
ALTER TABLE product_sale_item ADD CONSTRAINT invoice_item_invoice
    FOREIGN KEY (sale_id)
    REFERENCES sale (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: invoice_item_product_service (table: product_sale_item)
ALTER TABLE product_sale_item ADD CONSTRAINT invoice_item_product_service
    FOREIGN KEY (product_id)
    REFERENCES product (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: invoice_user_has_role (table: sale)
ALTER TABLE sale ADD CONSTRAINT invoice_user_has_role
    FOREIGN KEY (user_has_role_id)
    REFERENCES user_has_role (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: one_time_service_sale_item_one_time_service (table: one_time_service_sale_item)
ALTER TABLE one_time_service_sale_item ADD CONSTRAINT one_time_service_sale_item_one_time_service
    FOREIGN KEY (service_id)
    REFERENCES one_time_service (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: one_time_service_sale_item_sale (table: one_time_service_sale_item)
ALTER TABLE one_time_service_sale_item ADD CONSTRAINT one_time_service_sale_item_sale
    FOREIGN KEY (sale_id)
    REFERENCES sale (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: one_time_service_sale_item_user_has_role (table: one_time_service_sale_item)
ALTER TABLE one_time_service_sale_item ADD CONSTRAINT one_time_service_sale_item_user_has_role
    FOREIGN KEY (salesperson_role_id)
    REFERENCES user_has_role (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: service_sale_item_sale (table: continuous_service_sale_item)
ALTER TABLE continuous_service_sale_item ADD CONSTRAINT service_sale_item_sale
    FOREIGN KEY (sale_id)
    REFERENCES sale (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: service_sale_item_service (table: continuous_service_sale_item)
ALTER TABLE continuous_service_sale_item ADD CONSTRAINT service_sale_item_service
    FOREIGN KEY (service_id)
    REFERENCES continuous_service (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: service_sale_item_user_has_role (table: continuous_service_sale_item)
ALTER TABLE continuous_service_sale_item ADD CONSTRAINT service_sale_item_user_has_role
    FOREIGN KEY (salesperson_role_id)
    REFERENCES user_has_role (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: stock_product (table: stock)
ALTER TABLE stock ADD CONSTRAINT stock_product
    FOREIGN KEY (product_id)
    REFERENCES product (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: user_has_role_role (table: user_has_role)
ALTER TABLE user_has_role ADD CONSTRAINT user_has_role_role
    FOREIGN KEY (role_id)
    REFERENCES role (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: user_has_role_user_account (table: user_has_role)
ALTER TABLE user_has_role ADD CONSTRAINT user_has_role_user_account
    FOREIGN KEY (user_account_id)
    REFERENCES user_account (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: user_has_status_status (table: user_has_status)
ALTER TABLE user_has_status ADD CONSTRAINT user_has_status_status
    FOREIGN KEY (status_id)
    REFERENCES status (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: user_has_status_user_account (table: user_has_status)
ALTER TABLE user_has_status ADD CONSTRAINT user_has_status_user_account
    FOREIGN KEY (user_account_id)
    REFERENCES user_account (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.

