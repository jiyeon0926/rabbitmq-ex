package rabbitmq.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import rabbitmq.demo.dto.MessageDto;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerServiceImpl implements ProducerService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void directSendMessage(MessageDto messageDto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String objectToJSON = objectMapper.writeValueAsString(messageDto);
            rabbitTemplate.convertAndSend("exchange.direct", "hello.key", objectToJSON);
        } catch (JsonProcessingException e) {
            log.error("파싱 오류 발생");
        }
    }

    @Override
    public void topicSendMessage(MessageDto messageDto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String objectToJSON = objectMapper.writeValueAsString(messageDto);
            rabbitTemplate.convertAndSend("exchange.topic", "rabbit.test.key", objectToJSON);
        } catch (JsonProcessingException jpe) {
            log.error("파싱 오류 발생");
        }
    }

    @Override
    public void fanoutSendMessage(MessageDto messageDto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String objectToJSON = objectMapper.writeValueAsString(messageDto);
            rabbitTemplate.convertAndSend("exchange.fanout", "", objectToJSON);
        } catch (JsonProcessingException jpe) {
            log.error("파싱 오류 발생");
        }
    }

    @Override
    public void headersSendMessage(MessageDto messageDto, String header) {
        try {
            if (header == null) {
                log.warn("x-api-key 헤더 없음");
                return;
            }

            ObjectMapper objectMapper = new ObjectMapper();
            String objectToJSON = objectMapper.writeValueAsString(messageDto);

            // 메시지 헤더 설정
            MessageProperties props = new MessageProperties();
            props.setHeader("x-api-key", header);

            rabbitTemplate.convertAndSend("exchange.headers", "", objectToJSON, message -> {
                message.getMessageProperties().setHeaders(props.getHeaders());

                return message;
            });
        } catch (JsonProcessingException jpe) {
            log.error("파싱 오류 발생");
        }
    }
}