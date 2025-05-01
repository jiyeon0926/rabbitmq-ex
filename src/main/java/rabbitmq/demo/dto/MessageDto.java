package rabbitmq.demo.dto;

import lombok.*;

@Getter
@RequiredArgsConstructor
public class MessageDto {

    private final String title;
    private final String message;
}