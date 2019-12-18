package by.katerinachikova.messagingapp;

import by.katerinachikova.messagingapp.controllers.MessageController;
import by.katerinachikova.messagingapp.utils.JsonTransformer;
import static spark.Spark.port;
import static spark.Spark.post;
import by.katerinachikova.messagingapp.utils.CorsEnabled;

public class App {
    private final static String sending_url = "/messaging/";

    private MessageController msgController = new MessageController();

    private JsonTransformer jsonTransformer = new JsonTransformer();

    private CorsEnabled corsEnabled= new CorsEnabled();;
    public void run() {
        port(9000);
        corsEnabled.enableCORS("*");
        post(sending_url, msgController.sendMessage, jsonTransformer);
    }
}