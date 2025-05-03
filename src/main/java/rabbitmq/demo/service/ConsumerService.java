package rabbitmq.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerService {

    @RabbitListener(queues = "queue1")
    public void receiveQueue1Message(String message) {
        log.info("queue1 결과: {}", message);
    }

    @RabbitListener(queues = "queue2")
    public void receiveQueue2Message(String message) {
        log.info("queue2 결과: {}", message);
    }

    @RabbitListener(queues = "queue3")
    public void receiveQueue3Message(String message) {
        log.info("queue3 결과: {}", message);
    }

    @RabbitListener(queues = "queue4")
    public void receiveQueue4Message(String message) {
        log.info("queue4 결과: {}", message);
    }

    @RabbitListener(queues = "queue5")
    public void receiveQueue5Message(String message) {
        log.info("queue5 결과: {}", message);
    }

    @RabbitListener(queues = "queue6")
    public void receiveQueue6Message(String message) {
        log.info("queue6 결과: {}", message);
    }
}
