package sst.bank.model;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.Month;

@Data
@ToString(exclude = {"valueDate", "currency", "accountNumber", "status", "reasonForRefusal"})
public class Operation implements Comparable<Operation> {
    private String id;
    private LocalDate executionDate;
    private LocalDate valueDate;
    private Double amount;
    private String currency;
    private String accountNumber;
    private String transactionType;
    private String counterpartyAccountNumber;
    private String counterpartyName;
    private String communication;
    private String details;
    private String status;
    private String reasonForRefusal;

    private Category category;
    private Criteria criteria;

    private Month month;


    @Override
    public int compareTo(Operation o) {
        if (this.getExecutionDate().isEqual(o.getExecutionDate())) {
            return this.getValueDate().compareTo(o.getValueDate());
        }
        return this.getExecutionDate().compareTo(o.getExecutionDate());
    }
}
