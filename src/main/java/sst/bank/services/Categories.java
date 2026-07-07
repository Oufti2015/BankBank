package sst.bank.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.log4j.Log4j2;
import sst.bank.gson.conv.GsonLocalDate;
import sst.bank.main.BankBank;
import sst.bank.main.BankBankConstants;
import sst.bank.model.Category;
import sst.bank.model.repo.DataRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@Log4j2
public class Categories implements CategoriesService {
    private static Categories instance;
    public static Categories getInstance() {
        if (instance == null) {
            try {
                instance = new Categories();
            } catch (CategoriesServiceException e) {
                log.error("Cannot load categories", e);
                System.exit(1);
            }
        }
        return instance;
    }

    private Categories() throws CategoriesServiceException {
        try {
            BankBank.readCategories();
        } catch (IOException e) {
            throw new CategoriesServiceException("Cannot read Categories", e);
        }
    }

    @Override
    public List<Category> readCategories() {
        return DataRepository.me().categories();
    }

    @Override
    public Category category(String name) throws CategoriesServiceException {
        return DataRepository.me().category(name).orElseThrow(() -> new CategoriesServiceException("Category not found"));
    }

    @Override
    public Category createCategory(Category category) {
        DataRepository.me().categories().add(category);
        return category;
    }

    @Override
    public Category updateCategory(String name, Category category) throws CategoriesServiceException {
        Category ourCategory = DataRepository.me().category(name).orElseThrow(() -> new CategoriesServiceException("Category not found"));
        ourCategory.setName(category.getName());
        ourCategory.setIncome(category.getIncome());
        ourCategory.setSavings(category.getSavings());
        ourCategory.setPriority(category.getPriority());
        ourCategory.setBudget(category.getBudget());
        return category;
    }

    @Override
    public Category deleteCategory(String name) throws CategoriesServiceException {
        Category ourCategory = DataRepository.me().category(name).orElseThrow(() -> new CategoriesServiceException("Category not found"));
        DataRepository.me().categories().remove(ourCategory);
        return ourCategory;
    }

    public void save() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new GsonLocalDate()).excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(DataRepository.me().categories());
        try (PrintWriter out = new PrintWriter(BankBankConstants.CATEGORIES_FILE)) {
            out.println(json);
            log.info("File saved to " + BankBankConstants.CATEGORIES_FILE);
        } catch (FileNotFoundException e) {
            log.error("Cannot write to " + BankBankConstants.CATEGORIES_FILE, e);
        }
    }
}
