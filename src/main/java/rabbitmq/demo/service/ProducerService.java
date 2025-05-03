package rabbitmq.demo.service;

import rabbitmq.demo.dto.MessageDto;

public interface ProducerService {

    void directSendMessage(MessageDto messageDto);
}