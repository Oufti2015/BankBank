package sst.bank.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sst.bank.gson.conv.GsonLocalDate;
import sst.bank.model.Category;
import sst.bank.model.Criteria;
import sst.bank.model.CriteriaField;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class GSONTest {

    public static final String TEST_CATEGORY_1 = "Test Category 1";
    public static final String TEST_CATEGORY_2 = "Test Category 2";
    public static final String CRITERIA_TEXT = "CRITERIA ";

    private static Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        category.setIncome(false);
        category.setSavings(false);
        category.setPriority(50);
        for (int i = 0; i < 12; i++) {
            category.getBudget()[i] = 100.0 + i;
        }
        for (int i = 0; i < 12; i++) {
            Criteria criteria = null;

            if (i<10) {
                criteria = new Criteria(CRITERIA_TEXT + i, i < 6 ? CriteriaField.DETAIL : CriteriaField.COMMUNICATION);
            } else {
                criteria = new Criteria(LocalDate.of(2024, 1 + i, 1), LocalDate.of(2024, 1 + i, 28), CRITERIA_TEXT + i);
            }
            category.getCriteria().add(criteria);
        }

        return category;
    }

    @Test
    public void testGSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new GsonLocalDate()).excludeFieldsWithoutExposeAnnotation().create();
        List<Category> categories = new ArrayList<>();

        categories.add(createCategory(TEST_CATEGORY_1));
        categories.add(createCategory(TEST_CATEGORY_2));

        String json = gson.toJson(categories);
        // System.out.println(json);

        Assertions.assertTrue(json.contains(TEST_CATEGORY_1));
        Assertions.assertTrue(json.contains(TEST_CATEGORY_2));
        for (int i = 0; i < 12; i++) {
            Assertions.assertTrue(json.contains(CRITERIA_TEXT + i));
        }
        for (int i = 10; i < 12; i++) {
            Assertions.assertTrue(json.contains("startDate"));
            Assertions.assertTrue(json.contains("endDate"));
        }
    }
}
