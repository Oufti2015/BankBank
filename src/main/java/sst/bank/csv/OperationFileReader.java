package sst.bank.csv;

import com.google.common.base.Strings;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import sst.bank.model.Operation;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OperationFileReader {

    private final File inputFile;

    public OperationFileReader(String path) {
        this.inputFile = new File(path);
    }

    public List<Operation> read() throws IOException {
        List<Operation> result = new ArrayList<>();

        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(inputFile))
                .withCSVParser(csvParser)   // custom CSV parser
                .withSkipLines(1)           // skip the first line, header info
                .build()) {
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                if (!lineInArray[0].isEmpty()) {
                    result.add(csv2Operation(lineInArray));
                }
            }
        } catch (CsvValidationException e) {
            throw new IOException(String.format("Cannot parse <%s>", inputFile), e);
        }

        return result;
    }

    private Operation csv2Operation(String[] lineInArray) {
        int i = 0;
        Operation operation = new Operation();
        operation.setId(lineInArray[i++]);
        operation.setExecutionDate(string2Date(lineInArray[i++]));
        operation.setValueDate(string2Date(lineInArray[i++]));
        operation.setAmount(Double.parseDouble(lineInArray[i++].replace(",", ".")));
        operation.setCurrency(lineInArray[i++]);
        operation.setAccountNumber(lineInArray[i++]);
        operation.setTransactionType(lineInArray[i++]);
        operation.setCounterpartyAccountNumber(lineInArray[i++]);
        operation.setCounterpartyName(lineInArray[i++]);
        operation.setCommunication(lineInArray[i++]);
        operation.setDetails(lineInArray[i++]);
        operation.setStatus(lineInArray[i++]);
        operation.setReasonForRefusal(lineInArray[i]);
        return operation;
    }

    private LocalDate string2Date(String date) {
        LocalDate result = null;
        if (!Strings.isNullOrEmpty(date)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            result = LocalDate.parse(date, formatter);
        }
        return result;
    }
}
