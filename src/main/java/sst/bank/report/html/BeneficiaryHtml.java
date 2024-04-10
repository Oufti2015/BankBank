package sst.bank.report.html;

import sst.bank.main.BankBankConstants;
import sst.common.html.HTML;
import sst.common.html.HTMLBody;
import sst.common.html.HTMLDiv;
import sst.common.html.HTMLParagraph;
import sst.common.html.head.HTMLHead;

import java.io.File;

public class BeneficiaryHtml extends HTML {
    public BeneficiaryHtml() {
        super();
        create();
    }

    private void create() {
        HTMLHead head = this.head();
        head.css("html" + File.separator + BankBankConstants.BANKBANK_CSS);
        HTMLBody body = this.body();
        body.addChild(new Navigation(Navigation.Pages.BENEFICIARY));
        body.addChild(new HTMLParagraph());
        HTMLDiv div = new HTMLDiv();
        div.classId("centered-div");
        body.addChild(div);
        beneficiaries(div);
    }

    private void beneficiaries(HTMLDiv div) {

    }
}
