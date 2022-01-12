# Articles API

Boilerplate проект на основе микросервисной и clean архитектуры. 
Для коммуникации использует REST и Kafka. 
В качестве Gateway API используется неблокирующая реализация Spring Cloud Gateway.
Service discovery - Eureka. 

# Запуск

Запустить в консоли: `docker-compose up -d`

# Сервисы

- articles-api 8080. сервис для создания и редактирования статей.
- postgresql 5432 Credentails - articlesapi|123
- pgadmin 80. Credentails - pgadmin4@pgadmin.org|123
- lenses 3030 - мониторинг кафки. Credentails - admin|admin
- kafka 9092

Для кафки был использован образ lensesio/box - содержащий zookeeper + kafka + lenses. В качесте альтернативы можно использовать следующие образы:
```yaml
  zookeeper:
    image: 'bitnami/zookeeper:latest'
    ports:
      - '2181:2181'
    environment:
      - ALLOW_ANONYMOUS_LOGIN=yes
    networks:
      - kafka

  kafka:
    image: 'bitnami/kafka:latest'
    ports:
      - '9092:9092'
    environment:
      - KAFKA_BROKER_ID=1
      - KAFKA_CFG_LISTENERS=PLAINTEXT://:9092
      - KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://127.0.0.1:9092
      - KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181
      - ALLOW_PLAINTEXT_LISTENER=yes
    depends_on:
      - zookeeper
    networks:
      - kafka
```


# Структура папок:

Структура папок была создана по рекомендациям clean-architecture и возможно будет излише усложненной для маленьких сервисов

- persistence - все что касается хранения данных - репозитории, db-entity, cache ...
- domain - слой бизнес-логики приложения, сервисы, dto/pojo классы, domain-entities
- infrastructure - общие вещи для всего приложения, утилиты и классы для обмена между слоями приложения 
    - config - конфигурационные классы spring
- presentation - rest-controllers, request/response dto's

# Data flow diagram

# Добавление нового сервиса