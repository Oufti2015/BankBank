package sst.bank.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import sst.bank.model.Category;

@Log4j2
public class CategoryManagerController {
    private Stage primaryStage;
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

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
