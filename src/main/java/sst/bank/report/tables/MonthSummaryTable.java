package sst.bank.report.tables;

import lombok.extern.log4j.Log4j2;
import sst.bank.main.BankBankConstants;
import sst.bank.model.Category;
import sst.bank.model.Operation;
import sst.common.html.table.HTMLTable;
import sst.common.html.table.HTMLTableRow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class MonthSummaryTable extends HTMLTable {

    public static final String NEGATIVE = "negative";
    public static final String POSITIVE = "positive";
    public static final String NUMBER_TD = "number-td";
    private final List<Category> categories;
    private final List<Operation> operations;
    private Map<Category, Double> array = new HashMap<>();

    public MonthSummaryTable(List<Category> categories, List<Operation> operations) {
        super();
        this.categories = categories;
        this.operations = operations;
        createArray();
        createTable();
    }

    private void createArray() {
        for (Category category : categories.stream().sorted().toList()) {
            array.put(category, sum(category));
        }
    }

    private Double sum(Category category) {
        return operations
                .stream()
                .filter(operation -> operation.getCategory().equals(category))
                .mapToDouble(Operation::getAmount)
                .sum();
    }


    private void createTable() {
        this.classId("fixed-table");
        List<Category> expenses = categories.stream().sorted().filter(category -> !category.getIncome()).toList();
        List<Category> incomes = categories.stream().sorted().filter(Category::getIncome).toList();

        Double expenseSum = 0.0;
        Double incomeSum = 0.0;
        for (Category expense : expenses) {
            expenseSum += array.get(expense);
        }
        for (Category income : incomes) {
            incomeSum += array.get(income);
        }
        HTMLTableRow row = this.newRow();
        row.newCell("Total Dépenses");
        row.newCell(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, expenseSum)).classId(NUMBER_TD);

        row = this.newRow();
        row.newCell("Total Recettes");
        row.newCell(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, incomeSum)).classId(NUMBER_TD);

        row = this.newFooter();
        row.newCell("Balance");
        row.newCell(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, incomeSum + expenseSum)).classId(NUMBER_TD);
    }
}