package by.katerinachikova.soapwebservice.dao;

import by.katerinachikova.soapwebservice.service.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserDAO extends AbstractDAO<User> {
    public UserDAO() {
        super(User.class);
    }
}
