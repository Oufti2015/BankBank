package sst.bank.report.html;

import sst.common.html.HTMLFooter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Footer extends HTMLFooter {
    public Footer() {
        super("Généré le " + LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        id("footer");
    }
}
