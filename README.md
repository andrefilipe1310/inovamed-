# inovamed-
Plataforma para conectar pacientes, medicos e ensaios clinicos
## Fluxograma

### Fluxo 1
```mermaid
graph TD;
    A[Cadastro de Estudo] -->|salva no banco de dados e notifica médicos| B[Visualização de Estudos Clínicos];
    B -->|recomenda estudo a paciente| C[Recomendações de Estudos];
    C -->|atualiza status no banco de dados| D[Gerenciamento de Pacientes];
    D -->|envia notificações para médicos| E[Notificações para Médicos];
```
### Fluxo 2
```mermaid
flowchart TD
    A[Paciente deseja participar de um estudo] --> B[Paciente acessa a plataforma via médico]
    B --> C[Paciente fornece dados com consentimento]
    C --> D[Médico cadastra os dados na plataforma]
    D --> E[Médico busca estudos disponíveis]
    E --> F[Estudos filtrados com base nos dados do paciente]
    F --> G[Médico avalia critérios de inclusão/exclusão]
    G --> H[Médico seleciona o estudo adequado]
    H --> I[Candidatura ao estudo é feita]
    I --> J[Estudo clínico recebe candidatura]

    J --> K{Estudo aceita ou rejeita candidatura}
    K --> L[Notificação de aprovação/rejeição enviada ao médico]
    L --> M[Notificação ao médico sobre status da candidatura]

    M --> N[Médico informa paciente sobre a aceitação ou rejeição]
    N --> O{Paciente aceita ou recusa participação no estudo}
    O --> |Aceita| P[Participação confirmada]
    O --> |Recusa| Q[Processo encerrado]

    %% Feedback loop quando um estudo é aprovado
    P --> R[Estudo começa a ser conduzido]
    R --> S[Médico monitora progresso do paciente]
    S --> T[Estudo clínico fornece atualizações ao médico]
    T --> U[Notificações enviadas ao médico]

    %% Caso o estudo seja rejeitado
    K --> |Rejeita| V[Notificação de rejeição ao médico]
    V --> W[Médico informa o paciente sobre a rejeição]

    %% Alternativa se o paciente quiser se candidatar a outro estudo
    W --> F[Paciente pode ser redirecionado para outro estudo disponível]
```
## Diagrama de implantação 
```mermaid
graph TD
  subgraph "Usuário Final"
    paciente[Navegador Paciente]
    medico[Navegador Médico]
    representante[Navegador Representante Estudo]
  end

  subgraph "Sistema InovaMed"
    frontend[Frontend Web]
    backend[API REST]
    db[(Banco de Dados)]
  end

  paciente --> frontend
  medico --> frontend
  representante --> frontend
  
  frontend --> backend
  backend --> db


```
## Diagrama ER
```mermaid
erDiagram
    PACIENTE {
        string id
        string nome
        string email
        string telefone
        string senha
    }

    MEDICO {
        string id
        string nome
        string crm
        string especialidade
        string senha
    }

    ESTUDO_CLINICO {
        string id
        string titulo
        string descricao
        string criterios_inclusao
        string criterios_exclusao
        string contato
        string status
    }

    CANDIDATURA {
        string id
        string paciente_id
        string estudo_id
        string status
        string data_candidatura
    }

    NOTIFICACAO {
        string id
        string usuario_id
        string mensagem
        string data_envio
    }

PACIENTE ||--o{ CANDIDATURA : faz
    CANDIDATURA }o--|| ESTUDO_CLINICO : se_inscreve
    MEDICO ||--o{ ESTUDO_CLINICO : gerencia
    MEDICO ||--o{ NOTIFICACAO : envia
    PACIENTE ||--o{ NOTIFICACAO : recebe
```
## Diagrama UML
```mermaid
classDiagram
  class Paciente {
    +String nome
    +String email
    +consentirUsoDados()
  }
  
  class Medico {
    +String nome
    +String especialidade
    +cadastrarPaciente()
    +aprovarPaciente()
  }

  class EstudoClinico {
    +String titulo
    +String descricao
    +boolean aprovado
    +gerenciarEstudo()
  }
  
  Paciente --> Medico : é cadastrado por
  Medico --> EstudoClinico : candidata paciente a
  EstudoClinico --> Paciente : envia resultados para
```
## Fluxo de telas
```mermaid
graph TD
    A[Login] --> B[Email]
    A --> C[Senha]
    A --> D[Entrar]
    A --> E[Esqueceu a senha?]
```
```mermaid
graph TD
    A[Cadastrar Paciente] --> B[Nome]
    A --> C[Email]
    A --> D[Data de Nascimento]
    A --> E[Consentir uso dos dados]
    A --> F[Cadastrar]
```
```mermaid
graph TD
    A[Painel do Médico] --> B[Buscar Estudos]
    A --> C[Meus Pacientes]
    A --> D[Notificações]
    A --> E[Listagem de Pacientes]
```
```mermaid
    graph TD
    A[Buscar Estudos Clínicos] --> B[Buscar por nome do estudo]
    A --> C[Filtros]
    C --> D[Área de pesquisa]
    C --> E[Status do estudo]
    A --> F[Listagem de Estudos]
    A --> G[Candidatar Paciente]
```
```mermaid
graph TD
    A[Detalhes do Estudo] --> B[Título do Estudo]
    A --> C[Descrição]
    A --> D[Critérios de Inclusão/Exclusão]
    A --> E[Listagem de Pacientes]
    A --> F[Candidatar Paciente]

```
```mermaid
graph TD
    A[Notificações] --> B[Listagem de Notificações]
    A --> C[Marcar como lida]

```
```mermaid
graph TD
    A[Gerenciamento de Estudos] --> B[Listagem de Estudos]
    A --> C[Adicionar Novo Estudo]
    A --> D[Editar Estudo]
    A --> E[Excluir Estudo]
```
```mermaid
graph TD
    A[Resultados de Estudos] --> B[Listagem de Resultados]
    A --> C[Gráficos/Estatísticas]
```
```mermaid
graph TD
    A[Configurações de Conta] --> B[Nome]
    A --> C[Email]
    A --> D[Senha]
    A --> E[Salvar Alterações]
```
