CREATE TABLE tenant_empresa (
    id UUID PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);