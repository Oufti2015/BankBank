package sst.bank.report.html;

import sst.bank.main.BankBank;
import sst.common.html.table.HTMLTable;
import sst.common.html.table.HTMLTableFooterRow;
import sst.common.html.table.HTMLTableHeaderRow;
import sst.common.html.table.HTMLTableRow;

public class SummaryTable extends HTMLTable {
    public static final String[] MONTHS = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre", "Total"};
    private final Double[] incomeArray;
    private final Double[] expensesArray;
    private final Double[] savingArray;

    public SummaryTable(Double[] incomeArray, Double[] expensesArray, Double[] savingArray) {
        this.incomeArray = incomeArray;
        this.expensesArray = expensesArray;
        this.savingArray = savingArray;

        for (int i = 0; i < savingArray.length; i++) {
            savingArray[i] = savingArray[i] * -1.0;
        }
        createTable();
    }

    private void createTable() {
        this.classId("blueTable");
        HTMLTableHeaderRow row = this.newHeader();
        row.newHead();
        for (String s : MONTHS) {
            row.newHead(s);
        }

        summary(BankBank.INCOME_TITLE, incomeArray);
        summary(BankBank.EXPENSES_TITLE, expensesArray);
        summary(BankBank.SAVING_TITLE, savingArray);

        footer();
    }

    private void footer() {
        HTMLTableRow htmlTableRow;
        Double total;
        total = 0.00;
        HTMLTableFooterRow footer = this.newFooter();
        footer.newCell().textContent("Total");
        for (int i = 0; i < 12; i++) {
            double sum = incomeArray[i] + expensesArray[i] + savingArray[i];
            footer.newCell().textContent(String.format("%,.2f", sum));
            total += sum;
        }
        footer.newCell().textContent(String.format("%,.2f", total));
    }


    private void summary(String title, Double[] input) {
        HTMLTableRow htmlTableRow = this.newRow();
        htmlTableRow.newCell().textContent(title);
        Double total = 0.00;
        for (int i = 0; i < 12; i++) {
            htmlTableRow.newCell().textContent(String.format("%,.2f", input[i]));
            total += input[i];
        }
        htmlTableRow.newCell().textContent(String.format("%,.2f", total));
    }
}
