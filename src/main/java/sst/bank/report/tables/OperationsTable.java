package sst.bank.report.tables;

import com.google.common.base.Strings;
import sst.bank.main.BankBankConstants;
import sst.bank.model.Operation;
import sst.bank.model.repo.DataRepository;
import sst.bank.report.html.DetailsPrinter;
import sst.common.html.table.HTMLTable;
import sst.common.html.table.HTMLTableHeaderRow;
import sst.common.html.table.HTMLTableRow;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

public class OperationsTable extends HTMLTable {
    private static final String[] HEADERS = {"Id", "Date d'Exécution", "Date valeur", "Catégorie", "Commentaire", "Montant", "Type de Transaction", "Contrepartie", "Communication", "Détails"};
    private final List<Operation> operations;

    public OperationsTable(List<Operation> operations) {
        super();
        this.operations = operations.stream().sorted().toList();

        createTable();
    }

    private void createTable() {
        this.classId("operationstable");
        HTMLTableHeaderRow row = this.newHeader();
        for (String s : HEADERS) {
            row.newHead(s);
        }

        DetailsPrinter dp = new DetailsPrinter();
        for (Operation operation : operations) {
            HTMLTableRow htmlTableRow = this.newRow();
            htmlTableRow.newCell(Strings.isNullOrEmpty(operation.getId()) ? "---" : operation.getId());
            htmlTableRow.newCell(operation.getExecutionDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
            htmlTableRow.newCell(operation.getValueDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
            htmlTableRow.newCell(operation.getCategory().getName());
            final String comment = Strings.isNullOrEmpty(operation.getCriteria().getComment()) ? operation.getCriteria().getCriteria() : operation.getCriteria().getComment();
            comment(htmlTableRow, operation.getCounterpartyName(), operation.getCounterpartyAccountNumber(), comment);
            htmlTableRow.newCell(String.format(BankBankConstants.FORMAT_DOUBLE_OPERATION, operation.getAmount()));
            htmlTableRow.newCell(operation.getTransactionType());
            counterparty(htmlTableRow, operation.getCounterpartyName(), operation.getCounterpartyAccountNumber());
            htmlTableRow.newCell(operation.getCommunication());
            htmlTableRow.newCell(dp.format(operation.getDetails()));
        }
    }

    private static void comment(HTMLTableRow htmlTableRow, String name, String account, String comment) {
        String beneficiary = (String) DataRepository.me().beneficiaries().get(account);
        if (beneficiary == null) {
            beneficiary = name;
        }
        beneficiary = Strings.isNullOrEmpty(beneficiary) ? "&nbsp;" : beneficiary;
        String text = Strings.isNullOrEmpty(comment) ? beneficiary : comment;

        htmlTableRow.newCell("<B>" + text + "</B>");
    }

    private static void counterparty(HTMLTableRow htmlTableRow, String name, String account) {
        String beneficiary = (String) DataRepository.me().beneficiaries().get(account);
        if (beneficiary == null) {
            beneficiary = name;
        }
        htmlTableRow.newCell(String.format("<b>%s</b><br>%s", beneficiary, account));
    }
}
