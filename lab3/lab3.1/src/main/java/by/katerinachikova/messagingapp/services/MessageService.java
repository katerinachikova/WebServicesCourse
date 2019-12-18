package by.katerinachikova.messagingapp.services;

import by.katerinachikova.messagingapp.exception.MessageServiceException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MessageService {
    private final static String address = "localhost";
    private final static String queue = "test";

    private Logger logger = LogManager.getLogger(MessageService.class);

    public void sendMessage(String message) throws MessageServiceException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(address);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.basicPublish("", queue, null, message.getBytes());
        } catch (TimeoutException | IOException e) {
            logger.error("Can't connect to RabbitMQ", e);
            throw new MessageServiceException("Can't connect to RabbitMQ", e);
        }
    }
}