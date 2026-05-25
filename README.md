# 🏢 Sistema RH — Stack Completa

> Sistema fictício de Gestão de Recursos Humanos com backend Spring Boot e frontend Angular.

## 🏗️ Arquitetura

```
SistemaRh/
├── SistemaRh/          → Backend Spring Boot 3.4 (Java 21)
└── rh-frontend/        → Frontend Angular 19
```

---

## ⚙️ Backend — Spring Boot

### Tecnologias
| Dependência | Função |
|---|---|
| Spring Boot 3.4 + Java 21 | Framework base |
| Spring Data JPA + H2 | Persistência em memória |
| Lombok | Elimina boilerplate (getters/setters/construtores) |
| MapStruct | Mapeamento automático entre DTOs e entidades |
| Bean Validation | Validação de campos (CPF, e-mail, telefone) |
| Springdoc OpenAPI | Swagger UI automático |
| Spring DevTools | Reload automático em dev |

### Como rodar
```bash
cd SistemaRh
./mvnw spring-boot:run
```

### URLs
| URL | Descrição |
|---|---|
| `http://localhost:8080/api/funcionarios` | API REST |
| `http://localhost:8080/swagger-ui.html` | Documentação Swagger UI |
| `http://localhost:8080/h2-console` | Console do banco H2 |

### Endpoints da API

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/api/funcionarios?page=0&size=10` | Lista paginada |
| `GET` | `/api/funcionarios/{id}` | Busca por ID |
| `POST` | `/api/funcionarios` | Cadastrar |
| `PUT` | `/api/funcionarios/{id}` | Atualizar |
| `DELETE` | `/api/funcionarios/{id}` | Remover |

### Exemplo de Payload (POST/PUT)
```json
{
  "nomeFuncionario": "João da Silva",
  "cpf": "529.982.247-25",
  "email": "joao@empresa.com",
  "telefonePrimario": "(11) 99999-9999",
  "telefoneSegundario": null,
  "cargo": "Desenvolvedor Backend",
  "nivelHierarquico": "PLENO",
  "statusFuncionario": "ATIVO"
}
```

### Enums disponíveis

**HierarchicalLevel:** `JOVEM_APRENDIZ | ESTAGIARIO | ASSISTENTE | JUNIOR | PLENO | SENIOR | ESPECIALISTA | LIDER_EQUIPE | GERENTE | DIRETOR | CEO`

**StatusFuncionario:** `ATIVO | INATIVO | FERIAS | AFASTADO`

---

## 🅰️ Frontend — Angular

### Tecnologias
| Lib | Função |
|---|---|
| Angular 19 (Standalone) | Framework SPA |
| Angular Material | UI Components (tabela, formulários, dialogs) |
| Reactive Forms | Formulários com validação em tempo real |
| Angular Signals | Estado reativo moderno |
| HttpClient (Fetch) | Consumo da API REST |

### Como rodar
```bash
cd rh-frontend
npm install
npm start
# Acesse: http://localhost:4200
```

### Funcionalidades
- 📊 **Dashboard** com cards: Total, Ativos, Férias, Inativos/Afastados
- 📋 **Tabela paginada** com avatar por iniciais e badges de status coloridos
- ➕ **Modal de cadastro** com validação em tempo real
- ✏️ **Modal de edição** pré-preenchido
- 🗑️ **Exclusão** com confirmação
- 🎨 **Design dark mode premium** com animações suaves

---

## 📁 Estrutura de Pacotes — Backend

```
src/main/java/dev/sistema/SistemaRh/
├── config/
│   ├── CorsConfig.java          → Permite requisições do Angular (porta 4200)
│   └── OpenApiConfig.java       → Configura título/descrição do Swagger
├── controller/
│   ├── FuncionarioController.java
│   └── exception/
│       ├── ResourceExceptionHandler.java
│       ├── ResourceNotFoundException.java
│       ├── StandardError.java
│       ├── ValidationError.java
│       └── FieldMessage.java
├── dto/
│   ├── mapper/
│   │   └── FuncionarioMapper.java   → Interface MapStruct (gerada em compile time)
│   ├── request/
│   │   └── FuncionarioRequest.java
│   └── response/
│       └── FuncionarioResponse.java
├── model/
│   ├── FuncionarioModel.java
│   └── enums/
│       ├── HierarchicalLevel.java
│       └── StatusFuncionario.java
├── repository/
│   └── FuncionarioRepository.java
├── service/
│   └── FuncionarioService.java
└── SistemaRhApplication.java
```

## 📁 Estrutura — Frontend Angular

```
rh-frontend/src/app/
├── core/
│   ├── models/
│   │   └── funcionario.model.ts     → Interfaces e enums TypeScript
│   └── services/
│       └── funcionario.service.ts   → HttpClient → API REST
├── features/
│   └── funcionarios/
│       ├── lista/
│       │   └── funcionarios-lista.component.ts  → Tabela + Dashboard
│       └── form/
│           └── funcionario-form.component.ts    → Modal de cadastro/edição
├── app.component.ts    → Navbar + layout shell
├── app.config.ts       → Providers (router, http, animations)
└── app.routes.ts       → Roteamento com lazy loading
```
