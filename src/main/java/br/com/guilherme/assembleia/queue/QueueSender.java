package br.com.guilherme.assembleia.queue;

import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueueSender {

    private RabbitTemplate rabbitTemplate;

    public QueueSender(RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String nomeFila, Object object){

        rabbitTemplate.convertAndSend(nomeFila, object);
    }
}
