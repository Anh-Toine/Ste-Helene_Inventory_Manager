DROP TABLE IF EXISTS product;

CREATE TABLE IF NOT EXISTS product (
    product_id INT(4) AUTO_INCREMENT PRIMARY KEY,
    bar_code VARCHAR(255) NOT NULL UNIQUE,
    brand VARCHAR(255),
    category_id VARCHAR(255),
    price DOUBLE,
    product_name VARCHAR(255),
    quantity INT(100),
    quantity_sold INT(100)
) engine=InnoDB;