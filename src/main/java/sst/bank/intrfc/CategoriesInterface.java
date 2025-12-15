package sst.bank.intrfc;

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
import java.util.Optional;

@Log4j2
public class CategoriesInterface {
    private static CategoriesInterface instance;
    public static CategoriesInterface getInstance() {
        if (instance == null) {
            try {
                instance = new CategoriesInterface();
            } catch (IOException e) {
                log.error("Cannot load categories", e);
                System.exit(1);
            }
        }
        return instance;
    }

    private CategoriesInterface() throws IOException {
        BankBank.readCategories();
    }

    public List<Category> readCategories() throws IOException {
        return DataRepository.me().categories();
    }

    public Optional<Category> category(String name) {
        return DataRepository.me().category(name);
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
