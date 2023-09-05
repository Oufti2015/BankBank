package sst.bank.model;

import lombok.Data;

@Data
public class Criteria {
    private String criteria;
    private CriteriaField field;
    private String comment;

    public Criteria(String criteria, CriteriaField field) {
        this.criteria = criteria;
        this.field = field;
    }
}
