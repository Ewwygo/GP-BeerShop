# Beer Shop Web App

## Overview

Приложение для управления каталогом пивных напитков, составления и обработки заказов.

## User Stories

Начнём с работы "Администратора" с системой.

### BS-1 Как "Администратор" я хочу добавить новое пиво "Goose" в ассортимент

Request:

`POST /beer-shop-app/add-new-beer`
```json
{
  "title" : "Goose",
  "description" : "Strong",
  "alco" : "5.7%",
  "price" : 5
}
```

Response:
`200 OK`
```json
"Beer Goose successfully added"
```

### BS-2 Как "Администратор" я хочу удалить пиво "Goose" из ассортимента

Request:

`POST /beer-shop-app/delete-beer`
```json
{
  "title" : "Goose"
}
```

Response:
`200 OK`

### BS-3 Как "Клиент" я хочу добавить пиво в корзину заказа

Request:

`POST /beer-shop-app/catalog/${beerId}/add-to-buscket`

Response:
`200 OK`
```json
"${beerId} has been successfully added to the buscket"
```

### BS-4 Как "Клиент" я хочу создать заказ с пивом из моей корзины

Request:

`POST /beer-shop-app/make-order`

Response:
`200 OK`
```json
"Order has been successfully created"
```

### BS-5 Как "Администратор" я хочу просмотреть все оформленные заказы

Request:

`GET /beer-shop-app/orders`

Response:
`200 OK`
```json
[
    {
      "id" : 1,
      "clientName" : "Alex",
      "date" : "04.02.2020",
      "beer" : "Goose x 2, Kozel x 3",
      "totalCost" : 22
    }
]
```

### BS-6 Как "Администратор" я хочу обработать заказ и переместить его в список обработанных заказов

Request:

`POST /beer-shop-app/orders/${orderId}/complete-order`

Response:
`200 OK`
```json
"Order ${orderId} has been successfully completed"
```

### BS-7 Как "Клиент" я хочу просмотреть доступный ассортимент пива

Request:

`GET /beer-shop-app/catalog`

RESPONSE:
`200 OK`
```json
[
    {
      "id" : 1,
      "title" : "Goose",
      "description" : "Strong",
      "alco" : "5.7%",
      "price" : 5
    }
]
```

### BS-8 Как "Клиент" я хочу удалить пиво "Goose" из своей корзины заказа

Request:

`POST /beer-shop-app/buscket/${beerId}/remove-from-buscket`

Response:
`200 OK`
```json
"Beer ${beerTitle} has been successfully removed from your buscket"
```