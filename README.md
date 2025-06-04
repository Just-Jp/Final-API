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
