package sst.bank.report.tab;

import lombok.extern.log4j.Log4j2;
import sst.bank.main.BankBankConstants;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Log4j2
public abstract class FileReport  implements IReport {

    protected void writeToFile(String filePath, List<String[]> data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : data) {
                // Convertir chaque ligne en une chaîne délimitée par des tabulations
                String line = String.join("\t", row);
                writer.write(line);
                writer.newLine(); // Saut de ligne après chaque ligne de données
            }
        }
        log.info("File saved to {}", filePath);
    }
}
