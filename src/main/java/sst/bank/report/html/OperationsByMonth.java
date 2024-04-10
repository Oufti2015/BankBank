package sst.bank.report.html;

import sst.bank.main.BankBankConstants;
import sst.bank.model.Operation;
import sst.bank.model.repo.DataRepository;
import sst.common.html.HTML;
import sst.common.html.HTMLBody;
import sst.common.html.HTMLDiv;
import sst.common.html.HTMLHeader;
import sst.common.html.head.HTMLHead;

import java.time.Month;
import java.util.List;

public class OperationsByMonth extends HTML {

    private final int month;

    public OperationsByMonth(int month) {
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

        final List<Operation> operations = DataRepository.me().operations().stream()
                .filter(operation -> operation.getMonth().equals(Month.of(month)))
                .toList();

        final double sum = operations.stream().mapToDouble(Operation::getAmount).sum();

        div.addChild(title(String.format("Opérations de %s (%,.2f €)", BankBankConstants.MONTHS[month - 1], sum)));

        div.addChild(new OperationsTable(operations));

        div = new HTMLDiv();
        div.classId("centered-div");
        body.addChild(div);

        div.addChild(new BankTable(operations.stream().map(Operation::getCategory).distinct().toList(), operations));
    }

    private static HTMLDiv title(String title) {
        HTMLDiv div = new HTMLDiv();
        HTMLHeader h2 = new HTMLHeader(2);
        h2.textContent(title);
        div.addChild(h2);
        return div;
    }

}
