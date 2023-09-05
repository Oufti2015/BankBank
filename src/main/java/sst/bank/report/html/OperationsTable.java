package sst.bank.report.html;

import sst.bank.model.Category;
import sst.bank.model.Operation;

import java.util.List;

public class OperationsList {
    private final List<Category> categories;
    private final List<Operation> operations;

    public OperationsList(List<Category> categories, List<Operation> operations) {
        this.categories = categories;
        this.operations = operations;
    }


}
