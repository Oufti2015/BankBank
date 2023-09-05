package sst.bank.model;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString(exclude = {"valueDate", "currency", "accountNumber", "status", "reasonForRefusal"})
public class Operation {
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
}
