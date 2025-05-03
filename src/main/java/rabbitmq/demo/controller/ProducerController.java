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
}
