# Arquitetura do Sistema

O FlowDesk CRM utiliza uma arquitetura web mderna baseada em separação de responsa-
bilidades entre frontend, backend e banco de dados.

## Componentes

- Frontend: aplicação web construída com Next.js, responsável pela interface do usuário.
- Backend: API REST desenvolvida em Spring Boot, responsável pelas regras de negócio e segurança.
- Banco de dados: PostgreSQL, responsável pela persistência dos dados.

## Comunicação

A comunicação entre frontend e backend é feita via HTTP, utilizando API REST com payloads em JSON.

## Características

- arquitetura stateless
- separação clara de responsabilidades
- esalabilidade horizontal possível
- desacoplamento entre frontend e backend

## Estratégia Multi-tenant

O sistema utiliza a estratégia de banco único com tabelas compartilhadas e segregação lógica por `tenant_id`.

Cada registro relevante contém um identificador de empresa (`tenant_id`), garantindo o isolamento dos dados entre diferentes empresas.

## Regras

- todas as entidades de negócio possuem `tenant_id`
- o backend é responsável por garantir o isolamento dos dados
- usuários só acessam dados da sua própria empresa
- administradores da plataforma podem acessar múltiplos tenants

## Arquitetura do Backend

O backend segue uma arquitetura em camadas, separando responsabilidades entre entrada HTTP, regras de negócio e persistência.

### Camadas

- Controller: responsável por receber requisições HTTP
- Service: responsável pela lógica de negócio
- Repository: responsável pelo acesso ao banco de dados
- Domain: entidades do sistema
- DTO: objetos de entrada e saída da API
- Exception: tratamento global de erros
- Security: autenticação e autorização (implementado posteriormente)

## Arquitetura do Frontend

O frontend é estruturado de forma modular, separando componentes reutilizáveis, lógica de negócio e integração com API.

### Estrutura

- app: rotas e páginas
- components: componentes reutilizáveis
- features: módulos do sistema
- services: comunicação com API
- hooks: lógica reutilizável
- types: tipagens
- providers: contextos globais

## Convenções técnicas

### Idioma
Todo o código será escrito em inglês.

### Nomenclatura
- classes: PascalCase
- métodos e variáveis: camelCase
- tabelas e colunas: snake_case

### API
- padrão REST
- prefixo: /api/v1
- respostas padronizadas

### Erros
- tratamento global de exceções
- retorno consistente de erros