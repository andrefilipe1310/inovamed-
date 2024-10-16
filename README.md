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
classDiagram
    class clinicalStudyRepresentative {
        Long id
        String name
        String email
        String phone
        String clinicalRole
        String experiences
        String password
        List<Research> researchFeatures
        List<Notification> notifications
        List<Candidate> candidates
        List<Research> researches
    }

    class Research {
        Long id
        String title
        String area
        int code
        int numberOfPatients
        int availableSpots
        List<String> responsibleDoctors
        List<String> institution
        String description
        Criteria criteria
        Dates dates
        List<String> phases
        int currentPhase
        String location
        List<String> attachments
    }

    class Criteria {
        String inclusion
        String exclusion
    }

    class Dates {
        String start
        String end
    }

    class Notification {
        Long id
        String title
        String description
        List<Long> recipientEntities
        int researchCode
        List<String> attachments
        int senderCode
    }

    class Candidate {
        int code
        int age
        String gender
        String history
        String status
        Application application
    }

    class Application {
        int patientCode
        int doctorCode
        String message
    }

    %% Relations
    clinicalStudyRepresentative --> Research : "has many"
    clinicalStudyRepresentative --> Notification : "has many"
    clinicalStudyRepresentative --> Candidate : "has many"
    Research --> Criteria
    Research --> Dates
    Candidate --> Application

```
```mermaid
classDiagram
    class Doctor {
        +String name
        +String email
        +String clinic
        +String contactNumber
        +String specialty
        +String crm
        +String experience
    }

    class Patient {
        +int id
        +String name
        +boolean digitalSignatureConsent
        +boolean responsibleDoctor
        +List~String~ authorizations
        +List~ResearchFeature~ researches
        +List~Notification~ notifications
    }

    class PatientFeature {
        +int id
        +int code
        +String name
        +int age
    }

    class ResearchFeature {
        +int id
        +String title
        +String description
        +int numberOfPatients
        +String inclusionCriteria
        +String exclusionCriteria
        +Date startDate
        +Date endDate
        +String location
        +String officialDocument
    }

    class Document {
        +String name
        +String link
    }

    class Notification {
        +String title
        +String sender
        +String message
        +String link
        +String attachment
    }

    Doctor "1" --> "*" ResearchFeature : receives
    Doctor "1" --> "*" PatientFeature : registers for
    ResearchFeature "1" --> "*" PatientFeature : includes
    ResearchFeature "1" --> "*" Document : includes
    Doctor "1" --> "*" Notification : receives
    Patient "1" --> "*" Notification : receives
    Patient "1" --> "*" PatientFeature : has



```



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
        string cidade
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
    +Long id
    +String nome
    +String email
    +String cidade
    +consentirUsoDados()
    +@Masked
  }
  
  class Medico {
    +Long id
    +String nome
    +String crm
    +String especialidade
    +cadastrarPaciente()
    +aprovarPaciente()
  }

  class EstudoClinico {
    +Long id
    +String titulo
    +String descricao
    +String criterios_inclusao
    +String criterios_exclusao
    +boolean aprovado
    +gerenciarEstudo()
  }

  class Candidatura {
    +Long id
    +Long paciente_id
    +Long estudo_id
    +String status
    +String data_candidatura
  }

  class Notificacao {
    +Long id
    +Long usuario_id
    +String mensagem
    +String data_envio
  }

  class DataMasker {
    +maskData(Object obj)
  }

  Paciente --> Medico : é cadastrado por
  Medico --> EstudoClinico : candidata paciente a
  EstudoClinico --> Paciente : envia resultados para
  DataMasker --> Paciente : aplica máscara aos dados

```
## Fluxo de telas
### Tela login
```mermaid
graph TD
    A[Login] --> B[Email]
    A --> C[Senha]
    A --> D[Entrar]
    A --> E[Esqueceu a senha?]
```
### Tela de Cadastro de Paciente
```mermaid
graph TD
    A[Cadastrar Paciente] --> B[Nome]
    A --> C[Email]
    A --> D[Data de Nascimento]
    A --> E[Consentir uso dos dados]
    A --> F[Cadastrar]
```
### Painel do Médico
```mermaid
graph TD
    A[Painel do Médico] --> B[Buscar Estudos]
    A --> C[Meus Pacientes]
    A --> D[Notificações]
    A --> E[Listagem de Pacientes]
```
### Tela de Busca de Estudos Clínicos
```mermaid
    graph TD
    A[Buscar Estudos Clínicos] --> B[Buscar por nome do estudo]
    A --> C[Filtros]
    C --> D[Área de pesquisa]
    C --> E[Status do estudo]
    A --> F[Listagem de Estudos]
    A --> G[Candidatar Paciente]
```
### Tela de Detalhes do Estudo
```mermaid
graph TD
    A[Detalhes do Estudo] --> B[Título do Estudo]
    A --> C[Descrição]
    A --> D[Critérios de Inclusão/Exclusão]
    A --> E[Listagem de Pacientes]
    A --> F[Candidatar Paciente]

```
### Tela de Notificações
```mermaid
graph TD
    A[Notificações] --> B[Listagem de Notificações]
    A --> C[Marcar como lida]

```
### Tela de Gerenciamento de Estudos (para Representante de Estudos)
```mermaid
graph TD
    A[Gerenciamento de Estudos] --> B[Listagem de Estudos]
    A --> C[Adicionar Novo Estudo]
    A --> D[Editar Estudo]
    A --> E[Excluir Estudo]
```
### Tela de Configurações de Conta
```mermaid
graph TD
    A[Configurações de Conta] --> B[Nome]
    A --> C[Email]
    A --> D[Senha]
    A --> E[Salvar Alterações]
```
```mermaid
graph TD
    A[Configurações de Conta] --> B[Nome]
    A --> C[Email]
    A --> D[Senha]
    A --> E[Salvar Alterações]
```
