package sst.bank.report.tab;

import sst.bank.model.repo.DataRepository;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BudgetFamilialReport extends FileReport {
    @Override
    public void report(String filePath) throws IOException {
        String europeanDatePattern = "dd/MM/yyyy";
        DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);

        List<String[]> data = DataRepository.me().operations().stream().map(
                op -> new String[]{
                        europeanDateFormatter.format(op.getValueDate()),
                        op.getCategory().getName(),
                        op.getCategory().getName(),
                        "",
                        Boolean.TRUE.equals(op.getCategory().getIncome()) ? op.getAmount().toString() : Double.toString(op.getAmount() * -1.00),
                        op.getAmount().toString(),
                        Integer.toString(op.getValueDate().getYear()),
                        String.valueOf(op.getMonth().getValue()),
                        op.getMonth().toString()
                }
        ).toList();

        writeToFile(filePath, data);
    }
}
