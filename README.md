# yara_silva.Jp_capacitacao

**Objetivo:** Gerenciar operações de um e-commerce, abrangendo desde o catálogo de produtos e categorias até o controle de carrinho de compras, transações de estoque e processamento de pedidos.

**Contexto:** Este projeto foi desenvolvido como a entrega final do processo de capacitação para a vaga de JP 40+ (Trainee de Sistemas) da Minsait.

---

## Tecnologias e Dependências

- **Linguagem:** Java (JDK 21)
- **Framework:** Spring Boot
- **Segurança:** Spring Security com autenticação via token JWT
- **Banco de Dados:** Oracle SQL
- **Gerenciador de Dependências:** Maven
- **Documentação da API:** SpringDoc OpenAPI (Swagger-UI)
- **Validação:** Spring Boot Starter Validation

---

## Pré-requisitos

- **JDK 21:** Certifique-se de que a JDK 21 esteja instalada e configurada em seu ambiente.
- **Banco de Dados Oracle:** Uma instância do Oracle SQL rodando e configurada para receber as conexões.
- **Ambiente de Desenvolvimento:** Recomenda-se utilizar uma IDE de sua preferência (ex.: IntelliJ IDEA, Eclipse).

---

## Instalação e Execução

1. **Clone o Repositório:**

   ```bash
   git clone [https://github.com/Yara-Silva05/yara_silva.Jp_capacitacao.git](https://github.com/Yara-Silva05/yara_silva.Jp_capacitacao.git)
   ```

2. **Abra o Projeto na IDE:** Importe o projeto utilizando sua IDE preferida e certifique-se de que a JDK 21 está configurada no projeto.

3. **Configuração do Banco de Dados:** Verifique o arquivo `application.properties` (na pasta `src/main/resources`) e ajuste as credenciais (URL, usuário e senha) para conectar ao seu banco de dados Oracle.

4. **Inicie o Projeto:** Execute a classe principal da aplicação diretamente pela sua IDE. O Maven cuidará de todas as dependências.

---

## Documentação da API (Swagger)

A API está documentada com o Swagger UI, facilitando os testes e a visualização dos endpoints disponíveis. Após iniciar a aplicação, acesse a documentação no seu navegador:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

*Nota: Alguns endpoints exigem autenticação. Você deve criar um usuário, realizar o login para obter o token JWT e inseri-lo no botão "Authorize" do Swagger para liberar as rotas protegidas.*

---

## Estrutura do Projeto (Arquitetura em Camadas)

O projeto segue boas práticas de separação de responsabilidades, organizado nos seguintes pacotes principais:

- **`controllers`**: Expõem os endpoints da API REST (Authentication, Product, Order, Cart, etc.).
- **`services`**: Contêm toda a lógica de negócio e regras da aplicação.
- **`repository`**: Interfaces do Spring Data JPA para comunicação direta com o banco de dados Oracle.
- **`models`**: Entidades de domínio mapeadas para o banco de dados (divididas em `main`, `suport` e `infrastructure`).
- **`dtos`**: Objetos de Transferência de Dados, separados em `request` (entrada) e `response` (saída), garantindo segurança e flexibilidade.
- **`securityConfig`**: Classes de configuração do Spring Security, filtros de requisição e gerenciamento do JWT.
- **`exceptions` / `exceptionHandler`**: Classes para tratamento global de erros (Controller Advice) e exceções personalizadas (ex: `ProductNotFoundException`).
- **`enums`**: Constantes e tipos enumerados (ex: `RoleEnum`, `OrderStatusEnum`).

---

## Melhorias Futuras e Próximos Passos

Como o projeto foi desenvolvido dentro de uma janela de tempo curta para a avaliação, algumas das funcionalidades avançadas de e-commerce mapeadas para o ecossistema foram priorizadas para versões futuras. Sugestões de evolução incluem:

- **Relatórios e Métricas:** Implementação de endpoints analíticos (produtos mais vendidos, baixo estoque, faturamento).
- **Sistema de Cupons:** Evolução do módulo de promoções para contemplar códigos de cupons com regras de limite de uso e validade.
- **Auditoria Avançada:** Aprimorar o módulo `AuditLog` para rastreabilidade completa de quem alterou registros críticos e quando.
- **Testes:** Desenvolvimento de cobertura de testes unitários e de integração utilizando JUnit e Mockito.

---

## Contato

- **Autora:** Yara Silva Alves
- **LinkedIn:** [https://www.linkedin.com/in/yara-silva-alves-dev](https://www.linkedin.com/in/yara-silva-alves-dev)
- **Email:** [yara.silva.alves05@gmail.com](mailto:yara.silva.alves05@gmail.com)
