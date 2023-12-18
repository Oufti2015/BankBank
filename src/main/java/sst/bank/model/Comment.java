package sst.bank.model;

import com.google.gson.annotations.Expose;
import lombok.Data;
import sst.common.html.HTMLHyperlinks;

import java.time.LocalDate;

@Data
public class Comment {
    public enum Type {
        AMAZON
    }

    @Expose
    private Type type;
    @Expose
    private String operationId;
    @Expose
    private LocalDate executionDate;
    @Expose
    private Double amount;
    @Expose
    private String comment;
    @Expose
    private String link;

    public String hyperlink() {
        HTMLHyperlinks hl = new HTMLHyperlinks().href(link);
        hl.textContent(comment);
        return hl.toString();
    }
}
