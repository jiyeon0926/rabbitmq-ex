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

    // 생산자(Producer)가 메시지를 전송
    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDto messageDto) {
        producerService.sendMessage(messageDto);

        return new ResponseEntity<>("메시지 전송 성공", HttpStatus.OK);
    }
}
