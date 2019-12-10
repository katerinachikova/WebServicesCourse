package by.katerinachikova.messagingapp.controllers;

import by.katerinachikova.messagingapp.exception.MessageServiceException;
import by.katerinachikova.messagingapp.services.MessageService;
import by.katerinachikova.messagingapp.utils.JsonBodyConverter;
import by.katerinachikova.messagingapp.utils.ResponseUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Map;

public class MessageController {
    private final static String MESSAGE_KEY = "message";

    private MessageService messageService = new MessageService();

    private Logger logger = LogManager.getLogger(MessageController.class);

    public final Route sendMessage = (Request request, Response response) -> {
        Map<String, String> bodyParams = JsonBodyConverter.convertJsonBodyToMap(request);
        if (!bodyParams.containsKey(MESSAGE_KEY)) {
            return ResponseUtils.errorMessage(response, "Invalid request body");
        }
        String message = bodyParams.get(MESSAGE_KEY);
        logger.info("Sending message: " + message);
        try {
            messageService.sendMessage(message);
            logger.info("Message sent successfully");
            return ResponseUtils.successMessage(response, "Message sent successfully");
        } catch (MessageServiceException e) {
            logger.error("Failed to send message", e);
            return ResponseUtils.errorMessage(response, e.getMessage());
        }
    };
}