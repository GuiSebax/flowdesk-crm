CREATE TABLE usuario (
    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha_hash VARCHAR(255) NOT NULL,
    ativo BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_usuario_tenant
        FOREIGN KEY (tenant_id)
        REFERENCES tenant_empresa(id)
)