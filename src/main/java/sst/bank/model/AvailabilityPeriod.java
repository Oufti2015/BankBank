package sst.bank.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AvailabilityPeriod implements Comparable<AvailabilityPeriod> {
    private LocalDate startDate;
    private LocalDate endDate;

    @Override
    public int compareTo(AvailabilityPeriod o) {
        int result = startDate.compareTo(o.startDate);
        if (result == 0) {
            result = endDate.compareTo(o.endDate);
        }
        return result;
    }
}
