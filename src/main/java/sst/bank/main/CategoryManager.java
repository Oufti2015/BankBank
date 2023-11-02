package sst.bank.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import sst.bank.controllers.CategoryManagerController;

import java.net.URL;

@Log4j2
public class CategoryManager extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        URL fxml = CategoryManager.class.getResource("/categories.fxml");
        loader.setLocation(fxml);

        Parent root = loader.load();
        CategoryManagerController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);

        primaryStage.setTitle("Category Manager");
        primaryStage.setScene(new Scene(root, (1920 / 3) * 2, 540));
        primaryStage.setMaximized(false);
        primaryStage.show();
        log.info("Ready !");
    }

    @Override
    public void init() throws Exception {
        super.init();
        BankBank.readCategories();
    }
}
