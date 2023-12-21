package sst.bank.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.log4j.Log4j2;
import sst.bank.categories.AmazonHook;
import sst.bank.categories.CategoriesDispatcher;
import sst.bank.csv.OperationFileReader;
import sst.bank.gson.conv.GsonLocalDate;
import sst.bank.model.Category;
import sst.bank.model.Operation;
import sst.bank.model.repo.CategoryRepository;
import sst.bank.model.repo.CommentRepository;
import sst.bank.model.repo.DataRepository;
import sst.bank.report.html.*;
import sst.common.file.output.OutputFile;
import sst.common.html.HTML;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Log4j2
public class BankBank {


    public static final String LINE_STRING = "----------------------------------------------------------------";

    public static void main(String[] args) {
        try {
            log.info(LINE_STRING);

            readCategories();
            readComments();
            readOperations(args[0]);
            dispatchCategories();
            listOperations();
            summary();
            report();
            writeComments();
            log.info(LINE_STRING);
        } catch (IOException e) {
            log.error("ERROR", e);
            System.exit(-1);
        }
    }

    public static void readCategories() throws IOException {
        CategoryRepository repo = gsonEngine().fromJson(new String(Files.readAllBytes(Paths.get(BankBankConstants.CATEGORIES_FILE))), CategoryRepository.class);
        repo.calculateEpargne();
        DataRepository.me().addCategories(repo.getCategories());

        for (Category category : DataRepository.me().categories()) {
            if ("Amazon".equals(category.getName())) {
                category.setHook(new AmazonHook());
            }
        }

        log.info(String.format("%4d categories loaded.", DataRepository.me().categories().size()));
    }

    public static void readComments() throws IOException {
        CommentRepository repo = gsonEngine().fromJson(new String(Files.readAllBytes(Paths.get(BankBankConstants.COMMENTS_FILE))), CommentRepository.class);
        DataRepository.me().addComments(repo.getComments());

        log.info(String.format("%4d comments loaded.", DataRepository.me().comments().size()));
    }

    private static void readOperations(String filename) throws IOException {
        OperationFileReader fileReader = new OperationFileReader(filename);
        final List<Operation> operations = fileReader.read();
        DataRepository.me().addOperations(operations);
        log.info(String.format("%4d operations loaded.", DataRepository.me().operations().size()));
    }

    private static void dispatchCategories() {
        CategoriesDispatcher dispatcher = new CategoriesDispatcher();
        dispatcher.dispatch(DataRepository.me().operations());
    }

    private static void listOperations() {
        DataRepository.me().operations().stream().filter(operation -> operation.getCategory() == null).forEach(operation -> log.info(" - " + operation.getId() + " - " + operation.getAmount() + " - " + operation.getTransactionType() + " - " + operation.getCounterpartyAccountNumber() + " - " + operation.getCounterpartyName() + " - " + operation.getDetails()));
    }

    private static void summary() {
        final long countDone = DataRepository.me().operations().stream().filter(operation -> operation.getCategory() != null).count();
        final long countNotDone = DataRepository.me().operations().stream().filter(operation -> operation.getCategory().getName().equals(Category.UNKNOWN)).count();

        log.info(String.format("%4d operations with category", countDone));
        log.info(String.format("%4d operations on unknown category", countNotDone));
        log.info(LINE_STRING);
        for (int month = 1; month <= 12; month++) {
            int finalMonth = month;
            final long count = DataRepository.me().operations().stream()
                    .filter(o -> o.getExecutionDate().getMonthValue() == finalMonth)
                    .count();
            log.info(String.format("%16s : %3d operations", BankBankConstants.MONTHS[month - 1], count));
        }
        log.info(LINE_STRING);
        for (Category category : DataRepository.me().categories()) {
            final long count = DataRepository.me().operations().stream()
                    .filter(o -> o.getCategory().equals(category))
                    .count();
            log.info(String.format("%16s : %3d operations", category.getName(), count));
        }
    }

    private static void report() throws IOException {
        IndexHtml indexHtml = new IndexHtml();
        save(indexHtml, new File(BankBankConstants.OUTPUT_FOLDER + File.separator + "index.html"));
        for (Category category : DataRepository.me().categories()) {
            HTML html = new OperationsByCategory(category);
            save(html, new File(String.format("%s%s%s.html", BankBankConstants.HTML_FOLDER, File.separator, category.getName())));
            for (int month = 1; month <= 12; month++) {
                html = new OperationsByCategoryAndMonth(category, month);
                save(html, new File(String.format("%s%s%s_%d.html", BankBankConstants.HTML_FOLDER, File.separator, category.getName(), month)));
            }
        }
        for (int month = 1; month <= 12; month++) {
            HTML html = new OperationsByMonth(month);
            save(html, new File(String.format("%s%s\\%d.html", BankBankConstants.HTML_FOLDER, File.separator, month)));
        }
        Budget budget = new Budget();
        save(budget, new File(BankBankConstants.HTML_FOLDER + File.separator + "budget.html"));
    }

    private static void save(HTML html, File file) throws IOException {
        log.debug("Saving " + file.getAbsolutePath());
        try (OutputFile outputFile = new OutputFile(file)) {
            outputFile.println(html.toString());
        }
    }

    public static Gson gsonEngine() {
        final GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new GsonLocalDate());
        return builder.create();
    }

    private static void writeComments() {
        CommentRepository repo = new CommentRepository(DataRepository.me().comments());

        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new GsonLocalDate()).excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(repo);
        try (PrintWriter out = new PrintWriter(BankBankConstants.COMMENTS_FILE)) {
            out.println(json);
            log.info("File saved to " + BankBankConstants.COMMENTS_FILE);
        } catch (FileNotFoundException e) {
            log.error("Cannot write to " + BankBankConstants.COMMENTS_FILE, e);
        }
    }
}
