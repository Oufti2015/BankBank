package sst.bank.model.repo;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import sst.bank.model.Comment;

import java.util.List;

@Getter
@Log4j2
public class CommentRepository {
    @Expose
    private final List<Comment> comments;

    public CommentRepository(List<Comment> comments) {
        this.comments = comments;
    }
}
