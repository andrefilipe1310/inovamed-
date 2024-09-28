# inovamed-
Plataforma para conectar pacientes, medicos e ensaios clinicos
## Fluxograma
```mermaid
graph TD;
    A[Cadastro de Estudo] -->|salva no banco de dados e notifica médicos| B[Visualização de Estudos Clínicos];
    B -->|recomenda estudo a paciente| C[Recomendações de Estudos];
    C -->|atualiza status no banco de dados| D[Gerenciamento de Pacientes];
    D -->|envia notificações para médicos| E[Notificações para Médicos];
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
```
    PACIENTE ||--o{ CANDIDATURA : faz
    CANDIDATURA }o--|| ESTUDO_CLINICO : se_inscreve
    MEDICO ||--o{ ESTUDO_CLINICO : gerencia
    MEDICO ||--o{ NOTIFICACAO : envia
    PACIENTE ||--o{ NOTIFICACAO : recebe
