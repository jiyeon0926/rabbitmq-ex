# 🛠️ 기술 스택
- Java 21
- Spring Boot 3.4.5
- RabbitMQ

# 📨 RabbitMQ
- AMQP Port : 5672
- 대시보드 Port : 15672

### 🔁 역할 및 흐름
Producer → Exchange → Queue → Consumer
- Producer : 메시지를 생성하여 Exchange에 전송
- Exchange : 메시지를 수신하고, 어떤 Queue에 보낼지 결정하는 역할
- Queue : 메시지를 저장하는 공간
- Consumer : Queue에서 메시지를 꺼내서 처리

Consumer가 없다면❓
- 메시지는 Queue에 저장된 채로 대기
- 수신자는 메시지를 받을 수 없음

### 🗂️ 내용 정리
- https://blog.naver.com/yeondata/223851493671
- https://blog.naver.com/yeondata/223851519988

# 📝 참고
- https://adjh54.tistory.com/292 (basic)
- https://adjh54.tistory.com/497?category=1187853