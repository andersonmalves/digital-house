# Sprint Nº 1 - Spring

## Objetivo

A API abaixo consiste em 2 partes:

A. Desenvolver uma API para um determinado cenário. No ponto A da seção
seguinte há uma descrição detalhada do cenário e de cada um dos requisitos
implementados.
B. Bônus. Caso todos os requisitos estabelecidos no ponto A sejam cumpridos e ainda
haja tempo, esta atividade pode ser realizada, que apresenta um nível de
complexidade maior.

### A 1. Cenário
Uma plataforma de vendas de produtos online deseja melhorar as opções de pesquisa e
filtragem de seus produtos; Para isso, decidiu implementar um motor de busca que, a
partir das opções que o utilizador determina, devolve o(s) produto(s) que lhes
corresponde. Obs: Os produtos devem ser cadastrados a partir de um payload e
armazenados em um arquivo Json. Para isto, foi utilizado o ObjectMapper.

Para realizarmos isso, foi necessário desenvolver uma API que possibilite:
1. Cadastrar uma lista de produtos.
2. Retornar uma lista de todos os produtos disponíveis.
3. Retornar uma lista de produtos filtrados por categoria.
4. Retornar uma lista que permite as seguintes combinações de filtros:
a. categoria + frete grátis.
b. frete grátis + avaliação (*)
5. Alfabético (crescente e decrescente)
6. Preço mais alto
7. Menor preço
8. Possibilidade de envio de pedido de compra. A partir disso, o preço total da
requisição feita pode ser recebido como resposta.

```
● Leve em consideração, para cada uma dessas solicitações, os possíveis
"status code" que podem ser retornados.
```
**Por exemplo:**
○ Se um produto que não existe for solicitado, retorne o código de
status correspondente.
○ Se houver um problema com o servidor e a conexão não puder ser
feita, o código de status correspondente deve ser retornado.



### B. Extra Bonus

A plataforma afirmou que no futuro gostaria de ser capaz de realizar o desenvolvimento
dos seguintes requisitos como uma melhoria:

9. Para cada solicitação de compra é necessário realizar o controle de estoque
disponível. Por exemplo: Se forem solicitadas 4 unidades de um produto e houver
apenas duas, coloque as restrições e avisos correspondentes.
10. Permite a utilização de um "carrinho de compras" onde para cada pedido de
compra existe um valor total acumulado e devolvido ao utilizador. Por exemplo: Se
um produto de R$ 900 foi enviado em um pedido de compra e outro produto de
R$ 300 foi enviado em outro, devo receber a soma dos dois (R$ 1200) como
resposta.
Ao mesmo tempo, sugere o desenvolvimento de uma nova API que permita o
seguinte:
11. Cadastre novos clientes. Para isso, devem ser realizados os controles necessários,
por exemplo: cliente existente, cliente com dados incompletos, etc.
Obs: Defina com sua equipe os atributos de Cliente.
12. Obter uma lista completa de todos os clientes.
13. Obter uma lista de todos os clientes filtrados por Estado.
Além disso, a plataforma afirmou que concorda em receber sugestões de melhorias dos
desenvolvedores da solução, portanto os convidamos a incluir todas as melhorias que
considerem que possam impulsionar o projeto desenvolvido.
