package sst.bank.editor;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import sst.bank.gson.conv.GsonLocalDate;
import sst.bank.main.BankBankConstants;
import sst.bank.model.Category;
import sst.bank.model.repo.DataRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Log4j2
public class CategoriesEditorApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        log.info("Starting CategoriesEditorApp");

        // Load categories from file
        loadCategories();

        // Load FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/categories_editor.fxml"));
        Scene scene = new Scene(loader.load(), 1400, 900);

        primaryStage.setTitle("BankBank - Categories Editor");
        primaryStage.setScene(scene);

        // Set icon if available
        try {
            Image icon = new Image(getClass().getResourceAsStream("/icon.png"));
            primaryStage.getIcons().add(icon);
        } catch (Exception e) {
            log.debug("Icon not found");
        }

        primaryStage.show();
        log.info("CategoriesEditorApp started successfully");
    }

    private void loadCategories() throws IOException {
        log.info("Loading categories from " + BankBankConstants.CATEGORIES_FILE);
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new GsonLocalDate())
                    .create();

            String json = new String(Files.readAllBytes(Paths.get(BankBankConstants.CATEGORIES_FILE)));
            List<Category> categories = gson.fromJson(json, new TypeToken<List<Category>>(){}.getType());

            DataRepository.me().addCategories(categories);
            log.info(String.format("%d categories loaded.", DataRepository.me().categories().size()));
        } catch (IOException e) {
            log.error("Error loading categories", e);
            throw e;
        }
    }

    public static void main(String[] args) {
        log.info("Launching CategoriesEditorApp");
        launch(args);
    }
}
