package sst.bank.report.html;

import sst.bank.model.Category;
import sst.bank.model.Operation;
import sst.common.html.HTMLHyperlinks;
import sst.common.html.table.*;

import java.util.List;

public class BankTable extends HTMLTable {
    public static final String[] MONTHS = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre", "Total"};

    private final List<Category> categories;
    private final List<Operation> operations;
    private Double[][] array;
    private Double[] totalArray = new Double[12];

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
        row.newHead();
        for (String s : MONTHS) {
            row.newHead(s);
        }

        int i = 0;
        for (Category category : categories.stream().sorted().toList()) {
            HTMLTableRow htmlTableRow = this.newRow();
            HTMLHyperlinks link = new HTMLHyperlinks();
            link.href(String.format("c:\\zt974\\%s.html", category.getName()));
            link.textContent(category.getName());
            htmlTableRow.newCell().addChild(link);
            Double total = 0.0;
            for (int month = 0; month < 12; month++) {
                final Double amount = array[i][month];
                link = new HTMLHyperlinks();
                link.href(String.format("c:\\zt974\\%s_%d.html", category.getName(), month+1));
                link.textContent(String.format("%,.2f", amount));
                htmlTableRow.newCell().addChild(link);
                total += amount;
            }
            htmlTableRow.newCell(String.format("%,.2f", total));

            i++;
        }
        createFooter(this, array);
    }

    public static Double savingOrNot(Category category, Double aDouble) {
        return (category.getSavings() && aDouble != 0.00) ? aDouble * -1.0 : aDouble;
    }

    private void createFooter(HTMLTable table, Double[][] array) {
        HTMLTableFooterRow row = table.newFooter();
        row.newCell();
        Double fullTotal = 0.0;

        for (int month = 0; month < 12; month++) {
            Double total = 0.0;
            for (int i = 0; i < categories.size(); i++) {
                total += array[i][month];
            }
            row.newCell(String.format("%,.2f", total));
            fullTotal += total;
            totalArray[month] = total;
        }
        row.newCell(String.format("%,.2f", fullTotal));
    }

    public Double[] totals() {
        return totalArray;
    }
}
