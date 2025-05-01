package rabbitmq.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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

    /**
     * "hello.exchange" 라는 이름으로 Direct Exchange 구성
     * @return DirectExchange
     */
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("hello.exchange");
    }

    /**
     * "hello.queue" 라는 이름으로 큐를 구성
     * @return Queue
     */
    @Bean
    Queue queue() {
        return new Queue("hello.queue", false);
    }

    /**
     * 큐와 DirectExchange를 바인딩
     * @param directExchange
     * @param queue
     * @return "hello.key" 라는 이름으로 바인딩된 Binding 객체
     */
    @Bean
    Binding binding(DirectExchange directExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(directExchange).with("hello.key");
    }

    // RabbitMQ와 연결
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
