package sst.bank.report.html;

import com.google.common.base.Strings;
import sst.bank.main.BankBankConstants;
import sst.bank.model.Operation;
import sst.common.html.table.HTMLTable;
import sst.common.html.table.HTMLTableHeaderRow;
import sst.common.html.table.HTMLTableRow;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Comparator;
import java.util.List;

public class OperationsTable extends HTMLTable {
    private static final String[] HEADERS = {/*"Id", */"Date d'Exécution", "Date valeur", "Catégorie", "Commentaire", "Montant", "Type de Transaction", "Contrepartie", "Nom de Contrepartie", "Communication", "Détails"};
    private final List<Operation> operations;

    public OperationsTable(List<Operation> operations) {
        super();
        this.operations = operations.stream().sorted(Comparator.comparing(Operation::getExecutionDate)).toList();

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
            htmlTableRow.newCell(operation.getExecutionDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
            htmlTableRow.newCell(operation.getValueDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
            htmlTableRow.newCell(operation.getCategory().getName());
            final String comment = operation.getCriteria().getComment();
            htmlTableRow.newCell(Strings.isNullOrEmpty(comment) ? "&nbsp;" : comment);
            htmlTableRow.newCell(String.format(BankBankConstants.FORMAT_DOUBLE_OPERATION, operation.getAmount()));
            htmlTableRow.newCell(operation.getTransactionType());
            htmlTableRow.newCell(operation.getCounterpartyAccountNumber());
            htmlTableRow.newCell(operation.getCounterpartyName());
            htmlTableRow.newCell(operation.getCommunication());
            htmlTableRow.newCell(dp.format(operation.getDetails()));
        }
    }
}
