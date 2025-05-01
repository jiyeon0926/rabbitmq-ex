package rabbitmq.demo.service;

import rabbitmq.demo.dto.MessageDto;

public interface ProducerService {

    // 메시지를 큐로 전송
    void sendMessage(MessageDto messageDto);
}