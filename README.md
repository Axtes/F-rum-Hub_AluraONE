# 📝 Fórum Hub - Projeto Back-End

Bem-vindos ao **Fórum Hub**, um projeto desenvolvido por mim durante o programa **Oracle Next Education (ONE)** em parceria com a **Alura**. Este projeto tem como objetivo aprofundar conhecimentos práticos no papel de um desenvolvedor **Back-End**, criando uma **API REST** completa utilizando **Java** e **Spring Boot**.

---

## 💡 Sobre o Desafio

O desafio do Fórum Hub foi projetado para que você possa:

- Criar e gerenciar endpoints REST ✅  
- Implementar camadas de segurança 🔒  
- Trabalhar com bancos de dados relacionais (MySQL) 🛢️  
- Aplicar conceitos avançados de **Java** e **Spring Boot** 💻  

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**  
- **Spring Boot**  
- **Spring Security**  
- **JPA / Hibernate**  
- **MySQL**  
- **Lombok**  
- **Maven**  

---

## ⚙️ Configuração do Banco de Dados

1. Crie um banco de dados no MySQL, por exemplo:  
   ```sql
   CREATE DATABASE forum_hub_db;


2. Ajuste as configurações de conexão no application.properties:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/forum_hub_db
    spring.datasource.username=SEU_USUARIO
    spring.datasource.password=SUA_SENHA
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true


3. Execute a aplicação e os schemas/tabelas serão criados automaticamente. 🎉

🚀 Como Rodar a Aplicação

1. Clone este repositório:
    ```terminal
    git clone <github.com/Axtes/Forum-Hub_AluraONE>


2. Entre no diretório do projeto:

    ```Diretório
    cd Forum_Hub_Alura-ONE


3. Execute a aplicação usando Maven ou sua IDE preferida:
    ```terminal
    mvn spring-boot:run


4. Acesse os endpoints via Postman ou Insomnia.

---

📚 Funcionalidades

Cadastro, atualização e remoção de usuários 👤

Criação, listagem e exclusão de tópicos no fórum 🗂️

Mensagens em tópicos 💬

Controle de acesso e autenticação 🔑

🔗 Endpoints Principais

POST /usuarios - Cadastrar usuário

GET /usuarios - Listar usuários

POST /topicos - Criar tópico

GET /topicos - Listar tópicos

POST /comentarios - Comentar em tópico
