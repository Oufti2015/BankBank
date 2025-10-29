package sst.bank.main;

import java.io.File;

public class BankBankConstants {
    public static final String BANKBANK_CSS = "bankbank.css";
    public static final String BANKBANK_2_CSS = "BankBank2.css";

    private BankBankConstants() {
    }

    public static final String BANK_FOLDER = "c:\\zt974\\bank\\";
    public static final String OUTPUT_FOLDER = BANK_FOLDER + File.separator + "output";
    public static final String DATA_FOLDER = BANK_FOLDER + File.separator + "data";
    public static final String HTML_FOLDER = OUTPUT_FOLDER + File.separator + "html";
    public static final String CATEGORIES_FILE = DATA_FOLDER + File.separator + "categories.json";
    public static final String COMMENTS_FILE = DATA_FOLDER + File.separator + "comments.json";
    public static final String BENEF_FILE = DATA_FOLDER + File.separator + "beneficiaries.properties";
    public static final String OP_CAT_FILE = DATA_FOLDER + File.separator + "op-cat.properties";
    public static final String INCOME_TITLE = "Recettes";
    public static final String EXPENSES_TITLE = "Dépenses";
    public static final String SAVING_TITLE = "Epargne";
    public static final String SUMMARY_TITLE = "Résumé";
    public static final String[] MONTHS = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre", "Total"};

    public static final String FORMAT_DOUBLE_TABLE = "%,.0f";
    public static final String FORMAT_INTEGER_TABLE = "%d €";
    public static final String FORMAT_DOUBLE_OPERATION = "%,.2f";
}
