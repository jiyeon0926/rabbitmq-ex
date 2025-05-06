package rabbitmq.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    // DirectExchange
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("exchange.direct");
    }

    @Bean
    Queue queue1() {
        return new Queue("queue1", false);
    }

    @Bean
    Binding directBinding(DirectExchange directExchange, Queue queue1) {
        return BindingBuilder
                .bind(queue1)
                .to(directExchange)
                .with("hello.key");
    }

    // TopicExchange
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("exchange.topic");
    }

    @Bean
    Queue queue2() {
        return new Queue("queue2", false);
    }

    @Bean
    Queue queue3() {
        return new Queue("queue3", false);
    }

    @Bean
    Binding topicBinding1(TopicExchange topicExchange, Queue queue2) {
        return BindingBuilder
                .bind(queue2)
                .to(topicExchange)
                .with("rabbit.*.key");
    }

    @Bean
    Binding topicBinding2(TopicExchange topicExchange, Queue queue3) {
        return BindingBuilder
                .bind(queue3)
                .to(topicExchange)
                .with("rabbit.#.key");
    }

    // FanoutExchange
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("exchange.fanout");
    }

    @Bean
    Queue queue4() {
        return new Queue("queue4", false);
    }

    @Bean
    Queue queue5() {
        return new Queue("queue5", false);
    }

    @Bean
    Binding fanoutBinding1(FanoutExchange fanoutExchange, Queue queue4) {
        return BindingBuilder
                .bind(queue4)
                .to(fanoutExchange);
    }

    @Bean
    Binding fanoutBinding2(FanoutExchange fanoutExchange, Queue queue5) {
        return BindingBuilder
                .bind(queue5)
                .to(fanoutExchange);
    }

    // HeadersExchange
    @Bean
    HeadersExchange headersExchange() {
        return  new HeadersExchange("exchange.headers");
    }

    @Bean
    Queue queue6() {
        return new Queue("queue6", false);
    }

    @Bean
    Binding headersBinding(HeadersExchange headersExchange, Queue queue6) {
        return BindingBuilder
                .bind(queue6)
                .to(headersExchange)
                .where("x-api-key") // Header 내에 "x-api-key" 라는 값이 존재하는 경우
                .exists();
    }

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);

        return connectionFactory;
    }

    // JSON 형식으로 메시지를 전송하고 수신
    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // ConnectionFactory와 MessageConverter를 통해 템플릿을 구성
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);

        return rabbitTemplate;
    }
}
