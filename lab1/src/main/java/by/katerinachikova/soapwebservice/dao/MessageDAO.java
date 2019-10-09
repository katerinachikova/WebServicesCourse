package by.katerinachikova.soapwebservice.dao;

import by.katerinachikova.soapwebservice.service.models.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageDAO extends AbstractDAO<Message> {
    public MessageDAO() {
        super(Message.class);
    }
}
