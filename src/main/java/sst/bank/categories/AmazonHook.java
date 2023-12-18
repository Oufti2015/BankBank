package sst.bank.categories;

import com.google.common.base.Strings;
import sst.bank.model.Comment;
import sst.bank.model.Operation;
import sst.bank.model.repo.DataRepository;

import java.util.Optional;

public class AmazonHook implements CategoryHook {

    public static final String UNKNOWN = "-- Unknown --";

    @Override
    public void process(Operation operation) {
        if (!Strings.isNullOrEmpty(operation.getId())) {
            Optional<Comment> commentOpt = DataRepository.me().comments().stream()
                    .filter(c -> Comment.Type.AMAZON.equals(c.getType()))
                    .filter(c -> c.getOperationId().equals(operation.getId()))
                    .findAny();
            if (commentOpt.isEmpty()) {
                Comment comment = new Comment();
                comment.setType(Comment.Type.AMAZON);
                comment.setOperationId(operation.getId());
                comment.setExecutionDate(operation.getExecutionDate());
                comment.setAmount(operation.getAmount());
                comment.setComment(UNKNOWN);
                comment.setLink(UNKNOWN);
                DataRepository.me().comments().add(comment);
            } else {
                Comment comment = commentOpt.get();
                if (!comment.getComment().equals(UNKNOWN)) {
                    operation.setDetails(operation.getDetails() + "<P>" + comment.hyperlink());
                }
            }
        }
    }
}
