package sst.bank.report.html;

import sst.bank.model.Category;
import sst.bank.model.Operation;
import sst.common.html.table.HTMLTable;
import sst.common.html.table.HTMLTableHeaderRow;
import sst.common.html.table.HTMLTableRow;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

public class OperationsTable extends HTMLTable {
    private static final String[] HEADERS = {"Id", "Date", "Catégorie", "Montant", "Type de Transaction", "Contrepartie", "Nom de Contrepartie", "Communication", "Détails"};
    private final List<Category> categories;
    private final List<Operation> operations;

    public OperationsTable(List<Category> categories, List<Operation> operations) {
        super();
        this.categories = categories;
        this.operations = operations.stream().sorted((o1, o2) -> o1.getExecutionDate().compareTo(o2.getExecutionDate())).toList();

        createTable();
    }

    private void createTable() {
        this.classId("operationstable");
        HTMLTableHeaderRow row = this.newHeader();
        for (String s : HEADERS) {
            row.newHead(s);
        }

        int i = 0;
        for (Operation operation : operations) {
            HTMLTableRow htmlTableRow = this.newRow();
            htmlTableRow.newCell(operation.getId());
            htmlTableRow.newCell(operation.getExecutionDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
            htmlTableRow.newCell(operation.getCategory().getName());
            htmlTableRow.newCell(String.format("%,.2f", operation.getAmount()));
            htmlTableRow.newCell(operation.getTransactionType());
            htmlTableRow.newCell(operation.getCounterpartyAccountNumber());
            htmlTableRow.newCell(operation.getCounterpartyName());
            htmlTableRow.newCell(operation.getCommunication());
            htmlTableRow.newCell(operation.getDetails());
        }
    }
}
