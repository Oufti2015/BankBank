package sst.bank.editor;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.extern.log4j.Log4j2;
import sst.bank.gson.conv.GsonLocalDate;
import sst.bank.main.BankBankConstants;
import sst.bank.model.Category;
import sst.bank.model.Criteria;
import sst.bank.model.CriteriaField;
import sst.bank.model.repo.DataRepository;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Optional;

@Log4j2
public class CategoriesEditorController {
    public static final String NO_CATEGORY_SELECTED = "No category selected...";
    public static final String[] MONTHS = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};

    @FXML
    private ComboBox<Category> categoryComboBox;
    @FXML
    private TextField categoryNameTextField;
    @FXML
    private TextField priorityTextField;
    @FXML
    private CheckBox incomeCheckBox;
    @FXML
    private CheckBox savingCheckBox;
    @FXML
    private ListView<Criteria> criteriaListView;
    @FXML
    private TextField criteriaSearchTextField;
    @FXML
    private ComboBox<CriteriaField> criteriaFieldComboBox;
    @FXML
    private TextField criteriaCommentTextField;
    @FXML
    private Button addCriteriaButton;
    @FXML
    private Button removeCriteriaButton;
    @FXML
    private VBox budgetVBox;
    @FXML
    private Button createCategoryButton;
    @FXML
    private Button updateCategoryButton;
    @FXML
    private Button deleteCategoryButton;
    @FXML
    private Button saveCategoriesButton;

    private final TextField[] budgetTextFields = new TextField[12];

    @FXML
    public void initialize() {
        log.info("[CategoriesEditorController.initialize]");

        categoryComboBox.setItems(categories());
        categoryComboBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> fillCategoryInfo());

        criteriaFieldComboBox.setItems(FXCollections.observableArrayList(CriteriaField.values()));

        setupBudgetFields();
        setupCriteriaListView();

        createCategoryButton.setOnAction(e -> createCategoryAction());
        updateCategoryButton.setOnAction(e -> updateCategoryAction());
        deleteCategoryButton.setOnAction(e -> deleteCategoryAction());
        addCriteriaButton.setOnAction(e -> addCriteriaAction());
        removeCriteriaButton.setOnAction(e -> removeCriteriaAction());
        saveCategoriesButton.setOnAction(e -> saveCategoriesAction());

        priorityTextField.setText("50");
    }

    private void setupBudgetFields() {
        budgetVBox.getChildren().clear();

        for (int i = 0; i < 12; i++) {
            HBox monthBox = new HBox(10);
            monthBox.setAlignment(Pos.CENTER_LEFT);
            monthBox.setPrefHeight(30);

            Label monthLabel = new Label(MONTHS[i]);
            monthLabel.setPrefWidth(100);

            TextField budgetField = new TextField();
            budgetField.setPrefWidth(150);
            budgetTextFields[i] = budgetField;

            monthBox.getChildren().addAll(monthLabel, budgetField);
            budgetVBox.getChildren().add(monthBox);
        }
    }

    private void setupCriteriaListView() {
        criteriaListView.setCellFactory(param -> new CriteriaListCell());
    }

    private ObservableList<Category> categories() {
        return FXCollections.observableArrayList(
                DataRepository.me().categories().stream().sorted().toList());
    }

    private void fillCategoryInfo() {
        final Category category = categoryComboBox.getSelectionModel().getSelectedItem();
        if (category == null) {
            clearCategoryInfo();
            return;
        }

        log.info("Category selected: {}", category);

        categoryNameTextField.setText(category.getName());
        priorityTextField.setText(category.getPriority().toString());
        incomeCheckBox.setSelected(category.getIncome() != null && category.getIncome());
        savingCheckBox.setSelected(category.getSavings() != null && category.getSavings());

        fillBudgetFields(category.getBudget());
        fillCriteriaListView(category.getCriteria());
    }

    private void clearCategoryInfo() {
        categoryNameTextField.clear();
        priorityTextField.setText("50");
        incomeCheckBox.setSelected(false);
        savingCheckBox.setSelected(false);
        for (TextField tf : budgetTextFields) {
            tf.clear();
        }
        criteriaListView.setItems(FXCollections.observableArrayList());
    }

    private void fillBudgetFields(Double[] budget) {
        if (budget == null) {
            budget = new Double[12];
        }
        for (int i = 0; i < 12; i++) {
            if (budget[i] != null) {
                budgetTextFields[i].setText(String.format("%.2f", budget[i]));
            } else {
                budgetTextFields[i].clear();
            }
        }
    }

    private void fillCriteriaListView(java.util.List<Criteria> criteriaList) {
        criteriaListView.setItems(FXCollections.observableArrayList(criteriaList));
    }

    @FXML
    private void createCategoryAction() {
        log.info("Creating new category");

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create New Category");
        dialog.setHeaderText("Enter the name for the new category:");
        dialog.setContentText("Category name:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && !Strings.isNullOrEmpty(result.get())) {
            String categoryName = result.get();

            Category newCategory = new Category();
            newCategory.setName(categoryName);
            newCategory.setPriority(50);
            newCategory.setIncome(false);
            newCategory.setSavings(false);
            newCategory.setBudget(new Double[12]);

            DataRepository.me().categories().add(newCategory);
            categoryComboBox.setItems(categories());
            categoryComboBox.getSelectionModel().select(newCategory);
            log.info("Category created: {}", categoryName);

            showInfoAlert("Category '" + categoryName + "' created successfully!");
        }
    }

    @FXML
    private void updateCategoryAction() {
        log.info("Updating category");
        boolean error = false;

        final Category category = categoryComboBox.getSelectionModel().getSelectedItem();
        if (category == null) {
            showErrorAlert(NO_CATEGORY_SELECTED);
            return;
        }

        final String name = categoryNameTextField.getText();
        if (Strings.isNullOrEmpty(name)) {
            showErrorAlert("Please fill category name!");
            error = true;
        }

        final String priorityText = priorityTextField.getText();
        if (Strings.isNullOrEmpty(priorityText)) {
            showErrorAlert("Please fill priority!");
            error = true;
        } else {
            try {
                Integer.parseInt(priorityText);
            } catch (NumberFormatException e) {
                showErrorAlert("Priority must be a number!");
                error = true;
            }
        }

        if (!error) {
            category.setName(name);
            category.setPriority(Integer.parseInt(priorityText));
            category.setIncome(incomeCheckBox.isSelected());
            category.setSavings(savingCheckBox.isSelected());

            Double[] budget = new Double[12];
            for (int i = 0; i < 12; i++) {
                String text = budgetTextFields[i].getText();
                if (!Strings.isNullOrEmpty(text)) {
                    try {
                        budget[i] = parseDouble(text);
                    } catch (NumberFormatException e) {
                        log.warn("Invalid budget value for month {}: {}", i, text);
                        budget[i] = 0.0;
                    }
                }
            }
            category.setBudget(budget);

            //categoryComboBox.refresh();
            showInfoAlert("Category updated successfully!");
            log.info("Category updated: {}", name);
        }
    }

    @FXML
    private void deleteCategoryAction() {
        final Category category = categoryComboBox.getSelectionModel().getSelectedItem();
        if (category == null) {
            showErrorAlert(NO_CATEGORY_SELECTED);
            return;
        }

        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirm Delete");
        confirmDialog.setHeaderText("Delete Category");
        confirmDialog.setContentText("Are you sure you want to delete '" + category.getName() + "'?");

        Optional<ButtonType> result = confirmDialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DataRepository.me().categories().remove(category);
            categoryComboBox.setItems(categories());
            clearCategoryInfo();
            showInfoAlert("Category deleted successfully!");
            log.info("Category deleted: {}", category.getName());
        }
    }

    @FXML
    private void addCriteriaAction() {
        log.info("Adding criteria");
        final Category category = categoryComboBox.getSelectionModel().getSelectedItem();
        if (category == null) {
            showErrorAlert(NO_CATEGORY_SELECTED);
            return;
        }

        final String criteriaText = criteriaSearchTextField.getText();
        if (Strings.isNullOrEmpty(criteriaText)) {
            showErrorAlert("Please fill criteria value!");
            return;
        }

        final CriteriaField criteriaField = criteriaFieldComboBox.getSelectionModel().getSelectedItem();
        if (criteriaField == null) {
            showErrorAlert("Please select criteria field!");
            return;
        }

        Criteria newCriteria = new Criteria(criteriaText, criteriaField);
        String comment = criteriaCommentTextField.getText();
        if (!Strings.isNullOrEmpty(comment)) {
            newCriteria.setComment(comment);
        }

        category.getCriteria().add(newCriteria);
        fillCriteriaListView(category.getCriteria());

        criteriaSearchTextField.clear();
        criteriaCommentTextField.clear();
        criteriaFieldComboBox.getSelectionModel().clearSelection();

        showInfoAlert("Criteria added successfully!");
        log.info("Criteria added: {}", newCriteria);
    }

    @FXML
    private void removeCriteriaAction() {
        log.info("Removing criteria");
        final Category category = categoryComboBox.getSelectionModel().getSelectedItem();
        if (category == null) {
            showErrorAlert(NO_CATEGORY_SELECTED);
            return;
        }

        final Criteria selectedCriteria = criteriaListView.getSelectionModel().getSelectedItem();
        if (selectedCriteria == null) {
            showErrorAlert("Please select a criteria to remove!");
            return;
        }

        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirm Delete");
        confirmDialog.setHeaderText("Delete Criteria");
        confirmDialog.setContentText("Are you sure you want to remove this criteria?");

        Optional<ButtonType> result = confirmDialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            category.getCriteria().remove(selectedCriteria);
            fillCriteriaListView(category.getCriteria());
            showInfoAlert("Criteria removed successfully!");
            log.info("Criteria removed: {}", selectedCriteria);
        }
    }

    @FXML
    private void saveCategoriesAction() {
        log.info("Saving categories");
        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(LocalDate.class, new GsonLocalDate())
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();

            String json = gson.toJson(DataRepository.me().categories());
            try (PrintWriter out = new PrintWriter(BankBankConstants.CATEGORIES_FILE)) {
                out.println(json);
                log.info("File saved to {}", BankBankConstants.CATEGORIES_FILE);
                showInfoAlert("Categories saved successfully to " +
                        BankBankConstants.CATEGORIES_FILE);
            }
        } catch (FileNotFoundException e) {
            log.error("Cannot write to {}", BankBankConstants.CATEGORIES_FILE, e);
            showErrorAlert("Cannot save to " + BankBankConstants.CATEGORIES_FILE + "\n" + e.getMessage());
        }
    }

    private Double parseDouble(String text) {
        String result = text.replace(".", "");
        result = result.replace(",", ".");
        return Double.parseDouble(result);
    }

    private void showInfoAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showErrorAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private static class CriteriaListCell extends ListCell<Criteria> {
        @Override
        protected void updateItem(Criteria item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(item.getCriteria())
                        .append(" (").append(item.getField()).append(")");
                if (!Strings.isNullOrEmpty(item.getComment())) {
                    sb.append(" - ").append(item.getComment());
                }
                setText(sb.toString());
            }
        }
    }
}
