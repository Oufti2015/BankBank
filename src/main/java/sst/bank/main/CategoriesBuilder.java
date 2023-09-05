package sst.bank.csv;

import sst.bank.model.Categorie;
import sst.bank.model.Criteria;
import sst.bank.model.CriteriaField;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoriesBuilder {
    public CategoriesBuilder() {
    }

    public List<Categorie> build() {
        List<Categorie> result = new ArrayList<>();
        counterpartyAccount(result, "Crédit", "BE36774229214981");
        return result;
    }

    private void counterpartyAccount(List<Categorie> categories, String categoryName, String criteria) {
        final Optional<Categorie> first = categories.stream().filter(c -> categoryName.equals(c.getName())).findFirst();
        Categorie categorie;
        if (first.isEmpty()) {
            categorie = new Categorie();
            categorie.setName(categoryName);
            categories.add(categorie);
        } else {
            categorie = first.get();
        }
        categorie.getCriteria().add(new Criteria(criteria, CriteriaField.COUNTERPARTY_ACCOUNT));
    }
}
