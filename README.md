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

2. **Executar o Backend**
- Navegue até a pasta do backend e execute o seguinte comando:
```bash
mvn spring-boot:run
```
### Ambiente de Desenvolvimento (H2 Database)

Para executar o projeto utilizando banco de dados em memória H2:

```properties
spring.profiles.active=dev
```

### Ambiente Local (PostgreSQL)

Para executar com PostgreSQL localmente:

1. Crie um banco de dados chamado `inovamed`
2. Configure o profile:
```properties
spring.profiles.active=test
```

3. Configure as seguintes variáveis de ambiente:
```bash
DB_HOST=seu_host
DB_NAME=inovamed
DB_PORT=5432
DB_USERNAME=postgres
DB_PASSWORD=sua_senha
```

### Docker

O projeto possui um Dockerfile na pasta `/backend` que pode ser utilizado para criar uma imagem Docker:

```bash
# Navegue até a pasta do backend
cd backend

# Construa a imagem
docker build -t inovamed .

# Execute o container
docker run -p 8080:8080 \
  -e DB_HOST=seu_host \
  -e DB_NAME=inovamed \
  -e DB_PORT=5432 \
  -e DB_USERNAME=postgres \
  -e DB_PASSWORD=sua_senha \
  inovamed
```

## Ambiente Online

O backend está disponível online através do seguinte endpoint:

```
https://inovamed-latest.onrender.com/
```

### Notas Importantes

- Certifique-se de ter o PostgreSQL instalado localmente ao usar o profile `test`
- Para desenvolvimento rápido, recomenda-se usar o profile `dev` com H2
- Todas as variáveis de ambiente são obrigatórias ao usar PostgreSQL
- O ambiente online já está configurado e pronto para uso
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
## Documentação Visual do Projeto

### Diagramas

- [![Fluxogramas](https://img.shields.io/badge/Fluxogramas-blue?style=for-the-badge)](https://github.com/andrefilipe1310/inovamed-/tree/main/docs/diagrams/FLOWCHARTS.md)
- [![Diagrama UML](https://img.shields.io/badge/Diagrama%20UML-blue?style=for-the-badge)](https://github.com/andrefilipe1310/inovamed-/blob/main/docs/diagrams/CLASS_DIAGRAM.md)
- [![Diagrama de Deployment](https://img.shields.io/badge/Diagrama%20de%20Deployment-blue?style=for-the-badge)](https://github.com/andrefilipe1310/inovamed-/tree/main/docs/diagrams/DEPLOYMENT_DIAGRAM.md)
- [![Diagrama ER](https://img.shields.io/badge/Diagrama%20ER-blue?style=for-the-badge)](https://github.com/andrefilipe1310/inovamed-/tree/main/docs/diagrams/ER_DIAGRAM.md)

### Protótipo de Alta Fidelidade 

[![Visualizar no Figma](https://img.shields.io/badge/Visualizar%20no%20Figma-303133?style=for-the-badge&logo=figma&logoColor=white)](https://www.figma.com/design/MAYIorwVh0L0GMI3fWkQ8p/inovamed?node-id=0-1&t=oOfMIo0CWC2Q0o69-1)


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

- **Alberth Luiz**
  - **Função:** Desenvolvedor Backend 
  - **Email:** aluizprofi@gmail.com
  - **LinkedIn:** [Alberth Luiz](https://www.linkedin.com/in/alberth-luiz-736527229/)
  - **Github:** [PaidoNunu](https://github.com/PaidoNunu)

