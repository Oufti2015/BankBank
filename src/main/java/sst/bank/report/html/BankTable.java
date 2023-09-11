package sst.bank.report.html;

import lombok.extern.log4j.Log4j2;
import sst.bank.main.BankBankConstants;
import sst.bank.model.Category;
import sst.bank.model.Operation;
import sst.common.html.HTMLHyperlinks;
import sst.common.html.table.*;

import java.io.File;
import java.util.List;

@Log4j2
public class BankTable extends HTMLTable {

    private final List<Category> categories;
    private final List<Operation> operations;
    private Double[][] array;
    private final Double[] totalArray = new Double[12];
    private boolean debug = false;


    public BankTable(List<Category> categories, List<Operation> operations, boolean debug) {
        super();
        this.categories = categories;
        this.operations = operations;
        this.debug = debug;
        createArray();
        createTable();
    }

    public BankTable(List<Category> categories, List<Operation> operations) {
        super();
        this.categories = categories;
        this.operations = operations;
        createArray();
        createTable();
    }

    private void createArray() {
        array = new Double[categories.size()][13];
        int i = 0;
        for (Category category : categories.stream().sorted().toList()) {
            for (int month = 0; month < 12; month++) {
                array[i][month] = sum(category, month + 1);
            }
            i++;
        }
    }

    private Double sum(Category category, int month) {
        return savingOrNot(category, operations
                .stream()
                .filter(operation -> operation.getCategory().equals(category))
                .filter(operation -> operation.getExecutionDate().getMonthValue() == month)
                .mapToDouble(Operation::getAmount)
                .sum());
    }

    private void createTable() {
        this.classId("blueTable");
        HTMLTableHeaderRow row = this.newHeader();
        header(row);

        int i = 0;
        for (Category category : categories.stream().sorted().toList()) {
            String categoryBudgetString = category.getName();

            HTMLTableRow htmlTableRow = this.newRow();
            HTMLHyperlinks link = new HTMLHyperlinks();
            link.href(String.format("%s%s%s.html", BankBankConstants.HTML_FOLDER, File.separator, category.getName()));
            link.textContent(category.getName());
            htmlTableRow.newCell().addChild(link);
            Double total = 0.0;
            for (int month = 0; month < 12; month++) {
                final Double amount = array[i][month];
                link = new HTMLHyperlinks();
                link.href(String.format("%s%s%s_%d.html", BankBankConstants.HTML_FOLDER, File.separator, category.getName(), month + 1));
                link.textContent(String.format(BankBankConstants.FORMAT_DOUBLE, amount));
                htmlTableRow.newCell().addChild(link);
                final Double budget = category.budget(month);
                final HTMLTableCell cell = htmlTableRow.newCell(String.format(BankBankConstants.FORMAT_DOUBLE, budget));
                if (Boolean.FALSE.equals(category.getSavings())) {
                    if (amount < budget) {
                        cell.id("negative");
                    }
                } else {
                    if (amount > budget) {
                        cell.id("negative");
                    }
                }

                total += amount;

                categoryBudgetString = String.format("%s, %d.00", categoryBudgetString, amount.intValue());
            }
            categoryBudgetString += ")";

            if (debug) {
                log.debug(String.format("%,.2f : %s", (total / 9.00), categoryBudgetString));
            }
            htmlTableRow.newCell(String.format(BankBankConstants.FORMAT_DOUBLE, total));

            i++;
        }
        createFooter(this, array);
    }

    private static void header(HTMLTableHeaderRow row) {
        row.newHead();
        for (int i = 0; i < BankBankConstants.MONTHS.length; i++) {
            if (i < 12) {
                HTMLHyperlinks link = new HTMLHyperlinks();
                link.href(String.format("%s%s%d.html", BankBankConstants.HTML_FOLDER, File.separator, i + 1));
                link.textContent(BankBankConstants.MONTHS[i]);
                final HTMLTableHeader head = row.newHead();
                head.addChild(link);
                head.colspan(2);
            } else {
                row.newHead(BankBankConstants.MONTHS[i]);
            }
        }
    }

    public static Double savingOrNot(Category category, Double aDouble) {
        // return (category.getSavings() && aDouble != 0.00) ? aDouble * -1.0 : aDouble;
        return aDouble;
    }

    private void createFooter(HTMLTable table, Double[][] array) {
        HTMLTableFooterRow row = table.newFooter();
        row.newCell();
        double fullTotal = 0.0;

        for (int month = 0; month < 12; month++) {
            Double total = 0.0;
            for (int i = 0; i < categories.size(); i++) {
                total += array[i][month];
            }
            row.newCell(String.format(BankBankConstants.FORMAT_DOUBLE, total));
            int finalMonth = month;
            row.newCell(String.format(BankBankConstants.FORMAT_DOUBLE, categories.stream().mapToDouble(c -> c.budget(finalMonth)).sum()));

            fullTotal += total;
            totalArray[month] = total;
        }
        row.newCell(String.format(BankBankConstants.FORMAT_DOUBLE, fullTotal));
    }

    public Double[] totals() {
        return totalArray;
    }
}
