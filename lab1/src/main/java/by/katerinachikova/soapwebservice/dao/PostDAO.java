package by.katerinachikova.soapwebservice.dao;

import by.katerinachikova.soapwebservice.service.models.Post;
import org.springframework.stereotype.Component;

@Component
public class PostDAO extends AbstractDAO<Post> {
    public PostDAO() {
        super(Post.class);
    }
}
