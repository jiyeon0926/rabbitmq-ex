package rabbitmq.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerService {

    // 전달받은 데이터를 수신
    @RabbitListener(queues = "queue1")
    public void receiveMessage(String message) {
        log.info("결과: {}", message);
    }
}
