CREATE TABLE IF NOT EXISTS caixa (
    codigo VARCHAR(50) PRIMARY KEY,
    altura DECIMAL(10,2) NOT NULL,
    largura DECIMAL(10,2) NOT NULL,
    comprimento DECIMAL(10,2) NOT NULL,
    volume_maximo DECIMAL(10,2) GENERATED ALWAYS AS (altura * largura * comprimento) STORED
);

INSERT INTO caixa (codigo, altura, largura, comprimento) VALUES
('CAIXA 1', 30.00, 40.00, 80.00),
('CAIXA 2', 80.00, 50.00, 40.00),
('CAIXA 3', 50.00, 80.00, 60.00);