package sst.bank.model.repo;

import com.google.common.base.Strings;
import sst.bank.main.BankBankConstants;
import sst.bank.model.Category;
import sst.bank.model.Comment;
import sst.bank.model.Operation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class DataRepository {
    private static final DataRepository instance;

    static {
        instance = new DataRepository();
    }

    public static DataRepository me() {
        return instance;
    }

    private final List<Operation> operations = new ArrayList<>();
    private final List<Category> categories = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();
    private final Properties benefiaries = new Properties();
    private final Properties operationsCategories = new Properties();

    private DataRepository() {
    }

    public void addOperations(List<Operation> operations) throws IOException {
        this.operations.addAll(operations.stream().filter(operation -> Strings.isNullOrEmpty(operation.getReasonForRefusal())).toList());

        this.operations.stream()
                .filter(o -> benefiaries.get(o.getCounterpartyAccountNumber()) == null)
                .forEach(o -> benefiaries.put(o.getCounterpartyAccountNumber(), o.getCounterpartyName()));

        try (OutputStream output = new FileOutputStream(BankBankConstants.BENEF_FILE)) {
            benefiaries.store(output, "BankBank's Beneficiaries");
        }
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

    public Optional<Category> category(String name) {
        return categories.stream().filter(category -> category.getName().equals(name)).findFirst();
    }

    public void addComments(List<Comment> comments) {
        this.comments.addAll(comments);
    }

    public List<Comment> comments() {
        return comments;
    }

    public Properties beneficiaries() {
        return benefiaries;
    }

    public Properties operationsCategories() {
        return operationsCategories;
    }
}
