package sst.bank.report.html;

import sst.bank.main.BankBankConstants;
import sst.bank.model.repo.DataRepository;
import sst.bank.report.tables.BudgetTable;
import sst.common.html.HTML;
import sst.common.html.HTMLBody;
import sst.common.html.HTMLDiv;
import sst.common.html.HTMLHeader;
import sst.common.html.head.HTMLHead;

public class Budget extends HTML {

    public Budget() {
        super();
        create();
    }

    private void create() {
        HTMLHead head = this.head();
        head.css(BankBankConstants.BANKBANK_CSS);
        HTMLBody body = this.body();
        HTMLDiv div = new HTMLDiv();
        div.classId("centered-div");
        body.addChild(div);
        budget(div);
    }

    private void budget(HTMLDiv body) {
        body.addChild(title());
        BudgetTable bankTable = new BudgetTable(DataRepository.me().categories());
        body.addChild(bankTable);
    }


    private static HTMLDiv title() {
        HTMLDiv div = new HTMLDiv();
        HTMLHeader h2 = new HTMLHeader(2);
        h2.textContent("Budget");
        div.addChild(h2);
        return div;
    }
}
