# Articles API

Boilerplate проект на основе микросервисной и clean архитектуры. 
Для коммуникации использует REST и Kafka. 
В качестве Gateway API используется неблокирующая реализация Spring Cloud Gateway.
Service discovery - Eureka. 

# Запуск

Пересобрать приложение: `mvn clean install`  
Пересобрать docker compose images: `docker-compose build --force-rm --no-cache`  
Запустить в консоли: `docker-compose up --build -d`  
В случае возникновения ошибок, когда какой либо контейнер не поднялся можно посмотреть логи следующей командой: `docker-compose logs -f`  
Посмотреть поднятые контейнеры: `docker-compose ps`    
Остановить сервисы: `docker-compose down`

# Сервисы

- articles-api http://localhost:8080. сервис для создания и редактирования статей.

- discovery-service http://localhost:8761
- postgresql http://localhost:5432 Credentials - articlesapi|123
- pgadmin http://localhost:80. Credentials - pgadmin4@pgadmin.org|123
- lenses http://localhost:3030 - мониторинг кафки. Credentials - admin|admin
- kafka http://localhost:9092

Для кафки был использован образ lensesio/box - содержащий zookeeper + kafka + lenses. В качестве альтернативы можно использовать следующие образы:
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

Для добавления нового сервиса можно воспользоваться следующей командой для создания пустого проекта:
`mvn archetype:generate -DgroupId=com.lordpvi.articles -DartifactId=<serviceName> -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false`

Можно так же воспользоваться архитипом, исходники которого имеются в этом проекте /architype. Он создаст CRUD сервис с H2 базой данных
```
mvn archetype:generate                    \
-DarchetypeGroupId=com.lordpvi            \
-DarchetypeArtifactId=crud-service        \
-DarchetypeVersion=1.0                    \
-DgroupId=com.lordpvi                     \
-DartifactId=new-service
```