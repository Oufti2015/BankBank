package sst.bank.model.repo;

import lombok.Getter;
import sst.bank.model.Category;

import java.util.List;

@Getter
public class CategoryRepository {
    private final List<Category> categories;

    public CategoryRepository(List<Category> categories) {
        this.categories = categories;
    }
}
