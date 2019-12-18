package by.katerinachikova.messagingapp.utils;

import org.eclipse.jetty.websocket.api.StatusCode;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {
    public static Map<String, String> errorMessage(Response response, String message) {
        return message(response, message, by.katerinachikova.messagingapp.utils.StatusCode.status_error);
    }

    public static Map<String, String> message(Response response, String message, int status) {
        response.status(status);
        Map<String, String> result = new HashMap<>();
        result.put("message", message);
        return result;
    }

    public static Map<String, String> successMessage(Response response, String message) {
        return message(response, message, by.katerinachikova.messagingapp.utils.StatusCode.status_ok);
    }
}