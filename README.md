# inovamed-
## Descrição
InovaMed é uma plataforma digital inovadora desenvolvida para conectar pacientes a estudos clínicos, com foco na área de pesquisa clínica em saúde. O sistema permite que médicos candidatem seus pacientes a estudos relevantes, garantindo a privacidade e a segurança dos dados. Com uma interface simples e eficiente, InovaMed facilita a comunicação entre médicos, pacientes e representantes de estudos clínicos, promovendo um processo ágil e transparente. O projeto visa acelerar a pesquisa e o desenvolvimento de novos tratamentos, contribuindo para o avanço da medicina.
## Tecnologias utilizadas
- **Java 17**: A linguagem de programação utilizada para desenvolver a lógica de backend da aplicação.
- **Spring Boot 3**: Framework para construção de aplicações Java, utilizado para criar a API RESTful do projeto.
- **PostgreSQL**: Sistema de gerenciamento de banco de dados relacional utilizado para armazenar as informações da aplicação.
- **React**: Biblioteca JavaScript utilizada para construir a interface do usuário (front-end), proporcionando uma experiência interativa e dinâmica.

## como instalar

1. **Clone o repositório**

   Abra seu terminal e execute o seguinte comando para clonar o repositório:

   ```bash
git clone https://github.com/seu-usuario/inovamed.git](https://github.com/andrefilipe1310/inovamed-.git
cd inovamed
 

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
    class ClinicalStudyRepresentative {
        +Long id
        +String name
        +String email
        +String phone
        +String clinicalRole
        +String experiences
        +String password
        +List<Research> researches
        +List<Notification> notifications
        +List<Application> applicationsViewed
    }

    class Research {
        +Long id
        +String title
        +String area
        +int code
        +int numberOfPatients
        +int availableSpots
        +Doctor principalDoctor
        +List<Doctor> otherDoctors
        +List<String> institutions
        +String description
        +Criteria criteria
        +Dates dates
        +List<String> phases
        +int currentPhase
        +String location
        +List<Attachment> attachments
        +List<Application> applications
    }

    class Criteria {
        +String inclusion
        +String exclusion
    }

    class Dates {
        +String start
        +String end
    }

    class Notification {
        +Long id
        +String sender
        +Long senderCode
        +List<Long> recipientsCode
        +String title
        +String message
        +int researchCode
        +List<String> attachments
    }

    class Attachment {
        +Long id
        +String name
        +String link
    }

    class Patient {
        +int id
        +String name
        +String email
        +String gender
        +LocalDate birth
        +String phone
        +String password
        +Doctor doctor
        +boolean digitalSignatureConsent
        +boolean responsibleDoctor
        +String digitalSignature
        +List<String> authorizations
        +List<Research> researches
        +List<Notification> notifications
        +MedicalHistory medicalHistory
    }

    class MedicalHistory {
        +int id
        +String text
    }

    class Application {
        +int id
        +int patientCode
        +int doctorCode
        +String message
        +String type  %% Type: rejection, expulsion, or approval
        +Research research
    }

    class Doctor {
        +String name
        +String email
        +String clinic
        +String contactNumber
        +String specialty
        +String crm
        +Enum experience
        +List<Research> researches
        +List<Application> applicationsSubmitted
        +List<Notification> notifications
    }

    %% Relations
    ClinicalStudyRepresentative --> Research : "manages"
    ClinicalStudyRepresentative --> Notification : "receives updates"
    ClinicalStudyRepresentative --> Application : "views"
    Research --> Criteria : "defines"
    Research --> Dates : "happens in"
    Research --> Doctor : "has many"
    Research --> Attachment : "includes"
    Research --> Application : "receives"
    Doctor --> Application : "submits"
    Doctor --> Research : "participates in"
    Doctor --> Notification : "receives"
    Patient --> Research : "participates in"
    Patient --> Notification : "receives"
    Patient --> MedicalHistory : "has"
    Patient --> Doctor : "assigned to"
    Application --> Patient : "refers to"
    Application --> Research : "relates to"
    Application --> Doctor : "submitted by"

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
