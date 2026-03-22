# 🔐 API Login Page — Backend

API de autenticação completa desenvolvida com **Java 21** e **Spring Boot 3**, hospedada na **Railway**. O projeto vai além de um login simples, integrando autenticação via Google OAuth2, confirmação de cadastro por e-mail e tratamento global de exceções, seguindo as melhores práticas de desenvolvimento.

---

## 🚀 Funcionalidades

- **Autenticação JWT** — Login seguro com geração e validação de tokens JSON Web Token
- **Login Social com Google** — Autenticação via Google OAuth2 usando Google Identity Services (GSI)
- **Confirmação de Cadastro** — Sistema de `ConfirmationToken` para validar usuários via e-mail (SMTP)
- **Gestão de Usuários** — Cadastro, atualização de senha e recuperação de perfil autenticado
- **Segurança Avançada** — Filtros JWT customizados e proteção de rotas com Spring Security
- **Tratamento de Erros** — Respostas JSON padronizadas via `GlobalExceptionHandler`
- **Ambientes Configuráveis** — Perfis para Produção, Teste e Desenvolvimento

---

## 🛠️ Tecnologias

| Tecnologia | Descrição |
|---|---|
| Java 21 (LTS) | Linguagem principal |
| Spring Boot 4 | Framework base |
| Spring Security | Segurança e controle de rotas |
| Spring Data JPA | Persistência de dados |
| MySQL | Banco de dados relacional |
| JWT (JSON Web Token) | Autenticação stateless |
| Google OAuth2 (GSI) | Login social |
| Jakarta Mail (SMTP) | Envio de e-mails de confirmação |
| Maven | Gerenciador de dependências |

---

## 🏗️ Arquitetura

```
com.Jairo.API_Login_Page
├── 📁 config        # Configurações de ambiente (TestConfig, etc.)
├── 📁 controller    # Endpoints da API (Auth e User)
├── 📁 dto           # Objetos de transferência de dados (Request/Response)
├── 📁 entity        # Modelos de dados (User, ConfirmationToken)
├── 📁 exceptions    # Tratamento global de erros (GlobalExceptionHandler)
├── 📁 repository    # Interfaces de comunicação com o banco de dados
├── 📁 security      # Configurações de segurança, filtros JWT e CORS
└── 📁 services      # Regras de negócio (AuthService, JwtService, EmailService, GoogleAuthService)
```

---

## 📡 Endpoints da API

> A autenticação é baseada em **Bearer Token (JWT)**. Inclua o header `Authorization: Bearer <token>` nas rotas protegidas.

### Autenticação — `/auth`

| Método | Endpoint | Descrição | Body |
|---|---|---|---|
| `POST` | `/auth/register` | Registra um novo usuário | `UserCreatedDTO` |
| `POST` | `/auth/login` | Autentica e retorna o JWT | `LoginRequestDTO` |
| `POST` | `/auth/google` | Login via Google OAuth2 | `GoogleAuthRequestDTO` |
| `GET` | `/auth/confirm` | Valida cadastro pelo token de e-mail | `?token=...` |

### Usuário — `/user`

| Método | Endpoint | Descrição | Body |
|---|---|---|---|
| `GET` | `/user/me` | Retorna dados do usuário autenticado | — |
| `PUT` | `/user/password` | Atualiza a senha do usuário | `UserPasswordUpdateDTO` |

---

## ⚙️ Configuração de Ambientes

O projeto utiliza **Spring Profiles** para separar os ambientes:

| Perfil | Arquivo | Uso |
|---|---|---|
| Default / Dev | `application.properties` | Desenvolvimento local |
| Test | `application-test.properties` | Banco H2 em memória |
| Production | `application-production.properties` | Deploy na Railway |

### Variáveis de ambiente necessárias

```properties
# Banco de dados
spring.datasource.url=...
spring.datasource.username=...
spring.datasource.password=...

# JWT
api.security.token.secret=sua-chave-secreta

# SMTP (e-mail de confirmação)
brevo.api.key=sua api key da brevo

# Google OAuth2
google.client.id=seu-client-id.apps.googleusercontent.com
```

---

## ▶️ Como executar

**1. Clone o repositório:**
```bash
git clone https://github.com/jcdev01/API-Login-Page.git
cd API-Login-Page
```

**2. Configure as variáveis de ambiente** no arquivo `src/main/resources/application.properties`

**3. Execute o projeto:**
```bash
mvn spring-boot:run
```

A API será iniciada em `http://localhost:8080`

---

## 🛠️ Detalhes técnicos

- **`JwtAuthFilter`** intercepta todas as requisições e valida o token antes de chegar nos controllers
- **`SecurityConfig`** define as rotas públicas (`/auth/**`) e protegidas, além da configuração de CORS para o frontend hospedado na Vercel
- **`GlobalExceptionHandler`** captura exceções como `ResourceNotFoundException` e retorna respostas JSON padronizadas
- **`EmailService`** envia e-mails de confirmação via SMTP com link contendo o `ConfirmationToken`
- **`GoogleAuthService`** valida o `idToken` recebido do frontend junto à API do Google e retorna um JWT próprio da aplicação

---

## 🚀 Deploy

A API está hospedada na **[Railway](https://railway.app)**, garantindo alta disponibilidade e escalabilidade.

- O perfil de produção é ativado automaticamente via variável `SPRING_PROFILES_ACTIVE=production`
- O arquivo `system.properties` define a versão do Java para o ambiente Railway:

```properties
java.runtime.version=21
```

O frontend está hospedado na **[Vercel](https://vercel.com)** e se comunica com esta API via HTTPS.

---

## ⚠️ Avisos

> **Acesso restrito:** Atualmente apenas o frontend oficial da aplicação está autorizado a realizar requisições. Ele está disponível em **https://login-frontend-orcin.vercel.app**, mas você pode rodar a API localmente sem problemas.

---

## 👨‍💻 Autor

Desenvolvido por **Jairo**
