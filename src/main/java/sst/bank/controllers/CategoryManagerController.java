package sst.bank.controllers;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import lombok.extern.log4j.Log4j2;
import sst.bank.gson.conv.GsonLocalDate;
import sst.bank.main.BankBankConstants;
import sst.bank.model.Category;
import sst.bank.model.Criteria;
import sst.bank.model.CriteriaField;
import sst.bank.model.repo.CategoryRepository;
import sst.bank.model.repo.DataRepository;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;

@Log4j2
public class CategoryManagerController {
    public static final String NO_CATEGORY_SELECTED = "No category selected...";
    @FXML
    private ComboBox<Category> categoryComboBox;
    @FXML
    private TextField januaryBudget;
    @FXML
    private TextField februaryBudget;
    @FXML
    private TextField marchBudget;
    @FXML
    private TextField aprilBudget;
    @FXML
    private TextField mayBudget;
    @FXML
    private TextField juneBudget;
    @FXML
    private TextField julyBudget;
    @FXML
    private TextField augustBudget;
    @FXML
    private TextField septemberBudget;
    @FXML
    private TextField octoberBudget;
    @FXML
    private TextField novemberBudget;
    @FXML
    private TextField decemberBudget;
    @FXML
    private TextField criteriaNameTextField;
    @FXML
    private ComboBox<CriteriaField> criteriaTypeComboBox;
    @FXML
    private TextField priorityTextField;
    @FXML
    private RadioButton incomeRadioButton;
    @FXML
    private RadioButton savingRadioButton;
    @FXML
    private TextField categoryNameTextField;

    @FXML
    public void initialize() {
        log.info("[CategoryManagerController.initialize]");
        categoryComboBox.setItems(categories());
        categoryComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> fillCategoryInfo());

        criteriaTypeComboBox.setItems(criteriaTypes());
        priorityTextField.setText("50");
    }

    private ObservableList<CriteriaField> criteriaTypes() {
        return FXCollections.observableArrayList(CriteriaField.values());
    }

    private void fillCategoryInfo() {
        final Category category = categoryComboBox.getSelectionModel().getSelectedItem();
        log.info("Category selected : " + category);

        categoryNameTextField.setText(category.getName());

        final Double[] budget = category.getBudget();
        int i = 0;
        januaryBudget.setText(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, budget[i++]));
        februaryBudget.setText(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, budget[i++]));
        marchBudget.setText(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, budget[i++]));
        aprilBudget.setText(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, budget[i++]));
        mayBudget.setText(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, budget[i++]));
        juneBudget.setText(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, budget[i++]));
        julyBudget.setText(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, budget[i++]));
        augustBudget.setText(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, budget[i++]));
        septemberBudget.setText(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, budget[i++]));
        octoberBudget.setText(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, budget[i++]));
        novemberBudget.setText(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, budget[i++]));
        decemberBudget.setText(String.format(BankBankConstants.FORMAT_DOUBLE_TABLE, budget[i]));

        priorityTextField.setText(category.getPriority().toString());
        incomeRadioButton.setSelected(category.getIncome());
        savingRadioButton.setSelected(category.getSavings());
    }

    private ObservableList<Category> categories() {
        return FXCollections.observableArrayList(DataRepository.me().categories().stream().sorted().toList());
    }

    @FXML
    private void addCriteriaAction() {
        boolean error = false;
        final Category category = categoryComboBox.getSelectionModel().getSelectedItem();
        if (category == null) {
            log.error(NO_CATEGORY_SELECTED);
            error = true;
        }
        final String criteriaName = criteriaNameTextField.getText();
        if (Strings.isNullOrEmpty(criteriaName)) {
            log.error("Please fill criteria name !");
            error = true;
        }
        final CriteriaField criteriaType = criteriaTypeComboBox.getSelectionModel().getSelectedItem();
        if (criteriaType == null) {
            log.error("Please fill criteria type !");
            error = true;
        }
        if (!error) {
            Criteria criteria = new Criteria(criteriaName, criteriaType);
            log.info(criteria);
            category.getCriteria().add(criteria);
        }
    }

    @FXML
    public void applyBudgetAction() {
        final Category category = categoryComboBox.getSelectionModel().getSelectedItem();
        if (category == null) {
            log.error(NO_CATEGORY_SELECTED);
        } else {
            Double[] budget = category.getBudget();
            if (budget == null) {
                budget = new Double[12];
                category.setBudget(budget);
            }
            int i = 0;
            budget[i++] = parseDouble(januaryBudget.getText());
            budget[i++] = parseDouble(februaryBudget.getText());
            budget[i++] = parseDouble(marchBudget.getText());
            budget[i++] = parseDouble(aprilBudget.getText());
            budget[i++] = parseDouble(mayBudget.getText());
            budget[i++] = parseDouble(juneBudget.getText());
            budget[i++] = parseDouble(julyBudget.getText());
            budget[i++] = parseDouble(augustBudget.getText());
            budget[i++] = parseDouble(septemberBudget.getText());
            budget[i++] = parseDouble(octoberBudget.getText());
            budget[i++] = parseDouble(novemberBudget.getText());
            budget[i] = parseDouble(decemberBudget.getText());
        }
    }

    private Double parseDouble(String text) {
        String result = text.replace(".", "");
        result = result.replace(",", ".");
        return Double.parseDouble(result);
    }

    @FXML
    private void updateCategoryAction() {
        log.info("updateCategoryAction");
        boolean error = false;
        final Category category = categoryComboBox.getSelectionModel().getSelectedItem();
        if (category == null) {
            log.error(NO_CATEGORY_SELECTED);
        } else {
            final String name = categoryNameTextField.getText();
            if (Strings.isNullOrEmpty(name)) {
                log.error("Please fill category name !");
                error = true;
            }
            final String priorityText = priorityTextField.getText();
            if (Strings.isNullOrEmpty(priorityText)) {
                log.error("Please fill priority !");
                error = true;
            }
            final boolean income = incomeRadioButton.isSelected();
            final boolean saving = savingRadioButton.isSelected();
            if (!error) {
                category.setName(name);
                category.setPriority(Integer.parseInt(priorityText));
                category.setIncome(income);
                category.setSavings(saving);
            }
        }
    }

    @FXML
    private void saveAction() {

        CategoryRepository repo = new CategoryRepository(DataRepository.me().categories());

        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new GsonLocalDate()).excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(repo);
        try (PrintWriter out = new PrintWriter(BankBankConstants.CATEGORIES_FILE)) {
            out.println(json);
            log.info("File saved to " + BankBankConstants.CATEGORIES_FILE);
        } catch (FileNotFoundException e) {
            log.error("Cannot write to " + BankBankConstants.CATEGORIES_FILE, e);
        }
    }
}
