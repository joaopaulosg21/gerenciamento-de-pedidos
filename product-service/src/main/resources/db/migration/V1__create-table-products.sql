CREATE TABLE product(
    id SERIAL NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL NOT NULL,
    quantity INT NOT NULL,
    PRIMARy KEY(id)
);