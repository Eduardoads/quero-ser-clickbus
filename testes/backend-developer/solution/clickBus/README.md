# ClickBus Avaliação

API usada como avaliação para vaga na ClickBus.

## Uso
A API pode ser utilizada acessando os enpoints /places (para recuperação de todos os lugares já salvos), /plases/searchId/{id} (para recuperação o lugares pelo seu ID) e /plases/searchName/{name} (para recuperação o lugares pelo seu ID).

### Execução
Essa é a uma aplicação Maven com o plugin do spring-boot então, para executar a aplicação basta executar o comando maven **spring-boot:run**. 
A aplicação também provê o maven wrapper, desta forma não é necessario instalar o maven para utiliza-lo, para executar os comandos maven com o maven wrapper basta ir à raiz do projeto e executar ./mvnw (linux) ou mvnw.cmd(windows) e o comando especifico que queira executar.

Ex: ./mvnw spring-boot:run

### Empacotar
Caso o objetivo seja enpacota-lo para uma execução posterior, pode ser feito utilizando o comando **clean package**, assim será criado uma pasta target que conterá um jar chamado **clickbus-0.0.1-SNAPSHOT.jar**

Ex: ./mvnw clean package

## Execução do jar
O jar criado com o comando maven pode ser executado com o comando padrão **java -jar clickbus-0.0.1-SNAPSHOT.jar**

## Produção
Para o ambiente de produção uso o banco de dados PostgreSql, e para utiliza a aplicação pode acessar o link.

**https://clickbus-place-aeon.herokuapp.com/places**

## Postman

1. Criar ou Atualizar
* POST: https://clickbus-place-aeon.herokuapp.com/places

EX: Body
{
    "id": 1,
    "name": "My House",
    "slug": "teste",
    "city": "Salvador",
    "state": "BA",
    "countries": "Brasil",
    "createdAt": "2019-11-11",
    "upadateAt": "2019-11-15"
}

**obs: na criação de um lugar novo não é necessário informar o campo "updatedAt", apenas quando quiser atualizar um lugar já criado.**

2. Obter todos
* GET: https://clickbus-place-aeon.herokuapp.com/places

3. Obter por ID
* GET: https://clickbus-place-aeon.herokuapp.com/places/searchId/1

4. Obter por NOME
* GET: https://clickbus-place-aeon.herokuapp.com/places/searchName/My

5. Deletar por ID
* POST: https://clickbus-place-aeon.herokuapp.com/places/delete/1

