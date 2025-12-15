package sst.bank.model;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AvailabilityPeriod implements Comparable<AvailabilityPeriod> {
    @Expose
    private LocalDate startDate;
    @Expose
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
