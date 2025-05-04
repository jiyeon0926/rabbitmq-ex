package rabbitmq.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rabbitmq.demo.dto.MessageDto;
import rabbitmq.demo.service.ProducerService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/producers")
public class ProducerController {

    private final ProducerService producerService;

    @PostMapping("/direct")
    public ResponseEntity<String> directSendMessage(@RequestBody MessageDto messageDto) {
        producerService.directSendMessage(messageDto);

        return new ResponseEntity<>("메시지 전송 성공", HttpStatus.OK);
    }

    @PostMapping("/topic")
    public ResponseEntity<String> topicSendMessage(@RequestBody MessageDto messageDto) {
        producerService.topicSendMessage(messageDto);

        return new ResponseEntity<>("메시지 전송 성공", HttpStatus.OK);
    }

    @PostMapping("/fanout")
    public ResponseEntity<String> fanoutSendMessage(@RequestBody MessageDto messageDto) {
        producerService.fanoutSendMessage(messageDto);

        return new ResponseEntity<>("메시지 전송 성공", HttpStatus.OK);
    }

    @PostMapping("/headers")
    public ResponseEntity<String> headersSendMessage(@RequestBody MessageDto messageDto) {
        producerService.headersSendMessage(messageDto);

        return new ResponseEntity<>("메시지 전송 성공", HttpStatus.OK);
    }
}
