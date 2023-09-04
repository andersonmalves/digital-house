![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

# Sprint Nº 1 - Spring

## Objetivo

A API abaixo consiste em 2 partes:

A. Desenvolver uma API para um determinado cenário. No ponto A da seção
seguinte há uma descrição detalhada do cenário e de cada um dos requisitos
implementados.

B. Bônus. Caso todos os requisitos estabelecidos no ponto A sejam cumpridos e ainda
haja tempo, esta atividade pode ser realizada, que apresenta um nível de
complexidade maior.

### A. Cenário
Uma plataforma de vendas de produtos online deseja melhorar as opções de pesquisa e
filtragem de seus produtos; Para isso, decidiu implementar um motor de busca que, a
partir das opções que o utilizador determina, devolve o(s) produto(s) que lhes
corresponde. Obs: Os produtos devem ser cadastrados a partir de um payload e
armazenados em um arquivo Json. Para isto, foi utilizado o ObjectMapper.


#### Requisitos Obrigatórios 

Para realizarmos isso, foi necessário desenvolver uma API que possibilite:
- [x] ~1. Cadastrar uma lista de produtos.~ 
- [x] ~2. Retornar uma lista de todos os produtos disponíveis.~
- [x] ~3. Retornar uma lista de produtos filtrados por categoria.~
- [x] ~4. Retornar uma lista que permite as seguintes combinações de filtros:~
  - [x] ~a. categoria + frete grátis.~
  - [x] ~b. frete grátis + avaliação (*)~
- [x] ~5. Alfabético (crescente e decrescente)~
- [x] ~6. Preço mais alto~
- [x] ~7. Menor preço~
- [x] ~8. Possibilidade de envio de pedido de compra. A partir disso, o preço total da
requisição feita pode ser recebido como resposta.~

### B. Requisitos bônus

A plataforma afirmou que no futuro gostaria de ser capaz de realizar o desenvolvimento
dos seguintes requisitos como uma melhoria:

- [x] ~9. Para cada solicitação de compra é necessário realizar o controle de estoque
disponível. Por exemplo: Se forem solicitadas 4 unidades de um produto e houver
apenas duas, coloque as restrições e avisos correspondentes.~
- [x] ~10. Permite a utilização de um "carrinho de compras" onde para cada pedido de
compra existe um valor total acumulado e devolvido ao utilizador. Por exemplo: Se
um produto de R$ 900 foi enviado em um pedido de compra e outro produto de
R$ 300 foi enviado em outro, devo receber a soma dos dois (R$ 1200) como
resposta.~
Ao mesmo tempo, sugere o desenvolvimento de uma nova API que permita o
seguinte:
- [x] ~11. Cadastre novos clientes~
- [x] ~12. Obter uma lista completa de todos os clientes~
- [x] ~13. Obter uma lista de todos os clientes filtrados por Estado.~


Além disso, a plataforma afirmou que concorda em receber sugestões de melhorias dos
desenvolvedores da solução, portanto os convidamos a incluir todas as melhorias que
considerem que possam impulsionar o projeto desenvolvido.

## Tabela de endpoints

| Endpoint                                                             | Verbo |                     Função                      |
|:---------------------------------------------------------------------|:-----:|:-----------------------------------------------:|
| /api/v1/insert-articles-request                                      | POST  |           Cadastrar lista de produtos           |
| /api/v1/articles                                                     |  GET  |            Listar todos os produtos             |
| /api/v1/articles?category={categoria}                                |  GET  |          Listar produtos por categoria          |
| /api/v1/articles?category={categoria}&freeShipping={boolean}         |  GET  |  Listar produtos por categoria + frete grátis   |
| /api/v1/articles?freeShipping={boolean}&prestige={****}              |  GET  |  Listar produtos por frete grátis + avaliação   |
| /api/v1/articles?category={categoria}&freeShipping={boolean}&order=0 |  GET  | Listar produtos em ordem alafabética ascendente |
| /api/v1/articles?category={categoria}&freeShipping={boolean}&order=1      |  GET  | Listar produtos em ordem alfabética descendente |
| /api/v1/articles?category={categoria}&freeShipping={boolean}&order=2      |  GET  |    Listar produtos ordenando por menor preço    |
| /api/v1/articles?category={categoria}&freeShipping={boolean}&order=3      |  GET  |    Listar produtos ordenando por maior preço    |
| /api/v1/purchase-request                                             | POST  |              Solicitação de compra              |

## Equipe 7

- [Anderson Alves](https://github.com/andmalves)
- [Felipe Shinkae](https://github.com/fyshinkae)
- [Gabriel Viana](https://github.com/gabvteixeira)
- [Giovanna Eliz](https://github.com/giovannaelizs)
- [Matheus Ferreira (Theus)](https://github.com/matheusFerreira-meli)
- [Matheus Alves (Ma)](https://github.com/matheusaralves)

---
Feito com 💛 
