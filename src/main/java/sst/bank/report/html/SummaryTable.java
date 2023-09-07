package sst.bank.report.html;

import sst.bank.main.BankBankConstants;
import sst.common.html.table.HTMLTable;
import sst.common.html.table.HTMLTableFooterRow;
import sst.common.html.table.HTMLTableHeaderRow;
import sst.common.html.table.HTMLTableRow;

public class SummaryTable extends HTMLTable {
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
        for (String s : BankBankConstants.MONTHS) {
            row.newHead(s);
        }

        summary(BankBankConstants.INCOME_TITLE, incomeArray);
        summary(BankBankConstants.EXPENSES_TITLE, expensesArray);
        summary(BankBankConstants.SAVING_TITLE, savingArray);

        footer();
    }

    private void footer() {
        Double total;
        total = 0.00;
        HTMLTableFooterRow footer = this.newFooter();
        footer.newCell().textContent("Total");
        for (int i = 0; i < 12; i++) {
            double sum = incomeArray[i] + expensesArray[i] + savingArray[i];
            footer.newCell().textContent(String.format(BankBankConstants.FORMAT_DOUBLE, sum));
            total += sum;
        }
        footer.newCell().textContent(String.format(BankBankConstants.FORMAT_DOUBLE, total));
    }


    private void summary(String title, Double[] input) {
        HTMLTableRow htmlTableRow = this.newRow();
        htmlTableRow.newCell().textContent(title);
        Double total = 0.00;
        for (int i = 0; i < 12; i++) {
            htmlTableRow.newCell().textContent(String.format(BankBankConstants.FORMAT_DOUBLE, input[i]));
            total += input[i];
        }
        htmlTableRow.newCell().textContent(String.format(BankBankConstants.FORMAT_DOUBLE, total));
    }
}
