package sst.bank.services;

import sst.bank.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoriesService {
    List<Category> readCategories();

    Category category(String name) throws CategoriesServiceException;

    Category createCategory(Category category);

    Category updateCategory(String name, Category category) throws CategoriesServiceException;

    Category deleteCategory(String name) throws CategoriesServiceException;
}
