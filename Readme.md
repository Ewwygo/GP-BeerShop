# Beer Shop Web App

## Overview

Приложение для управления каталогом пивных напитков, составления и обработки заказов.

## Сущности
Ниже перечисленный сущности в предметной области проекта и их поля.

### Admin (Администратор)
Пользователь осуществляющий управление магазином. Может добавлять, удалять, изменять пиво. Занимается обработкой заказов.

Поля:
- Email
- ФИО
- Информация о себе

### Client (Клиент)
Пользователь имеющий доступ к просмотру магазина. Может добавлять выбранное пиво в корзину заказа, убрать пиво из 
корзины заказа, оформить заказ.

Поля:
- Email
- ФИО
- Номер телефона
- Информация о себе

### Beer (Пиво)
Основной товар магазина.

Поля:
- Название
- Описание
- Крепость
- Плотность
- Стоимость

### Order (Заказ)
Список пива, которое пользователь добавил в корзину и оформил к покупке. Требует обработки администратора.

- Пиво (список)
- Клиент
- Общая стоимость
- Статус

## User Stories
=
### BS-1 Как "Администратор" я хочу добавить новое пиво "Goose" в ассортимент и в результате добавляю его

Request:

`POST /beer-shop-app/catalog/add-new-beer`
```json
{
  "title" : "Goose",
  "description" : "Strong",
  "alco" : "5.7%",
  "density" : "10%",
  "price" : 5
}
```

Response:
`200 OK`
```json
"Beer Goose successfully added"
```

### BS-2 Как "Администратор" я хочу удалить пиво "Goose" из ассортимента и в результате удаляю его

Request:

`POST /beer-shop-app/catalog/delete-beer/${beerId}`

Response:
`200 OK`

### BS-3 Как "Клиент" я хочу добавить пиво в корзину заказа и в результате добавляю его

Request:

`POST /beer-shop-app/catalog/add-to-bucket/${beerId}`
`Headers: clientId=1`

Response:
`200 OK`
```json
"$Beer {beerId} successfully added to client {clientId} bucket"
```

### BS-4 Как "Клиент" я хочу создать заказ с пивом из моей корзины и в резульате создаю его

Request:

`POST /beer-shop-app/make-order`
`Headers: clientId=1`
Response:
`200 OK`
```json
"Order has been successfully created for client 1"
```

### BS-5 Как "Администратор" я хочу просмотреть все оформленные заказы и в результате получаю список всех актуальных заказов

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

### BS-6 Как "Администратор" я хочу обработать заказ и в результате он переместится в список обработанных заказов

Request:

`POST /beer-shop-app/orders/${orderId}/complete-order`

Response:
`200 OK`
```json
"Order ${orderId} has been successfully completed"
```

### BS-7 Как "Клиент" я хочу просмотреть доступный ассортимент пива и в результате получаю список всего пива

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

### BS-8 Как "Клиент" я хочу удалить пиво "Goose" из своей корзины заказа и в результате удаляю его

Request:

`POST /beer-shop-app/bucket/remove-from-bucket/${beerId}`

Response:
`200 OK`
```json
"Beer ${beerTitle} has been successfully removed from your bucket"
```

### BS-9 Как "Клиент" я хочу зарегистрироваться в системе , и, если такого пользователя не найдено, регистрируюсь

Request:

`POST /beer-shop-app/client/sign-up`
```json
{
  "email" : "vasya@email.com",
  "password" : "qwerty",
  "fio" : "Пупкин Василий Иванович",
  "phoneNumber" : "+3752912345678",
  "info" : "some info" 
}
```

Response:
`201 CREATED`
```json
{
  "id" : 1
}
```

### BS-10 Как "Клиент" будучи зарегистрированным, я хочу войти в систему, и, если почта и пароль введены правильно, вхожу

Request:

`POST /beer-shop-app/client/sign-in`
```json
{
  "email" : "vasya@email.com",
  "password" : "qwerty"
}
```

Response:
`200 OK`