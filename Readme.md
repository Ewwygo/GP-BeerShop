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

### Guest (Гость)
Пользователь который не зарегестрировался в магазине. В последствии может зарегестрироваться и стать "Клиентом"

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
### BS-1 Как "Администратор" я хочу добавить новое пиво "Goose" в ассортимент, чтобы "Клиент" мог заказать новый вид пива

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

### BS-2 Как "Администратор" я хочу удалить пиво "Goose" из ассортимента, чтобы "Клиент" больше не мог заказать его

Request:

`POST /beer-shop-app/catalog/delete-beer/${beerId}`

Response:
`200 OK`


### BS-4 Как "Клиент" я хочу создать заказ с пивом из моей корзины и в резульате создаю его

Request:

`POST /beer-shop-app/make-order`
`Headers: clientId=1`
```json
[
  {
  "beerId" : 1,
  "amount" : 1
  },
  
  {
  "beerId" : 2,
  "amount" : 2
  }
]
```
Response:
`200 OK`


### BS-5 Как "Администратор" я хочу просмотреть все оформленные заказы, чтобы узнать количество и номера необработанных заказов

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

### BS-7 Как "Клиент" я хочу просмотреть доступный ассортимент пива,чтобы выбрать пиво, которое я хочу заказать, и в результате получаю список всего пива

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

### BS-9 Как "Гость" я хочу зарегистрироваться в системе , и, если такого пользователя не найдено, регистрируюсь и получаю JWT токен для работы в системе

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
  "token" : "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2YXN5YUBlbWFpbC5jb20iLCJleHAiOjE1Nzk5MDQ2OTksImlhdCI6MTU3OTg2ODY5OX0.8JG6O4U5F3xyOlOTyeSfl3Siim91HiJ-d4Dz5Guse8I"
}
```

### BS-10 Как "Гость" будучи зарегистрированным, я хочу войти в систему, и, если почта и пароль введены правильно, вхожу

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
```json
{
  "token" : "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2YXN5YUBlbWFpbC5jb20iLCJleHAiOjE1Nzk5MDQ2OTksImlhdCI6MTU3OTg2ODY5OX0.8JG6O4U5F3xyOlOTyeSfl3Siim91HiJ-d4Dz5Guse8I"
}
```