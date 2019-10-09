package by.katerinachikova.soapwebservice.dao;

import by.katerinachikova.soapwebservice.service.models.Thread;
import org.springframework.stereotype.Component;

@Component
public class ThreadDAO extends AbstractDAO<Thread> {
    public ThreadDAO() {
        super(Thread.class);
    }
}
