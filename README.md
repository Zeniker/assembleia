# Assembleia

#### Requerimentos

- Sistema Linux (Pode ser windows, mas vai precisar de ajustes)
- Docker 
- Docker-compose

#### Tecnologias utilizadas

- Docker: Utilizado para facilitar o deploy do serviço em qualquer máquina.
- Spring Boot: Utilizado para facilitar a criação da aplicação.
- Spring Web: Utilizado para expor às funções da aplicação através dos controllers.
- DevTools: Utilizado no ambiente de desenvolvimento, facilita o reload da aplicação.
- JUnit 5: Utilizada a versão 5 pois possui funcionalidades que facilitam o entendimento 
e execução dos testes
- Mockito: Utilizado para simular funcionalidades que só podem ser utilizadas no contexto 
do Spring
- JPA: Utilizado para cria a comunicação com o banco de dados, e também pois permite que 
as entidades sejam criadas ao iniciar a aplicação.
- Postgres: Banco de dados open-source, além de possuir uma imagem mais leve no docker.
- H2: Banco em memórias utilizado nos testes da aplicação.
- Spring Boot Maven Plugin: Utilizado para subir a aplicação através do maven.
- Apache Http: Utilizado devido à facilidade de realizar requisições através de sua api.
- Lombok: Utilizado para eliminar a necessidade de criação de getters, setters e logs.
- RabbitMQ: Utilizado para controlar filas e mensageria

### Execução

Para executar a aplicação, basta ir na raiz do projeto e executar o comando abaixo. Ele irá baixar as dependências 
necessárias e iniciar a aplicação na sequência. 

```
docker-compose build
docker-compose up
```

A api ficará disponível na porta 8080, podendo ser acessada pelo link abaixo.

```
http://localhost:8080/
```

### Documentação API

A documentação da api foi disponibilizada através de Swagger. Pode acessa-lá a partir da url abaixo
```
http://localhost:8080/swagger-ui.html
```

### Testes De Performance

Os testes de performance foram realizados em uma máquina com 8GB de RAM, Processador Intel Core I5-7200. 
Foram feitos através do software JMeter. Os resultados se encontram na pasta testePerformance, na raiz do projeto.
Para visualizá-los, basta abrir o arquivo index.html no navegador.

### Versionamento

O versionamento desta API se dará através das releases do Github, onde cada release culminará em uma modificação no link da api. 
Exemplo:
- v1: www.assembleia.com.br/v1/
- v2: www.assembleia.com.br/v2/
- v3: www.assembleia.com.br/v3/