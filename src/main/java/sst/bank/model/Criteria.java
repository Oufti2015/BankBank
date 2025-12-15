package sst.bank.model;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;

@Data
public class Criteria {
    @Expose
    private String criteria;
    @Expose
    private CriteriaField field;
    @Expose
    private String comment;
    @Expose
    private AvailabilityPeriod period;

    public Criteria() {
    }

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
