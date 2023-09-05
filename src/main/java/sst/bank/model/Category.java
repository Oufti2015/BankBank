package sst.bank.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Categorie {
    private String name;
    private final List<Criteria> criteria = new ArrayList<>();
}
