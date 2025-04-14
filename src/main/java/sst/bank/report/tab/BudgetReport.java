package sst.bank.report.tab;

import sst.bank.model.repo.DataRepository;

import java.io.IOException;
import java.util.List;

public class BudgetReport extends FileReport {
    @Override
    public void report(String filePath) throws IOException {
        List<String[]> data = DataRepository.me().categories().stream().map(
                cat -> new String[]{
                        cat.getName(),
                        getString(cat.getBudget()[0]),
                        getString(cat.getBudget()[1]),
                        getString(cat.getBudget()[2]),
                        getString(cat.getBudget()[3]),
                        getString(cat.getBudget()[4]),
                        getString(cat.getBudget()[5]),
                        getString(cat.getBudget()[6]),
                        getString(cat.getBudget()[7]),
                        getString(cat.getBudget()[8]),
                        getString(cat.getBudget()[9]),
                        getString(cat.getBudget()[10]),
                        getString(cat.getBudget()[11]),
                        cat.getName()
                }
        ).toList();

        writeToFile(filePath, data);
    }

    private String getString(Double amount) {
        return String.valueOf(amount.intValue() * -1);
    }
}
