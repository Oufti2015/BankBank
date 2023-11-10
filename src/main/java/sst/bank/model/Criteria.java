package sst.bank.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Criteria {
    private String criteria;
    private CriteriaField field;
    private String comment;
    private AvailabilityPeriod period;

    public Criteria(String criteria, CriteriaField field) {
        this.criteria = criteria;
        this.field = field;
    }

    public Criteria(LocalDate startDate, LocalDate endDate, String comment) {
        this.field = CriteriaField.VALUE_DATE;
        this.period = new AvailabilityPeriod();
        this.period.setStartDate(startDate);
        this.period.setEndDate(endDate);
        this.comment = comment;
    }
}
