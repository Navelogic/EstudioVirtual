# Contribuindo com o Estúdio Virtual
Obrigado por se interessar em contribuir para o Estúdio Virtual! Este projeto é um simulador de gestão de produtora audiovisual, e qualquer ajuda — seja com código, documentação, ou sugestões — é muito bem-vinda.

## Como Configurar o Projeto Localmente
Antes de começar a contribuir, siga os passos abaixo para rodar o projeto em seu ambiente local.

### Requisitos
- Java 21
- Maven 4.0.0
- MySQL 8.0
- Git
- Editor ou IDE, como IntelliJ ou VS Code
- Criar um arquivo .env na raiz do projeto (veja abaixo)

#### Passos para Rodar Localmente
1. Clone o repositório
``` bash
git clone https://github.com/seu-usuario/estudiovirtual.git
cd estudiovirtual
```
2. Configure o arquivo <code>.env</code>

Crie um arquivo <code>.env</code> na raiz do projeto com o seguinte conteúdo:
``` dotenv
PROVIDER_KEY=my_provider_key
USER_ISSUER=estudiovirtual_user

MYSQL_DATABASE_DRIVER_CLASS=com.mysql.cj.jdbc.Driver
MYSQL_DATASOURCE_URL=jdbc:mysql://127.0.0.1:3306/estudiovirtual_db?createDatabaseIfNotExist=true
MYSQL_DATASOURCE_USERNAME=root
MYSQL_DATASOURCE_PASSWORD=password
```
>Substitua os valores conforme seu ambiente local (ex: usuário e senha do MySQL).

3. Configure o banco de dados

Crie um banco chamado <code>estudiovirtual_db</code> no MySQL, ou certifique-se de que ele será criado automaticamente com a opção <code>createDatabaseIfNotExist=true</code>.

4. Rode o projeto

Com o <code>.env</code> configurado, rode o projeto com o Maven:
``` bash
./mvnw spring-boot:run
```
>Alternativamente, você pode compilar com <code>./mvnw clean install</code> e depois rodar a JAR gerada.

5. Acesse a aplicação

- A API estará disponível em: http://localhost:8080
- Console H2 (se necessário para debug): http://localhost:8080/h2-console
- Swagger Docs: http://localhost:8080/swagger-ui/index.html

## Contribuindo com Código
1. Faça um Fork
Crie uma cópia do repositório no seu GitHub.

2. Clone o Repositório
``` bash
git clone https://github.com/seu-usuario/estudiovirtual.git
cd estudiovirtual
```
3. Crie uma nova Branch
``` bash
git checkout -b feature/nome-da-sua-feature
```
4. Faça as alterações necessárias
Implemente sua feature ou correção.
5. Commit
```bash
git add .
git commit -m "Descrição clara das mudanças feitas"
```
6. Mantenha seu fork atualizado
``` bash
git fetch upstream
git merge upstream/main
```
7. Envie para seu fork
``` bash
git push origin feature/nome-da-sua-feature
```
8. Crie um Pull Request
No GitHub, abra um PR descrevendo detalhadamente sua contribuição.

## Diretrizes de Contribuição
- Siga o Código de Conduta ao interagir com outros membros.
- Documente novas funcionalidades ou alterações relevantes.
- Reporte bugs com clareza, incluindo passos para reprodução e logs.
- Sugira melhorias explicando como elas beneficiam o projeto.

## Reportando Bugs
### Antes de criar um novo issue:
- Verifique se já existe uma issue semelhante.
- Informe o ambiente, passos para reprodução e mensagens de erro.

## Sugestões de Funcionalidade
- Explique o propósito da funcionalidade.
- Discuta antes de implementar via uma issue.

# Agradecimentos
Agradecemos a todos os contribuidores por ajudarem a tornar o Estúdio Virtual ainda melhor!
