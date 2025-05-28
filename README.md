# Desafio 1 - SEA: Sistema de Gerenciamento de Clientes

Este projeto faz parte do **Desafio 1 da SEA** e implementa um sistema de **autenticação** e **gerenciamento de clientes**, com:

- **Backend** em Java (Spring Boot)
- **Frontend** em React

---

## 📁 Estrutura do Projeto
/backend → API REST em Java com Spring Boot
/frontend → Interface web em React

---

## ✅ Funcionalidades

### 🔐 Autenticação
- Login com dois perfis de usuário:
  - **admin** (permite criação e edição)
  - **user** (apenas visualização)
- Proteção de rotas no frontend (React Router v6 + ProtectedRoute).
- Logout destrói o token e redireciona ao login.

### 👥 Gerenciamento de Clientes
- CRUD de clientes (Create, Read, Update, Delete) disponível para **admin**.
- Visualização de lista e detalhe disponível para **admin** e **user**.
- Campos do cliente:
  - **Nome** (3–100 caracteres; letras, números e espaços)
  - **CPF** (11 dígitos; exibido com máscara, persistido sem máscara)
  - **CEP** (exibido com máscara, persistido sem máscara)
  - **Endereço** (preenchido via ViaCEP; editável)
  - **Telefones** (mínimo 1; tipos residencial/comercial/celular; máscaras diferentes)
  - **E-mails** (mínimo 1; formato válido)

### 📡 Integração com API Externa
- **ViaCEP**: ao digitar CEP, busca logradouro, bairro, cidade e UF.

### 💡 Validações
- CPF: apenas números e 11 dígitos.
- CEP: formato `00000-000`.
- Telefone: máscaras de acordo com tipo.
- E-mail: validação básica de formato.
- Feedback imediato de erros no frontend.

---

## 🧪 Pré-requisitos

- **Java 17+**  
- **Maven**  
- **Node.js 16+**  
- **npm** ou **yarn**  

---

## 🚀 Como Executar

### 1. Backend (Java Spring Boot)

```bash
cd backend
./mvnw spring-boot:run
```
A API REST ficará disponível em http://localhost:8080/.
Usuários pré-criados

Login: admin  Senha: 123qwe!@#
Login: user   Senha: 123qwe123

---

2. Frontend (React)
cd frontend
npm install
npm start

A interface estará em http://localhost:3000/.

---

🛠️ Tecnologias Utilizadas
- Backend

Java 17

Spring Boot

Spring Security (JWT)

Spring Data JPA (Hibernate)

Banco de dados H2 (in-memory)

Lombok

- Frontend

React 18

React Router v6

Axios

react-input-mask

CSS modular (App.css, LoginPage.css, Navbar.css)

Integração com API ViaCEP
