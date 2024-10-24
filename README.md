# Inovamed
## Descrição
InovaMed é uma plataforma digital inovadora desenvolvida para conectar pacientes a estudos clínicos, com foco na área de pesquisa clínica em saúde. O sistema permite que médicos candidatem seus pacientes a estudos relevantes, garantindo a privacidade e a segurança dos dados. Com uma interface simples e eficiente, InovaMed facilita a comunicação entre médicos, pacientes e representantes de estudos clínicos, promovendo um processo ágil e transparente. O projeto visa acelerar a pesquisa e o desenvolvimento de novos tratamentos, contribuindo para o avanço da medicina.
## Tecnologias utilizadas
- **Java 17**: A linguagem de programação utilizada para desenvolver a lógica de backend da aplicação.
- **Spring Boot 3**: Framework para construção de aplicações Java, utilizado para criar a API RESTful do projeto.
- **PostgreSQL**: Sistema de gerenciamento de banco de dados relacional utilizado para armazenar as informações da aplicação.
- **React**: Biblioteca JavaScript utilizada para construir a interface do usuário (front-end), proporcionando uma experiência interativa e dinâmica.

## Como Instalar
### Pré-requisitos
- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [PostgreSQL](https://www.postgresql.org/download/)
- [Node.js](https://nodejs.org/) 
### Passo a Passo
1. **Clone o repositório**

Abra seu terminal e execute o seguinte comando para clonar o repositório:

 ```bash
git clone https://github.com/seu-usuario/inovamed.git
```
 ```bash
cd inovamed
```
2. **Configure o banco de dados**
- Crie um banco de dados no PostgreSQL chamado inovamed.
- Atualize as credenciais do banco de dados no arquivo application.properties localizado em src/main/resources:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/inovamed
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```
3. **Executar o Backend**
- Navegue até a pasta do backend e execute o seguinte comando:
```bash
mvn spring-boot:run
```
4. **Executar o Frontend**
- Navegue até a pasta do front-end e execute os seguintes comandos:
```bash
cd frontend
cd vite
npm install
npm run dev
```
5. **Acessar a Aplicação**
- Abra seu navegador e acesse http://localhost:5173 para ver a aplicação em funcionamento.

6. **Acessar as rotas da aplicação (swagger)**
- Abra seu navegador e acesse http://localhost:8080/swagger-ui/index.html
### Observações
- Certifique-se de que o servidor do PostgreSQL esteja em execução antes de iniciar a aplicação.
- Verifique as dependências e versões para evitar conflitos.
## Diagrama UML, Diagramas de Caso de Uso, Fluxogramas e Diagrama ER

### Fluxograma (cadastro paciente)
```mermaid
flowchart TD
    A[Início do Cadastro do Paciente] --> B{Médico fornece a chave do paciente}
    B -- Sim --> C[Preencher informações do paciente]
    B -- Não --> D[Erro: Chave do médico é necessária]
    D --> E[Fim do Cadastro]
    C --> F[Validar informações]
    F --> |Válidas| G[Cadastrar paciente]
    F --> |Inválidas| H[Erro: Informações inválidas]
    H --> E
    G --> I[Paciente cadastrado com sucesso]
    I --> E[Fim do Cadastro]
```
### Fluxograma (cadidatar paciente)
```mermaid
flowchart TD
    A[Início do Processo de Candidatura] --> B[Médico seleciona paciente para candidatura]
    B --> C[Verificar se o paciente é elegível]
    C --> |Sim| D[Médico envia candidatura]
    D --> E[Notificar o representante do estudo]
    E --> F[Representante do estudo recebe candidatura]
    F --> G[Representante avalia candidatura]
    G --> |Aprovar| H[Notificar médico e paciente sobre aprovação]
    G --> |Rejeitar| I[Notificar médico e paciente sobre rejeição]
    C --> |Não| J[Notificar médico: Paciente não é elegível]
    J --> K[Fim do Processo]
    H --> K
    I --> K
```
### Fluxograma (Concordar com os termos de uso e assinar digitalmente)
```mermaid
flowchart TD
    A[Usuário acessa a plataforma InovaMed] --> B[Usuário seleciona a opção de assinatura digital]
    B --> C[Usuário visualiza o documento a ser assinado]
    C --> D[Usuário clica em Assinar Documento]
    D --> E[Sistema solicita autenticação forte ex: senha + código OTP]
    E --> F{Usuário autentica com sucesso?}
    F -->|Sim| G[Sistema carrega o certificado digital do usuário]
    F -->|Não| H[Usuário é notificado sobre falha na autenticação]
    H --> E
    G --> I[Usuário revisa informações da assinatura]
    I --> J[Usuário confirma a assinatura]
    J --> K[Sistema gera a assinatura digital no documento]
    K --> L[Assinatura digital é armazenada no sistema]
    L --> M[Usuário recebe confirmação da assinatura realizada]
    M --> N[Usuário pode visualizar ou baixar o documento assinado]
```
### Diagrama UML 

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
        +String archive
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
        +DigitalSignature digitalSignature
        +List<String> authorizations
        +List<Research> researches
        +List<Notification> notifications
        +MedicalHistory medicalHistory
    }
    class DigitalSignature {
        +int id
        +String documentName
        +String documentName
        +byte[] documentContent
        +byte[] signature
        +LocalDateTime timestamp
        +LocalDateTime validFrom
        +LocalDateTime validUntil
        +List<Consent> consents
        +User user;
        +boolean isActive;
    }

    class Consent {
        +int id
        +ConsentType consentType
        +LocalDateTime validFrom
        +LocalDateTime validUntil
        +boolean isActive
        +List<DigitalSignature> digitalSignature
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
    Patient --> DigitalSignature : "has"
    Patient --> Doctor : "assigned to"
    DigitalSignature --> Consent : "has many"
    Application --> Patient : "refers to"
    Application --> Research : "relates to"
    Application --> Doctor : "submitted by"

```




### Diagrama de implantação 
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
### Diagrama ER
```mermaid
erDiagram
    CLINICAL_STUDY_REPRESENTATIVE {
        string id PK
        string nome
        string email
        string telefone
        string papel_clinico
        string experiencias
        string senha
    }

    RESEARCH {
        string id PK
        string titulo
        string area
        int codigo
        int numero_de_pacientes
        int vagas_disponiveis
        string localizacao
        string descricao
        string fases
        int fase_atual
    }

    CRITERIA {
        string id PK
        string inclusao
        string exclusao
    }

    DATES {
        string id PK
        string inicio
        string fim
    }

    NOTIFICACAO {
        string id PK
        string remetente
        string codigo_remetente
        string mensagem
        string titulo
        string codigo_pesquisa
        string anexos
    }

    ANEXO {
        string id PK
        string nome
        string link
    }

    PACIENTE {
        string id PK
        string nome
        string email
        string genero
        string telefone
        string senha
        boolean consentimento_assinatura_digital
        string assinatura_digital
        string autorizacoes
    }

    HISTORICO_MEDICO {
        string id PK
        string texto
    }

    CANDIDATURA {
        string id PK
        string codigo_paciente FK
        string codigo_medico FK
        string mensagem
        string status
        string pesquisa FK
    }

    MEDICO {
        string id PK
        string nome
        string email
        string clinica
        string numero_de_contato
        string especialidade
        string crm
        string experiencia
    }

    PACIENTE ||--o{ CANDIDATURA : faz
    PACIENTE ||--|| HISTORICO_MEDICO : tem
    CANDIDATURA }o--|| RESEARCH : se_inscreve
    MEDICO ||--o{ RESEARCH : gerencia
    MEDICO ||--o{ NOTIFICACAO : envia
    PACIENTE ||--o{ NOTIFICACAO : recebe
    RESEARCH ||--o{ CRITERIA : define
    RESEARCH ||--o{ DATES : acontece_em
    RESEARCH ||--o{ ANEXO : inclui
```

## Prototipo de alta fidelidade (figma)
- [link](https://www.figma.com/design/MAYIorwVh0L0GMI3fWkQ8p/inovamed?node-id=0-1&t=oOfMIo0CWC2Q0o69-1)

## Responsáveis e Contato

### Equipe do Projeto

- **André Filipe**
  - **Função:** Desenvolvedor Backend e Co-Gestor do projeto
  - **Email:** andrefilipef1310@gmail.com
  - **LinkedIn:** [André Filipe](https://linkedin.com/in/andre-filipe-/)
  - **Github:** [andrefilipef1310](https://github.com/andrefilipe1310/)

- **Ariano Souza**
  - **Função:** Desenvolvedor Front-end e Co-Gestor do projeto
  - **Email:** arianosouzapro@gmail.com
  - **LinkedIn:** [Ariano Souza](https://www.linkedin.com/in/ariano-souza-14777926b)
  - **Github:** [ArianoSouza](https://github.com/ArianoSouza)


 - **Ayrton Fernandes**
   - **Função:** Desenvolvedor Backend e Co-Gestor do projeto
   - **Email:** ayrtonleonardo14@gmail.com
   - **LinkedIn:** [Ayrton Leonardo](https://www.linkedin.com/in/ayrton-leonardo-956a4026b/)
   - **Github:** [AyrtonF](https://github.com/AyrtonF)
  

 - **Amanda Lima**
   - **Função:** Desenvolvedora Front-end 
   - **Email:** amandakaawanny@gmail.com
   - **LinkedIn:** [Amanda Kaawanny](https://linkedin.com/in/amanda-lima-5bb61a1b0/)
   - **Github:** [amandaklima](github.com/amandaklima)

 - **Bruno Klisman**
   - **Função:** Desenvolvedor Front-end 
   - **Email:** brunoserafim.dev@gmail.com
   - **LinkedIn:** [Bruno KLisman](https://www.linkedin.com/in/bruno-klisman-30aa14267/)
   - **Github:** [Bruno-Klisman](https://github.com/Bruno-Klisman)
     
- **Estephani Germana**
   - **Função:** Desenvolvedor Backend e QA
   - **Email:** estephani.germana@gmail.com
   - **LinkedIn:** [Estephani Germana](https://www.linkedin.com/in/estephanigermana/)
   - **Github:** [estephgermana](https://github.com/estephgermana)
