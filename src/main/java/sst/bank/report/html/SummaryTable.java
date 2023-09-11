package sst.bank.report.html;

import sst.bank.main.BankBankConstants;
import sst.bank.model.Category;
import sst.bank.model.repo.DataRepository;
import sst.common.html.table.*;

import java.util.List;

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
            final HTMLTableCell cell = row.newHead(s);
            cell.colspan(2);
        }

        summary(BankBankConstants.INCOME_TITLE, incomeArray, DataRepository.me().categories().stream().filter(Category::getIncome).toList());
        summary(BankBankConstants.EXPENSES_TITLE, expensesArray, DataRepository.me().categories().stream().filter(category -> !category.getIncome() && !category.getSavings()).toList());
        summary(BankBankConstants.SAVING_TITLE, savingArray, DataRepository.me().categories().stream().filter(Category::getSavings).toList());

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
            footer.newCell().textContent(String.format(BankBankConstants.FORMAT_DOUBLE, 0.00));
            total += sum;
        }
        footer.newCell().textContent(String.format(BankBankConstants.FORMAT_DOUBLE, total));
        footer.newCell().textContent(String.format(BankBankConstants.FORMAT_DOUBLE, 0.00));
    }


    private void summary(String title, Double[] input, List<Category> categories) {
        HTMLTableRow htmlTableRow = this.newRow();
        htmlTableRow.newCell().textContent(title);
        Double total = 0.00;
        Double totalBudget = 0.00;
        for (int i = 0; i < 12; i++) {
            htmlTableRow.newCell().textContent(String.format(BankBankConstants.FORMAT_DOUBLE, input[i]));
            int finalI = i;
            final double budget = categories.stream().mapToDouble(c -> c.budget(finalI)).sum();
            htmlTableRow.newCell().textContent(String.format(BankBankConstants.FORMAT_DOUBLE, budget));
            total += input[i];
            totalBudget += budget;
        }
        htmlTableRow.newCell().textContent(String.format(BankBankConstants.FORMAT_DOUBLE, total));
        htmlTableRow.newCell().textContent(String.format(BankBankConstants.FORMAT_DOUBLE, totalBudget));
    }
}
