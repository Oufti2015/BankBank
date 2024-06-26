package sst.bank.report.html;

import lombok.extern.log4j.Log4j2;
import sst.bank.main.BankBankConstants;
import sst.bank.model.Category;
import sst.bank.model.Operation;
import sst.common.html.HTMLHyperlinks;
import sst.common.html.table.*;

import java.io.File;
import java.time.Month;
import java.util.List;

@Log4j2
public class BankTable extends HTMLTable {

    public static final String NEGATIVE = "negative";
    public static final String POSITIVE = "positive";
    private final List<Category> categories;
    private final List<Operation> operations;
    private Double[][] array;
    private final Double[] totalArray = new Double[12];

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
        return operations
                .stream()
                .filter(operation -> operation.getCategory().equals(category))
                .filter(operation -> operation.getMonth().equals(Month.of(month)))
                .mapToDouble(Operation::getAmount)
                .sum();
    }

    private void createTable() {
        this.classId("blueTable");
        HTMLTableHeaderRow row = this.newHeader();
        header(row);

        int i = 0;
        for (Category category : categories.stream().sorted().toList()) {
            HTMLTableRow htmlTableRow = this.newRow();
            HTMLHyperlinks link = new HTMLHyperlinks();
            link.href(String.format("%s%s%s.html", BankBankConstants.HTML_FOLDER, File.separator, category.getName()));
            link.textContent(category.getName());
            htmlTableRow.newCell().addChild(link);
            double total = 0.0;
            for (int month = 0; month < 12; month++) {
                total = byMonth(category, i, month, htmlTableRow, total);
            }
            htmlTableRow.newCell(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, total));

            i++;
        }
        createFooter(this, array);
    }

    private double byMonth(Category category, int i, int month, HTMLTableRow htmlTableRow, double total) {
        HTMLHyperlinks link;
        final Double amount = array[i][month];
        link = new HTMLHyperlinks();
        link.href(String.format("%s%s%s_%d.html", BankBankConstants.HTML_FOLDER, File.separator, category.getName(), month + 1));
        link.textContent(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, amount));
        htmlTableRow.newCell().addChild(link);
        final Double budget = category.budget(month);
        final HTMLTableCell cell = htmlTableRow.newCell(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, budget));
        if (Boolean.FALSE.equals(category.getSavings())) {
            if (amount < budget) {
                cell.id(NEGATIVE);
            } else {
                cell.id(POSITIVE);
            }
        } else {
            if (amount > budget) {
                cell.id(NEGATIVE);
            } else {
                cell.id(POSITIVE);
            }
        }

        total += amount;
        return total;
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

    private void createFooter(HTMLTable table, Double[][] array) {
        HTMLTableFooterRow row = table.newFooter();
        row.newCell();
        double fullTotal = 0.0;

        for (int month = 0; month < 12; month++) {
            Double total = 0.0;
            for (int i = 0; i < categories.size(); i++) {
                total += array[i][month];
            }
            row.newCell(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, total));
            int finalMonth = month;
            row.newCell(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, categories.stream().mapToDouble(c -> c.budget(finalMonth)).sum()));

            fullTotal += total;
            totalArray[month] = total;
        }
        row.newCell(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, fullTotal));
    }

    public Double[] totals() {
        return totalArray;
    }
}
