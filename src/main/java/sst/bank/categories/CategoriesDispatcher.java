package sst.bank.categories;

import com.google.common.base.Strings;
import sst.bank.model.AvailabilityPeriod;
import sst.bank.model.Category;
import sst.bank.model.Criteria;
import sst.bank.model.Operation;
import sst.bank.model.repo.DataRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class CategoriesDispatcher {
    public void dispatch(List<Operation> operations) {
        operations.stream().filter(operation -> operation.getCategory() == null)
                .forEach(this::findACategory);
    }

    private void findACategory(Operation operation) {
        final List<Category> categories = DataRepository.me().categories().stream().sorted(Comparator.comparing(Category::getPriority)).toList();
        for (Category category : categories) {
            if (match(category, operation)) {
                operation.setCategory(category);
                break;
            }
        }
    }

    private boolean match(Category category, Operation operation) {
        boolean result = false;
        for (Criteria criteria : category.getCriteria()) {
            switch (criteria.getField()) {
                case COUNTERPARTY_ACCOUNT -> result = counterpartyAccount(category, operation, criteria);
                case COUNTERPARTY_NAME -> result = counterpartyName(category, operation, criteria);
                case COMMUNICATION -> result = communication(category, operation, criteria);
                case DETAIL -> result = detail(category, operation, criteria);
                case TRANSACTION_TYPE -> result = criteria.getCriteria().equals(operation.getTransactionType());
                case VALUE_DATE -> result = checkValueDate(operation.getValueDate(), criteria.getPeriod());
                case ALL -> result = true;
            }
            if (result && operation.getCriteria() == null) {
                operation.setCriteria(criteria);
            }
            if (result && category.getHook() != null) {
                category.getHook().process(operation);
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    private static boolean detail(Category category, Operation operation, Criteria criteria) {
        boolean result;
        result = true;
        if (Boolean.TRUE.equals(category.getIncome())) {
            result = operation.getAmount().compareTo(0.00) > 0;
        }
        result = result && !Strings.isNullOrEmpty(operation.getDetails()) && operation.getDetails().contains(criteria.getCriteria());
        return result;
    }

    private static boolean communication(Category category, Operation operation, Criteria criteria) {
        boolean result;
        result = true;
        if (Boolean.TRUE.equals(category.getIncome())) {
            result = operation.getAmount().compareTo(0.00) > 0;
        }
        result = result && !Strings.isNullOrEmpty(operation.getCommunication()) && operation.getCommunication().contains(criteria.getCriteria());
        return result;
    }

    private static boolean counterpartyName(Category category, Operation operation, Criteria criteria) {
        boolean result;
        result = true;
        if (Boolean.TRUE.equals(category.getIncome())) {
            result = operation.getAmount().compareTo(0.00) > 0;
        }
        result = result && !Strings.isNullOrEmpty(operation.getCounterpartyName()) && operation.getCounterpartyName().contains(criteria.getCriteria());
        return result;
    }

    private static boolean counterpartyAccount(Category category, Operation operation, Criteria criteria) {
        boolean result;
        result = true;
        if (Boolean.TRUE.equals(category.getIncome())) {
            result = operation.getAmount().compareTo(0.00) > 0;
        }
        result = result && criteria.getCriteria().equals(operation.getCounterpartyAccountNumber());
        return result;
    }

    private static boolean checkValueDate(LocalDate valueDate, AvailabilityPeriod period) {
        final LocalDate startDate = period.getStartDate();
        final LocalDate endDate = period.getEndDate();

        final boolean afterStart = valueDate.isAfter(startDate);
        final boolean equalToStart = valueDate.isEqual(startDate);
        final boolean beforeEnd = valueDate.isBefore(endDate);
        final boolean equalToEnd = valueDate.isEqual(endDate);

        return (afterStart && beforeEnd) || equalToStart || equalToEnd;
    }
}
