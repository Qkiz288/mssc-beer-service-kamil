[![Qkiz288](https://circleci.com/gh/Qkiz288/mssc-beer-service-kamil.svg?style=shield)](https://circleci.com/gh/Qkiz288/mssc-beer-service-kamil)

Repository created to complete Microservices course on Udemy

#### Default Port Mappings - For Single Host
| Service Name | Port | 
| --------| -----|
| Brewery Beer Service | 8080 |
| [Brewery Beer Order Service](https://github.com/Qkiz288/mssc-beer-order-service) | 8081 |
| [Brewery Beer Inventory Service](https://github.com/Qkiz288/mssc-beer-inventory-service) | 8082 |

1. In order to run the application with local MySQL DB:
    -  MySQL service has to be installed locally.
    - When running the main class, in `Edit Configuration / VM Options` add `-Dspring.profiles.active=localmysql` profile.
 
2. To run ActiveMQ:
    - `docker run -it --rm   -p 8161:8161   -p 61616:61616   vromero/activemq-artemis`
    - [More info on ActiveMQ with Docker](https://github.com/vromero/activemq-artemis-docker)

3. To register service in local Eureka, `local-discovery` profile has to be set

4. To open Eureka console, go to: `http://localhost:8761/`

5. To run Zipkin:
    - `docker run -d -p 9411:9411 openzipkin/zipkin`

6. To push the Docker image to your docker hub:
    - in maven `settings.xml` add:<br />
    `<servers>`<br />
    `<server>`<br />
    `<id>docker.io</id>`<br />
    `<username>dockerhub_login</username>`<br />
    `<password>dockerhub_password</password>`<br />
    `</server>`<br />
    `</servers>`
    - In `pom.xml` set `qkiz288` as `docker.image.prefix` property
    - run: `mvn clean package docker:build docker:push`