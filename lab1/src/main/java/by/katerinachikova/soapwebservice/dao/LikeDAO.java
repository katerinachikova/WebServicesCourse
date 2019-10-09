package by.katerinachikova.soapwebservice.dao;

import by.katerinachikova.soapwebservice.service.models.Like;
import org.springframework.stereotype.Component;

@Component
public class LikeDAO extends AbstractDAO<Like> {
    public LikeDAO() {
        super(Like.class);
    }
}
