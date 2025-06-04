# 🛒 API E-Commerce – Trabalho Final Serratec

Este repositório contém a implementação de uma **API RESTful para um sistema de E-Commerce**, desenvolvida como **trabalho final da disciplina de API** no programa Serratec. A aplicação foi construída utilizando **Spring Boot**, **Spring Security com JWT**, **JPA/Hibernate**, com funcionalidades como envio de e-mails e geração de notas fiscais em **PDF**.

---

## 📌 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA (Hibernate)
- PostgreSQL
- Swagger/OpenAPI
- Biblioteca de PDF (ex: iText)
- API ViaCEP (para consulta de endereços)
- Envio de e-mails

---

## 🔐 Segurança

- **Autenticação** via **JWT** (`/login`)
- **Autorização baseada em perfis**:
  - `CLIENTE`
  - `FUNCIONARIO`
  - `GERENTE`
- Regras de acesso definidas em:  
  [`ConfigSeguranca.java`](src/main/java/com/example/demo/security/ConfigSeguranca.java)

---

## 🚀 Como Executar o Projeto

1. **Configure o Banco de Dados** (PostgreSQL):

   Execute os comandos SQL abaixo para inserir os dados iniciais:

   ```sql
   INSERT INTO usuario(nome, email, senha)
   VALUES
   ('Joao da Silva', 'joao@email.com', '$2a$12$d3Uz3Cec1B4NGnyESDdOe.7YuVMoh5TP2SkE3C7lMmUcVrUfm39Sa'), 
   ('Andre das coves', 'andre@email.com','$2a$12$etoe4PMMqD5KgOoixBY1gOPavOOnp4IASJVPAr9dQXFGM8SiX8ZEu');

   INSERT INTO perfil (nome) VALUES 
   ('CLIENTE'),
   ('FUNCIONARIO'),
   ('GERENTE');

   INSERT INTO usuario_perfil (id_usuario, id_perfil) VALUES
   ( (SELECT id_usuario FROM usuario WHERE email='joao@email.com'), (SELECT id_perfil FROM perfil WHERE nome='GERENTE') ),
   ( (SELECT id_usuario FROM usuario WHERE email='joao@email.com'), (SELECT id_perfil FROM perfil WHERE nome='FUNCIONARIO') ),
   ( (SELECT id_usuario FROM usuario WHERE email='andre@email.com'), (SELECT id_perfil FROM perfil WHERE nome='GERENTE') );
   ```

2. **Abra o projeto** na sua IDE (IntelliJ, Eclipse, VS Code etc).

3. **Execute a classe `FinalApplication`** para iniciar a aplicação.

4. **Acesse a API**:
   - API base: `http://localhost:8080/`
   - Documentação Swagger: `http://localhost:8080/swagger-ui.html`

---

## 📚 Documentação dos Endpoints

A documentação interativa completa está disponível via Swagger. Abaixo está um resumo organizado por entidade. Todos os endpoints estão protegidos conforme o tipo de usuário autenticado:

<details>
<summary><strong>Autenticação</strong></summary>

- `POST /login` – Retorna um JWT para autenticação.

</details>

<details>
<summary><strong>Usuários</strong> - <code>/usuarios</code></summary>

- `GET /usuarios`  
- `POST /usuarios`  
- `PUT /usuarios/{id}`  
- `DELETE /usuarios/{id}`  
- **Acesso:** FUNCIONARIO, GERENTE

</details>

<details>
<summary><strong>Clientes</strong> - <code>/clientes</code></summary>

- `GET /clientes`  
- `GET /clientes/{id}`  
- `GET /clientes/cpf/{cpf}`  
- `POST /clientes`  
- `PUT /clientes/{id}`  
- `DELETE /clientes/{id}`  
- **Acesso:** CLIENTE (apenas o próprio), FUNCIONARIO, GERENTE

</details>

<details>
<summary><strong>Produtos</strong> - <code>/produtos</code></summary>

- `GET /produtos`  
- `GET /produtos/completo`  
- `GET /produtos/{id}`  
- `POST /produtos`  
- `PUT /produtos/{id}`  
- `DELETE /produtos/{id}`  
- `PUT /produtos/inativar/{id}`  
- `PUT /produtos/reativar/{id}`  
- **Acesso:** CLIENTE, FUNCIONARIO, GERENTE

</details>

<details>
<summary><strong>Categorias</strong> - <code>/categorias</code></summary>

- `GET /categorias`  
- `GET /categorias/{id}`  
- `POST /categorias`  
- `PUT /categorias/{id}`  
- `DELETE /categorias/{id}`  
- **Acesso:** FUNCIONARIO, GERENTE

</details>

<details>
<summary><strong>Endereços</strong> - <code>/enderecos</code></summary>

- `GET /enderecos`  
- `GET /enderecos/{cep}`  
- **Acesso:** FUNCIONARIO, GERENTE

</details>

<details>
<summary><strong>Pedidos</strong> - <code>/pedidos</code></summary>

- `GET /pedidos`  
- `GET /pedidos/{id}`  
- `POST /pedidos`  
- `PUT /pedidos/{id}`  
- `DELETE /pedidos/{id}`  
- `GET /pedidos/{id}/nota-fiscal`  
- `POST /pedidos/{id}/nota-fiscal`  
- **Acesso:** CLIENTE (apenas o próprio), FUNCIONARIO, GERENTE

</details>

<details>
<summary><strong>Cupons</strong> - <code>/cupons</code></summary>

- `GET /cupons`  
- `GET /cupons/{id}`  
- `POST /cupons`  
- `DELETE /cupons/{id}`  
- `GET /cupons/validar/{codigo}`  
- `PUT /cupons/ativar/{id}`  
- `PUT /cupons/desativar/{id}`  
- **Acesso:** FUNCIONARIO, GERENTE

</details>

<details>
<summary><strong>WishLists</strong> - <code>/wishlists</code></summary>

- `POST /wishlists`  
- `GET /wishlists`  
- `GET /wishlists/cliente/{clienteId}`  
- `DELETE /wishlists/{id}`  
- `PUT /wishlists/{id}/adicionar-produto`  
- `PUT /wishlists/{id}/remover-produto/{produtoId}`  
- **Acesso:** CLIENTE (apenas o próprio), FUNCIONARIO, GERENTE

</details>

<details>
<summary><strong>Fidelidade</strong> - <code>/fidelidade</code></summary>

- `POST /fidelidade/{clienteId}`  
- `GET /fidelidade/{clienteId}`  
- `PUT /fidelidade/{clienteId}/ganhar/{pontos}`  
- **Acesso:** CLIENTE (apenas o próprio), FUNCIONARIO, GERENTE

</details>

---

## ⚠️ Avisos Importantes

- Certifique-se de usar **CEPs válidos** ao cadastrar endereços.
- Você pode consultar CEPs pela API [ViaCEP](https://viacep.com.br/).

---

## 👥 Membros do Grupo

| Nome               | GitHub                                         | Função no Projeto                         |
|--------------------|------------------------------------------------|-------------------------------------------|
| Adriana Franco     | [@adrianafranco](https://www.linkedin.com/in/adriana-franco-marins-00585958/) | Sistema de Categorias e Fidelidade
| Bernardo Ennes     | [@bernardoennes](https://www.linkedin.com/in/bernardo-ennes-7ab160343/)     | Pedidos e Clientes, Segurança (JWT), Geração de Nota Fiscal e ViaCep |
| Carlos 'Leo' Otoline   | [@carlosotoline](https://www.linkedin.com/in/carlos-leonardo-carvalho-otoline-694534278/) | Pedidos e Programa de Cupons |
| Joao Pedro B. Dias | [@joaobispo](https://github.com/seuusuario)   | Segurança (JWT), Sistema de Usuarios, Historico de Precos |
| Mateus Karl Peixoto     | [@mateuspeixoto](https://www.linkedin.com/in/mateus-karl-peixoto-a30656174/) | Documentação, Produtos e Wishlists |
| Patrick Oliveira |  [@patrickoliveira](https://www.linkedin.com/in/patrick-dos-santos-120211271/) | Deletar Logico e Produtos |
