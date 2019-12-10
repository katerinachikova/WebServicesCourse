package by.katerinachikova.messagingapp;

import by.katerinachikova.messagingapp.controllers.MessageController;
import by.katerinachikova.messagingapp.utils.JsonTransformer;

import static spark.Spark.port;
import static spark.Spark.post;

public class App {
    private final static String SEND_MESSAGE_URL = "/messaging";

    private MessageController messageController = new MessageController();

    private JsonTransformer jsonTransformer = new JsonTransformer();

    public void run() {
        port(9000);

        post(SEND_MESSAGE_URL, messageController.sendMessage, jsonTransformer);
    }
}