## RabbitMQ를 이용한 AMQP 라우팅 모델 실습

본 프로젝트는 Spring AMQP를 활용하여 메시지 브로커의 핵심 라우팅 모델 4가지를 구현하고 테스트한 기록입니다.

## Prerequisites
First install and start below services
```
  rabbitmq
```

##  Architecture Overview
RabbitMQ의 메시지 전달은 다음과 같은 흐름을 따릅니다.
```
  Producer → Exchange → Binding → Queue → Consumer
```
## Routing Types
### 1. direct type 

메시지의 exchange 와 routing key가 정확히 일치하는 큐로만 배달
일대일 메시지 전달 시 사용.

### 2. Fanout
routing key를 무시하고, Exchange에 바인딩된 모든 큐에 브로드캐스팅

### 3. Topic Exchange
routing key의 패턴을 보고 큐에 배달

\*: 단어 1개 일치 (예: hr.\*.info)

#: 0개 이상의 단어 일치 (예: hr.#)

### 4. Headers Exchange
Routing key 사용 X
메시지 전송 시 포함된 Header 정보를 보고 큐에 배달함.
복잡한 다중 조건 필터링이 필요할 때 사용


