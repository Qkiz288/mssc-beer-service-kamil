[![Qkiz288](https://circleci.com/gh/Qkiz288/mssc-beer-service-kamil.svg?style=shield)](https://circleci.com/gh/Qkiz288/mssc-beer-service-kamil)

Repository created to complete [John Thompson's Microservices](https://www.udemy.com/course/spring-boot-microservices-with-spring-cloud-beginner-to-guru/) course on Udemy

#### Architecture
- see [Architecure Draft](https://github.com/Qkiz288/mssc-beer-service-kamil/blob/master/architectureDraft.png) of the developed microservices

- see [Architecture Explanation](https://github.com/Qkiz288/mssc-beer-service-kamil/blob/master/architectureOverview) for details about developed microservices architecture

#### In order to run the microservices

1. Install [Docker](https://docs.docker.com/get-docker/) on your machine (v20.10.3 used during development)

2. Install [Docker IDE plugin](https://plugins.jetbrains.com/plugin/7724-docker)

3. Run `src/main/docker/local-logging/compose-logging.yaml` file

#### Eureka
- To open Eureka console, go to: `http://netflix:eureka@eureka:8761/eureka/`

#### Elasticsearch
- To see the Elasticsearch logs, go to: `http://localhost:5601/app/kibana`

#### phpMyAdmin

- To enter `phpMyAdmin`, go to: `http://root:password@localhost:8084/`

#### Docker Hub

##### See Docker images in my Docker Hub repo under `https://hub.docker.com/u/qkiz288`

##### To push the Docker image to your docker hub:
   1. in maven `settings.xml` add:<br />
    `<servers>`<br />
    `<server>`<br />
    `<id>docker.io</id>`<br />
    `<username>dockerhub_login</username>`<br />
    `<password>dockerhub_password</password>`<br />
    `</server>`<br />
    `</servers>`
   2. In `pom.xml` set `qkiz288` as `docker.image.prefix` property
   3. run: `mvn clean package docker:build docker:push`
   
#### Related repositories
1. Developed for microservices
    - [Inventory Service](https://github.com/Qkiz288/mssc-beer-inventory-service)
    - [Inventory Failover Service](https://github.com/Qkiz288/mssc-inventory-failover)
    - [Order Service](https://github.com/Qkiz288/mssc-beer-order-service)
    - [Gateway](https://github.com/Qkiz288/mssc-brewery-gateway)
    - [Config Server](https://github.com/Qkiz288/mssc-config-server)
    - [Config Repo](https://github.com/Qkiz288/mssc-brewery-config-repo)
    - [Eureka](https://github.com/Qkiz288/mssc-brewery-eureka)

2. Developed for practice:
    - [Spring State Machine](https://github.com/Qkiz288/spring-state-machine)
    - [Brewery BOM](https://github.com/Qkiz288/brewery-bom)
