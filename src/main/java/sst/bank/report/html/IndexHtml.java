package sst.bank.report.html;

import sst.bank.main.BankBankConstants;
import sst.bank.model.Category;
import sst.bank.model.Operation;
import sst.bank.model.repo.DataRepository;
import sst.common.html.*;
import sst.common.html.head.HTMLHead;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class IndexHtml extends HTML {
    private Double[] incomeArray;
    private Double[] expensesArray;
    private Double[] savingArray;

    public IndexHtml() {
        super();
        create();
    }

    private void create() {
        HTMLHead head = this.head();
        head.css("html" + File.separator + BankBankConstants.BANKBANK_CSS);
        HTMLBody body = this.body();
        body.addChild(new Navigation(Navigation.Pages.INDEX));
        body.addChild(new HTMLParagraph());
        HTMLDiv div = new HTMLDiv();
        div.classId("centered-div");
        body.addChild(div);
        income(div);
        expenses(div);
        savings(div);
        summary(div);
    }

    private void income(Container body) {
        body.addChild(title(BankBankConstants.INCOME_TITLE));
        BankTable bankTable = new BankTable(DataRepository.me().categories().stream().filter(Category::getIncome).toList(), DataRepository.me().operations());
        body.addChild(bankTable);
        incomeArray = bankTable.totals();
    }

    private void expenses(Container body) {
        body.addChild(title(BankBankConstants.EXPENSES_TITLE));
        BankTable bankTable = new BankTable(DataRepository.me().categories().stream().filter(category -> !category.getIncome() && !category.getSavings()).toList(), DataRepository.me().operations());
        body.addChild(bankTable);
        expensesArray = bankTable.totals();
    }

    private void savings(Container body) {
        body.addChild(title(BankBankConstants.SAVING_TITLE));
        BankTable bankTable = new BankTable(DataRepository.me().categories().stream().filter(Category::getSavings).toList(), DataRepository.me().operations());
        body.addChild(bankTable);
        savingArray = bankTable.totals();
    }

    private void summary(Container body) {
        body.addChild(title(BankBankConstants.SUMMARY_TITLE));
        SummaryTable summaryTable = new SummaryTable(incomeArray, expensesArray, savingArray);
        body.addChild(summaryTable);
    }

    private static HTMLDiv title(String title) {
        HTMLDiv div = new HTMLDiv();
        HTMLHeader h2 = new HTMLHeader(2);
        h2.textContent(addDates(title));
        div.addChild(h2);
        return div;
    }

    private static String addDates(String title) {
        Operation first = DataRepository.me().operations()
                .stream()
                .min(Comparator.comparing(Operation::getExecutionDate))
                .orElseThrow(NoSuchElementException::new);
        Operation last = DataRepository.me().operations()
                .stream()
                .max(Comparator.comparing(Operation::getExecutionDate))
                .orElseThrow(NoSuchElementException::new);

        return String.format("%s du %s au %s", title,
                first.getExecutionDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)),
                last.getExecutionDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
    }
}
