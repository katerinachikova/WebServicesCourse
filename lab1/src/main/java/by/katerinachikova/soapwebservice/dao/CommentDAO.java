package by.katerinachikova.soapwebservice.dao;

import by.katerinachikova.soapwebservice.service.models.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentDAO extends AbstractDAO<Comment> {
    public CommentDAO() {
        super(Comment.class);
    }
}
