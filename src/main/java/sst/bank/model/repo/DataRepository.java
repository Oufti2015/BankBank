package sst.bank.model.repo;

import com.google.common.base.Strings;
import sst.bank.model.Category;
import sst.bank.model.Comment;
import sst.bank.model.Operation;

import java.util.ArrayList;
import java.util.List;

public class DataRepository {
    private static DataRepository instance;

    static {
        instance = new DataRepository();
    }

    public static DataRepository me() {
        return instance;
    }

    private final List<Operation> operations = new ArrayList<>();
    private final List<Category> categories = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();

    private DataRepository() {
    }

    public void addOperations(List<Operation> operations) {
        this.operations.addAll(operations.stream().filter(operation -> Strings.isNullOrEmpty(operation.getReasonForRefusal())).toList());
    }

    public List<Operation> operations() {
        return operations;
    }

    public void addCategories(List<Category> categories) {
        this.categories.addAll(categories);
    }

    public List<Category> categories() {
        return categories;
    }

    public void addComments(List<Comment> comments) {
        this.comments.addAll(comments);
    }

    public List<Comment> comments() {
        return comments;
    }
}
