package sst.bank.report.html;

import lombok.extern.log4j.Log4j2;
import sst.bank.main.BankBankConstants;
import sst.bank.model.Category;
import sst.common.html.HTMLHyperlinks;
import sst.common.html.table.*;

import java.io.File;
import java.util.List;

@Log4j2
public class BudgetTable extends HTMLTable {
    private final List<Category> categories;

    public BudgetTable(List<Category> categories) {
        this.categories = categories;
        createTable();
    }

    private void createTable() {
        this.classId("blueTable");
        HTMLTableHeaderRow row = this.newHeader();
        header(row);

        for (Category category : categories.stream().sorted().toList()) {
            HTMLTableRow htmlTableRow = this.newRow();
            HTMLHyperlinks link = new HTMLHyperlinks();
            link.href(String.format("%s%s%s.html", BankBankConstants.HTML_FOLDER, File.separator, category.getName()));
            link.textContent(category.getName());
            htmlTableRow.newCell().addChild(link);
            double total = 0.0;
            for (int month = 0; month < 12; month++) {
                total = byMonth(category, month, htmlTableRow, total);
            }
            htmlTableRow.newCell(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, total));
        }
        createFooter(this);
    }

    private static void header(HTMLTableHeaderRow row) {
        row.newHead();
        for (int i = 0; i < BankBankConstants.MONTHS.length; i++) {
            if (i < 12) {
                final HTMLTableHeader head = row.newHead();
                head.textContent(BankBankConstants.MONTHS[i]);
            } else {
                row.newHead(BankBankConstants.MONTHS[i]);
            }
        }
    }

    private double byMonth(Category category, int month, HTMLTableRow htmlTableRow, double total) {
        final Double budget = category.budget(month);
        htmlTableRow.newCell(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, budget));

        total += budget;
        return total;
    }


    private void createFooter(HTMLTable table) {
        HTMLTableFooterRow row = table.newFooter();
        row.newCell();
        double fullTotal = 0.0;

        for (int month = 0; month < 12; month++) {
            int finalMonth = month;
            final double total = categories.stream().mapToDouble(c -> c.budget(finalMonth)).sum();
            row.newCell(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, total));

            fullTotal += total;
        }
        row.newCell(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, fullTotal));
    }

}
