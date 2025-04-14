package sst.bank.report.tables;

import lombok.extern.log4j.Log4j2;
import sst.bank.main.BankBankConstants;
import sst.bank.model.Category;
import sst.bank.model.Operation;
import sst.common.html.HTMLHyperlinks;
import sst.common.html.table.HTMLTable;
import sst.common.html.table.HTMLTableFooterRow;
import sst.common.html.table.HTMLTableHeaderRow;
import sst.common.html.table.HTMLTableRow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class MonthTable extends HTMLTable {

    public static final String NEGATIVE = "negative";
    public static final String POSITIVE = "positive";
    public static final String NUMBER_TD = "number-td";
    public static final String NBSP = "&nbsp;";
    private final List<Category> categories;
    private final List<Operation> operations;
    private Map<Category, Double> array = new HashMap<>();
    private final int month;

    public MonthTable(int month, List<Category> categories, List<Operation> operations) {
        super();
        this.month = month;
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
        header(this.newHeader());
        List<Category> expenses = categories.stream().sorted((h1, h2) -> array.get(h1).compareTo(array.get(h2))).filter(category -> !category.getIncome()).toList();
        List<Category> incomes = categories.stream().sorted((h1, h2) -> array.get(h2).compareTo(array.get(h1))).filter(Category::getIncome).toList();

        int max = Math.max(expenses.size(), incomes.size());
        int expenseSum = 0;
        int expenseBudgetSum = 0;
        int incomeSum = 0;
        int incomeBudgetSum = 0;

        Result result = getResult(max, expenses, expenseSum, expenseBudgetSum, incomes, incomeSum, incomeBudgetSum);
        footer(this.newFooter(), result.expenseSum(), result.expenseBudgetSum(), result.incomeSum(), result.incomeBudgetSum());
    }

    private Result getResult(int max, List<Category> expenses, int expenseSum, int expenseBudgetSum, List<Category> incomes, int incomeSum, int incomeBudgetSum) {
        for (int i = 0; i < max; i++) {
            HTMLTableRow row = this.newRow();
            if (i < expenses.size()) {
                Category expense = expenses.get(i);
                row.newCell(new HTMLHyperlinks().href(String.format("%s_%d.html", expense.getName(), month)).textContent(expense.getName()).toString());
                int expenseAmount = array.get(expense).intValue();
                if (expenseAmount != 0) {
                    expenseAmount = expenseAmount * -1;
                }
                row.newCell(String.format(BankBankConstants.FORMAT_INTEGER_TABLE, expenseAmount)).classId(NUMBER_TD);
                expenseSum += expenseAmount;
                int budget = expense.budget(month - 1).intValue();
                if (budget != 0) {
                    budget = budget * -1;
                }
                String classId = budget >= 0 ? NUMBER_TD : "number-red";
                row.newCell(String.format(BankBankConstants.FORMAT_INTEGER_TABLE, budget)).classId(classId);
                expenseBudgetSum += budget;

                int diff = budget - expenseAmount;
                classId = diff >= 0 ? NUMBER_TD : "number-red";
                row.newCell(String.format(BankBankConstants.FORMAT_INTEGER_TABLE, diff)).classId(classId);
            } else {
                row.newCell(NBSP);
                row.newCell(NBSP);
            }
            row.newCell(NBSP).width(5);
            if (i < incomes.size()) {
                Category income = incomes.get(i);
                row.newCell(income.getName());
                Integer incomeAmount = array.get(income).intValue();
                row.newCell(String.format(BankBankConstants.FORMAT_INTEGER_TABLE, incomeAmount)).classId(NUMBER_TD);
                incomeSum += incomeAmount;
                Integer budget = income.budget(month - 1).intValue();
                row.newCell(String.format(BankBankConstants.FORMAT_INTEGER_TABLE, budget)).classId(NUMBER_TD);
                incomeBudgetSum += budget;
                row.newCell(String.format(BankBankConstants.FORMAT_INTEGER_TABLE, budget - incomeAmount)).classId(NUMBER_TD);
            } else {
                row.newCell(NBSP);
                row.newCell(NBSP);
            }
        }
        return new Result(expenseSum, expenseBudgetSum, incomeSum, incomeBudgetSum);
    }

    private record Result(int expenseSum, int expenseBudgetSum, int incomeSum, int incomeBudgetSum) {
    }

    private static void header(HTMLTableHeaderRow row) {
        row.newHead().textContent(NBSP);
        row.newHead().textContent("Dépenses").classId(NUMBER_TD);
        row.newHead().textContent("Budget").classId(NUMBER_TD);
        row.newHead().textContent("Diff").classId(NUMBER_TD);

        row.newHead().textContent(NBSP);
        row.newHead().textContent(NBSP);
        row.newHead().textContent("Recettes").classId(NUMBER_TD);
        row.newHead().textContent("Budget").classId(NUMBER_TD);
        row.newHead().textContent("Diff").classId(NUMBER_TD);
    }

    private void footer(HTMLTableFooterRow row, Integer expenseSum, Integer expenseBudgetSum, Integer incomeSum, Integer incomeBudgetSum) {
        row.newCell("Total Dépenses");
        row.newCell(String.format(BankBankConstants.FORMAT_INTEGER_TABLE, expenseSum)).classId(NUMBER_TD);
        row.newCell(String.format(BankBankConstants.FORMAT_INTEGER_TABLE, expenseBudgetSum)).classId(NUMBER_TD);
        row.newCell(String.format(BankBankConstants.FORMAT_INTEGER_TABLE, expenseBudgetSum - expenseSum)).classId(NUMBER_TD);
        row.newCell(NBSP);
        row.newCell("Total Recettes");
        row.newCell(String.format(BankBankConstants.FORMAT_INTEGER_TABLE, incomeSum)).classId(NUMBER_TD);
        row.newCell(String.format(BankBankConstants.FORMAT_INTEGER_TABLE, incomeBudgetSum)).classId(NUMBER_TD);
        row.newCell(String.format(BankBankConstants.FORMAT_INTEGER_TABLE, incomeBudgetSum - incomeSum)).classId(NUMBER_TD);
    }
}