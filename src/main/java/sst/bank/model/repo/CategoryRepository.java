package sst.bank.model.repo;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import sst.bank.model.Category;

import java.util.List;

@Getter
@Log4j2
public class CategoryRepository {
    private final List<Category> categories;

    public CategoryRepository(List<Category> categories) {
        this.categories = categories;
        calculateEpargne();
    }

    public void calculateEpargne() {
        Category saving = null;
        for (int month = 0; month < 12; month++) {
            Double sum = 0.00;
            for (Category category : categories) {
                if (category.getSavings()) {
                    saving = category;
                    continue;
                }
                sum += category.budget(month);
            }
            if (saving != null) {
                saving.getBudget()[month] = (sum * -1);
                log.debug(String.format("%d : %,.2f", month, sum));
            }
        }
    }
}
