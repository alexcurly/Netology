# Курсовой проект «Сетевой чат» ([->](https://github.com/netology-code/jd-homeworks/blob/video/diploma/networkchat.md "Ссылка на GitHub Netologia"))


## Описание проекта

Вам нужно разработать два приложения для обмена текстовыми сообщениями по сети с помощью консоли (терминала) между двумя и более пользователями. 

**Первое приложение — сервер чата**. Оно должно ожидать подключения пользователей.

**Второе приложение — клиент чата**. Подключается к серверу чата и осуществляет доставку и получение новых сообщений.

Все сообщения должны записываться в file.log как на сервере, так и на клиентах. File.log должен дополняться при каждом запуске, а также при отправленном или полученном сообщении. Выход из чата должен быть осуществлён по команде exit.

## Требования к серверу

1. Установка порта для подключения клиентов через файл настроек (например, settings.txt).
1. Возможность подключиться к серверу в любой момент и присоединиться к чату.
1. Отправка новых сообщений клиентам.
1. Запись всех отправленных через сервер сообщений с указанием имени пользователя и времени отправки.

## Требования к клиенту

1. Выбор имени для участия в чате.
1. Прочитать настройки приложения из файла настроек: например, номер порта сервера.
1. Подключение к указанному в настройках серверу.
1. Для выхода из чата нужно набрать команду выхода “/exit”.
1. Каждое сообщение участников должно записываться в текстовый файл — файл логирования. При каждом запуске приложения файл должен дополняться.

## Требования в реализации

1. Сервер должен уметь одновременно ожидать новых пользователей и обрабатывать поступающие сообщения от пользователей.
1. Использован сборщик пакетов gradle/maven.
1. Код размещён на GitHub.
1. Код покрыт unit-тестами.

## Шаги реализации

1. Нарисовать схему приложений.
2. Описать архитектуру приложений — сколько потоков за что отвечают, придумать протокол обмена сообщениями между приложениями.
3. Создать репозиторий проекта на GitHub.
4. Написать сервер.
5. Провести интеграционный тест сервера, например, с помощью telnet.
6. Написать клиент.
7. Провести интеграционный тест сервера и клиента.
8. Протестировать сервер при подключении нескольких клиентов.
9. Написать README.md к проекту.
10. Отправить на проверку.
