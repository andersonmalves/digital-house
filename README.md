# BootCamp Backend Java - Desafio Testing
<p>
    <img alt="GitHub top language" src="https://img.shields.io/github/languages/top/matheusFerreira-meli/testing-desafio-I">
    <img alt="Repository size" src="https://img.shields.io/github/repo-size/matheusFerreira-meli/testing-desafio-I">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/matheusFerreira-meli/testing-desafio-I">
</p>


### Objetivo
O objetivo deste desafio é aplicar os conteúdos dados até o momento durante o
Programa de aceleração MeLi (Git, Java e Spring), com ênfase principal nas validações e
tipos de teste que podem ser usados a partir de uma proposta, uma especificação de
requisitos e documentação anexa.

---

### Cenário

A empresa **"Seu Imóvel"** precisa ser capaz de calcular a metragem e o custo dos
diferentes imóveis que possui em sua carteira de clientes.

Para isso, solicita de cada **Imóvel**:

- _um nome do imóvel_,
- _um bairro_,
- _e a lista de cômodos._

Um **Bairro** deve ter:

- _nome do bairro_,
- _valor do metro quadrado no bairro_

Cada **cômodo** tem:

- _um nome,_
- _uma largura e_
- _um comprimento._

**Para isso, é necessário a criação de uma API Rest que permita:**

- [x] ~**US-0001:** Calcular a área total de uma propriedade.~
    - [x] ~Teste na camada Service~
    - [x] ~Teste na camada Controller~
- [x] ~**US-0002:** Indicar o preço dessa mesma propriedade com base na área total.~
    - [x] ~Teste na camada Service~
    - [x] ~Teste na camada Controller~
- [x] ~**US-0003:** Determinar qual é o maior cômodo da propriedade.~
    - [x] ~Teste na camada Service~
    - [x] ~Teste na camada Controller~
- [x] ~**US-0004:** Determinar a área de cada cômodo.~
    - [x] ~Teste na camada Service~
    - [x] ~Teste na camada Controller~

**"Seu Imóvel"** tem padrões de qualidade muito elevados no que diz respeito aos
produtos de software que utiliza, uma vez que as transações que realiza diariamente
são por quantias muito altas de dinheiro. É por isso que um consultor de informática
que trabalha com eles estabeleceu uma série de validações que considera necessárias
para levar em consideração na hora de incorporar dados, bem como diferentes testes
de unidade que garantem os cálculos corretos.


#### Bônus
Seguindo o princípio de que **"Seu Imóvel"** possui padrões de qualidade altos, o
consultor de informática sugeriu a possibilidade de ter diferentes testes de integração,
além de testes de unidade.

O consultor conhece os tempos limitados disponíveis para realizar o desenvolvimento
solicitado, por isso sugere realizar esta implementação apenas no caso de cumprir os
prazos para que a data de entrega estimada seja cumprida.

- [ ] Testes de Integração 


---

### Tabela de endpoints

| Endpoint                                 | Verbo |                         Função                          |
|:-----------------------------------------|:-----:|:-------------------------------------------------------:|
| /api/v1/properties/area/{propId}         |  GET  | Calcular o total de metros quadrados de uma propriedade |
| /api/v1/properties/value/{propId}        |  GET  | Calcular o valor de uma propriedade                     |
| /api/v1/properties/biggest-room/{propId} |  GET  | Determinar qual é o maior cômodo                        |
| /api/v1/properties/rooms/{propId}        |  GET  | Lista os cômodos de uma propriedade                     |
| /api/v1/properties                       |  POST | Cria uma nova propriedade na base de dados              |
| /api/v1/districts                        |  POST | Cria um novo bairro na base de dados                    |
| /api/v1/rooms/properties/{propId}        |  POST | Adiciona um novo cômodo em uma propriedade existente    |


### Carga inicial de dados

- properties.json
```json
[
  {
    "propId": 1,
    "propName": "Felipe",
    "districtId": 1,
    "rooms": [
      {
        "roomName": "Quarto",
        "roomWidth": 12.00,
        "roomLength": 12.00
      }, {
        "roomName": "Sala",
        "roomWidth": 24.00,
        "roomLength": 24.00
      }, {
        "roomName": "Cozinha",
        "roomWidth": 10.00,
        "roomLength": 10.00
      }]
  },   {
    "propId": 2,
    "propName": "Gabriel",
    "districtId": 2,
    "rooms": [
      {
        "roomName": "Quarto",
        "roomWidth": 4.00,
        "roomLength": 4.00
      }, {
        "roomName": "Sala",
        "roomWidth": 10.00,
        "roomLength": 10.00
      }, {
        "roomName": "Cozinha",
        "roomWidth": 15.00,
        "roomLength": 15.00
      }]
  }
]
```
- districts.json
```json
[{
  "districtId": 1,
  "districtName": "Interlagos",
  "valueDistrictM2": 24.000
}]
```

---

### Equipe 7

- [Anderson Alves](https://github.com/andmalves)
- [Felipe Shinkae](https://github.com/fyshinkae)
- [Gabriel Viana](https://github.com/gabvteixeira)
- [Giovanna Eliz](https://github.com/giovannaelizs)
- [Matheus Ferreira (Theus)](https://github.com/matheusFerreira-meli)
- [Matheus Alves (Ma)](https://github.com/matheusaralves)


---
Feito com 💛 
