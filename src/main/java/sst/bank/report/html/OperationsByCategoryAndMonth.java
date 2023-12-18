package sst.bank.report.html;

import sst.bank.main.BankBankConstants;
import sst.bank.model.Category;
import sst.bank.model.Operation;
import sst.bank.model.repo.DataRepository;
import sst.common.html.HTML;
import sst.common.html.HTMLBody;
import sst.common.html.HTMLDiv;
import sst.common.html.HTMLHeader;
import sst.common.html.head.HTMLHead;

import java.util.List;

public class OperationsByCategoryAndMonth extends HTML {

    private final Category category;
    private final int month;

    public OperationsByCategoryAndMonth(Category category, int month) {
        this.category = category;
        this.month = month;
        create();
    }

    private void create() {
        HTMLHead head = this.head();
        head.css(BankBankConstants.BANKBANK_CSS);
        HTMLBody body = this.body();
        HTMLDiv div = new HTMLDiv();
        div.classId("centered-div");
        body.addChild(div);

        div.addChild(title("Opérations " + category.getName() + " de " + BankBankConstants.MONTHS[month - 1]));

        List<Operation> operations = DataRepository.me().operations().stream()
                .filter(operation -> category.equals(operation.getCategory()))
                .filter(operation -> operation.getExecutionDate().getMonthValue() == month)
                .toList();

        div.addChild(new OperationsTable(operations));

        div = new HTMLDiv();
        div.classId("centered-div");
        body.addChild(div);

        operations = DataRepository.me().operations().stream()
                .filter(operation -> category.equals(operation.getCategory()))
                .toList();

        div.addChild(new BankTable(List.of(category), operations));
    }

    private static HTMLDiv title(String title) {
        HTMLDiv div = new HTMLDiv();
        HTMLHeader h2 = new HTMLHeader(2);
        h2.textContent(title);
        div.addChild(h2);
        return div;
    }

}
