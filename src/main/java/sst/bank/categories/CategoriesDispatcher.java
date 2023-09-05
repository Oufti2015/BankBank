package sst.bank.categories;

import com.google.common.base.Strings;
import sst.bank.model.Category;
import sst.bank.model.Criteria;
import sst.bank.model.CriteriaField;
import sst.bank.model.Operation;
import sst.bank.model.repo.DataRepository;

import java.util.List;

public class CategoriesDispatcher {
    public void dispatch(List<Operation> operations) {
        operations.stream().filter(operation -> operation.getCategory() == null)
                .forEach(operation -> findACategory(operation));
    }

    private void findACategory(Operation operation) {
        final List<Category> categories = DataRepository.me().categories().stream().sorted((o1, o2) -> o1.getPriority().compareTo(o2.getPriority())).toList();
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
                case COUNTERPARTY_ACCOUNT ->
                        result = criteria.getCriteria().equals(operation.getCounterpartyAccountNumber());
                case COUNTERPARTY_NAME ->
                        result = !Strings.isNullOrEmpty(operation.getCounterpartyName()) && operation.getCounterpartyName().contains(criteria.getCriteria());
                case COMMUNICATION ->
                        result = !Strings.isNullOrEmpty(operation.getCommunication()) && operation.getCommunication().contains(criteria.getCriteria());
                case DETAIL ->
                        result = !Strings.isNullOrEmpty(operation.getDetails()) && operation.getDetails().contains(criteria.getCriteria());
                case TRANSACTION_TYPE -> result = criteria.getCriteria().equals(operation.getTransactionType());
                case ALL -> result = true;
            }
            if (result && operation.getCriteria() == null) {
                operation.setCriteria(criteria);
            }
            if (result) {
                break;
            }
        }
        return result;
    }
}
