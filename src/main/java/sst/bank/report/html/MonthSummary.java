package sst.bank.report.html;

import sst.bank.main.BankBankConstants;
import sst.bank.model.Operation;
import sst.bank.model.repo.DataRepository;
import sst.bank.report.tables.MonthTable;
import sst.common.html.*;
import sst.common.html.head.HTMLHead;
import sst.common.html.table.HTMLTable;
import sst.common.html.table.HTMLTableCell;
import sst.common.html.table.HTMLTableRow;

import java.time.Month;
import java.util.List;

public class MonthSummary extends HTML {

    public static final int WIDTH_MARGIN = 15;
    private final int month;
    private final Navigation.Pages[] pages = {Navigation.Pages.JANUARY, Navigation.Pages.FEBRUARY, Navigation.Pages.MARCH, Navigation.Pages.APRIL, Navigation.Pages.MAY, Navigation.Pages.JUNE,
            Navigation.Pages.JULY, Navigation.Pages.AUGUST, Navigation.Pages.SEPTEMBER, Navigation.Pages.OCTOBER, Navigation.Pages.NOVEMBER, Navigation.Pages.DECEMBER};

    public MonthSummary(int month) {
        this.month = month;
        create();
    }

    private void create() {
        HTMLHead head = this.head();
        head.css(BankBankConstants.BANKBANK_CSS);
        head.css(BankBankConstants.BANKBANK_2_CSS);
        HTMLBody body = this.body();
        body.addChild(new Navigation(pages[month - 1]));

        final List<Operation> operations = DataRepository.me().operations().stream()
                .filter(operation -> operation.getMonth().equals(Month.of(month)))
                .toList();

        body.addChild(new HTMLParagraph());

        HTMLTable table = new HTMLTable();
        table.width(100);
        HTMLTableRow row = table.newRow();
        HTMLTableCell cell = row.newCell()/*.width(50)*/;
        HTMLDiv div = new HTMLDiv();
        div.addChild(new MonthTable(month, DataRepository.me().categories(), operations));
        cell.addChild(div);

        body.addChild(table);
        footer();
    }

    private void footer() {
        HTMLTable table = new HTMLTable();
        HTMLTableRow row = table.newRow();
        for (int i = 1; i <= 12; i++) {
            HTMLLink link = new HTMLLink();
            link.href(String.format("summary-%d.html", i));
            link.textContent(Month.of(i).toString());
            row.newCell().addChild(link);
        }
        new HTMLFooter(table.toString());
    }
}
