package by.katerinachikova.messagingapp;

import by.katerinachikova.messagingapp.controllers.MessageController;
import by.katerinachikova.messagingapp.utils.JsonTransformer;

import static spark.Spark.port;
import static spark.Spark.post;
import by.katerinachikova.messagingapp.utils.CorsEnabled;

public class App {
    private final static String SEND_MESSAGE_URL = "/messaging/";

    private MessageController messageController = new MessageController();

    private JsonTransformer jsonTransformer = new JsonTransformer();

    private CorsEnabled corsEnabled= new CorsEnabled();;
    public void run() {
        port(9000);
        corsEnabled.enableCORS("*");
        post(SEND_MESSAGE_URL, messageController.sendMessage, jsonTransformer);
    }
}