package sst.bank.categories;

import sst.bank.model.Operation;

public interface CategoryHook {

    void process(Operation operation);
}
