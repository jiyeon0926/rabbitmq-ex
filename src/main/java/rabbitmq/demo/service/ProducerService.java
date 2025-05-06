package rabbitmq.demo.service;

import rabbitmq.demo.dto.MessageDto;

public interface ProducerService {

    void directSendMessage(MessageDto messageDto);

    void topicSendMessage(MessageDto messageDto);

    void fanoutSendMessage(MessageDto messageDto);

    void headersSendMessage(MessageDto messageDto, String header);
}