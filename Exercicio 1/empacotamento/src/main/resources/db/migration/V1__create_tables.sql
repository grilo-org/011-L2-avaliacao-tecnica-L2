CREATE TABLE pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE caixa (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    pedido_id BIGINT,
    CONSTRAINT fk_caixa_pedido FOREIGN KEY (pedido_id) REFERENCES pedido(id)
);

CREATE TABLE produto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE caixa_produto (
    caixa_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    PRIMARY KEY (caixa_id, produto_id),
    CONSTRAINT fk_caixa_produto_caixa FOREIGN KEY (caixa_id) REFERENCES caixa(id) ON DELETE CASCADE,
    CONSTRAINT fk_caixa_produto_produto FOREIGN KEY (produto_id) REFERENCES produto(id) ON DELETE CASCADE
);

CREATE TABLE pedido_produto (
    pedido_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    PRIMARY KEY (pedido_id, produto_id),
    CONSTRAINT fk_pedido_produto_pedido FOREIGN KEY (pedido_id) REFERENCES pedido(id) ON DELETE CASCADE,
    CONSTRAINT fk_pedido_produto_produto FOREIGN KEY (produto_id) REFERENCES produto(id) ON DELETE CASCADE
);
