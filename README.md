# 🚀 Eventos & Voluntariado: Sua Plataforma Full-Stack de Gestão Social

[![Feito com Spring Boot](https://img.shields.io/badge/Backend-Spring%20Boot-6DB33F?style=for-the-badge&logo=spring)](https://spring.io/)
[![Banco de Dados](https://img.shields.io/badge/Database-MySQL-4479A1?style=for-the-badge&logo=mysql)](https://www.mysql.com/)
[![Frontend](https://img.shields.io/badge/Frontend-Thymeleaf%20%26%20CSS3-005C9D?style=for-the-badge&logo=thymeleaf)](https://www.thymeleaf.org/)
[![Segurança](https://img.shields.io/badge/Security-Spring%20Security-6DB33F?style=for-the-badge&logo=spring-security)](https://spring.io/security)

## ✨ Sobre o Projeto

O Projeto de Extensão — Plataforma de Eventos e Voluntariado Comunitário é uma solução robusta para **gestão de eventos de extensão e o recrutamento de voluntários**. Desenvolvido com uma arquitetura moderna Full-Stack em Java, o sistema é projetado para ser intuitivo, seguro e visualmente atraente, unificando a experiência do voluntário e a eficiência do administrador.

### 🎯 Destaques Técnicos e Visuais

* **Persistência Segura:** Dados de eventos e voluntários armazenados no **MySQL**.
* **Segurança Robusta:** Implementação completa do **Spring Security** para autenticação (Login/Logout), autorização de acesso e proteção CSRF em todas as requisições `POST`.
* **Design Coeso (UX/UI):** Padrão de design consistente com paleta de cores vibrantes, cards semi-transparentes e tabelas padronizadas (`.data-table`) que garantem uma experiência de usuário unificada.

---

## 🛠️ Tecnologias Utilizadas

| Categoria | Tecnologia | Função no Projeto |
| :--- | :--- | :--- |
| **Inicialização** | **Spring Initializr** | Ferramenta padrão para setup inicial do projeto e gerenciamento de dependências. |
| **Backend** | **Java 17+** | Linguagem principal e lógica de negócio. |
| **Framework** | **Spring Boot** | Configuração rápida e ambiente de execução robusto. |
| **Persistência** | **Spring Data JPA / Hibernate** | Mapeamento Objeto-Relacional eficiente com o MySQL. |
| **Segurança** | **Spring Security** | Gerenciamento de sessões, perfis (Admin/Voluntário) e proteção de rotas. |
| **Frontend** | **Thymeleaf** | Motor de templates para renderização dinâmica das views. |
| **Database** | **MySQL** | Banco de dados relacional para persistência de dados. |

---

## 📚 Funcionalidades

### 👤 Módulo do Voluntário

| Funcionalidade | Detalhes |
| :--- | :--- |
| **Catálogo de Eventos** | Visualização de todos os eventos abertos em um layout de cards intuitivo. |
| **Inscrição / Cancelamento** | Processo seguro de inscrição e possibilidade de cancelamento via perfil. |
| **Meu Perfil** | Área para visualizar dados pessoais e eventos inscritos. |

### 👑 Módulo do Administrador

| Funcionalidade | Detalhes |
| :--- | :--- |
| **Dashboard** | Visão geral em tempo real com métricas (`metric-card`) de Eventos, Voluntários e Usuários. |
| **CRUD de Eventos** | Controle total para criar, editar e excluir eventos, gerindo vagas e informações. |
| **Gerenciamento de Voluntários** | Manutenção da base de dados de voluntários cadastrados. |

---

## ⚙️ Configuração e Execução Local

### Pré-requisitos

Certifique-se de ter instalado:
1.  **JDK** (Java Development Kit) 17+
2.  **Maven** (ou Gradle)
3.  **Servidor MySQL** (local ou remoto)

### 1. 💾 Configuração do Banco de Dados

1.  Crie um novo esquema no seu servidor MySQL (Ex: `CREATE DATABASE nome_do_seu_projeto;`).
2.  **Ajuste de Conexão:** Atualize o arquivo `src/main/resources/application.properties` com as **suas credenciais de acesso ao MySQL** (Usuário e Senha do seu servidor local).

#### properties
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_seu_projeto?useSSL=false&serverTimezone=UTC
spring.datasource.username=USUÁRIO_DO_SEU_MYSQL
spring.datasource.password=SENHA_DO_SEU_MYSQL
spring.jpa.hibernate.ddl-auto=update # Cria e atualiza as tabelas automaticamente.

## ▶️ Rodando a Aplicação

### 1️. Clone o repositório
git clone https://github.com/SarinhaCri/eventos-voluntariado-spring-boot.git
cd eventos-voluntariado-spring-boot

### 2. Execute a aplicação Spring Boot com Maven
./mvnw spring-boot:run

### 3️. Acesse no navegador
A aplicação estará disponível em:
http://localhost:8080

---

## 🔑 Credenciais de Teste

### Voluntário
- Acesso: /cadastro  
- Credenciais: criar uma conta no sistema

### Administrador
- Acesso: /login  
- Usuário: **admin**  
- Senha: **123456** (ajuste conforme sua configuração)

---

## 👥 Desenvolvedores
Este projeto foi desenvolvido em equipe:

- Sara Cristina Viana Rocha 
- Júlia Maria da Silva de Oliveira
- Luan Victor Campos
- Rodrigo Sousa
- Aline Siqueira Menezes
- Amanda Santos Perez
- Poliana Caroline Lopes de Souza

Agradecemos o apoio e a orientação do Projeto de Extensão.
