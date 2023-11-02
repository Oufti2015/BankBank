package sst.bank.model;

public enum CriteriaField {
    COUNTERPARTY_ACCOUNT("Counterparty Account"),
    COUNTERPARTY_NAME("Counterparty Name"),
    COMMUNICATION("Communication"),
    DETAIL("Detail"),
    TRANSACTION_TYPE("Transaction Type"),
    ALL("All");

    private final String name;

    CriteriaField(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
