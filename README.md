# TechForum API

## Sobre o projeto
O **TechForum API** é uma API REST desenvolvida com Java utilizando o ecossistema Spring para simular o funcionamento de um fórum de tecnologia. A aplicação permite o gerenciamento de tópicos, usuários, cursos e perfis, além de implementar autenticação e controle de acesso.

O sistema segue boas práticas de desenvolvimento de APIs REST, incluindo separação de camadas, uso de DTOs para comunicação via JSON, validação de dados e autenticação stateless baseada em token.

Entre as principais funcionalidades estão:

- Cadastro de novos tópicos
- Atualização e exclusão de tópicos
- Listagem de tópicos cadastrados
- Autenticação de usuários
- Controle de acesso às rotas protegidas

A autenticação da aplicação é realizada utilizando **JWT (JSON Web Token)**, permitindo que a API seja stateless e adequada para integração com aplicações front-end ou outros serviços.

---

## Tecnologias utilizadas

As seguintes tecnologias foram utilizadas no desenvolvimento do projeto:

- **Java 17+**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Hibernate**
- **MySQL**
- **Flyway (migrations de banco de dados)**
- **Lombok**
- **Maven**

Ferramentas auxiliares utilizadas durante o desenvolvimento:

- **Postman / Insomnia** para testes das requisições da API
- **MySQL Workbench** para gerenciamento do banco de dados

---

## Como executar o projeto

### 1. Clonar o repositório

\`\`\`bash
git clone https://github.com/seu-usuario/tech_forum.git
cd tech_forum
\`\`\`

### 2. Configurar o banco de dados

Crie um banco de dados MySQL:

\`\`\`sql
CREATE DATABASE techforum;
\`\`\`

Depois configure o arquivo:

\`\`\`
src/main/resources/application.properties
\`\`\`

Exemplo:

\`\`\`properties
spring.datasource.url=jdbc:mysql://localhost:3306/techforum
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
\`\`\`

As tabelas serão criadas automaticamente através das migrations.

---

### 3. Executar a aplicação

Execute o projeto utilizando o Maven:

\`\`\`bash
mvn spring-boot:run
\`\`\`

Ou execute a classe principal:

\`\`\`
TechforumApplication.java
\`\`\`

---

### 4. Testar a API

A aplicação ficará disponível em:

\`\`\`
http://localhost:8080
\`\`\`

Exemplo de requisição de autenticação:

\`\`\`
POST /login
\`\`\`

Body JSON:

\`\`\`json
{
  \"login\": \"usuario@email.com\",
  \"senha\": \"123456\"
}
\`\`\`

Após autenticação, a API retornará um **JWT**, que deve ser enviado nas demais requisições no header:

\`\`\`
Authorization: Bearer <token>
\`\`\`

---

## Estrutura do projeto

\`\`\`
controller
domain
repository
infra.security
\`\`\`

- **controller** → endpoints da API  
- **domain** → entidades e DTOs  
- **repository** → acesso ao banco de dados  
- **infra.security** → configuração de autenticação e segurança

---

## Autor

Projeto desenvolvido como parte de estudos em **Java e desenvolvimento de APIs com Spring Boot** do programa ONE da ORACLE.

YuriOC

GitHub Profile: https://github.com/YuriOC
